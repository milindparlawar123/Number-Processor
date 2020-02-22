package numberPlay.observer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.PriorityQueue;

import numberPlay.util.PersisterI;
import numberPlay.util.TopKNumbersData;
import numberPlay.util.TopKNumbersResultsI;

public class TopKNumbersObserver implements ObserverI {
	private int windowSize;
	PriorityQueue<Double> priorityQueue;
	private TopKNumbersResultsI topKNumbersData;
	private PersisterI topKNumbersDataWrite;

	public TopKNumbersObserver(int windowSize, String inputFilePath) {
		super();
		this.windowSize = windowSize;
		this.priorityQueue = new PriorityQueue<Double>();
		this.topKNumbersData = new TopKNumbersData(inputFilePath);
		this.topKNumbersDataWrite = (PersisterI) this.topKNumbersData;
	}

	@Override
	public void update(Number n) {
		if (n == null) {
			this.topKNumbersDataWrite.writeToFile();
			this.topKNumbersDataWrite.close();
		} else {
			this.priorityQueue.add(n.doubleValue());
			if (this.priorityQueue.size() == windowSize + 1) {
				priorityQueue.remove();
			}
			this.topKNumbersData.store(getListDscOrder(this.priorityQueue));
		}

	}

	public List<Double> getListDscOrder(PriorityQueue<Double> pq) {
		List ls = new ArrayList<Double>();
		Iterator value = pq.iterator();
		while (value.hasNext()) {

			ls.add(Double.parseDouble(String.format("%.2f", value.next())));
		}
		Collections.sort(ls, Collections.reverseOrder());
		return ls;

	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((priorityQueue == null) ? 0 : priorityQueue.hashCode());
		result = prime * result + ((topKNumbersData == null) ? 0 : topKNumbersData.hashCode());
		result = prime * result + ((topKNumbersDataWrite == null) ? 0 : topKNumbersDataWrite.hashCode());
		result = prime * result + windowSize;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TopKNumbersObserver other = (TopKNumbersObserver) obj;
		if (priorityQueue == null) {
			if (other.priorityQueue != null)
				return false;
		} else if (!priorityQueue.equals(other.priorityQueue))
			return false;
		if (topKNumbersData == null) {
			if (other.topKNumbersData != null)
				return false;
		} else if (!topKNumbersData.equals(other.topKNumbersData))
			return false;
		if (topKNumbersDataWrite == null) {
			if (other.topKNumbersDataWrite != null)
				return false;
		} else if (!topKNumbersDataWrite.equals(other.topKNumbersDataWrite))
			return false;
		if (windowSize != other.windowSize)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "TopKNumbersObserver [windowSize=" + windowSize + ", priorityQueue=" + priorityQueue
				+ ", topKNumbersData=" + topKNumbersData + ", topKNumbersDataWrite=" + topKNumbersDataWrite + "]";
	}
	
}
