package com.eljhoset.bowlingscoring.app;

import com.eljhoset.bowlingscoring.formatter.model.GameScore;

/**
 *
 * @author jd-jd
 */
public interface ScoreAppConsumer {

    void consume(GameScore gameScore);
}
