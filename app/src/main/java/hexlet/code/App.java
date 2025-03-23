package hexlet.code;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;

import java.util.concurrent.Callable;

@Command(name = "gendiff", mixinStandardHelpOptions = true, version = "gendiff v0.1.0",
        description = "Compares two configuration files and shows a difference.")
public class App implements Callable<Integer> {
    @Override
    public Integer call() throws Exception {
        var mapFile1 = jsonToMap(readFile(filepath1));
        var mapFile2 = jsonToMap(readFile(filepath2));
        var result = Differ.generate(mapFile1, mapFile2);
        System.out.println(result);
        return 0;
    }

    @Option(names = {"-f", "--format"}, paramLabel = "format", description = "output format [default: stylish]")
    String format;

    @Parameters(index = "0", description = "path to first file", paramLabel = "filepath1")
    String filepath1;

    @Parameters(index = "1", description = "path to second file", paramLabel = "filepath2")
    String filepath2;

    private static Path getPath(String fileName) {
        return Paths.get(fileName).toAbsolutePath().normalize();
    }

    private static String readFile(String fileName) throws Exception {
        var path = getPath(fileName);
        return Files.readString(path).trim();
    }

    private static Map<String, Object> jsonToMap(String json) throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(json, new TypeReference<>() { });
    }

    public static void main(String[] args) {
        var exitCode = new CommandLine(new App()).execute(args);
        System.exit(exitCode);
    }
}
