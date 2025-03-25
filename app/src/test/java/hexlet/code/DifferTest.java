package hexlet.code;

import java.util.Map;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DifferTest {

    public static String expectedVal;
    public static Map<String, Object> file1;
    public static Map<String, Object> file2;

    private static String readFixture(String fileName) throws Exception {
        var path = Paths.get("src", "test", "resources", "fixtures", fileName).toAbsolutePath().normalize();
        return FileUtils.readFile(path.toString());
    }

    @BeforeAll
    public static void beforeAll() throws Exception {
        expectedVal = readFixture("test1-json");
        file1 = FileUtils.jsonToMap(readFixture("file1.json"));
        file2 = FileUtils.jsonToMap(readFixture("file2.json"));
    }

    @Test
    public void testGenerateFromJson() {

        var actual = Differ.generate(file1, file2);

        assertEquals(expectedVal, actual);
    }


}
