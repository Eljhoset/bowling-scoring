package com.eljhoset.bowlingscoring.parser;

import com.eljhoset.bowlingscoring.parser.model.PlayerFrames;
import java.util.List;

/**
 * Convert raw string data into domain model
 *
 * @author jd-jd
 */
public interface RollParser {

    List<PlayerFrames> parse(List<String> rolls);

}
