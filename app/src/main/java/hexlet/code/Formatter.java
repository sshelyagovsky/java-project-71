package hexlet.code;

import hexlet.code.formatters.Plain;
import hexlet.code.formatters.Stylish;

import java.util.HashMap;
import java.util.Set;

public class Formatter {

    public static String formatFileContent(HashMap<String, HashMap<String, Object>> diffContentData,
                                           Set<String> uniqKeys, String format) throws Exception {
        if (format.equals("stylish")) {
            return Stylish.getStylishFormat(diffContentData, uniqKeys);
        } else if (format.equals("plain")) {
            return Plain.getPlainFormat(diffContentData, uniqKeys);
        } else {
            throw new Exception("Format file is unknown!");
        }
    }


}
