package com.eljhoset.bowlingscoring.parser.model;

import java.util.Objects;
import java.util.Optional;

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
        Roll firstRoll = rolls.getFirstRoll();
        Optional<Roll> secondRoll = rolls.getSecondRoll();
        return 10 - firstRoll.getValue() > 0
                && firstRoll.getValue() + secondRoll.get().getValue() == 10;
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
