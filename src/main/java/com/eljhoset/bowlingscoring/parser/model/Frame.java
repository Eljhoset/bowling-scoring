package com.eljhoset.bowlingscoring.parser.model;

/**
 * Provides a representation of a game frame
 * @author jd-jd
 */
public interface Frame {

    Integer getNumber();

    FrameRolls rolls();

    boolean isSpare();

    boolean isStrike();

    default boolean isLast() {
        return false;
    }
}
