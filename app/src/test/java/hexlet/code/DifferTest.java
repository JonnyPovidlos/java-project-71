package hexlet.code;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class DifferTest {

    public static String expectedValStylish;
    public static String expectedValPlain;
    public static String expectedValJson;
    public static String file1;
    public static String file2;

    private static String readFixture(String fileName) throws Exception {
        return FileUtils.readFile(getFixturePath(fileName));
    }

    private static String getFixturePath(String fileName) {
        return Paths.get("src", "test", "resources", "fixtures", fileName).toAbsolutePath().normalize().toString();
    }

    @BeforeAll
    public static void beforeAll() throws Exception {
        expectedValStylish = readFixture("result-stylish");
        expectedValPlain = readFixture("result-plain");
        expectedValJson = readFixture("result-json");
    }

    @Test
    public void testGenerateFromJson() throws Exception {

        file1 = getFixturePath("file1.json");
        file2 = getFixturePath("file2.json");

        var actual = Differ.generate(file1, file2);
        assertEquals(expectedValStylish, actual);

        actual = Differ.generate(file1, file2, "plain");
        assertEquals(expectedValPlain, actual);

        actual = Differ.generate(file1, file2, "json");
        assertEquals(expectedValJson, actual);
    }

    @Test
    public void testGenerateFromYml() throws Exception {

        file1 = getFixturePath("file1.yml");
        file2 = getFixturePath("file2.yml");

        var actual = Differ.generate(file1, file2);
        assertEquals(expectedValStylish, actual);

        actual = Differ.generate(file1, file2, "plain");
        assertEquals(expectedValPlain, actual);

        actual = Differ.generate(file1, file2, "json");
        assertEquals(expectedValJson, actual);
    }

    @Test
    public void testInvalidFormat() {

        var exception = assertThrows(IllegalArgumentException.class, () -> Parser.parse("", ".ini"));

        assertEquals("Invalid format file", exception.getMessage());

    }


}
