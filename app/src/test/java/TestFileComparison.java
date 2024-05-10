import hexlet.code.Differ;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestFileComparison {
    private static String FILE_JSON_PATH_1;
    private static String FILE_JSON_PATH_2;
    private static String FILE_YAML_PATH_1;
    private static String FILE_YAML_PATH_2;
    private static String FILE_JSON_RESULT_PATH;
    private static String FILE_YAML_RESULT_PATH;


    @BeforeEach
    public void beforeEach() {
        FILE_JSON_PATH_1 = "src/test/resources/file1.json";
        FILE_JSON_PATH_2 = "src/test/resources/file2.json";
        FILE_JSON_RESULT_PATH = "src/test/resources/compare_result_json.txt";

        FILE_YAML_PATH_1 = "src/test/resources/file1.yaml";
        FILE_YAML_PATH_2 = "src/test/resources/file2.yaml";
        FILE_YAML_RESULT_PATH = "src/test/resources/compare_result_json.txt";

    }

    @Test
    public void testFileExists() {
        Path pathJson1 = Paths.get(FILE_JSON_PATH_1).toAbsolutePath().normalize();
        Path pathJson2 = Paths.get(FILE_JSON_PATH_2).toAbsolutePath().normalize();
        Path pathJsonDiffResult = Paths.get(FILE_JSON_RESULT_PATH).toAbsolutePath().normalize();

        assertTrue(Files.exists(pathJson1));
        assertTrue(Files.exists(pathJson2));
        assertTrue(Files.exists(pathJsonDiffResult));
    }
    @Test
    public void testDiffJson() throws Exception {
        Path pathJsonFileDiff = Paths.get(FILE_JSON_RESULT_PATH).toAbsolutePath().normalize();
        var expected = Files.readString(pathJsonFileDiff);
        var actual = Differ.generate(FILE_JSON_PATH_1, FILE_JSON_PATH_2);

        assertEquals(expected, actual);
    }
    @Test
    public void testDiffYaml() throws Exception {
        Path pathYamlFileDiff = Paths.get(FILE_YAML_RESULT_PATH).toAbsolutePath().normalize();
        var expected = Files.readString(pathYamlFileDiff);
        var actual = Differ.generate(FILE_YAML_PATH_1, FILE_YAML_PATH_2);

        assertEquals(expected, actual);
    }

}
