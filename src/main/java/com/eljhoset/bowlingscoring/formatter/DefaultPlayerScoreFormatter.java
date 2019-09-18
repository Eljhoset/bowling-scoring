package com.eljhoset.bowlingscoring.formatter;

import com.eljhoset.bowlingscoring.formatter.model.GameScore;
import com.eljhoset.bowlingscoring.formatter.model.GameScoreImpl;
import com.eljhoset.bowlingscoring.parser.model.FrameRolls;
import com.eljhoset.bowlingscoring.parser.model.Player;
import com.eljhoset.bowlingscoring.parser.model.Roll;
import com.eljhoset.bowlingscoring.processor.model.FrameScore;
import com.eljhoset.bowlingscoring.processor.model.FrameScoreList;
import com.eljhoset.bowlingscoring.processor.model.PlayerScore;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class DefaultPlayerScoreFormatter implements PlayerScoreFormatter {

    @Override
    public GameScore format(List<PlayerScore> playerScores) {
	Objects.requireNonNull(playerScores, "player scores must not be null");
	String header = createHeader();
	List<String> scores = playerScores.stream().map(this::createScore)
		.collect(Collectors.toList());
	return new GameScoreImpl(header, scores);
    }

    private String createHeader() {
	StringBuilder sb = new StringBuilder("Frame\t\t");
	String frames = IntStream.rangeClosed(1, 10)
		.mapToObj(Integer::toString)
		.collect(Collectors.joining("\t\t"));
	sb.append(frames)
		.append("\n");
	return sb.toString();
    }

    private String createScore(PlayerScore playerScore) {
	final Player player = playerScore.getPlayer();
	final FrameScoreList frameScoreList = playerScore.getFrames();
	List<FrameScore> frames = frameScoreList.getFrames();
	StringBuilder sb = new StringBuilder(player.getName());
	sb.append("\n");
	sb.append("Pinfalls\t");
	String rolls = frames.stream().map(this::mapFrameRolls).collect(Collectors.joining("\t"));
	String score = frames.stream().map(FrameScore::getScore).map(Object::toString).collect(Collectors.joining("\t\t"));
	sb.append(rolls)
		.append("\n")
		.append(String.format("Score\t\t%s", score))
		.append("\n");

	return sb.toString();

    }

    private String mapFrameRolls(FrameScore frameScore) {
	FrameRolls frameRolls = frameScore.rolls();

	if (frameScore.isStrike() && !frameScore.isLast()) {
	    return "\tX";
	}
	if (frameScore.isSpare()) {
	    return String.format("%s\t/", frameRolls.getFirstRoll().getPins());
	}
	if (frameScore.isLast()) {
	    return mapLastFrame(frameScore);
	}

	String secondRoll = frameRolls.getSecondRoll().map(Roll::getPins).orElse("");
	return String.format("%s\t%s", frameRolls.getFirstRoll().getPins(), secondRoll);
    }

    private String mapLastFrame(FrameScore frameScore) {
	FrameRolls frameRolls = frameScore.rolls();
	final StringBuilder sb = new StringBuilder(1);
	final Roll firstRoll = frameRolls.getFirstRoll();
	final Optional<Roll> secondRoll = frameRolls.getSecondRoll();
	final Optional<Roll> thirdRoll = frameRolls.getThirdRoll();

	Function<Roll, String> mapFirstRoll = roll -> {
	    if (frameScore.isStrike()) {
		return "X";
	    }
	    return roll.getPins();
	};
	Function<Optional<Roll>, String> mapScondRoll = roll -> {
	    String secondRollPins = roll.map(Roll::getPins).orElse("");
	    if (frameScore.isSpare()) {
		return "\t/";
	    }
	    if (secondRollPins.equals("10")) {
		return "\tX";
	    }
	    if (secondRollPins.isEmpty()) {
		return "";
	    }
	    return String.format("\t%s", secondRollPins);
	};

	Function<Optional<Roll>, String> mapThirdRoll = roll -> {
	    String thirdRollPins = roll.map(Roll::getPins).orElse("");
	    if (thirdRollPins.equals("10")) {
		return "\tX";
	    }
	    if (thirdRollPins.isEmpty()) {
		return "";
	    }
	    return String.format("\t%s", thirdRollPins);
	};

	return sb.append(mapFirstRoll.apply(firstRoll))
		.append(mapScondRoll.apply(secondRoll))
		.append(mapThirdRoll.apply(thirdRoll))
		.toString();
    }
}
