package com.eljhoset.bowlingscoring.parser.factory;

import com.eljhoset.bowlingscoring.parser.RollParser;

/**
 *
 * @author jd-jd
 */
public interface RollParserFactory {

    RollParser get(RollParserType parserType);
}
