package com.eljhoset.bowlingscoring;

import com.eljhoset.bowlingscoring.app.DefaultScoreAppConsumer;
import com.eljhoset.bowlingscoring.app.ScoreAppAbstractFacade;
import com.eljhoset.bowlingscoring.app.ScoreAppConsumer;
import com.eljhoset.bowlingscoring.app.ScoreAppFacade;
import com.eljhoset.bowlingscoring.data.exception.EmptyFileException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author jd-jd
 */
public class App {

    private static final Logger LOG = Logger.getLogger(App.class.getName());

    public static void main(String[] args) {
        if (args.length == 0) {
            LOG.log(Level.SEVERE, "Please provide a file path with the game results");
            System.exit(0);
        }

        final String filePath = args[0];
        ScoreAppAbstractFacade facade = new ScoreAppFacade();
        ScoreAppConsumer appConsumer = new DefaultScoreAppConsumer();
        try {
            facade.getScore(filePath, appConsumer);
        } catch (IOException | EmptyFileException ex) {
            LOG.log(Level.SEVERE, String.format("Error reading source file: %s", ex.getMessage()));
        } catch (IllegalArgumentException ex) {
            LOG.log(Level.SEVERE, ex.getMessage());
        }
        System.exit(0);

    }
}
