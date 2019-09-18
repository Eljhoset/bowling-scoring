package com.eljhoset.bowlingscoring.processor.model;

import com.eljhoset.bowlingscoring.parser.model.Frame;

/**
 *  Provide a representation of the score archived y a frame
 * @author jd-jd
 */
public interface FrameScore extends Frame {

    int getScore();

}
