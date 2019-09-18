package com.eljhoset.bowlingscoring.processor.model;

import com.eljhoset.bowlingscoring.parser.model.Roll;
import java.util.Collections;
import java.util.List;

public class FrameScoreImpl implements FrameScore {

    private final List<Roll> rolls;
    private final Integer score;

    public FrameScoreImpl(List<Roll> rolls, Integer score) {
        this.rolls = rolls;
        this.score = score;
    }

    @Override
    public List<Roll> getRolls() {
        return Collections.unmodifiableList(rolls);
    }

    @Override
    public int getScore() {
        return score;
    }

}
