package numberPlay.util;

import java.util.ArrayList;
import java.util.List;

public class NumberPeaksData implements PersisterI, NumberPeaksResultsI {
	List data = new ArrayList<Double>();

	@Override
	public void store(Double d) {
		this.data.add(d);
	}

	@Override
	public void writeToFile() {
		System.out.println( " NumberPeaksData ");
		System.out.println(this.data);
	}

	@Override
	public void close() {
	}
}
