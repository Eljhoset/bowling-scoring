package com.eljhoset.bowlingscoring.parser.validator;

import com.eljhoset.bowlingscoring.parser.model.PlayerFrames;

/**
 * Provides a way to ensure the PlayerFrames
 * {@link com.eljhoset.bowlingscoring.parser.model.PlayerFrames} complain all
 * the business logic
 *
 * @author jd-jd
 */
public interface PlayerFrameValidator {

    PlayerFrames validate(PlayerFrames frames);
}
