package numberPlay.util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Milind
 * below class is to store peak numbers and also to write numbers to output
 * file and to close file.
 *
 */
public class NumberPeaksData implements PersisterI, NumberPeaksResultsI {
	/**
	 * data is store peak numbers
	 */
	List data = new ArrayList<Double>();
	private File file;
	private BufferedWriter fileWriter;

	/**
	 * @param inputFilePath incoming file name, this constructor will open inputFilePath file if not
	 *              present then it will create new file with name inputFilePath
	 */
	public NumberPeaksData(String inputFilePath) {
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
	 *store method is to add number in ArrayList
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
			}finally {
				
			}
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
		}finally {
			
		}
	}
}
