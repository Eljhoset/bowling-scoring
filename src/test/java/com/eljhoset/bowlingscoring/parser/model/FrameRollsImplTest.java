package com.eljhoset.bowlingscoring.parser.model;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import static org.hamcrest.CoreMatchers.is;
import org.junit.After;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author jd-jd
 */
public class FrameRollsImplTest {

    private List<Roll> oneRoll;
    private List<Roll> twoRolls;
    private List<Roll> threeRolls;
    private List<Roll> fourRolls;
    private List<Roll> incompleteRoll;

    @Before
    public void setup() {

        Roll roll = new Roll() {
            @Override
            public String getPins() {
                return "10";
            }

            @Override
            public Integer getValue() {
                return 10;
            }
        };
        Roll roll2 = new Roll() {
            @Override
            public String getPins() {
                return "9";
            }

            @Override
            public Integer getValue() {
                return 9;
            }
        };
        Roll roll3 = new Roll() {
            @Override
            public String getPins() {
                return "7";
            }

            @Override
            public Integer getValue() {
                return 7;
            }
        };
        Roll roll4 = new Roll() {
            @Override
            public String getPins() {
                return "6";
            }

            @Override
            public Integer getValue() {
                return 6;
            }
        };

        oneRoll = Arrays.asList(roll);
        incompleteRoll = Arrays.asList(roll2);
        twoRolls = Arrays.asList(roll, roll2);
        threeRolls = Arrays.asList(roll, roll2, roll3);
        fourRolls = Arrays.asList(roll, roll2, roll3, roll4);
    }

    @After
    public void tearDown() {
        oneRoll = null;
        twoRolls = null;
        threeRolls = null;
        fourRolls = null;
        incompleteRoll = null;
    }

    @Test(expected = NullPointerException.class)
    public void instanciate_nullList_throwException() {
        FrameRollsImpl rolls = new FrameRollsImpl(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void instanciate_emptyList_throwException() {
        FrameRollsImpl rolls = new FrameRollsImpl(Collections.emptyList());
    }

    @Test(expected = IllegalArgumentException.class)
    public void instanciate_listWihtMoreThanThreeRolls_throwException() throws Exception {
        FrameRollsImpl rolls = new FrameRollsImpl(fourRolls);
    }
    @Test(expected = IllegalArgumentException.class)
    public void instanciate_incompleteFrame_throwException() throws Exception {
        FrameRollsImpl rolls = new FrameRollsImpl(incompleteRoll);
    }

    @Test
    public void instanciate_withValidList_instanciateObject() throws Exception {
        FrameRollsImpl rolls = new FrameRollsImpl(twoRolls);
        assertNotNull(rolls);
    }

    @Test
    public void getFirstRoll_withValidList_returnFirstRoll() throws Exception {
        FrameRollsImpl rolls = new FrameRollsImpl(twoRolls);
        Roll roll = rolls.getFirstRoll();
        assertThat(roll.getPins(), is("10"));
    }

    @Test
    public void getSecondRoll_withOneRollList_returnEmpty() throws Exception {
        FrameRollsImpl rolls = new FrameRollsImpl(oneRoll);
        Optional<Roll> roll = rolls.getSecondRoll();
        assertFalse(roll.isPresent());
    }

    @Test
    public void getSecondRoll_withMoreThanOneRollList_returnRoll() throws Exception {
        FrameRollsImpl rolls = new FrameRollsImpl(twoRolls);
        Optional<Roll> roll = rolls.getSecondRoll();
        assertThat(roll.get().getPins(), is("9"));
    }

    @Test
    public void getThirdRoll_withLessThanThreeRollList_returnEmpty() throws Exception {
        FrameRollsImpl rolls = new FrameRollsImpl(oneRoll);
        Optional<Roll> roll = rolls.getThirdRoll();
        assertFalse(roll.isPresent());
    }

    @Test
    public void getSecondRoll_withMoreThanTwoRollList_returnRoll() throws Exception {
        FrameRollsImpl rolls = new FrameRollsImpl(threeRolls);
        Optional<Roll> roll = rolls.getThirdRoll();
        assertThat(roll.get().getPins(), is("7"));
    }

}
