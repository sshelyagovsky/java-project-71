package hexlet.code.formatters;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.HashMap;
import java.util.Map;

public class Json {

    public static String getFormat(Map<String, HashMap<String, Object>> diffContentData)
            throws JsonProcessingException {
        ObjectMapper result = new ObjectMapper();
        return result.writeValueAsString(diffContentData);

    }
}
