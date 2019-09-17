package com.eljhoset.bowlingscoring.parser.model;

import java.util.List;
import java.util.Optional;

/**
 *
 * @author jd-jd
 */
public interface Frames {

    List<Frames> getFrames();

    Optional<Frame> getFrame(int index);

    int getScore();

}
