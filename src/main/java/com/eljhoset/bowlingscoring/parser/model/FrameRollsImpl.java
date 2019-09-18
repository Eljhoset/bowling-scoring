package com.eljhoset.bowlingscoring.parser.model;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class FrameRollsImpl implements FrameRolls {

    private final List<Roll> rolls;

    public FrameRollsImpl(List<Roll> rolls) {
        Objects.requireNonNull(rolls, "rolls must not be null");
        if (rolls.isEmpty()) {
            throw new IllegalArgumentException("There must be at least one roll");
        }
        if (rolls.size() > 3) {
            throw new IllegalArgumentException(String.format("A frame cannot have more than three rolls, you have[%d]", rolls.size()));
        }
        Roll firstRol = rolls.get(0);
        if (firstRol.getValue() != 10 && rolls.size() < 2) {
            throw new IllegalArgumentException("Second roll is required when first roll is not a strike");
        }
        this.rolls = rolls;
    }

    @Override
    public Roll getFirstRoll() {
        return rolls.get(0);
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
    public Integer getRollsNumber() {
        return rolls.size();
    }

    @Override
    public List<Roll> getRolls() {
        return Collections.unmodifiableList(rolls);
    }

}
