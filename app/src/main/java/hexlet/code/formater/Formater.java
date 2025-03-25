package hexlet.code.formater;

import java.util.List;
import java.util.Map;

public class Formater {
    public static String format(List<Map<String, Object>> diff, String format) {
        return switch (format) {
            case "stylish" -> Stylish.format(diff);
            default -> "";
        };
    }
}
