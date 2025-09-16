package hexlet.code;

import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;

import java.util.concurrent.Callable;

@Command(name = "genDiff", description = "Prints the difference between the data as a string.")
public class App implements Callable<Integer> {

	@Parameters(index = "0", description = "path to first file")
	private String firstFilePath;

	@Parameters(index = "1", description = "path to second file")
	private String secondFilePath;

	@Option(names = {"-h", "--help"}, usageHelp = true, description = "Show this help message and exit.")
	private boolean helpRequested;

	@Option(names = {"-V", "--version"}, versionHelp = true, description = "Print version information and exit.")
	private boolean versionRequested;

	@Option(names = {"-f", "--format"}, defaultValue = "stylish",
			description = "output format [default: stylish]")
	private String formatFile;

	public static void main(String[] args) {
		CommandLine commandLine = new CommandLine(new App());
		commandLine.execute(args);
	}

	@Override
	public Integer call() throws Exception {
		String result = Differ.generate(firstFilePath, secondFilePath);
		System.out.println(result);
		return 0;
	}
}