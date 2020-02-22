package numberPlay.util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TopKNumbersData implements PersisterI, TopKNumbersResultsI {
	private List data = new ArrayList<List<Double>>();
	private File file;
	private BufferedWriter fileWriter;

	public TopKNumbersData(String inputFilePath) {
		file = new File(inputFilePath);
		try {
			this.fileWriter = new BufferedWriter(new FileWriter(file));

		} catch (IOException e) {
			e.printStackTrace();
			System.exit(0);
		} finally {

		}

	}

	@Override
	public void store(List<Double> topK) {
		this.data.add(topK);
	}

	@Override
	public void writeToFile() {

		for (int i = 0; i < data.size(); i++) {
			try {
				this.fileWriter.write(data.get(i).toString());
				this.fileWriter.write("\n");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public void close() {
		try {
			this.fileWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
