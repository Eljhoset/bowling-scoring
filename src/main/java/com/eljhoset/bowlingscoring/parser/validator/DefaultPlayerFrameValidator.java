package com.eljhoset.bowlingscoring.parser.validator;

import com.eljhoset.bowlingscoring.parser.model.Frame;
import com.eljhoset.bowlingscoring.parser.model.FrameList;
import com.eljhoset.bowlingscoring.parser.model.PlayerFrames;
import com.eljhoset.bowlingscoring.parser.model.Roll;
import java.util.List;
import java.util.Objects;

public class DefaultPlayerFrameValidator implements PlayerFrameValidator {

    @Override
    public PlayerFrames validate(PlayerFrames playerFrames) {
        Objects.requireNonNull(playerFrames, "playerFrames must not be null");
        FrameList frames = playerFrames.getFrames();
        List<Frame> frameList = frames.getFrames();
        frameList.stream().forEach(this::validateFrame);
        return playerFrames;
    }

    public Frame validateFrame(Frame frame) {
        final List<Roll> rolls = frame.rolls().getRolls();
        int sum = rolls.stream().mapToInt(Roll::getValue).sum();
        if (sum > 10 && !frame.isLast()) {
            throw new IllegalArgumentException(String.format("Invalid number of knocked pins[%d] on frame[%d]", sum, frame.getNumber()));
        }
        if (frame.rolls().getRollsNumber() == 3 && !frame.isLast() && !frame.isStrike()) {
            throw new IllegalArgumentException(String.format("Invalid number of rolls[%d] on frame[%d]", frame.rolls().getRollsNumber(), frame.getNumber()));
        }
        if (frame.rolls().getRollsNumber() > 3) {
            throw new IllegalArgumentException(String.format("Invalid number of rolls[%d] on frame[%d]", frame.rolls().getRollsNumber(), frame.getNumber()));
        }
        return frame;
    }

}
