package com.eljhoset.bowlingscoring.parser.validator;

import com.eljhoset.bowlingscoring.parser.model.Frame;
import com.eljhoset.bowlingscoring.parser.model.Frames;
import com.eljhoset.bowlingscoring.parser.model.PlayerFrames;
import com.eljhoset.bowlingscoring.parser.model.Roll;
import java.util.List;
import java.util.Objects;

public class DefaultPlayerFrameValidator implements PlayerFrameValidator {

    @Override
    public PlayerFrames validate(PlayerFrames playerFrames) {
        Objects.requireNonNull(playerFrames, "playerFrames must not be null");
        Frames frames = playerFrames.getFrames();
        List<Frame> frameList = frames.getFrames();
        frameList.stream().forEach(this::validateFrame);
        return playerFrames;
    }

    public Frame validateFrame(Frame frame) {
        final List<Roll> rolls = frame.rolls().getRolls();
        int sum = rolls.stream().mapToInt(Roll::getValue).sum();
        boolean lastFrame = frame.getNumber() == 10;
        if (sum > 10 && !lastFrame) {
            throw new IllegalArgumentException(String.format("Invalid number of knocked pins[%d] on frame[%d], must no be empty ", sum, frame.getNumber()));
        }
        if (frame.rolls().getRollsNumber() > 2 && !lastFrame && !frame.isStrike()) {
            throw new IllegalArgumentException(String.format("Invalid number of rolls on frame[%d], must no be empty ", frame.getNumber()));
        }
        return frame;
    }

}
