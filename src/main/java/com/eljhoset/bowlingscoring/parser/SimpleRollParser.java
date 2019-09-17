package com.eljhoset.bowlingscoring.parser;

import com.eljhoset.bowlingscoring.parser.mapper.RollMapper;
import com.eljhoset.bowlingscoring.parser.model.PlayerFrames;
import com.eljhoset.bowlingscoring.parser.validator.PlayerFrameValidator;
import com.eljhoset.bowlingscoring.parser.validator.RollValidator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 *
 * @author jd-jd
 */
public abstract class SimpleRollParser implements RollParser {

    @Override
    public List<PlayerFrames> parse(List<String> rolls) {
        Objects.requireNonNull(rolls, "rolls must not be null");
        if (rolls.isEmpty()) {
            throw new IllegalArgumentException("There must be at least one roll");
        }
        Objects.requireNonNull(rolls, "rolls must not be null");
        Objects.requireNonNull(getRollValidator(), "roll validator must not be null");
        Objects.requireNonNull(getPlayerFrameValidator(), "plaver frmae validatormust not be null");
        Objects.requireNonNull(getRollMapper(), "roll mapper must not be null");

        List<PlayerFrames> result = rolls.stream().filter(getRollValidator()::isValid)
                .map(getRollMapper()::map)
                .filter(getPlayerFrameValidator()::isValid)
                .collect(Collectors.toList());

        return result;
    }

    public abstract RollMapper getRollMapper();

    public abstract RollValidator getRollValidator();

    public abstract PlayerFrameValidator getPlayerFrameValidator();
}
