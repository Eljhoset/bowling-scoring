package com.eljhoset.bowlingscoring.parser.validator;

import com.eljhoset.bowlingscoring.parser.model.Frame;
import com.eljhoset.bowlingscoring.parser.model.FrameList;
import com.eljhoset.bowlingscoring.parser.model.FrameRolls;
import com.eljhoset.bowlingscoring.parser.model.Player;
import com.eljhoset.bowlingscoring.parser.model.PlayerFrames;
import com.eljhoset.bowlingscoring.parser.model.Roll;
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
public class DefaultPlayerFrameValidatorTest {

    private PlayerFrameValidator validator;
    private PlayerFrames playerFramesWithBadRollNumber;
    private PlayerFrames playerFramesWithBadPinNumberKnocked;
    private PlayerFrames playerFramesValid;
    private PlayerFrames playerFramesFrameWithMoreThan3Rolls;

    @Before
    public void setup() {
        Frame frameWithBadRollNumber = new Frame() {
            @Override
            public Integer getNumber() {
                return 0;
            }

            @Override
            public FrameRolls rolls() {
                return new FrameRolls() {
                    @Override
                    public List<Roll> getRolls() {
                        return Collections.emptyList();
                    }

                    @Override
                    public Integer getRollsNumber() {
                        return 3;
                    }

                    @Override
                    public Roll getFirstRoll() {
                        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                    }

                };
            }

            @Override
            public boolean isSpare() {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public boolean isStrike() {
                return false;
            }

            @Override
            public boolean isLast() {
                return false;
            }

        };
        Frame frameWithBadRollNumber2 = new Frame() {
            @Override
            public Integer getNumber() {
                return 0;
            }

            @Override
            public FrameRolls rolls() {
                return new FrameRolls() {
                    @Override
                    public List<Roll> getRolls() {
                        return Collections.emptyList();
                    }

                    @Override
                    public Integer getRollsNumber() {
                        return 10;
                    }

                    @Override
                    public Roll getFirstRoll() {
                        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                    }

                };
            }

            @Override
            public boolean isSpare() {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public boolean isStrike() {
                return false;
            }

            @Override
            public boolean isLast() {
                return false;
            }

        };
        Frame frameValid = new Frame() {
            @Override
            public Integer getNumber() {
                return 0;
            }

            @Override
            public FrameRolls rolls() {
                return new FrameRolls() {
                    @Override
                    public List<Roll> getRolls() {
                        return Collections.emptyList();
                    }

                    @Override
                    public Integer getRollsNumber() {
                        return 3;
                    }

                    @Override
                    public Roll getFirstRoll() {
                        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                    }

                };
            }

            @Override
            public boolean isSpare() {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public boolean isStrike() {
                return true;
            }

            @Override
            public boolean isLast() {
                return false;
            }

        };
        Frame frameWithBadNumberOfPins = new Frame() {
            @Override
            public Integer getNumber() {
                return 0;
            }

            @Override
            public FrameRolls rolls() {
                return new FrameRolls() {
                    @Override
                    public List<Roll> getRolls() {
                        Roll r = new Roll() {
                            @Override
                            public String getPins() {
                                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                            }

                            @Override
                            public Integer getValue() {
                                return 10;
                            }
                        };
                        return Arrays.asList(r, r);
                    }

                    @Override
                    public Integer getRollsNumber() {
                        return 3;
                    }

                    @Override
                    public Roll getFirstRoll() {
                        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                    }

                };
            }

            @Override
            public boolean isSpare() {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public boolean isStrike() {
                return false;
            }

        };
        this.playerFramesWithBadRollNumber = new PlayerFrames() {
            @Override
            public Player getPlayer() {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public FrameList getFrames() {
                return () -> Arrays.asList(frameWithBadRollNumber);
            }

        };
        this.playerFramesWithBadPinNumberKnocked = new PlayerFrames() {
            @Override
            public Player getPlayer() {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public FrameList getFrames() {
                return () -> Arrays.asList(frameWithBadNumberOfPins);
            }

        };
        this.playerFramesValid = new PlayerFrames() {
            @Override
            public Player getPlayer() {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public FrameList getFrames() {
                return () -> Arrays.asList(frameValid);
            }

        };
        this.playerFramesFrameWithMoreThan3Rolls = new PlayerFrames() {
            @Override
            public Player getPlayer() {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public FrameList getFrames() {
                return () -> Arrays.asList(frameWithBadRollNumber2);
            }

        };
        this.validator = new DefaultPlayerFrameValidator();
    }

    @After
    public void tearDown() {
        this.validator = null;
        this.playerFramesWithBadRollNumber = null;
        this.playerFramesWithBadPinNumberKnocked = null;
        this.playerFramesValid = null;
        this.playerFramesFrameWithMoreThan3Rolls = null;
    }

    @Test(expected = NullPointerException.class)
    public void intanciate_nullPathParam_throwException() throws Exception {
        this.validator.validate(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void validate_firstFrameThreeRolls_throwException() throws Exception {
        this.validator.validate(playerFramesWithBadRollNumber);
    }

    @Test(expected = IllegalArgumentException.class)
    public void validate_firstFrameTenRolls_throwException() throws Exception {
        this.validator.validate(playerFramesFrameWithMoreThan3Rolls);
    }

    @Test(expected = IllegalArgumentException.class)
    public void validate_20Knocked_throwException() throws Exception {
        this.validator.validate(playerFramesWithBadPinNumberKnocked);
    }

    @Test
    public void validate_frame_returnFrame() throws Exception {
        PlayerFrames validated = this.validator.validate(playerFramesValid);
        assertNotNull(validated);
    }
}
