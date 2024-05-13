package hexlet.code.formatters;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

public class Json {

    public static String getFormat(Map<String, HashMap<String, Object>> diffContentData,
                                          Set<String> uniqKeys) throws JsonProcessingException {
        ObjectMapper result = new ObjectMapper();
        Map<String, Object> data = new LinkedHashMap<>();
        for (var key : uniqKeys) {
            var values = diffContentData.get(key);
            if (values.get("compareResult").equals("EQUAL")) {
                data.put("  " + key, values.get("value1"));
            } else if (values.get("compareResult").equals("DIFFER")) {
                data.put("- " + key, values.get("value1"));
                data.put("+ " + key, values.get("value2"));
            } else if (values.get("compareResult").equals("DELETE")) {
                data.put("- " + key, values.get("value1"));
            } else {
                data.put("+ " + key, values.get("value2"));
            }
        }

        return result.writeValueAsString(data);
    }
}
