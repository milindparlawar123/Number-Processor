package numberPlay.observer;

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

	public NumberAverageObserver(int windowSize) {
		super();
		this.windowSize = windowSize;
		this.blockingQueue = new ArrayBlockingQueue<Integer>(3);
		this.runningAverageResult = new RunningAverageData();
		this.runningAverageWrite = (PersisterI)this.runningAverageResult;
	}

	@Override
	public void update(Number n) {
		// TODO Auto-generated method stub
		if (n == null) {
			this.runningAverageWrite.writeToFile();
			System.out.println(" final"  );
			//this.runningAverageWrite.writeToFile();
		} else {

			if (blockingQueue.remainingCapacity() == 0) {
				blockingQueue.poll();
			}
			this.blockingQueue.add(n.intValue());
            calculateAverage();
			// this.runningAverageResult.store(d);
		}

	}

	private Double calculateAverage() {
		Iterator itr = this.blockingQueue.iterator();
		Integer total=0;
		while (!this.blockingQueue.isEmpty()) {     
		       if(itr.hasNext()) {
		    	 //  Double val=(Double) itr.next();
		    	   total+=(Integer)itr.next();
		          //System.out.println(itr.next());
		        }else
		        {
		        	break;
		        }
		       
		    }
		this.runningAverageResult.store(Double.parseDouble(String.format("%.2f",
				(total *1.0/(this.windowSize-blockingQueue.remainingCapacity()))))   );
		return 1.0;
	}

}
