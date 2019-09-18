package com.eljhoset.bowlingscoring.processor.model;

import static org.junit.Assert.assertNotNull;
import org.junit.Test;

/**
 *
 * @author jd-jd
 */
public class PlayerScoreImplTest {

    public PlayerScoreImplTest() {
    }

    @Test(expected = NullPointerException.class)
    public void instanciate_nullPlayer_throwException() {
        PlayerScore playerScore = new PlayerScoreImpl(null, null);
    }

    @Test(expected = NullPointerException.class)
    public void instanciate_nullScores_throwException() {
        PlayerScore playerScore = new PlayerScoreImpl(() -> "", null);
    }

    @Test
    public void instanciate_ValidParam_instanciate() {
        PlayerScore playerScore = new PlayerScoreImpl(() -> "", () -> null);
        assertNotNull(playerScore);
    }

}
