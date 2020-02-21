package numberPlay.util;

import java.util.ArrayList;
import java.util.List;

public class TopKNumbersData implements PersisterI, TopKNumbersResultsI {
	private List data = new ArrayList<List<Double>>();
	
	@Override
	public void store(List<Double> topK) {
		this.data.add(topK);
	}

	@Override
	public void writeToFile() {
		System.out.println(" TopKNumbersData ");
		for(int i = 0; i < data.size(); i++)
		{
		    System.out.println(data.get(i));
		    System.out.println();
		}
	}

	@Override
	public void close() {
	}
}
