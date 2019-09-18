package com.eljhoset.bowlingscoring.processor;

import com.eljhoset.bowlingscoring.parser.DefaultRollParser;
import com.eljhoset.bowlingscoring.parser.mapper.DefaultPlayerFramelMapper;
import com.eljhoset.bowlingscoring.parser.model.PlayerFrames;
import com.eljhoset.bowlingscoring.parser.validator.DefaultPlayerFrameValidator;
import com.eljhoset.bowlingscoring.parser.validator.DefaultRollLineValidator;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import org.junit.After;
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
        List<String> allStrikesGameOnePlayerLines = Files.readAllLines(Paths.get(FILE_PATH_REGULAR_GAME_TWO_PLAYERS));
        List<String> allFoulsOnePlayerLines = Files.readAllLines(Paths.get(FILE_PATH_ALL_FOULS));
        List<String> regularGameTwoPlayerLines = Files.readAllLines(Paths.get(FILE_PATH_ALL_STRIKE));

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
}
