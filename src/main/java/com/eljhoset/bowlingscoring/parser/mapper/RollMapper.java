package com.eljhoset.bowlingscoring.parser.mapper;

import com.eljhoset.bowlingscoring.parser.model.PlayerFrames;

/**
 *
 * @author jd-jd
 */
public interface RollMapper {

    PlayerFrames map(String line);
}
