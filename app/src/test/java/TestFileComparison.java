import hexlet.code.Differ;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestFileComparison {
    private static String fileJsonPath1;
    private static String fileJsonPath2;
    private static String fileYamlPath1;
    private static String fileYamlPath2;
    private static String fileJsonResultPath;
    private static String fileYamlResultPath;

    private static String fileNestJsonPath1;
    private static String fileNestJsonPath2;
    private static String fileNestYamlPath1;
    private static String fileNestYamlPath2;
    private static String fileNestJsonResultPath;
    private static String fileNestYamlResultPath;


    @BeforeEach
    public void beforeEach() {
        fileJsonPath1 = "src/test/resources/file1.json";
        fileJsonPath2 = "src/test/resources/file2.json";
        fileJsonResultPath = "src/test/resources/compare_result_json.txt";

        fileYamlPath1 = "src/test/resources/file1.yaml";
        fileYamlPath2 = "src/test/resources/file2.yaml";
        fileYamlResultPath = "src/test/resources/compare_result_yaml.txt";

        fileNestJsonPath1 = "src/test/resources/filenested1.json";
        fileNestJsonPath2 = "src/test/resources/filenested2.json";
        fileNestJsonResultPath = "src/test/resources/compare_nested_res_json.txt";

        fileNestYamlPath1 = "src/test/resources/filenested1.yaml";
        fileNestYamlPath2 = "src/test/resources/filenested2.yaml";
        fileNestYamlResultPath = "src/test/resources/compare_nested_res_yaml.txt";
    }

    @Test
    public void testFileExists() {
        Path pathJson1 = Paths.get(fileJsonPath1).toAbsolutePath().normalize();
        Path pathJson2 = Paths.get(fileJsonPath2).toAbsolutePath().normalize();
        Path pathJsonDiffResult = Paths.get(fileJsonResultPath).toAbsolutePath().normalize();

        assertTrue(Files.exists(pathJson1));
        assertTrue(Files.exists(pathJson2));
        assertTrue(Files.exists(pathJsonDiffResult));

        Path pathYaml1 = Paths.get(fileYamlPath1).toAbsolutePath().normalize();
        Path pathYaml2 = Paths.get(fileYamlPath2).toAbsolutePath().normalize();
        Path pathYamlDiffResult = Paths.get(fileYamlResultPath).toAbsolutePath().normalize();

        assertTrue(Files.exists(pathYaml1));
        assertTrue(Files.exists(pathYaml2));
        assertTrue(Files.exists(pathYamlDiffResult));

        Path pathNestJson1 = Paths.get(fileNestJsonPath1).toAbsolutePath().normalize();
        Path pathNestJson2 = Paths.get(fileNestJsonPath2).toAbsolutePath().normalize();
        Path pathNestJsonDiffResult = Paths.get(fileNestJsonResultPath).toAbsolutePath().normalize();

        assertTrue(Files.exists(pathNestJson1));
        assertTrue(Files.exists(pathNestJson2));
        assertTrue(Files.exists(pathNestJsonDiffResult));

        Path pathNestYaml1 = Paths.get(fileNestYamlPath1).toAbsolutePath().normalize();
        Path pathNestYaml2 = Paths.get(fileNestYamlPath2).toAbsolutePath().normalize();
        Path pathNestYamlDiffResult = Paths.get(fileNestYamlResultPath).toAbsolutePath().normalize();

        assertTrue(Files.exists(pathNestYaml1));
        assertTrue(Files.exists(pathNestYaml2));
        assertTrue(Files.exists(pathNestYamlDiffResult));

    }
    @Test
    public void testDiffJson() throws Exception {
        Path pathJsonFileDiff = Paths.get(fileJsonResultPath).toAbsolutePath().normalize();
        var expected = Files.readString(pathJsonFileDiff);
        var actual = Differ.generate(fileJsonPath1, fileJsonPath2);

        assertEquals(expected, actual);
    }
    @Test
    public void testDiffYaml() throws Exception {
        Path pathYamlFileDiff = Paths.get(fileYamlResultPath).toAbsolutePath().normalize();
        var expected = Files.readString(pathYamlFileDiff);
        var actual = Differ.generate(fileYamlPath1, fileYamlPath2);

        assertEquals(expected, actual);
    }

    @Test
    public void testNestDiffJson() throws Exception {
        Path pathNestJsonFileDiff = Paths.get(fileNestJsonResultPath).toAbsolutePath().normalize();
        var expected = Files.readString(pathNestJsonFileDiff);
        var actual = Differ.generate(fileNestJsonPath1, fileNestJsonPath2);

        assertEquals(expected, actual);
    }

    @Test
    public void testNestDiffYAml() throws Exception {
        Path pathNestYamlFileDiff = Paths.get(fileNestYamlResultPath).toAbsolutePath().normalize();
        var expected = Files.readString(pathNestYamlFileDiff);
        var actual = Differ.generate(fileNestYamlPath1, fileNestYamlPath2);

        assertEquals(expected, actual);
    }

}
