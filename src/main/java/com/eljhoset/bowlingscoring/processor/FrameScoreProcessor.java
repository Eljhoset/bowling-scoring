package com.eljhoset.bowlingscoring.processor;

import com.eljhoset.bowlingscoring.parser.model.PlayerFrames;
import com.eljhoset.bowlingscoring.processor.model.PlayerScore;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author jd-jd
 */
public interface FrameScoreProcessor {

    PlayerScore process(PlayerFrames frames);

    default List<PlayerScore> processAll(List<PlayerFrames> frames) {
        return frames.stream()
                .map(this::process)
                .collect(Collectors.toList());
    }
}
