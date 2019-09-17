package com.eljhoset.bowlingscoring.data.repository;

import com.eljhoset.bowlingscoring.data.exception.EmptyFileException;
import java.io.IOException;
import java.util.List;

/**
 *
 * @author jd-jd
 */
public interface FileRollRepository {

    List<String> load(String path) throws IOException, EmptyFileException;
}
