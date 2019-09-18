package com.eljhoset.bowlingscoring.processor.model;

import com.eljhoset.bowlingscoring.parser.model.Roll;
import java.util.List;

/**
 *
 * @author jd-jd
 */
public interface FrameScore {

    List<Roll> getRolls();

    int getScore();

}
