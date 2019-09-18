package com.eljhoset.bowlingscoring.processor.model;

import com.eljhoset.bowlingscoring.parser.model.FrameRolls;

public class FrameScoreImpl implements FrameScore {

    private final FrameRolls rolls;
    private final Integer score;
    private final boolean spare;
    private final boolean strike;
    private final boolean last;
    private final Integer number;

    public FrameScoreImpl(Integer number, FrameRolls rolls, Integer score, boolean spare, boolean strike, boolean last) {
	this.rolls = rolls;
	this.number = number;
	this.score = score;
	this.spare = spare;
	this.strike = strike;
	this.last = last;
    }

    @Override
    public int getScore() {
	return score;
    }

    @Override
    public boolean isSpare() {
	return spare;
    }

    @Override
    public boolean isStrike() {
	return strike;
    }

    @Override
    public boolean isLast() {
	return last;
    }

    @Override
    public Integer getNumber() {
	return number;
    }

    @Override
    public FrameRolls rolls() {
	return rolls;
    }

}
