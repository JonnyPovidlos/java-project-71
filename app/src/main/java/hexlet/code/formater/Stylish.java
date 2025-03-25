package hexlet.code.formater;

import java.util.List;
import java.util.Map;

public class Stylish {
    public static String format(List<Map<String, Object>> diff) {
        var result = new StringBuilder("{");

        for (var entry : diff) {
            var typeOp = (String) entry.get("type");
            var field = (String) entry.get("field");
            switch (typeOp) {
                case "add" -> result.append(String.format("\n  + %s: %s", field, entry.get("value")));
                case "remove" -> result.append(String.format("\n  - %s: %s", field, entry.get("value")));
                case "change" -> result.append(String.format("\n  - %s: %s\n  + %s: %s",
                        field, entry.get("oldValue"), field, entry.get("newValue")));
                case "stable" -> result.append(String.format("\n    %s: %s", field, entry.get("value")));
                default -> { }
            }
        }
        result.append("\n}");
        return result.toString();
    }
}
