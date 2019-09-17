package com.eljhoset.bowlingscoring.parser.mapper;

import com.eljhoset.bowlingscoring.parser.model.PlayerFrames;
import java.util.List;

/**
 *
 * @author jd-jd
 */
public interface PlayerFramelMapper {

    List<PlayerFrames> map(List<String> line);
}
