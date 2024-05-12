package hexlet.code;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

public class Comparator {

    public static HashMap<String, HashMap<String, Object>> compareFileContent(Map<String, Object> content1,
                                 Map<String, Object> content2, Set<String> uniqKeys) {

        var mapDiffResult = new HashMap<String, HashMap<String, Object>>();
        for (var key : uniqKeys) {
            var values = new HashMap<String, Object>();
            if (content1.containsKey(key) && !content2.containsKey(key)) {
                values.put("compareResult", "DELETE");
                values.put("value1", content1.get(key));
                mapDiffResult.put(key, values);
            } else if (!content1.containsKey(key) && content2.containsKey(key)) {
                values.put("compareResult", "ADD");
                values.put("value2", content2.get(key));
                mapDiffResult.put(key, values);
            } else if (Objects.equals(content1.get(key), (content2.get(key)))) {
                values.put("compareResult", "EQUAL");
                values.put("value1", content1.get(key));
                mapDiffResult.put(key, values);
            } else {
                values.put("compareResult", "DIFFER");
                values.put("value1", content1.get(key));
                values.put("value2", content2.get(key));
                mapDiffResult.put(key, values);
            }
        }

        return mapDiffResult;
    }
}
