package com.eljhoset.bowlingscoring.parser;

import com.eljhoset.bowlingscoring.parser.mapper.PlayerFramelMapper;
import com.eljhoset.bowlingscoring.parser.model.PlayerFrames;
import com.eljhoset.bowlingscoring.parser.validator.PlayerFrameValidator;
import com.eljhoset.bowlingscoring.parser.validator.RollLineValidator;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.junit.After;
import static org.junit.Assert.assertNotNull;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author jd-jd
 */
public class SimpleRollParserTest {

    private SimpleRollParser parser;
    private SimpleRollParser parserWithValidators;
    private SimpleRollParser parserWithValidatorsAndMappers;
    private SimpleRollParser parserWithRollValidator;

    @Before
    public void setup() {
        this.parser = new SimpleRollParser() {
            @Override
            public PlayerFramelMapper getRollMapper() {
                return null;
            }

            @Override
            public RollLineValidator getRollValidator() {
                return null;
            }

            @Override
            public PlayerFrameValidator getPlayerFrameValidator() {
                return null;
            }

        };
        RollLineValidator rollValidator = line -> line;
        PlayerFrameValidator frameValidator = line -> true;
        PlayerFramelMapper mapper = line -> Collections.emptyList();

        this.parserWithRollValidator = new SimpleRollParser() {
            @Override
            public PlayerFramelMapper getRollMapper() {
                return null;
            }

            @Override
            public RollLineValidator getRollValidator() {
                return rollValidator;
            }

            @Override
            public PlayerFrameValidator getPlayerFrameValidator() {
                return null;
            }

        };

        this.parserWithValidators = new SimpleRollParser() {
            @Override
            public PlayerFramelMapper getRollMapper() {
                return null;
            }

            @Override
            public RollLineValidator getRollValidator() {
                return rollValidator;
            }

            @Override
            public PlayerFrameValidator getPlayerFrameValidator() {
                return frameValidator;
            }

        };

        this.parserWithValidatorsAndMappers = new SimpleRollParser() {
            @Override
            public PlayerFramelMapper getRollMapper() {
                return mapper;
            }

            @Override
            public RollLineValidator getRollValidator() {
                return rollValidator;
            }

            @Override
            public PlayerFrameValidator getPlayerFrameValidator() {
                return frameValidator;
            }

        };
    }

    @After
    public void tearDown() {
        this.parser = null;
        this.parserWithValidators = null;
        this.parserWithValidatorsAndMappers = null;
        this.parserWithRollValidator = null;
    }

    @Test(expected = NullPointerException.class)
    public void parse_nullPathParam_throwException() throws Exception {
        parser.parse(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void parse__emptyList_throwException() throws Exception {
        parser.parse(Collections.emptyList());
    }

    @Test(expected = NullPointerException.class)
    public void parse__nullRollValidator_throwException() throws Exception {
        parser.parse(Arrays.asList(""));
    }

    @Test(expected = NullPointerException.class)
    public void parse__nullFrameValidator_throwException() throws Exception {
        parserWithRollValidator.parse(Arrays.asList(""));
    }

    @Test(expected = NullPointerException.class)
    public void parse__nullFrameMapper_throwException() throws Exception {
        parserWithValidators.parse(Arrays.asList(""));
    }

    @Test
    public void parse__validInstance_shouldReturn() throws Exception {
        List<PlayerFrames> parseFrames = parserWithValidatorsAndMappers.parse(Arrays.asList(""));
        assertNotNull(parseFrames);
    }

}
