package com.eljhoset.bowlingscoring.processor.model;

import com.eljhoset.bowlingscoring.parser.model.Player;

/**
 *
 * @author jd-jd
 */
public interface PlayerScore {

    Player getPlayer();

    FrameScoreList getFrames();

    Integer getScore();

}
