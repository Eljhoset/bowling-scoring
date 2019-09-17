package com.eljhoset.bowlingscoring.parser;

import com.eljhoset.bowlingscoring.parser.mapper.RollMapper;
import com.eljhoset.bowlingscoring.parser.validator.PlayerFrameValidator;
import com.eljhoset.bowlingscoring.parser.validator.RollValidator;

public class DefaultRollParser extends SimpleRollParser {

    private RollMapper rollMapper;
    private RollValidator rollValidator;
    private PlayerFrameValidator playerFrameValidator;
    

    @Override
    public RollMapper getRollMapper() {
        return rollMapper;
    }

    public void setRollMapper(RollMapper rollMapper) {
        this.rollMapper = rollMapper;
    }

    @Override
    public RollValidator getRollValidator() {
        return rollValidator;
    }

    public void setRollValidator(RollValidator rollValidator) {
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
