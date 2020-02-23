package numberPlay.observer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.PriorityQueue;

import numberPlay.util.Constants;
import numberPlay.util.PersisterI;
import numberPlay.util.TopKNumbersData;
import numberPlay.util.TopKNumbersResultsI;

/**
 * @author Milind
 * below class is TopKNumbersObserver class which listens to integer event
 * and floating point event
 * and calls other method of classes to do further operations on number
 */
public class TopKNumbersObserver implements ObserverI {
	/**
	 * windowSize is for keep all numbers within boundary
	 */
	private int windowSize;
	/**
	 * priority queue is to store numbers and delete least number
	 */
	PriorityQueue<Double> priorityQueue;
	/**
	 * topKNumbersData is to call store method
	 */
	private TopKNumbersResultsI topKNumbersData;
	/**
	 * topKNumbersDataWrite is to call write and close method
	 */
	private PersisterI topKNumbersDataWrite;

	/**
	 * @param windowSize incoming size
	 * @param inputFilePath incoming output file name
	 * below constructor is to initialize queue and data members 
	 */
	public TopKNumbersObserver(int windowSize, String inputFilePath) {
		super();
		this.windowSize = windowSize;
		this.priorityQueue = new PriorityQueue<Double>();
		this.topKNumbersData = new TopKNumbersData(inputFilePath);
		this.topKNumbersDataWrite = (PersisterI) this.topKNumbersData;
	}

	/**
	 *below method is to find top k numbers and call store method to store all
	 *top k numbers in descending order
	 *and once done with all numbers, it calls write method
	 */
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

	/**
	 * @param pq is incoming queue 
	 * @return it will return ArrayList in sorted descending order
	 */
	public List<Double> getListDscOrder(PriorityQueue<Double> pq) {
		List ls = new ArrayList<Double>();
		Iterator value = pq.iterator();
		while (value.hasNext()) {

			ls.add(Double.parseDouble(String.format(Constants.ROUND_TO_TWO_DECIMAL, value.next())));
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
