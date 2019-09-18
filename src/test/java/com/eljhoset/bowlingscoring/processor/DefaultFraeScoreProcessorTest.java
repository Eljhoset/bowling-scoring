package com.eljhoset.bowlingscoring.processor;

import com.eljhoset.bowlingscoring.parser.DefaultRollParser;
import com.eljhoset.bowlingscoring.parser.mapper.DefaultPlayerFramelMapper;
import com.eljhoset.bowlingscoring.parser.model.PlayerFrames;
import com.eljhoset.bowlingscoring.parser.validator.DefaultPlayerFrameValidator;
import com.eljhoset.bowlingscoring.parser.validator.DefaultRollLineValidator;
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
public class DefaultFraeScoreProcessorTest {

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

        DefaultRollParser parser = new DefaultRollParser();
        parser.setPlayerFrameValidator(new DefaultPlayerFrameValidator());
        parser.setRollMapper(new DefaultPlayerFramelMapper());
        parser.setRollValidator(new DefaultRollLineValidator());

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
    public void process_onePlayerAllStrikes_returnScore() {
        PlayerScore playerScore = this.processor.process(allStrikesGameOnePlayer.get(0));
        assertThat(playerScore.getScore(), is(300));
    }
    @Test
    public void process_onePlayerFouls_returnScore() {
        PlayerScore playerScore = this.processor.process(allFoulsOnePlayer.get(0));
        assertThat(playerScore.getScore(), is(0));
    }

    @Test
    public void process_twoPlayerRegularGame_returnScore() {
        List<Integer> integers = regularGameTwoPlayer.stream()
                .map(processor::process)
                .map(PlayerScore::getScore)
                .collect(Collectors.toList());
        assertThat(integers, contains(151, 167));
    }
}
