package hexlet.code.formatter;

import java.util.List;
import java.util.Map;

public class Stylish {
    private static final int SPACE_NUMBER = 4;

    public static String format(List<Map<String, Object>> diff) {
        var result = new StringBuilder("{");

        for (var entry : diff) {
            var typeOp = (String) entry.get("type");
            var field = (String) entry.get("field");

            switch (typeOp) {
                case "added" -> result.append(
                        String.format("\n%s+ %s: %s", " ".repeat(2), field, entry.get("value")));
                case "removed" -> result.append(
                        String.format("\n%s- %s: %s", " ".repeat(2), field, entry.get("value")));
                case "updated" -> result.append(
                        String.format("\n%s- %s: %s\n  + %s: %s", " ".repeat(2),
                                field, entry.get("oldValue"), field, entry.get("newValue")));
                case "stable" -> result.append(
                        String.format("\n%s%s: %s", " ".repeat(SPACE_NUMBER), field, entry.get("value")));
                default -> { }
            }
        }
        result.append("\n}");
        return result.toString();
    }
}
