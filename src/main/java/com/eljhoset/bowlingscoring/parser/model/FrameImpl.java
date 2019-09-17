package com.eljhoset.bowlingscoring.parser.model;

import java.util.Objects;

public class FrameImpl implements Frame {

    private final FrameRolls rolls;
    private final Integer number;

    public FrameImpl(FrameRolls rolls, Integer number) {
        Objects.requireNonNull(rolls, "rolls must not be null");
        Objects.requireNonNull(number, "number must not be null");
        this.rolls = rolls;
        this.number = number;
    }

    @Override
    public FrameRolls rolls() {
        return rolls;
    }

    @Override
    public boolean isSpare() {
        Roll firstRoll = rolls.getFirstRoll();
        return 10 - firstRoll.getValue() > 0;
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

}
