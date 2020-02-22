package numberPlay.observer;

import numberPlay.util.NumberPeaksData;
import numberPlay.util.NumberPeaksResultsI;
import numberPlay.util.PersisterI;

public class NumberPeaksObserver implements ObserverI {
	private Double prevNumber = Double.MIN_VALUE;
	private NumberPeaksResultsI numberPeaksResult;
	private PersisterI numberPeaksResultWrite;

	public NumberPeaksObserver(String inputFilePath) {
		this.numberPeaksResult = new NumberPeaksData(inputFilePath);
		this.numberPeaksResultWrite = (PersisterI) this.numberPeaksResult;
	}

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
