package com.eljhoset.bowlingscoring.processor.model;

import java.util.Collections;
import java.util.List;

public class FrameScoreListImpl implements FrameScoreList {

    private final List<FrameScore> scores;

    public FrameScoreListImpl(List<FrameScore> scores) {
        this.scores = scores;
    }

    @Override
    public List<FrameScore> getFrames() {
        return Collections.unmodifiableList(scores);
    }

}
