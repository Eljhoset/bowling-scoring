package com.eljhoset.bowlingscoring.data.repository;

import com.eljhoset.bowlingscoring.data.exception.EmptyFileException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;
import java.util.List;
import org.junit.After;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author jd-jd
 */
public class DefaultFileRollRepositoryTest {

    private static final String FILE_PATH_NOT_EXIST = "unknown_file.txt";
    private static final String FILE_PATH_EMPTY = "src/test/resources/empty.txt";
    private static final String FILE_PATH_VALID = "src/test/resources/all_strikes.txt";
    private FileRollRepository repository;

    @Before
    public void setup() {
        this.repository = new DefaultFileRollRepository();
    }

    @After
    public void tearDown() {
        this.repository = null;
    }

    @Test(expected = NullPointerException.class)
    public void load_nullPathParam_throwException() throws Exception {
        repository.load(null);
    }

    @Test(expected = NoSuchFileException.class)
    public void load_filePathNotExists_throwException() throws Exception {
        repository.load(FILE_PATH_NOT_EXIST);
    }

    @Test(expected = EmptyFileException.class)
    public void load_filePathToEmptyFile_throwException() throws Exception {
        repository.load(FILE_PATH_EMPTY);
    }

    @Test
    public void load_validFile_returnFileLines() throws Exception {
        List<String> content = repository.load(FILE_PATH_VALID);
        List<String> expectedContent = Files.readAllLines(Paths.get(FILE_PATH_VALID));
        assertEquals(content, expectedContent);
    }

}
