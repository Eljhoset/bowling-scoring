package com.eljhoset.bowlingscoring.parser.model;

import java.util.Objects;

public class FrameImpl implements Frame {

    private final FrameRolls rolls;
    private final Integer number;
    private final boolean last;

    public FrameImpl(FrameRolls rolls, Integer number, boolean last) {
        Objects.requireNonNull(rolls, "rolls must not be null");
        Objects.requireNonNull(number, "number must not be null");
        this.rolls = rolls;
        this.number = number;
        this.last = last;
    }

    @Override
    public FrameRolls rolls() {
        return rolls;
    }

    @Override
    public boolean isSpare() {
        Integer pinsKnocked = rolls.getSecondRoll()
                .map(Roll::getValue).orElse(0) + rolls.getFirstRoll().getValue();
        return !isStrike() && pinsKnocked == 10;
    }

    @Override
    public boolean isStrike() {
        Roll firstRoll = rolls.getFirstRoll();
        return 10 - firstRoll.getValue() == 0;
    }

    @Override
    public Integer getNumber() {
        return this.number;
    }

    @Override
    public boolean isLast() {
        return last;
    }

}
