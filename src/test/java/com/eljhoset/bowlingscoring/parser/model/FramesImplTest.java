package com.eljhoset.bowlingscoring.parser.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import org.junit.After;
import static org.junit.Assert.assertNotNull;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author jd-jd
 */
public class FramesImplTest {

    private ArrayList<Frame> tenFrames;
    private ArrayList<Frame> twentyFrames;
    private Frame frame;

    @Before
    public void setup() {
        this.tenFrames = new ArrayList<>(10);
        this.twentyFrames = new ArrayList<>(10);
        this.frame = new Frame() {
            @Override
            public FrameRolls rolls() {
                return null;
            }

            @Override
            public boolean isSpare() {
                return false;
            }

            @Override
            public boolean isStrike() {
                return false;
            }

        };
        for (int i = 0; i < 10; i++) {
            tenFrames.add(frame);
        }
        for (int i = 0; i < 20; i++) {
            twentyFrames.add(frame);
        }
    }

    @After
    public void tearDown() {
        tenFrames=null;
        twentyFrames=null;
        frame=null;
    }

    @Test(expected = NullPointerException.class)
    public void instanciate_nullList_throwException() {
        FramesImpl frames = new FramesImpl(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void instanciate_emptyList_throwException() {
        FramesImpl frames = new FramesImpl(Collections.emptyList());
    }

    @Test(expected = IllegalArgumentException.class)
    public void instanciate_listWihtMoreThanTenFrames_throwException() throws Exception {
        FramesImpl frames = new FramesImpl(twentyFrames);
    }
    @Test(expected = IllegalArgumentException.class)
    public void instanciate_listWihtLessThanTenFrames_throwException() throws Exception {
        FramesImpl frames = new FramesImpl(Arrays.asList(frame));
    }

    @Test
    public void instanciate_withValidList_instanciateObject() throws Exception {
        FramesImpl frames = new FramesImpl(tenFrames);
        assertNotNull(frames);
    }
}
