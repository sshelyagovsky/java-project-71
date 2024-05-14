import hexlet.code.Differ;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class TestFileComparison {

    private static String testResourcePath;
    private static String fileJsonPath1;
    private static String fileJsonPath2;

    private static String fileYamlPath1;
    private static String fileYamlPath2;

    private static String resultStylishFilePath;
    private static String resultPlainFilePath;
    private static String resultJsonFilePath;

    public static boolean isFileExists(String inputPath) {
        Path path = Paths.get(inputPath).toAbsolutePath().normalize();
        return Files.exists(path);
    }

    public static String readFile(String inputPath) throws IOException {
        Path path = Paths.get(inputPath).toAbsolutePath().normalize();
        return Files.readString(path);
    }

    @BeforeEach
    public void beforeEach() {

        testResourcePath = "src/test/resources/";
        fileJsonPath1 = testResourcePath + "fileNested1.json";
        fileJsonPath2 = testResourcePath + "fileNested2.json";

        fileYamlPath1 = testResourcePath + "fileNested1.yaml";
        fileYamlPath2 = testResourcePath + "fileNested2.json";

        resultStylishFilePath = testResourcePath + "resultStylish.txt";
        resultPlainFilePath = testResourcePath + "resultPlain.txt";
        resultJsonFilePath = testResourcePath + "resultJson.txt";
    }

    @Test
    public void testFileExists() {

        assertTrue(isFileExists(fileJsonPath1));
        assertTrue(isFileExists(fileJsonPath2));

        assertTrue(isFileExists(fileYamlPath1));
        assertTrue(isFileExists(fileYamlPath2));

        assertTrue(isFileExists(resultStylishFilePath));
        assertTrue(isFileExists(resultPlainFilePath));
        assertTrue(isFileExists(resultJsonFilePath));
    }

    @Test
    public void testDiffJsonFormatStylish() throws Exception {
        var expected = readFile(resultStylishFilePath);
        var actual = Differ.generate(fileJsonPath1, fileJsonPath2);
        assertEquals(expected, actual);
    }

    @Test
    public void testDiffYamlFormatStylish() throws Exception {
        var expected = readFile(resultStylishFilePath);
        var actual = Differ.generate(fileYamlPath1, fileYamlPath2);
        assertEquals(expected, actual);
    }

    @Test
    public void testDiffJsonFormatPlain() throws Exception {
        var expected = readFile(resultPlainFilePath);
        var actual = Differ.generate(fileJsonPath1, fileJsonPath2, "plain");
        assertEquals(expected, actual);
    }

    @Test
    public void testDiffYamlFormatPlain() throws Exception {
        var expected = readFile(resultPlainFilePath);
        var actual = Differ.generate(fileYamlPath1, fileYamlPath2, "plain");
        assertEquals(expected, actual);
    }

    @Test
    public void testDiffJsonFormatJson() throws Exception {
        var expected = readFile(resultJsonFilePath);
        var actual = Differ.generate(fileJsonPath1, fileJsonPath2, "json");
        assertEquals(expected, actual);
    }

    @Test
    public void testDiffYamlFormatJson() throws Exception {
        var expected = readFile(resultJsonFilePath);
        var actual = Differ.generate(fileYamlPath1, fileYamlPath2, "json");
        assertEquals(expected, actual);
    }

    @Test
    public void negativeTestFormat() {
        Exception exception = assertThrows(Exception.class, () -> {
            Differ.generate(fileYamlPath1, fileYamlPath2, "xml");
        });
        String expectedMessage = "Format file is unknown!";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    public void negativeTestExtensionFile() {
        var incorrectFileExt = "src/test/resources/fileNested1.yml";
        Exception exception = assertThrows(Exception.class, () -> {
            Differ.generate(fileYamlPath1, incorrectFileExt);
        });
        String expectedMessage = "Type of file is unknown!";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    public void negativeTestFileName() {
        Exception exception = assertThrows(Exception.class, () -> {
            Differ.generate("src/test/resources/file.json", fileYamlPath1);
        });
        String expectedMessage = "File " + Paths.get("src/test/resources/file.json")
                .toAbsolutePath().normalize() + " doesn't exists!";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }
}
