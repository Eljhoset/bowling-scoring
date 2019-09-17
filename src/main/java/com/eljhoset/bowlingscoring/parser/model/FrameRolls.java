package com.eljhoset.bowlingscoring.parser.model;

import java.util.List;
import java.util.Optional;

/**
 *
 * @author jd-jd
 */
public interface FrameRolls {

    List<Roll> getRolls();

    Integer getRollsNumber();

    Roll getFirstRoll();

    default Optional<Roll> getSecondRoll() {
        return Optional.empty();
    }

    default Optional<Roll> getThirdRoll() {
        return Optional.empty();
    }

}
