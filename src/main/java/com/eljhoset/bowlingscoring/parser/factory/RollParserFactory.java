package com.eljhoset.bowlingscoring.parser.factory;

import com.eljhoset.bowlingscoring.parser.RollParser;

/**
 * Provides a instance of a RollParser
 * {@link com.eljhoset.bowlingscoring.parser.RollParser}
 *
 * @author jd-jd
 */
public interface RollParserFactory {

    RollParser get(RollParserType parserType);
}
