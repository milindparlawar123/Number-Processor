package numberPlay.driver;

import java.io.IOException;
import java.nio.file.InvalidPathException;

import numberPlay.Filter.FilterI;
import numberPlay.Filter.FloatingFilter;
import numberPlay.Filter.IntegerFilter;
import numberPlay.Filter.ProcessCompleteFilter;
import numberPlay.observer.NumberAverageObserver;
import numberPlay.observer.NumberPeaksObserver;
import numberPlay.observer.ObserverI;
import numberPlay.observer.TopKNumbersObserver;
import numberPlay.subject.NumberProcessor;
import numberPlay.subject.SubjectI;
import numberPlay.util.Constants;
import numberPlay.util.FileProcessor;
import numberPlay.validator.DriverValidator;

/**
 * @author John Doe TODO update the author name.
 */
public class Driver {
	public static void main(String[] args) {

		/*
		 * As the build.xml specifies the arguments as argX, in case the argument value
		 * is not given java takes the default value specified in build.xml. To avoid
		 * that, below condition is used FIXME Refactor commandline validation using the
		 * validation design taught in class.
		 */

		// below try block to validate arguments
		try {
			new DriverValidator(args.length, args);
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(0);
		} finally {

		}
		/*
		 * final int REQUIRED_NUMBER_OF_ARGS = 6; if ((args.length !=
		 * REQUIRED_NUMBER_OF_ARGS) || (args[0].equals("${inputNumStream}")) ||
		 * (args[1].equals("${runAvgWindowSize}")) ||
		 * (args[2].equals("${runAvgOutFile}")) || (args[3].equals("${k}")) ||
		 * (args[4].equals("${topKNumOutFile}")) ||
		 * (args[5].equals("${numPeaksOutFile}"))) {
		 * 
		 * System.err.
		 * printf("Error: Incorrect number of arguments. Program accepts %d arguments.",
		 * REQUIRED_NUMBER_OF_ARGS); System.exit(0); }
		 */

		FilterI integerFilter = new IntegerFilter();
		FilterI floatingFilter = new FloatingFilter();
		FilterI processCompleteFilter = new ProcessCompleteFilter();
		SubjectI numberProcessor = new NumberProcessor();
		ObserverI numberAverageO;
		try {
			numberAverageO = new NumberAverageObserver(3, args[2]);
			ObserverI numberPeaksO = new NumberPeaksObserver(args[5]);
			ObserverI topKNumbersO = new TopKNumbersObserver(3, args[4]);
			numberProcessor.register(numberAverageO, integerFilter);
			numberProcessor.register(numberPeaksO, integerFilter);
			numberProcessor.register(topKNumbersO, integerFilter);
			numberProcessor.register(numberPeaksO, floatingFilter);
			numberProcessor.register(topKNumbersO, floatingFilter);
			numberProcessor.register(numberAverageO, processCompleteFilter);
			numberProcessor.register(numberPeaksO, processCompleteFilter);
			numberProcessor.register(topKNumbersO, processCompleteFilter);
		} catch (Exception e2) {
			System.err.println(Constants.ERROR_IN_REGISTER_OBSERVERS);
			e2.printStackTrace();
			System.exit(0);
		} finally {

		}
		try {
			FileProcessor fileProcessor = new FileProcessor(args[0]);
			String numberStr = null;
			Number num = null;
			while ((numberStr = fileProcessor.poll()) != null) {
				try {
					num = Integer.parseInt(numberStr);
					numberProcessor.process(num, Enum.INTEGER_EVENT);
				} catch (NumberFormatException e1) {
					try {
						num = Float.parseFloat(numberStr);
						numberProcessor.process(num, Enum.FLOATING_POINT_EVENT);
					} catch (NumberFormatException e) {
						System.err.println(Constants.ERROR_INVALID_NUMBER);
						e.printStackTrace();
					} finally {

					}
				}
			}
			if (numberStr == null) {
				fileProcessor.close();
				num = null;
				numberProcessor.process(num, Enum.PROCESSING_COMPLETE);
			}

		} catch (InvalidPathException | SecurityException | IOException e2) {
			System.err.println(Constants.ERROR);
			e2.printStackTrace();
			System.exit(0);
		} finally {

		}

	}
}

enum Enum {
	INTEGER_EVENT, FLOATING_POINT_EVENT, PROCESSING_COMPLETE
}