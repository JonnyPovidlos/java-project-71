package hexlet.code;

import hexlet.code.formatter.Plain;
import hexlet.code.formatter.Stylish;
import hexlet.code.formatter.Json;

import java.util.List;
import java.util.Map;

public class Formatter {
    public static String format(List<Map<String, Object>> diff, String format) {
        return switch (format) {
            case "stylish" -> Stylish.format(diff);
            case "plain" -> Plain.format(diff);
            case "json" -> Json.format(diff);
            default -> throw new IllegalArgumentException("invalid format");
        };
    }
}
