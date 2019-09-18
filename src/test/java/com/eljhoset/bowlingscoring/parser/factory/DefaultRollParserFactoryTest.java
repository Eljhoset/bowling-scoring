package com.eljhoset.bowlingscoring.parser.factory;

import com.eljhoset.bowlingscoring.parser.RollParser;
import static org.junit.Assert.assertNotNull;
import org.junit.Test;

/**
 *
 * @author jd-jd
 */
public class DefaultRollParserFactoryTest {

    public DefaultRollParserFactoryTest() {
    }

    @Test
    public void instance_returnInstance() {
        RollParserFactory instance = DefaultRollParserFactory.instance();
        assertNotNull(instance);
    }

    @Test
    public void get_default_returnParser() {
        RollParser parser = DefaultRollParserFactory.instance().get(RollParserType.DEFAULT);
        assertNotNull(parser);
    }

    @Test(expected = NullPointerException.class)
    public void get_nullType_throwException() {
        DefaultRollParserFactory.instance().get(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void get_unkanownType_throwException() {
        DefaultRollParserFactory.instance().get(RollParserType.JPA);
    }

}
