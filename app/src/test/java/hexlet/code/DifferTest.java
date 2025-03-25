package hexlet.code;

import java.util.Map;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class DifferTest {

    public static String expectedValStylish;
    public static String expectedValPlain;
    public static Map<String, Object> file1;
    public static Map<String, Object> file2;

    private static String readFixture(String fileName) throws Exception {
        var path = Paths.get("src", "test", "resources", "fixtures", fileName).toAbsolutePath().normalize();
        return FileUtils.readFile(path.toString());
    }

    @BeforeAll
    public static void beforeAll() throws Exception {
        expectedValStylish = readFixture("result-stylish");
        expectedValPlain = readFixture("result-plain");
    }

    @Test
    public void testGenerateFromJson() throws Exception {

        file1 = Parser.parse(readFixture("file1.json"), ".json");
        file2 = Parser.parse(readFixture("file2.json"), ".json");

        var actual = Differ.generate(file1, file2);
        assertEquals(expectedValStylish, actual);

        actual = Differ.generate(file1, file2, "plain");
        assertEquals(expectedValPlain, actual);
    }

    @Test
    public void testGenerateFromYml() throws Exception {

        file1 = Parser.parse(readFixture("file1.yml"), ".yml");
        file2 = Parser.parse(readFixture("file2.yml"), ".yml");

        var actual = Differ.generate(file1, file2);
        assertEquals(expectedValStylish, actual);

        actual = Differ.generate(file1, file2, "plain");
        assertEquals(expectedValPlain, actual);
    }

    @Test
    public void testInvalidFormat() throws Exception {

        var exception = assertThrows(IllegalArgumentException.class, () -> Parser.parse("", ".ini"));

        assertEquals("Invalid format file", exception.getMessage());

    }


}
