package numberPlay.util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Milind
 * below class is to store running average of numbers and to write numbers
 * to output file and close output file.
 *
 */
public class RunningAverageData implements PersisterI, RunningAverageResultsI {
	/**
	 * data is store running average of numbers
	 */
	List data = new ArrayList<Double>();
	private File file;
	private BufferedWriter fileWriter;

	/**
	 * @param inputFilePath incoming file name, this constructor will open inputFilePath file if not
	 *              present then it will create new file with name inputFilePath
	 */
	public RunningAverageData(String inputFilePath) {
		file = new File(inputFilePath);
		try {
			this.fileWriter = new BufferedWriter(new FileWriter(file));

		} catch (IOException e) {
			e.printStackTrace();
			System.exit(0);
		} finally {

		}

	}

	/**
	 * @param d is incoming number
	 *below method is to add running average in data
	 */
	@Override
	public void store(Double d) {
		this.data.add(d);

	}

	/**
	 *to write numbers to output file
	 */
	@Override
	public void writeToFile() {

		for (int i = 0; i < data.size(); i++) {
			try {
				this.fileWriter.write(data.get(i).toString());
				this.fileWriter.write("\n");
			} catch (IOException e) {
				e.printStackTrace();
				System.exit(0);
			}finally{}
		}

	}

	/**
	 *below method is to close output file
	 */
	@Override
	public void close() {
		try {
			this.fileWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(0);
		}
		finally{}
	}
}
