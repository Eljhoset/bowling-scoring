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
    private List<PlayerScore> allZerosOnePlayerProcessed;

    @Before
    public void setup() throws Exception {

	final String filePathRegularGame = "src/test/resources/regular_game.txt";
	final String filePathRegularGameTwoPlayers = "src/test/resources/regular_game_two_players.txt";
	final String filePathAllStrike = "src/test/resources/all_strikes.txt";
	final String filePathAllFouls = "src/test/resources/all_fouls.txt";
	final String filePathAllZeross = "src/test/resources/all_zeros.txt";

	FileRollRepository repository = new DefaultFileRollRepository();

	List<String> regularGameOnePlayerLines = repository.load(filePathRegularGame);
	List<String> allStrikesGameOnePlayerLines = repository.load(filePathAllStrike);
	List<String> allFoulsOnePlayerLines = repository.load(filePathAllFouls);
	List<String> allZerosOnePlayerLines = repository.load(filePathAllZeross);
	List<String> regularGameTwoPlayerLines = repository.load(filePathRegularGameTwoPlayers);

	RollParser parser = DefaultRollParserFactory.instance().get(RollParserType.DEFAULT);

	List<PlayerFrames> regularGameOnePlayerParsed = parser.parse(regularGameOnePlayerLines);
	List<PlayerFrames> allStrikesGameOnePlayerParsed = parser.parse(allStrikesGameOnePlayerLines);
	List<PlayerFrames> allFoulsOnePlayerParsed = parser.parse(allFoulsOnePlayerLines);
	List<PlayerFrames> allZerosOnePlayerParsed = parser.parse(allZerosOnePlayerLines);
	List<PlayerFrames> regularGameTwoPlayerPasced = parser.parse(regularGameTwoPlayerLines);

	FrameScoreProcessor processor = new DefaultFrameScoreProcessor();

	this.regularGameOnePlayerProcessed = processor.processAll(regularGameOnePlayerParsed);
	this.allStrikesGameOnePlayerProcessed = processor.processAll(allStrikesGameOnePlayerParsed);
	this.allFoulsOnePlayerProcessed = processor.processAll(allFoulsOnePlayerParsed);
	this.allZerosOnePlayerProcessed = processor.processAll(allZerosOnePlayerParsed);
	this.regularGameTwoPlayerProcessed = processor.processAll(regularGameTwoPlayerPasced);
	this.formatter = new DefaultPlayerScoreFormatter();
    }

    @After
    public void tearDown() {
	this.regularGameOnePlayerProcessed = null;
	this.allStrikesGameOnePlayerProcessed = null;
	this.allFoulsOnePlayerProcessed = null;
	this.regularGameTwoPlayerProcessed = null;
	this.formatter = null;
	this.allZerosOnePlayerProcessed = null;
    }

    @Test(expected = NullPointerException.class)
    public void format_nullList_throwException() {
	formatter.format(null);
    }

    @Test
    public void format_validList_returnFormattedHeader() throws Exception {
	String headerFormat = new String(Files.readAllBytes(Paths.get("src/test/resources/formatter/header.txt")))
		.replaceAll("\\r", "");
	GameScore gameScore = formatter.format(regularGameOnePlayerProcessed);
	String header = gameScore.getHeader();
	assertTrue(header.equals(headerFormat));
    }

    @Test
    public void format_validList_returnFormattedScore() throws Exception {
	String scorePlayerOneFormat = new String(Files.readAllBytes(Paths.get("src/test/resources/formatter/player_two_score_regular.txt")))
		.replaceAll("\\r", "");
	String scorePlayerTwoFormat = new String(Files.readAllBytes(Paths.get("src/test/resources/formatter/player_one_score_regular.txt")))
		.replaceAll("\\r", "");
	GameScore gameScore = formatter.format(regularGameTwoPlayerProcessed);
	List<String> players = gameScore.getPlayers();
	String playerOneScore = players.get(0);
	String playerTwoScore = players.get(1);
	assertTrue(playerOneScore.equals(scorePlayerOneFormat));
	assertTrue(playerTwoScore.equals(scorePlayerTwoFormat));
    }

    @Test
    public void format_allStrikes_returnFormattedScore() throws Exception {
	String scorePlayerOneFormat = new String(Files.readAllBytes(Paths.get("src/test/resources/formatter/player_one_all_strikes.txt")))
		.replaceAll("\\r", "");
	GameScore gameScore = formatter.format(allStrikesGameOnePlayerProcessed);
	List<String> players = gameScore.getPlayers();
	String playerOneScore = players.get(0);
	assertTrue(playerOneScore.equals(scorePlayerOneFormat));
    }

    @Test
    public void format_allZeros_returnFormattedScore() throws Exception {
	String scorePlayerOneFormat = new String(Files.readAllBytes(Paths.get("src/test/resources/formatter/player_one_all_zeros.txt")))
		.replaceAll("\\r", "");
	GameScore gameScore = formatter.format(allZerosOnePlayerProcessed);
	List<String> players = gameScore.getPlayers();
	String playerOneScore = players.get(0);
	assertTrue(playerOneScore.equals(scorePlayerOneFormat));
    }

    @Test
    public void format_allFouls_returnFormattedScore() throws Exception {
	String scorePlayerOneFormat = new String(Files.readAllBytes(Paths.get("src/test/resources/formatter/player_one_all_fouls.txt")))
		.replaceAll("\\r", "");
	GameScore gameScore = formatter.format(allFoulsOnePlayerProcessed);
	List<String> players = gameScore.getPlayers();
	String playerOneScore = players.get(0);
	assertTrue(playerOneScore.equals(scorePlayerOneFormat));
    }
}
