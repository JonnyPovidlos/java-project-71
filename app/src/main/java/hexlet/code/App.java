package hexlet.code;

import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;


import java.util.concurrent.Callable;

@Command(name = "gendiff", mixinStandardHelpOptions = true, version = "gendiff v0.1.0",
        description = "Compares two configuration files and shows a difference.")
public class App implements Callable<Integer> {
    @Override
    public Integer call() throws Exception {
        var fileExtension1 = filepath1.substring(filepath1.lastIndexOf("."));
        var fileExtension2 = filepath2.substring(filepath1.lastIndexOf("."));
        var mapFile1 = Parser.parse(FileUtils.readFile(filepath1), fileExtension1);
        var mapFile2 = Parser.parse(FileUtils.readFile(filepath2), fileExtension2);
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




    public static void main(String[] args) {
        var exitCode = new CommandLine(new App()).execute(args);
        System.exit(exitCode);
    }
}
