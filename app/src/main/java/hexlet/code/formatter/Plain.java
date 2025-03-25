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
                case "added" -> result.append(String.format("Property '%s' was %s with value: %s\n",
                        field, typeOp, getValue(entry.get("value"))));
                case "removed" -> result.append(String.format("Property '%s' was %s\n", field, typeOp));
                case "updated" -> result.append(String.format("Property '%s' was %s. From %s to %s\n",
                        field, typeOp, getValue(entry.get("oldValue")), getValue(entry.get("newValue"))));
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
