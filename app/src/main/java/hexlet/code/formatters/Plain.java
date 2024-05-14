package hexlet.code.formatters;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Plain {
    public static String getFormat(Map<String, HashMap<String, Object>> diffContentData,
                                        Set<String> uniqKeys) {

        StringBuilder str = new StringBuilder();
        for (var key : uniqKeys) {
            var values = diffContentData.get(key);
            if (values.get("compareResult").equals("DIFFER")) {
                str.append("Property '").append(key).append("' was updated. From ")
                        .append(checkComplex(values.get("value1")))
                        .append(" to ").append(checkComplex(values.get("value2"))).append("\n");
            } else if (values.get("compareResult").equals("DELETE")) {
                str.append("Property '").append(key).append("' was removed").append("\n");
            } else if (values.get("compareResult").equals("ADD")) {
                str.append("Property '").append(key).append("' was added with value: ")
                        .append(checkComplex(values.get("value2"))).append("\n");
            }
        }

        return str.toString().trim();
    }

    public static String checkComplex(Object value) {
        String convertStr;
        if (value instanceof List || value instanceof Map) {
            convertStr = "[complex value]";
        } else if (value instanceof String) {
            convertStr = "'" + value + "'";
        } else {
            convertStr = String.valueOf(value);
        }
        return convertStr;
    }
}
