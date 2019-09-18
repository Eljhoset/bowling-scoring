package com.eljhoset.bowlingscoring.formatter;

import com.eljhoset.bowlingscoring.data.repository.DefaultFileRollRepository;
import com.eljhoset.bowlingscoring.data.repository.FileRollRepository;
import com.eljhoset.bowlingscoring.formatter.model.GameScore;
import com.eljhoset.bowlingscoring.parser.RollParser;
import com.eljhoset.bowlingscoring.parser.factory.DefaultRollParserFactory;
import com.eljhoset.bowlingscoring.parser.factory.RollParserType;
import com.eljhoset.bowlingscoring.parser.model.PlayerFrames;
import com.eljhoset.bowlingscoring.processor.DefaultFrameScoreProcessor;
import com.eljhoset.bowlingscoring.processor.FrameScoreProcessor;
import com.eljhoset.bowlingscoring.processor.model.PlayerScore;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import org.junit.After;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author jd-jd
 */
public class DefaultPlayerScoreFormatterIT {

    private List<PlayerScore> regularGameOnePlayerProcessed;
    private List<PlayerScore> allStrikesGameOnePlayerProcessed;
    private List<PlayerScore> allFoulsOnePlayerProcessed;
    private List<PlayerScore> regularGameTwoPlayerProcessed;
    private PlayerScoreFormatter formatter;

    @Before
    public void setup() throws Exception {

        final String filePathRegularGame = "src/test/resources/regular_game.txt";
        final String filePathRegularGameTwoPlayers = "src/test/resources/regular_game_two_players.txt";
        final String filePathAllStrike = "src/test/resources/all_strikes.txt";
        final String filePathAllFouls = "src/test/resources/all_fouls.txt";

        FileRollRepository repository = new DefaultFileRollRepository();

        List<String> regularGameOnePlayerLines = repository.load(filePathRegularGame);
        List<String> allStrikesGameOnePlayerLines = repository.load(filePathAllStrike);
        List<String> allFoulsOnePlayerLines = repository.load(filePathAllFouls);
        List<String> regularGameTwoPlayerLines = repository.load(filePathRegularGameTwoPlayers);

        RollParser parser = DefaultRollParserFactory.instance().get(RollParserType.DEFAULT);

        List<PlayerFrames> regularGameOnePlayerParced = parser.parse(regularGameOnePlayerLines);
        List<PlayerFrames> allStrikesGameOnePlayerParced = parser.parse(allStrikesGameOnePlayerLines);
        List<PlayerFrames> allFoulsOnePlayerParced = parser.parse(allFoulsOnePlayerLines);
        List<PlayerFrames> regularGameTwoPlayerParced = parser.parse(regularGameTwoPlayerLines);

        FrameScoreProcessor processor = new DefaultFrameScoreProcessor();

        this.regularGameOnePlayerProcessed = processor.processAll(regularGameOnePlayerParced);
        this.allStrikesGameOnePlayerProcessed = processor.processAll(allStrikesGameOnePlayerParced);
        this.allFoulsOnePlayerProcessed = processor.processAll(allFoulsOnePlayerParced);
        this.regularGameTwoPlayerProcessed = processor.processAll(regularGameTwoPlayerParced);
        this.formatter = new DefaultPlayerScoreFormatter();
    }

    @After
    public void tearDown() {
        this.regularGameOnePlayerProcessed = null;
        this.allStrikesGameOnePlayerProcessed = null;
        this.allFoulsOnePlayerProcessed = null;
        this.regularGameTwoPlayerProcessed = null;
        this.formatter = null;
    }

    @Test(expected = NullPointerException.class)
    public void format_nullList_throwException() {
        formatter.format(null);
    }

    @Test
    public void format_validList_returnFormattedHeader() throws Exception {
        String headerFormat = new String(Files.readAllBytes(Paths.get("src/test/resources/formatter/header.txt")));
        GameScore gameScore = formatter.format(regularGameOnePlayerProcessed);
        String header = gameScore.getHeader();
        assertTrue(header.equals(headerFormat));
    }
    @Test
    public void format_validList_returnFormattedScore() throws Exception {
        String headerFormat = new String(Files.readAllBytes(Paths.get("src/test/resources/formatter/header.txt")));
        GameScore gameScore = formatter.format(regularGameTwoPlayerProcessed);
        String header = gameScore.getHeader();
        assertTrue(header.equals(headerFormat));
    }

}