package com.eljhoset.bowlingscoring.formatter;

import com.eljhoset.bowlingscoring.formatter.model.GameScore;
import com.eljhoset.bowlingscoring.processor.model.PlayerScore;
import java.util.List;

/**
 * Convert the PlayerScore
 * {@link com.eljhoset.bowlingscoring.processor.model.PlayerScore} into a
 * representation ready to be printed
 *
 * @author jd-jd
 */
public interface PlayerScoreFormatter {

    GameScore format(List<PlayerScore> scores);
}
