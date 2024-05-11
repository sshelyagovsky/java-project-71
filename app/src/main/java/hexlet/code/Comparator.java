package hexlet.code;

import hexlet.code.enums.Operation;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Comparator {

    public static HashMap<String, HashMap<String, Object>> compareFileContent(Map<String, Object> mapContent1,
                                 Map<String, Object> mapContent2, Set<String> uniqKeys) throws IOException {

        var compareResult = "";
        var mapDiffResult = new HashMap<String, HashMap<String, Object>>();

        for (var key : uniqKeys) {
            var val1 = mapContent1.get(key);
            var val2 = mapContent2.get(key);
            val1 = val1 == null ? "null" : val1.toString();
            val2 = val2 == null ? "null" : val2.toString();
            var values = new HashMap<String, Object>();

            if (mapContent1.containsKey(key) && mapContent2.containsKey(key)) {
                if (val1.equals(val2)) {
                    compareResult = String.valueOf(Operation.EQUAL);
                } else {
                    compareResult = String.valueOf(Operation.DIFFER);
                }
                values.put("compareResult", compareResult);
                values.put("value1", val1);
                values.put("value2", val2);
                mapDiffResult.put(key, values);
            } else if (mapContent1.containsKey(key)) {
                compareResult = String.valueOf(Operation.DELETE);
                values.put("compareResult", compareResult);
                values.put("value1", val1);
                mapDiffResult.put(key, values);
            } else {
                compareResult = String.valueOf(Operation.ADD);
                values.put("compareResult", compareResult);
                values.put("value2", val2);
                mapDiffResult.put(key, values);
            }
        }

        return mapDiffResult;
    }
}
