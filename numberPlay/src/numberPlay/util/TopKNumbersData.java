package numberPlay.util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import numberPlay.util.Constants;

/**
 * @author Anand
 *below class is to store top K numbers and write numbers to output file and
 *close output file.
 */
public class TopKNumbersData implements PersisterI, TopKNumbersResultsI {
	/**
	 * data is store top k numbers
	 */
	private List data = new ArrayList<List<Double>>();
	private File file;
	private BufferedWriter fileWriter;

	/**
	 * @param inputFilePath incoming file name, this constructor will open inputFilePath file if not
	 *              present then it will create new file with name inputFilePath
	 */
	public TopKNumbersData(String inputFilePath) {
		file = new File(inputFilePath);
		try {
			this.fileWriter = new BufferedWriter(new FileWriter(file));

		} catch (IOException e) {
			System.err.println(Constants.ERROR_OPENING_FILE);
			e.printStackTrace();
			System.exit(0);
		} finally {

		}

	}

	/**
	 * @param topK is incoming double list
	 *below method is to add list of top k numbers in data
	 */
	@Override
	public void store(List<Double> topK) {
		this.data.add(topK);
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
				System.err.println(Constants.ERROR_WRITING_FILE);
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
			System.err.println(Constants.ERROR_CLOSING_FILE);
			e.printStackTrace();
			System.exit(0);
		}finally {
			
		}
	}
}
