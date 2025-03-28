package hexlet.code;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Stream;

public class InnerPresent {
    public static List<Map<String, Object>> compuite(Map<String, Object> mapFile1, Map<String, Object> mapFile2) {
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
