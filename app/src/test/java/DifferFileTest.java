import hexlet.code.Differ;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class DifferFileTest {
    private static String filePath1;
    private static String filePath2;
    private static String filePathDiff;


    @BeforeEach
    public void beforeEach() {
        filePath1 = "src/test/resources/file1.json";
        filePath2 = "src/test/resources/file2.json";
        filePathDiff = "src/test/resources/diffTest.input";
    }

    @Test
    public void testFileExists() {
        Path path1 = Paths.get(filePath1).toAbsolutePath().normalize();
        Path path2 = Paths.get(filePath2).toAbsolutePath().normalize();
        assertTrue(Files.exists(path1));
        assertTrue(Files.exists(path2));
    }
    @Test
    public void testDiff() throws Exception {
        Path pathFileDiff = Paths.get(filePathDiff).toAbsolutePath().normalize();
        var expected = Files.readString(pathFileDiff);
        var actual = Differ.generate(filePath1, filePath2);

        assertEquals(expected, actual);
    }

}
