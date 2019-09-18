package com.eljhoset.bowlingscoring.processor;

import com.eljhoset.bowlingscoring.parser.model.PlayerFrames;
import com.eljhoset.bowlingscoring.processor.model.PlayerScore;

/**
 *
 * @author jd-jd
 */
public interface FrameScoreProcessor {

    PlayerScore process(PlayerFrames frames);
}
