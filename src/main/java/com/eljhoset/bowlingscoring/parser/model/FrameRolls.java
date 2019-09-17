package com.eljhoset.bowlingscoring.parser.model;

import java.util.List;
import java.util.Optional;

/**
 *
 * @author jd-jd
 */
public interface FrameRolls {

    List<Roll> getRolls();

    Roll getFirstRoll();

    Optional<Roll> getSecondRoll();

    Optional<Roll> getThirdRoll();

}
