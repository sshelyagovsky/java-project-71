package hexlet.code.formatters;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Stylish {

    public static String getStylishFormat(Map<String, HashMap<String, Object>> diffContentData,
                                          Set<String> uniqKeys) {

        StringBuilder str = new StringBuilder("{\n");
        for (var key : uniqKeys) {
            var values = diffContentData.get(key);
            if (values.get("compareResult").equals("EQUAL")) {
                str.append("    ").append(key).append(": ").append(values.get("value1")).append("\n");
            } else if (values.get("compareResult").equals("DIFFER")) {
                str.append("  - ").append(key).append(": ").append(values.get("value1")).append("\n");
                str.append("  + ").append(key).append(": ").append(values.get("value2")).append("\n");
            } else if (values.get("compareResult").equals("DELETE")) {
                str.append("  - ").append(key).append(": ").append(values.get("value1")).append("\n");
            } else {
                str.append("  + ").append(key).append(": ").append(values.get("value2")).append("\n");
            }
        }
        str.append("}");

        return str.toString();
    }
}
