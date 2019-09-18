package com.eljhoset.bowlingscoring.processor;

import com.eljhoset.bowlingscoring.parser.RollParser;
import com.eljhoset.bowlingscoring.parser.factory.DefaultRollParserFactory;
import com.eljhoset.bowlingscoring.parser.factory.RollParserType;
import com.eljhoset.bowlingscoring.parser.model.PlayerFrames;
import com.eljhoset.bowlingscoring.processor.model.FrameScore;
import com.eljhoset.bowlingscoring.processor.model.FrameScoreList;
import com.eljhoset.bowlingscoring.processor.model.PlayerScore;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.contains;
import org.junit.After;
import static org.junit.Assert.assertThat;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author jd-jd
 */
public class DefaultFrameScoreProcessorTest {

    private static final String FILE_PATH_REGULAR_GAME = "src/test/resources/regular_game.txt";
    private static final String FILE_PATH_REGULAR_GAME_TWO_PLAYERS = "src/test/resources/regular_game_two_players.txt";
    private static final String FILE_PATH_ALL_STRIKE = "src/test/resources/all_strikes.txt";
    private static final String FILE_PATH_ALL_FOULS = "src/test/resources/all_fouls.txt";
    private FrameScoreProcessor processor;
    private List<PlayerFrames> regularGameOnePlayer;
    private List<PlayerFrames> allStrikesGameOnePlayer;
    private List<PlayerFrames> allFoulsOnePlayer;
    private List<PlayerFrames> regularGameTwoPlayer;

    @Before
    public void setup() throws Exception {
        this.processor = new DefaultFrameScoreProcessor();
        List<String> regularGameOnePlayerLines = Files.readAllLines(Paths.get(FILE_PATH_REGULAR_GAME));
        List<String> allStrikesGameOnePlayerLines = Files.readAllLines(Paths.get(FILE_PATH_ALL_STRIKE));
        List<String> allFoulsOnePlayerLines = Files.readAllLines(Paths.get(FILE_PATH_ALL_FOULS));
        List<String> regularGameTwoPlayerLines = Files.readAllLines(Paths.get(FILE_PATH_REGULAR_GAME_TWO_PLAYERS));

        RollParser parser = DefaultRollParserFactory.instance().get(RollParserType.DEFAULT);

        this.regularGameOnePlayer = parser.parse(regularGameOnePlayerLines);
        this.allStrikesGameOnePlayer = parser.parse(allStrikesGameOnePlayerLines);
        this.allFoulsOnePlayer = parser.parse(allFoulsOnePlayerLines);
        this.regularGameTwoPlayer = parser.parse(regularGameTwoPlayerLines);
    }

    @After
    public void tearDown() {
        this.processor = null;
        this.regularGameOnePlayer = null;
        this.allStrikesGameOnePlayer = null;
        this.allFoulsOnePlayer = null;
        this.regularGameTwoPlayer = null;
    }

    @Test(expected = NullPointerException.class)
    public void process_nullPathParam_throwException() throws Exception {
        this.processor.process(null);
    }

    @Test
    public void process_onePlayerRegularGame_returnScore() {
        PlayerScore playerScore = this.processor.process(regularGameOnePlayer.get(0));
        assertThat(playerScore.getScore(), is(170));
    }

    @Test
    public void process_onePlayerRegularGame_returnFrameScore() {
        PlayerScore playerScore = this.processor.process(regularGameOnePlayer.get(0));
        List<Integer> frames = playerScore.getFrames()
                .getFrames()
                .stream()
                .map(FrameScore::getScore).collect(Collectors.toList());
        assertThat(frames, contains(17, 30, 37, 57, 77, 105, 123, 131, 151, 170));
    }

    @Test
    public void process_onePlayerAllStrikes_returnScore() {
        PlayerScore playerScore = this.processor.process(allStrikesGameOnePlayer.get(0));
        assertThat(playerScore.getScore(), is(300));
    }

    @Test
    public void process_onePlayerAllStrikes_returnFrameScore() {
        PlayerScore playerScore = this.processor.process(allStrikesGameOnePlayer.get(0));
        List<Integer> frames = playerScore.getFrames()
                .getFrames()
                .stream()
                .map(FrameScore::getScore).collect(Collectors.toList());
        assertThat(frames, contains(30, 60, 90, 120, 150, 180, 210, 240, 270, 300));
    }

    @Test
    public void process_onePlayerFouls_returnScore() {
        PlayerScore playerScore = this.processor.process(allFoulsOnePlayer.get(0));
        assertThat(playerScore.getScore(), is(0));
    }

    @Test
    public void process_twoPlayerRegularGame_returnScore() {
        List<Integer> integers = this.processor
                .processAll(regularGameTwoPlayer).stream()
                .map(PlayerScore::getScore)
                .collect(Collectors.toList());
        assertThat(integers, contains(167, 151));
    }

    @Test
    public void process_twoPlayerRegularGame_returnFrameScore() {

        List<List<FrameScore>> collect = this.processor
                .processAll(regularGameTwoPlayer).stream()
                .map(PlayerScore::getFrames)
                .map(FrameScoreList::getFrames)
                .collect(Collectors.toList());
        List<Integer> framesPlayerOne = collect.get(0).stream()
                .map(FrameScore::getScore)
                .collect(Collectors.toList());
        List<Integer> framesPlayerTwo = collect.get(1).stream()
                .map(FrameScore::getScore)
                .collect(Collectors.toList());

        assertThat(framesPlayerTwo, contains(16, 25, 44, 53, 82, 101, 110, 124, 132, 151));
        assertThat(framesPlayerOne, contains(20, 39, 48, 66, 74, 84, 90, 120, 148, 167));
    }
}
