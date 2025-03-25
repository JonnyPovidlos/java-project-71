package hexlet.code;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.util.Objects;
import java.util.stream.Stream;

public class Differ {
    public static String generate(Map<String, Object> struct1, Map<String, Object> struct2, String format) {

        List<String> sortedKeys = Stream.concat(struct1.keySet().stream(), struct2.keySet().stream())
                .distinct()
                .sorted()
                .toList();

        List<Map<String, Object>> diff = new ArrayList<>();


        for (String key : sortedKeys) {
            Map<String, Object> item = new HashMap<>();
            item.put("field", key);

            if (!struct1.containsKey(key)) {
                item.put("type", "add");
                item.put("value", struct2.get(key));
            } else if (!struct2.containsKey(key)) {
                item.put("type", "remove");
                item.put("value", struct1.get(key));
            } else if (!Objects.equals(struct1.get(key), struct2.get(key))) {
                item.put("type", "change");
                item.put("oldValue", struct1.get(key));
                item.put("newValue", struct2.get(key));
            } else {
                item.put("type", "stable");
                item.put("value", struct1.get(key));
            }

            diff.add(item);
        }
        return Formatter.format(diff, format);
    }

    public static String generate(Map<String, Object> struct1, Map<String, Object> struct2) {
        return generate(struct1, struct2, "stylish");
    }
}
