package numberPlay.observer;

import numberPlay.util.NumberPeaksData;
import numberPlay.util.NumberPeaksResultsI;
import numberPlay.util.PersisterI;

public class NumberPeaksObserver implements ObserverI {
	private Double prevNumber = Double.MIN_VALUE;
	private NumberPeaksResultsI numberPeaksResult;
	private PersisterI  numberPeaksResultWrite;
    public NumberPeaksObserver() {
		this.numberPeaksResult= new NumberPeaksData();
		this.numberPeaksResultWrite= (PersisterI)this.numberPeaksResult;
	}
	@Override
	public void update(Number n) {

		if (n == null) {
          this.numberPeaksResultWrite.writeToFile();
		} else {
			Double val= n.doubleValue();
			if(val>this.prevNumber) {
				if(this.prevNumber== Double.MIN_VALUE) {
					this.prevNumber=val;
				}else {
					this.prevNumber=val;
				}
				
			}else {
				this.numberPeaksResult.store(Double.parseDouble(String.format("%.2f",
						this.prevNumber)));
				this.prevNumber=val;
			}

		}
	}

}
