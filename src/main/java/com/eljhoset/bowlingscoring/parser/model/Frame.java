package com.eljhoset.bowlingscoring.parser.model;

/**
 *
 * @author jd-jd
 */
public interface Frame {

    Integer getNumber();

    FrameRolls rolls();

    boolean isSpare();

    boolean isStrike();

}
