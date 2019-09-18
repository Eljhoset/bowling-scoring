package com.eljhoset.bowlingscoring.processor.model;

import com.eljhoset.bowlingscoring.parser.model.Roll;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class FrameScoreImpl implements FrameScore {

    private final List<Roll> rolls;
    private final Integer score;
    private final boolean spare;
    private final boolean strike;
    private final boolean last;

    public FrameScoreImpl(List<Roll> rolls, Integer score, boolean spare, boolean strike, boolean last) {
        this.rolls = rolls;
        this.score = score;
        this.spare = spare;
        this.strike = strike;
        this.last = last;
    }

    @Override
    public List<Roll> getRolls() {
        return Collections.unmodifiableList(rolls);
    }

    @Override
    public int getScore() {
        return score;
    }

    @Override
    public boolean isSpare() {
        return spare;
    }

    @Override
    public boolean isStrike() {
        return strike;
    }

    @Override
    public boolean isLast() {
        return last;
    }

    @Override
    public Optional<Roll> getSecondRoll() {
        return getElementByIndex(1);
    }

    @Override
    public Optional<Roll> getThirdRoll() {
        return getElementByIndex(2);
    }

    private Optional<Roll> getElementByIndex(int index) {
        if (index < rolls.size()) {
            return Optional.of(rolls.get(index));
        }
        return Optional.empty();
    }

    @Override
    public Roll getFirstRoll() {
        return rolls.get(0);
    }

}
