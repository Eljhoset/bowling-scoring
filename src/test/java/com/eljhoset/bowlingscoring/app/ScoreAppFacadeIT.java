package com.eljhoset.bowlingscoring.app;

import org.junit.After;
import static org.junit.Assert.assertNotNull;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author jd-jd
 */
public class ScoreAppFacadeIT {

    private ScoreAppAbstractFacade facade;

    @Before
    public void setup() {
        this.facade = new ScoreAppFacade();
    }

    @After
    public void tearDown() {
        this.facade = null;
    }

    @Test
    public void getScore_validPath_callConsumer() throws Exception {

        facade.getScore("src/test/resources/regular_game_two_players.txt", (gameScore) -> {
            assertNotNull(gameScore);
        });

    }

}
