package com.eljhoset.bowlingscoring.processor.model;

import com.eljhoset.bowlingscoring.parser.model.PlayerFrames;

/**
 *
 * @author jd-jd
 */
public interface FrameScore {

    PlayerFrames getFrame();

    int getScore();
}
