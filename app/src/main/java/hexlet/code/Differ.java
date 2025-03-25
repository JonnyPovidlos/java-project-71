package hexlet.code;

import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

public class Differ {
    public static String generate(Map<String, Object> struct1, Map<String, Object> struct2) {

        StringBuilder result = new StringBuilder("{");
        List<String> sortedKeys = Stream.concat(struct1.keySet().stream(), struct2.keySet().stream())
                .distinct()
                .sorted()
                .toList();

        for (String key : sortedKeys) {
            if (!struct1.containsKey(key)) {
                result.append(String.format("\n  + %s: %s", key, struct2.get(key)));
            } else if (!struct2.containsKey(key)) {
                result.append(String.format("\n  - %s: %s", key, struct1.get(key)));

            } else if (!struct1.get(key).equals(struct2.get(key))) {
                result.append(String.format("\n  - %s: %s", key, struct1.get(key)));
                result.append(String.format("\n  + %s: %s", key, struct2.get(key)));

            } else {
                result.append(String.format("\n  %s: %s", key, struct1.get(key)));
            }
        }
        result.append("\n}");
        return result.toString();
    }
}
