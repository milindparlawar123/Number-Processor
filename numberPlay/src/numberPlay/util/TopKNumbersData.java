package numberPlay.util;

import java.util.List;

public class TopKNumbersData implements PersisterI, TopKNumbersResultsI {
	@Override
	public void store(List<Double> topK) {}

	@Override
	public void writeToFile() {}
	
	@Override
	public void close() {}
}
