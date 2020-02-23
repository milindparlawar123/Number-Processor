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
import numberPlay.util.FileProcessor;

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
		final int REQUIRED_NUMBER_OF_ARGS = 6;
		if ((args.length != REQUIRED_NUMBER_OF_ARGS) || (args[0].equals("${inputNumStream}"))
				|| (args[1].equals("${runAvgWindowSize}")) || (args[2].equals("${runAvgOutFile}"))
				|| (args[3].equals("${k}")) || (args[4].equals("${topKNumOutFile}"))
				|| (args[5].equals("${numPeaksOutFile}"))) {

			System.err.printf("Error: Incorrect number of arguments. Program accepts %d arguments.",
					REQUIRED_NUMBER_OF_ARGS);
			// System.exit(0);
		}

		// FIXME Create an instance of each of the classes implementing PersisterI and
		// the
		// corresponding ResultsI interface.
		// Observers use these objects to dump data to be stored and eventually
		// persisted
		// to the corresponding output file.

		// FIXME Instantiate the subject.
		FilterI integerFilter = new IntegerFilter();
		FilterI floatingFilter = new FloatingFilter();
		FilterI processCompleteFilter = new ProcessCompleteFilter();
		SubjectI numberProcessor = new NumberProcessor();
		ObserverI numberAverageO;
		try {
			numberAverageO = new NumberAverageObserver(3, "numAvg.txt");
			ObserverI numberPeaksO = new NumberPeaksObserver("numPeaks.txt");
			ObserverI topKNumbersO = new TopKNumbersObserver(3, "topKnum.txt");
			numberProcessor.register(numberAverageO, integerFilter);
			numberProcessor.register(numberPeaksO, integerFilter);
			numberProcessor.register(topKNumbersO, integerFilter);
			numberProcessor.register(numberPeaksO, floatingFilter);
			numberProcessor.register(topKNumbersO, floatingFilter);
			numberProcessor.register(numberAverageO, processCompleteFilter);
			numberProcessor.register(numberPeaksO, processCompleteFilter);
			numberProcessor.register(topKNumbersO, processCompleteFilter);
		} catch (Exception e2) {
			e2.printStackTrace();
			System.exit(0);
		}
		finally {
			
		}
		try {
			FileProcessor fileProcessor = new FileProcessor("input.txt");
			String numberStr = null;
			Number num = null;
			while ((numberStr=fileProcessor.poll()) != null) {
				try {
					num = Integer.parseInt(numberStr);
					numberProcessor.process(num, Enum.INTEGER_EVENT);
				} catch (NumberFormatException e1) {
					try {
						num = Float.parseFloat(numberStr);
						numberProcessor.process(num, Enum.FLOATING_POINT_EVENT);
					} catch (NumberFormatException e) {
						//input number is not valid
						e.printStackTrace();
					}finally {
						
					}
					
				}
			}
			if (numberStr == null) {
				fileProcessor.close();
				num = null;;
				numberProcessor.process(num, Enum.PROCESSING_COMPLETE);
			}

		} catch (InvalidPathException | SecurityException | IOException e2) {
			e2.printStackTrace();
			System.exit(0);
		}finally{
			
		}


		// FIXME Instantiate the observers, providing the necessary filter and the
		// results object.

		// FIXME Register each observer with the subject for the necessary set of
		// events.

		// FIXME Delegate control to a separate utility class/method that provides
		// numbers one at a time, from the FileProcessor,
		// to the subject.
	}
}

enum Enum {
	INTEGER_EVENT, FLOATING_POINT_EVENT, PROCESSING_COMPLETE
}