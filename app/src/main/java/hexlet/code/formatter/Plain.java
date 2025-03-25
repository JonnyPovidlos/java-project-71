package hexlet.code.formatter;

import java.util.List;
import java.util.Map;

public class Plain {
    public static String format(List<Map<String, Object>> diff) {
        var result = new StringBuilder();
        for (var entry : diff) {
            var typeOp = (String) entry.get("type");
            var field = (String) entry.get("field");
            switch (typeOp) {
                case "add" -> result.append(String.format("Property '%s' was added with value: %s\n",
                        field, getValue(entry.get("value"))));
                case "remove" -> result.append(String.format("Property '%s' was removed\n", field));
                case "change" -> result.append(String.format("Property '%s' was updated. From %s to %s\n",
                        field, getValue(entry.get("oldValue")), getValue(entry.get("newValue"))));
                default -> { }
            }
        }
        return result.toString().trim();
    }

    private static String getValue(Object obj) {
        if (obj instanceof String) {
            return String.format("'%s'", obj);
        } else if (obj instanceof Map || obj instanceof List) {
            return "[complex value]";
        }
        return String.valueOf(obj);
    }
}
