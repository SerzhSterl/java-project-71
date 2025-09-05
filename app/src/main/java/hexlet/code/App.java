package hexlet.code;

import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;

import java.util.concurrent.Callable;

@Command(name = "genDiff", description = "Prints the difference between the data as a string.")
public class App implements Callable<Integer> {

	@Option(names = {"-h", "--help"}, usageHelp = true, description = "Show this help message and exit.")
	private boolean helpRequested;

	@Option(names = {"-V", "--version"}, versionHelp = true, description = "Print version information and exit.")
	private boolean versionRequested;

	public static void main(String[] args) {
		CommandLine commandLine = new CommandLine(new App());
		commandLine.execute(args);

		System.out.println("Hello, World!");
	}

	@Override
	public Integer call() throws Exception {
		return 0;
	}
}