package com.eljhoset.bowlingscoring.formatter.model;

import java.util.List;

/**
 * Provides a representation of a game ready to presented 
 *
 * @author jd-jd
 */
public interface GameScore {

    String getHeader();

    List<String> getPlayers();
}
