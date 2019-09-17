package com.eljhoset.bowlingscoring.parser;

import com.eljhoset.bowlingscoring.parser.mapper.PlayerFramelMapper;
import com.eljhoset.bowlingscoring.parser.validator.PlayerFrameValidator;
import com.eljhoset.bowlingscoring.parser.validator.RollLineValidator;

public class DefaultRollParser extends SimpleRollParser {

    private PlayerFramelMapper rollMapper;
    private RollLineValidator rollValidator;
    private PlayerFrameValidator playerFrameValidator;

    @Override
    public PlayerFramelMapper getRollMapper() {
        return rollMapper;
    }

    public void setRollMapper(PlayerFramelMapper rollMapper) {
        this.rollMapper = rollMapper;
    }

    @Override
    public RollLineValidator getRollValidator() {
        return rollValidator;
    }

    public void setRollValidator(RollLineValidator rollValidator) {
        this.rollValidator = rollValidator;
    }

    @Override
    public PlayerFrameValidator getPlayerFrameValidator() {
        return playerFrameValidator;
    }

    public void setPlayerFrameValidator(PlayerFrameValidator playerFrameValidator) {
        this.playerFrameValidator = playerFrameValidator;
    }

}
