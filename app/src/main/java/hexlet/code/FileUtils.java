package hexlet.code;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;


public class FileUtils {
    public static Path getPath(String fileName) {
        return Paths.get(fileName).toAbsolutePath().normalize();
    }

    public static String readFile(String fileName) throws Exception {
        var path = getPath(fileName);
        return Files.readString(path).trim();
    }

}
