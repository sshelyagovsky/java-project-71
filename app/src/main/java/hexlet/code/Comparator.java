package hexlet.code;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import static hexlet.code.Differ.TYPE_DIFF_EQUAL;
import static hexlet.code.Differ.TYPE_DIFF_DIFFER;
import static hexlet.code.Differ.TYPE_DIFF_DELETE;
import static hexlet.code.Differ.TYPE_DIFF_ADD;

public class Comparator {
    public static String compareFileContent(Map<String, Object> mapContent1,
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
        return Differ.getFormattedData(mapDiffResult, uniqKeys);
    }
}
