package com.eljhoset.bowlingscoring.parser.validator;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author jd-jd
 */
public class DefaultRollLineValidatorTest {

    private RollLineValidator validator;

    @Before
    public void setup() {
        this.validator = new DefaultRollLineValidator();
    }

    @After
    public void tearDown() {
        this.validator = null;
    }

    @Test(expected = NullPointerException.class)
    public void validate_nullPathParam_throwException() throws Exception {
        this.validator.validate(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void validate_emptyPathParam_throwException() throws Exception {
        this.validator.validate("");
    }

    @Test(expected = IllegalArgumentException.class)
    public void validate_nonTabSeparatedValue_throwException() throws Exception {
        this.validator.validate("Jeff->10");
    }

    @Test(expected = IllegalArgumentException.class)
    public void validate_moreThanTwoTabSeparatedValue_throwException() throws Exception {
        this.validator.validate("Daniel\t10\t10");
    }

    @Test(expected = IllegalArgumentException.class)
    public void validate_negativeKnockedPins_throwException() throws Exception {
        this.validator.validate("Daniel\t-10");
    }

    @Test(expected = IllegalArgumentException.class)
    public void validate_greaterThan10KnockedPins_throwException() throws Exception {
        this.validator.validate("Daniel\t100");
    }

    @Test(expected = IllegalArgumentException.class)
    public void validate_invalidCharacterKnockedPins_throwException() throws Exception {
        this.validator.validate("Daniel\tP");
    }
    @Test
    public void validate_validLine_returnLine() throws Exception {
        String line = this.validator.validate("Daniel\t8");
    }

}
