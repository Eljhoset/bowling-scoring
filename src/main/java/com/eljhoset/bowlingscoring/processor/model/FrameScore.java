package com.eljhoset.bowlingscoring.processor.model;

import com.eljhoset.bowlingscoring.parser.model.Roll;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author jd-jd
 */
public interface FrameScore {

    List<Roll> getRolls();

    int getScore();

    boolean isSpare();

    boolean isStrike();

    default boolean isLast() {
        return false;
    }

    Roll getFirstRoll();

    default Optional<Roll> getSecondRoll() {
        return Optional.empty();
    }

    default Optional<Roll> getThirdRoll() {
        return Optional.empty();
    }

}
