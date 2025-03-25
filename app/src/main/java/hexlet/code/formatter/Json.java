package hexlet.code.formatter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.util.List;
import java.util.Map;

public class Json {
    public static String format(List<Map<String, Object>> diff) {
        var mapper = new ObjectMapper();
        mapper.enable(SerializationFeature.INDENT_OUTPUT);

        try {
            return mapper.writeValueAsString(diff);
        } catch (Exception exception) {
            throw new RuntimeException("Error to write json");
        }

    }
}
