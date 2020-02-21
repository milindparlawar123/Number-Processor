package numberPlay.observer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.PriorityQueue;

import numberPlay.util.PersisterI;
import numberPlay.util.TopKNumbersData;
import numberPlay.util.TopKNumbersResultsI;

public class TopKNumbersObserver  implements ObserverI{
	private int windowSize;
	PriorityQueue<Double> priorityQueue;
	private TopKNumbersResultsI topKNumbersData;
	private PersisterI topKNumbersDataWrite;
	public TopKNumbersObserver(int windowSize) {
		super();
		this.windowSize = windowSize;
		this.priorityQueue=new PriorityQueue<Double>();
		this.topKNumbersData= new TopKNumbersData();
		this.topKNumbersDataWrite=(PersisterI)this.topKNumbersData;	
	}

	@Override
	public void update(Number n) {
		if(n==null) {
			this.topKNumbersDataWrite.writeToFile();
		}else{		
			this.priorityQueue.add(n.doubleValue());
			if(this.priorityQueue.size()==windowSize+1) {
				priorityQueue.remove();
			}
		this.topKNumbersData.store(getListDscOrder(this.priorityQueue));
		}
		
	}
   
	public List<Double> getListDscOrder(PriorityQueue<Double> pq){
		List ls= new ArrayList<Double>();
		Iterator value = pq.iterator(); 
        while (value.hasNext()) { 
        	
            ls.add(Double.parseDouble(String.format("%.2f",
        			value.next()))) ;
        } 
        Collections.sort(ls, Collections.reverseOrder());
		return ls;
		
		
	}
}
