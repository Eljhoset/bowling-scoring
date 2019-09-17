package com.eljhoset.bowlingscoring.parser;

import com.eljhoset.bowlingscoring.parser.model.PlayerFrames;
import java.util.List;

/**
 *
 * @author jd-jd
 */
public interface RollParser {

    List<PlayerFrames> parse(List<String> rolls);

}
