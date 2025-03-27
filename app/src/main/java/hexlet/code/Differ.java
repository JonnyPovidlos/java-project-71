package hexlet.code;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.util.Objects;
import java.util.stream.Stream;

public class Differ {
    public static String generate(String file1, String file2, String format) throws Exception {
        var fileExtension1 = file1.substring(file1.lastIndexOf("."));
        var fileExtension2 = file2.substring(file2.lastIndexOf("."));
        var mapFile1 = Parser.parse(FileUtils.readFile(file1), fileExtension1);
        var mapFile2 = Parser.parse(FileUtils.readFile(file2), fileExtension2);

        var diff = compuite(mapFile1, mapFile2);
        return Formatter.format(diff, format);
    }

    public static String generate(String file1, String file2) throws Exception {
        return generate(file1, file2, "stylish");
    }

    private static List<Map<String, Object>> compuite(Map<String, Object> mapFile1, Map<String, Object> mapFile2) {
        List<String> sortedKeys = Stream.concat(mapFile1.keySet().stream(), mapFile2.keySet().stream())
                .distinct()
                .sorted()
                .toList();

        List<Map<String, Object>> diff = new ArrayList<>();


        for (String key : sortedKeys) {
            Map<String, Object> item = new HashMap<>();
            item.put("field", key);

            if (!mapFile1.containsKey(key)) {
                item.put("type", "added");
                item.put("value", mapFile2.get(key));
            } else if (!mapFile2.containsKey(key)) {
                item.put("type", "removed");
                item.put("value", mapFile1.get(key));
            } else if (!Objects.equals(mapFile1.get(key), mapFile2.get(key))) {
                item.put("type", "updated");
                item.put("oldValue", mapFile1.get(key));
                item.put("newValue", mapFile2.get(key));
            } else {
                item.put("type", "stable");
                item.put("value", mapFile1.get(key));
            }

            diff.add(item);
        }
        return diff;
    }
}
