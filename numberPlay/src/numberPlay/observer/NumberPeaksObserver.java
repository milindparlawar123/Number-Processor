package numberPlay.observer;

import numberPlay.util.NumberPeaksData;
import numberPlay.util.NumberPeaksResultsI;
import numberPlay.util.PersisterI;

/**
 * @author Milind
 * below class is NumberPeaksObserver class which listens to integer event
 * and floating point event
 * and calls other method of classes to do further operations on number
 *
 */
public class NumberPeaksObserver implements ObserverI {
	/**
	 * prevNumber is used to compare against incoming number
	 * like to see whether incoming number is greater or not
	 */
	private Double prevNumber = Double.MIN_VALUE;
	/**
	 * numberPeaksResult is to call store method
	 */
	private NumberPeaksResultsI numberPeaksResult;
	/**
	 * numberPeaksResultWrite is to call write and close method
	 */
	private PersisterI numberPeaksResultWrite;

	/**
	 * @param inputFilePath is output text file name
	 * below constructor is for to do initialize objects only once 
	 */
	public NumberPeaksObserver(String inputFilePath) {
		this.numberPeaksResult = new NumberPeaksData(inputFilePath);
		this.numberPeaksResultWrite = (PersisterI) this.numberPeaksResult;
	}

	/**
	 *below method will check incoming number is greater than minimum number or not
	 *basically below method logic is to to find peak number.
	 *if peak is found then it will call store method.
	 *Once done with all numbers, it will call write method
	 */
	@Override
	public void update(Number n) {

		if (n == null) {
			this.numberPeaksResultWrite.writeToFile();
			this.numberPeaksResultWrite.close();
		} else {
			Double val = n.doubleValue();
			if (val >= this.prevNumber) {
				if (this.prevNumber == Double.MIN_VALUE) {
					this.prevNumber = val;
				} else {
					this.prevNumber = val;
				}

			} else {
				this.numberPeaksResult.store(Double.parseDouble(String.format("%.2f", this.prevNumber)));
				this.prevNumber = val;
			}

		}
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((numberPeaksResult == null) ? 0 : numberPeaksResult.hashCode());
		result = prime * result + ((numberPeaksResultWrite == null) ? 0 : numberPeaksResultWrite.hashCode());
		result = prime * result + ((prevNumber == null) ? 0 : prevNumber.hashCode());
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
		NumberPeaksObserver other = (NumberPeaksObserver) obj;
		if (numberPeaksResult == null) {
			if (other.numberPeaksResult != null)
				return false;
		} else if (!numberPeaksResult.equals(other.numberPeaksResult))
			return false;
		if (numberPeaksResultWrite == null) {
			if (other.numberPeaksResultWrite != null)
				return false;
		} else if (!numberPeaksResultWrite.equals(other.numberPeaksResultWrite))
			return false;
		if (prevNumber == null) {
			if (other.prevNumber != null)
				return false;
		} else if (!prevNumber.equals(other.prevNumber))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "NumberPeaksObserver [prevNumber=" + prevNumber + ", numberPeaksResult=" + numberPeaksResult
				+ ", numberPeaksResultWrite=" + numberPeaksResultWrite + "]";
	}

}
