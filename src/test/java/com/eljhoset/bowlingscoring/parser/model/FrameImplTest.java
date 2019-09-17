package com.eljhoset.bowlingscoring.parser.model;

import java.util.List;
import org.junit.After;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author jd-jd
 */
public class FrameImplTest {

    private FrameRolls strikeFrame;
    private FrameRolls spareFrame;

    @Before
    public void setup() {
        this.strikeFrame = new FrameRolls() {

            @Override
            public Roll getFirstRoll() {
                return new Roll() {
                    @Override
                    public String getPins() {
                        throw new UnsupportedOperationException("Not supported yet.");
                    }

                    @Override
                    public Integer getValue() {
                        return 10;
                    }

                };
            }

            @Override
            public Integer getRollsNumber() {
                throw new UnsupportedOperationException("Not supported yet.");
            }

            @Override
            public List<Roll> getRolls() {
                throw new UnsupportedOperationException("Not supported yet.");
            }

        };
        this.spareFrame = new FrameRolls() {

            @Override
            public Roll getFirstRoll() {
                return new Roll() {
                    @Override
                    public String getPins() {
                        throw new UnsupportedOperationException("Not supported yet.");
                    }

                    @Override
                    public Integer getValue() {
                        return 7;
                    }

                };
            }

            @Override
            public Integer getRollsNumber() {
                throw new UnsupportedOperationException("Not supported yet.");
            }

            @Override
            public List<Roll> getRolls() {
                throw new UnsupportedOperationException("Not supported yet.");
            }

        };
    }

    @After
    public void tearDown() {
        strikeFrame = null;
        spareFrame = null;
    }

    @Test(expected = NullPointerException.class)
    public void instanciate_nullRolls_throwException() {
        Frame frame = new FrameImpl(null, null, false);
    }

    @Test(expected = NullPointerException.class)
    public void instanciate_nullNumber_throwException() {
        Frame frame = new FrameImpl(strikeFrame, null, false);
    }

    @Test
    public void instanciate_validRoll_instanciate() {
        Frame frame = new FrameImpl(strikeFrame, 1, false);
        assertNotNull(frame);
    }

    @Test
    public void isStrike_strikeRoll_true() {
        Frame frame = new FrameImpl(strikeFrame, 1, false);
        assertTrue(frame.isStrike());
    }

    @Test
    public void isStrike_spareRoll_false() {

        Frame frame = new FrameImpl(spareFrame, 1, false);
        assertFalse(frame.isStrike());
    }

    @Test
    public void isSpare_strikeRoll_false() {

        Frame frame = new FrameImpl(strikeFrame, 1, false);
        assertFalse(frame.isSpare());
    }

    @Test
    public void isSpare_spareRoll_true() {

        Frame frame = new FrameImpl(spareFrame, 1, false);
        assertTrue(frame.isSpare());
    }
    @Test
    public void isLast_nonLast_false() {

        Frame frame = new FrameImpl(spareFrame, 1, false);
        assertFalse(frame.isLast());
    }
    @Test
    public void isLast_last_true() {

        Frame frame = new FrameImpl(spareFrame, 1, true);
        assertTrue(frame.isLast());
    }

}
