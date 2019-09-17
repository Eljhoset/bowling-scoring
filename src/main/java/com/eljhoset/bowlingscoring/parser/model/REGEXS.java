package com.eljhoset.bowlingscoring.parser.model;

/**
 *
 * @author jd-jd
 */
public enum REGEXS {
    VALUE_SEPARATOR("\t"), PINS("^(?:[0-9]0?|F)$");
    private final String regex;

    REGEXS(String regex) {
        this.regex = regex;
    }

    public String get() {
        return regex;
    }
}
