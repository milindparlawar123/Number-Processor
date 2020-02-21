package numberPlay.util;

import java.util.ArrayList;
import java.util.List;

public class RunningAverageData implements PersisterI, RunningAverageResultsI {
	List data= new ArrayList<Double>();
	@Override
	public void store(Double d) {
		//System.out.println(" milind >> " + d);
		this.data.add(d);
	}

	@Override
	public void writeToFile() {
		System.out.println(" RunningAverageData ");
		System.out.println(data);
	}

	@Override
	public void close() {}
}
