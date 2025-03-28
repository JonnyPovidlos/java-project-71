package hexlet.code;



public class Differ {
    public static String generate(String file1, String file2, String format) throws Exception {
        var fileExtension1 = file1.substring(file1.lastIndexOf(".") + 1);
        var fileExtension2 = file2.substring(file2.lastIndexOf(".") + 1);
        var mapFile1 = Parser.parse(FileUtils.readFile(file1), fileExtension1);
        var mapFile2 = Parser.parse(FileUtils.readFile(file2), fileExtension2);

        var diff = InnerPresent.compuite(mapFile1, mapFile2);
        return Formatter.format(diff, format);
    }

    public static String generate(String file1, String file2) throws Exception {
        return generate(file1, file2, "stylish");
    }


}
