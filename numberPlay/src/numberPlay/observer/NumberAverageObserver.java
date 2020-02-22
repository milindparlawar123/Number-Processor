package numberPlay.observer;

import java.io.FileNotFoundException;
import java.util.Iterator;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

import numberPlay.util.PersisterI;
import numberPlay.util.RunningAverageData;
import numberPlay.util.RunningAverageResultsI;

public class NumberAverageObserver implements ObserverI {
	private int windowSize;
	private BlockingQueue<Integer> blockingQueue;
	private RunningAverageResultsI runningAverageResult;
	private PersisterI runningAverageWrite;

	public NumberAverageObserver(int windowSize, String inputFilePath) throws FileNotFoundException {
		super();
		this.windowSize = windowSize;
		this.blockingQueue = new ArrayBlockingQueue<Integer>(3);
		this.runningAverageResult = new RunningAverageData(inputFilePath);
		this.runningAverageWrite = (PersisterI) this.runningAverageResult;
	}

	@Override
	public void update(Number n) {

		if (n == null) {
			this.runningAverageWrite.writeToFile();
			this.runningAverageWrite.close();

		} else {

			if (blockingQueue.remainingCapacity() == 0) {
				blockingQueue.poll();
			}
			this.blockingQueue.add(n.intValue());
			this.runningAverageResult.store(calculateAverage());
		}

	}

	private Double calculateAverage() {
		Iterator itr = this.blockingQueue.iterator();
		Integer total = 0;
		while (!this.blockingQueue.isEmpty()) {
			if (itr.hasNext()) {
				total += (Integer) itr.next();
			} else {
				break;
			}

		}

		return Double.parseDouble(
				String.format("%.2f", (total * 1.0 / (this.windowSize - blockingQueue.remainingCapacity()))));
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((blockingQueue == null) ? 0 : blockingQueue.hashCode());
		result = prime * result + ((runningAverageResult == null) ? 0 : runningAverageResult.hashCode());
		result = prime * result + ((runningAverageWrite == null) ? 0 : runningAverageWrite.hashCode());
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
		NumberAverageObserver other = (NumberAverageObserver) obj;
		if (blockingQueue == null) {
			if (other.blockingQueue != null)
				return false;
		} else if (!blockingQueue.equals(other.blockingQueue))
			return false;
		if (runningAverageResult == null) {
			if (other.runningAverageResult != null)
				return false;
		} else if (!runningAverageResult.equals(other.runningAverageResult))
			return false;
		if (runningAverageWrite == null) {
			if (other.runningAverageWrite != null)
				return false;
		} else if (!runningAverageWrite.equals(other.runningAverageWrite))
			return false;
		if (windowSize != other.windowSize)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "NumberAverageObserver [windowSize=" + windowSize + ", blockingQueue=" + blockingQueue
				+ ", runningAverageResult=" + runningAverageResult + ", runningAverageWrite=" + runningAverageWrite
				+ "]";
	}

}
