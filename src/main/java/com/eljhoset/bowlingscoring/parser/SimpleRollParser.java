package com.eljhoset.bowlingscoring.parser;

import com.eljhoset.bowlingscoring.parser.mapper.PlayerFramelMapper;
import com.eljhoset.bowlingscoring.parser.model.Player;
import com.eljhoset.bowlingscoring.parser.model.PlayerFrames;
import com.eljhoset.bowlingscoring.parser.validator.PlayerFrameValidator;
import com.eljhoset.bowlingscoring.parser.validator.RollLineValidator;
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

	List<String> validRolls = rolls.stream()
		.map(getRollValidator()::validate)
		.collect(Collectors.toList());

	return getRollMapper().map(validRolls)
		.stream().map(getPlayerFrameValidator()::validate)
		.sorted((o1, o2) -> {
		    Player player = o1.getPlayer();
		    Player player2 = o2.getPlayer();
		    return player.getName().compareTo(player2.getName());
		})
		.collect(Collectors.toList());

    }

    public abstract PlayerFramelMapper getRollMapper();

    public abstract RollLineValidator getRollValidator();

    public abstract PlayerFrameValidator getPlayerFrameValidator();
}
