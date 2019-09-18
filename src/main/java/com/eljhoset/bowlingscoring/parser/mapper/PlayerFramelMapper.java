package com.eljhoset.bowlingscoring.parser.mapper;

import com.eljhoset.bowlingscoring.parser.model.PlayerFrames;
import java.util.List;

/**
 * Transform raw lines into PlayerFrames
 * {@link com.eljhoset.bowlingscoring.parser.model.PlayerFrames}
 *
 * @author jd-jd
 */
public interface PlayerFramelMapper {

    List<PlayerFrames> map(List<String> line);
}
