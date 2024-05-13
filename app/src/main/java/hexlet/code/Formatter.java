package hexlet.code;

import hexlet.code.formatters.Plain;
import hexlet.code.formatters.Stylish;
import hexlet.code.formatters.Json;

import java.util.HashMap;
import java.util.Set;

public class Formatter {

    public static String formatFileContent(HashMap<String, HashMap<String, Object>> diffContentData,
                                           Set<String> uniqKeys, String format) throws Exception {
        return switch (format) {
            case "stylish" -> Stylish.getFormat(diffContentData, uniqKeys);
            case "plain" -> Plain.getFormat(diffContentData, uniqKeys);
            case "json" -> Json.getFormat(diffContentData, uniqKeys);
            default -> throw new Exception("Format file is unknown!");
        };
    }


}
