package hexlet.code;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Differ {

    static final String TYPE_DIFF_DELETE = "DELETE";
    static final String TYPE_DIFF_ADD = "ADD";
    static final String TYPE_DIFF_EQUAL = "EQUAL";
    static final String TYPE_DIFF_DIFFER = "DIFFER";

    public static String generate(String filePath1, String filePath2) throws Exception {

        Map<String, Object> mapContentFile1 = readFileContent(filePath1);
        Map<String, Object> mapContentFile2 = readFileContent(filePath2);

        return Comparator.compareFileContent(mapContentFile1, mapContentFile2);
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

    public static String getFormattedData(Map<String, HashMap<String, Object>> mapDiffResult,
                                          Set<String> uniqKeys) {

        StringBuilder str = new StringBuilder("{\n");
        for (var key : uniqKeys) {
            var values = mapDiffResult.get(key);
            if (values.get("compareResult").equals(TYPE_DIFF_EQUAL)) {
                str.append("  ").append("  ").append(key).append(": ").append(values.get("value1")).append("\n");
            } else if (values.get("compareResult").equals(TYPE_DIFF_DIFFER)) {
                str.append("  ").append("- ").append(key).append(": ").append(values.get("value1")).append("\n");
                str.append("  ").append("+ ").append(key).append(": ").append(values.get("value2")).append("\n");
            } else if (values.get("compareResult").equals(TYPE_DIFF_DELETE)) {
                str.append("  ").append("- ").append(key).append(": ").append(values.get("value1")).append("\n");
            } else {
                str.append("  ").append("+ ").append(key).append(": ").append(values.get("value2")).append("\n");
            }
        }
        str.append("}");
        return str.toString();
    }
}
