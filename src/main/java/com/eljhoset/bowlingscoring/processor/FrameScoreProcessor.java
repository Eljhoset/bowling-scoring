package com.eljhoset.bowlingscoring.processor;

import com.eljhoset.bowlingscoring.parser.model.PlayerFrames;
import com.eljhoset.bowlingscoring.processor.model.FrameScore;

/**
 *
 * @author jd-jd
 */
public interface FrameScoreProcessor {

    FrameScore process(PlayerFrames frames);
}
