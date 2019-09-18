package com.eljhoset.bowlingscoring.parser.factory;

import com.eljhoset.bowlingscoring.parser.DefaultRollParser;
import com.eljhoset.bowlingscoring.parser.RollParser;
import com.eljhoset.bowlingscoring.parser.mapper.DefaultPlayerFramelMapper;
import com.eljhoset.bowlingscoring.parser.mapper.PlayerFramelMapper;
import com.eljhoset.bowlingscoring.parser.validator.DefaultPlayerFrameValidator;
import com.eljhoset.bowlingscoring.parser.validator.DefaultRollLineValidator;
import com.eljhoset.bowlingscoring.parser.validator.PlayerFrameValidator;
import com.eljhoset.bowlingscoring.parser.validator.RollLineValidator;
import java.util.Objects;

public class DefaultRollParserFactory implements RollParserFactory {

    public static RollParserFactory instance() {
	return new DefaultRollParserFactory();
    }

    private DefaultRollParserFactory() {
    }

    @Override
    public RollParser get(RollParserType parserType) {
	Objects.requireNonNull(parserType, "parserType must no be null");
	if (parserType.equals(RollParserType.DEFAULT)) {
	    DefaultRollParser parser = new DefaultRollParser();
	    parser.setPlayerFrameValidator(getFrameValidator());
	    parser.setRollMapper(getRollMapper());
	    parser.setRollValidator(getRollLineValidator());
	    return parser;
	}
	throw new IllegalArgumentException(String.format("unknown parserType [%s]", parserType));
    }

    protected PlayerFrameValidator getFrameValidator() {
	return new DefaultPlayerFrameValidator();
    }

    protected PlayerFramelMapper getRollMapper() {
	return new DefaultPlayerFramelMapper();
    }

    protected RollLineValidator getRollLineValidator() {
	return new DefaultRollLineValidator();
    }

}
