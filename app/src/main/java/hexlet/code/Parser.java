package hexlet.code;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

import java.util.Map;

public class Parser {

    public static Map<String, Object> parseContent(String contentFile, String extensionFile) throws Exception {

        Map<String, Object> mapContent;

        if (extensionFile.equals("json")) {
            ObjectMapper objectMapper = new ObjectMapper();
            mapContent = objectMapper.readValue(contentFile,
                    new TypeReference<Map<String, Object>>() { });
        } else if (extensionFile.equals("yaml")) {
            ObjectMapper objectMapper = new ObjectMapper(new YAMLFactory());
            mapContent = objectMapper.readValue(contentFile,
                    new TypeReference<Map<String, Object>>() { });
        } else {
            throw new Exception("Type of file is unknown!");
        }

        return mapContent;
    }
}
