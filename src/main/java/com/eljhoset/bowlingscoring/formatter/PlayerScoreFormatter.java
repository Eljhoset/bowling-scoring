package com.eljhoset.bowlingscoring.formatter;

import com.eljhoset.bowlingscoring.formatter.model.GameScore;
import com.eljhoset.bowlingscoring.processor.model.PlayerScore;
import java.util.List;

/**
 *
 * @author jd-jd
 */
public interface PlayerScoreFormatter {

    GameScore format(List<PlayerScore> scores);
}
