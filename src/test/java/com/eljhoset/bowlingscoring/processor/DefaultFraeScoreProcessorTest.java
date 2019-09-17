package com.eljhoset.bowlingscoring.processor;

import org.junit.After;
import org.junit.Before;

/**
 *
 * @author jd-jd
 */
public class DefaultFraeScoreProcessorTest {

    private FrameScoreProcessor processor;

    @Before
    public void setup() {
        this.processor = new DefaultFrameScoreProcessor();
    }

    @After
    public void tearDown() {
        this.processor = null;
    }
}
