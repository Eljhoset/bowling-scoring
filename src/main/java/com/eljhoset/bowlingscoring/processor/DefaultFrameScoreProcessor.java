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
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.Objects;
import java.util.function.ToIntFunction;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class DefaultFrameScoreProcessor implements FrameScoreProcessor {

    @Override
    public PlayerScore process(PlayerFrames playerFrames) {
        Objects.requireNonNull(playerFrames, "frames must not be null");

        FrameList frameList = playerFrames.getFrames();
        List<Frame> frames = frameList.getFrames();

        List<FrameScore> frameScores = new ArrayList<>(10);

        ListIterator<Frame> listIterator = frames.listIterator();
        int score = 0;
        while (listIterator.hasNext()) {
            int nextIndex = listIterator.nextIndex();
            final Frame frame = listIterator.next();
            final FrameRolls frameRolls = frame.rolls();
            
            //get nex two rolls, this is important if the frame is strike or spare
            Iterable<Frame> remainingFrameIterable = () -> frames.listIterator(nextIndex);
            List<Roll> nextTwoRolls = StreamSupport.stream(remainingFrameIterable.spliterator(), false)
                    .map(Frame::rolls)
                    .map(FrameRolls::getRolls)
                    .flatMap(List::stream)
                    .skip(frameRolls.getRollsNumber())
                    .limit(2)
                    .collect(Collectors.toList());

            final FrameScore frameScore = mapFrameScore(score, frame, nextTwoRolls);
            score = frameScore.getScore();

            frameScores.add(frameScore);

        }

        FrameScoreList frameScoreList = new FrameScoreListImpl(frameScores);
        return new PlayerScoreImpl(playerFrames.getPlayer(), frameScoreList, score);
    }

    private FrameScore mapFrameScore(int previous, Frame frame, List<Roll> nextTwoRolls) {
        FrameRolls frameRolls = frame.rolls();

        ToIntFunction<FrameRolls> sumPins = rolls -> rolls.getRolls().stream().mapToInt(Roll::getValue).sum();

        int score = sumPins.applyAsInt(frameRolls);
        score += previous;

        int rollBonusCount = 0;
        if (frame.isStrike()) {
            rollBonusCount = 2;
        }
        if (frame.isSpare()) {
            rollBonusCount = 1;
        }

        score += nextTwoRolls.stream()
                .limit(rollBonusCount)
                .mapToInt(Roll::getValue)
                .sum();

        return new FrameScoreImpl(frame.getNumber(), frameRolls, score, frame.isSpare(), frame.isStrike(), frame.isLast());
    }

}
