package com.eljhoset.bowlingscoring.processor;

import com.eljhoset.bowlingscoring.parser.model.Frame;
import com.eljhoset.bowlingscoring.parser.model.FrameList;
import com.eljhoset.bowlingscoring.parser.model.FrameRolls;
import com.eljhoset.bowlingscoring.parser.model.PlayerFrames;
import com.eljhoset.bowlingscoring.parser.model.Roll;
import com.eljhoset.bowlingscoring.processor.model.FrameScore;
import com.eljhoset.bowlingscoring.processor.model.FrameScoreImpl;
import com.eljhoset.bowlingscoring.processor.model.FrameScoreList;
import com.eljhoset.bowlingscoring.processor.model.FrameScoreListImpl;
import com.eljhoset.bowlingscoring.processor.model.PlayerScore;
import com.eljhoset.bowlingscoring.processor.model.PlayerScoreImpl;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Function;
import java.util.stream.Collectors;

public class DefaultFrameScoreProcessor implements FrameScoreProcessor {

    @Override
    public PlayerScore process(PlayerFrames playerFrames) {
        Objects.requireNonNull(playerFrames, "frames must not be null");

        FrameList frameList = playerFrames.getFrames();
        List<Frame> frames = frameList.getFrames();

        List<FrameScore> frameScores = frames.stream()
                .map((e) -> {
                    int currentIndex = frames.indexOf(e);
                    int indexToFindNextFrame = currentIndex + 1;
                    if (indexToFindNextFrame < frames.size()) {
                        return mapFrameScore(e, Optional.of(frames.get(indexToFindNextFrame)));
                    }
                    return mapFrameScore(e, Optional.empty());
                })
                .collect(Collectors.toList());

        FrameScoreList frameScoreList = new FrameScoreListImpl(frameScores);
        PlayerScore playerScore = new PlayerScoreImpl(playerFrames.getPlayer(), frameScoreList);
        return playerScore;
    }

    private FrameScore mapFrameScore(Frame frame, Optional<Frame> nextFrame) {
        FrameRolls frameRolls = frame.rolls();

        Function<FrameRolls, Integer> sumPins = (rolls) -> {
            return rolls.getRolls().stream().mapToInt(Roll::getValue).sum();
        };
        final AtomicInteger score = new AtomicInteger(sumPins.apply(frameRolls));
        if (frame.isStrike()) {
            nextFrame.map(Frame::rolls)
                    .map(FrameRolls::getRolls)
                    .ifPresent((f) -> {
                        score.addAndGet(f.stream().mapToInt(Roll::getValue).sum());
                    });
        }
        if (frame.isSpare()) {
            score.addAndGet(nextFrame.map(Frame::rolls)
                    .map(FrameRolls::getFirstRoll)
                    .map(Roll::getValue).orElse(0));

        }
        return new FrameScoreImpl(frameRolls.getRolls(), score.get());
    }

}
