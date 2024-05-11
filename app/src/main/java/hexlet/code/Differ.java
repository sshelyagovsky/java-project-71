package hexlet.code;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

public class Differ {

    public static String generate(String filePath1, String filePath2) throws Exception {
        return generate(filePath1, filePath2, "stylish");
    }

    public static String generate(String filePath1, String filePath2, String format) throws Exception {

        Map<String, Object> mapContentFile1 = readFileContent(filePath1);
        Map<String, Object> mapContentFile2 = readFileContent(filePath2);

        Set<String> uniqKeys = getUniqKeys(mapContentFile1, mapContentFile2);
        var diffContentData = Comparator.compareFileContent(mapContentFile1, mapContentFile2, uniqKeys);
        return Formatter.formatFileContent(diffContentData, uniqKeys, format);
    }

    public static Map<String, Object> readFileContent(String filePath) throws Exception {
        Path normalizePath = Paths.get(filePath).toAbsolutePath().normalize();

        if (!Files.exists(normalizePath)) {
            throw new Exception("File " + normalizePath + " doesn't exists!");
        }
        var extensionFile = getExtensionFile(normalizePath).toLowerCase();
        var contentFile = Files.readString(normalizePath);

        return Parser.parseContent(contentFile, extensionFile);
    }

    public static String getExtensionFile(Path path) {
        String fileName = path.getFileName().toString();
        int indexDotFile1 = fileName.indexOf('.');
        return fileName.substring(indexDotFile1 + 1);
    }

    public static Set<String> getUniqKeys(Map<String, Object> mapContent1, Map<String, Object> mapContent2) {
        Set<String> uniqKeys = new TreeSet<>(mapContent1.keySet());
        uniqKeys.addAll(mapContent2.keySet());
        return uniqKeys;
    }

}
