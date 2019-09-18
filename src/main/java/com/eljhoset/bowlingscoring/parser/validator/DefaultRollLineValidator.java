package com.eljhoset.bowlingscoring.parser.validator;

import static com.eljhoset.bowlingscoring.parser.model.REGEXS.PINS;
import static com.eljhoset.bowlingscoring.parser.model.REGEXS.VALUE_SEPARATOR;
import java.util.Objects;

public class DefaultRollLineValidator implements RollLineValidator {

    @Override
    public String validate(String line) {
        Objects.requireNonNull(line, "line cannot be null.");
        if (line.trim().isEmpty()) {
            throw new IllegalArgumentException(String.format("Invalid line [%s], must no be empty ", line));
        }
        String[] parts = line.split(VALUE_SEPARATOR.get());
        if (parts.length != 2) {
            throw new IllegalArgumentException(String.format("Invalid line [%s], should contain only the player name and the knocked pins separated by tab ", line));
        }
        String pins = parts[1];
        if (!pins.matches(PINS.get())) {
            String message = String.format("Invalid line [%s], invalid knocked pins [%s] values should only be positive numbers betwen 0 and 10 or F", line, pins);
            throw new IllegalArgumentException(message);
        }
        return line;
    }

}
