package hexlet.code;

import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

public class Differ {
    public static String generate(Map<String, Object> struct1, Map<String, Object> struct2) {
        System.out.println(struct1);
        System.out.println(struct2);
        StringBuilder result = new StringBuilder("{");
        List<String> sortedKeys = Stream.concat(struct1.keySet().stream(), struct2.keySet().stream())
                .distinct()
                .sorted()
                .toList();
        for (String key : sortedKeys) {
//            var contains1 = struct1.containsKey(key);
//            var contains2 = struct2.containsKey(key);
//            if (contains1 && !contains2) {
//                result.append("\n  - ").append(key);
//            }
            result.append("\n\t").append(key);
        }
        result.append("\n}");
        return result.toString();
    }
}
