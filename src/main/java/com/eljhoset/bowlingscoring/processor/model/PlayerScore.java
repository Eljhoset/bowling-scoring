package com.eljhoset.bowlingscoring.processor.model;

import com.eljhoset.bowlingscoring.parser.model.Player;

/**
 * Provides a representation of a game result for a player
 *
 * @author jd-jd
 */
public interface PlayerScore {

    Player getPlayer();

    FrameScoreList getFrames();

    Integer getScore();

}
