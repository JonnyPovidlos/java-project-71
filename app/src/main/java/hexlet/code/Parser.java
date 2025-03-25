package hexlet.code;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;

import java.util.Map;

public class Parser {

    public static Map<String, Object> parse(String content, String fileExtension) throws Exception {
        ObjectMapper mapper;
        if (fileExtension.endsWith(".json")) {
            mapper = new ObjectMapper();
        } else if (fileExtension.endsWith(".yml") || fileExtension.endsWith(".yaml")) {
            mapper = new YAMLMapper();
        } else {
            throw new IllegalArgumentException("Invalid format file");
        }
        return mapper.readValue(content, new TypeReference<>() { });
    }
}
