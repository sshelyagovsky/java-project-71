package hexlet.code;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

public class Differ {

    private static final String TYPE_DIFF_DELETE = "DELETE";
    private static final String TYPE_DIFF_ADD = "ADD";
    private static final String TYPE_DIFF_EQUAL = "EQUAL";
    private static final String TYPE_DIFF_DIFFER = "DIFFER";

    public static String generate(String filePath1, String filePath2) throws Exception {

        String contentFile1 = readFileContent(filePath1);
        String contentFile2 = readFileContent(filePath2);

        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, Object> mapContentFile1 = objectMapper.readValue(contentFile1,
                new TypeReference<Map<String, Object>>() { });
        Map<String, Object> mapContentFile2 = objectMapper.readValue(contentFile2,
                new TypeReference<Map<String, Object>>() { });

        return getDiff(mapContentFile1, mapContentFile2);
    }

    public static String readFileContent(String filePath) throws Exception {
        Path path = Paths.get(filePath).toAbsolutePath().normalize();

        if (!Files.exists(path)) {
            throw new Exception("File " + path + " doesn't exists!");
        }

        return Files.readString(path);
    }

    public static String getDiff(Map<String, Object> mapContent1,
                                 Map<String, Object> mapContent2) {

        Set<String> uniqKeys = new TreeSet<>(mapContent1.keySet());
        uniqKeys.addAll(mapContent2.keySet());

        var compareResult = "";
        var mapDiffResult = new HashMap<String, HashMap<String, Object>>();

        for (var key : uniqKeys) {
            var val1 = mapContent1.get(key);
            var val2 = mapContent2.get(key);
            var values = new HashMap<String, Object>();

            if (mapContent1.containsKey(key) && mapContent2.containsKey(key)) {
                if (val1.equals(val2)) {
                    compareResult = TYPE_DIFF_EQUAL;
                } else {
                    compareResult = TYPE_DIFF_DIFFER;
                }
                values.put("compareResult", compareResult);
                values.put("value1", val1);
                values.put("value2", val2);
                mapDiffResult.put(key, values);
            } else if (mapContent1.containsKey(key)) {
                compareResult = TYPE_DIFF_DELETE;
                values.put("compareResult", compareResult);
                values.put("value1", val1);
                mapDiffResult.put(key, values);
            } else {
                compareResult = TYPE_DIFF_ADD;
                values.put("compareResult", compareResult);
                values.put("value2", val2);
                mapDiffResult.put(key, values);
            }
        }
        return getFormattedData(mapDiffResult, uniqKeys);
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
