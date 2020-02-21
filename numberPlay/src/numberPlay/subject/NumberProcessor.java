package numberPlay.subject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import numberPlay.Filter.FilterI;
import numberPlay.observer.ObserverI;

public class NumberProcessor  implements SubjectI{
	Map<FilterI, List<ObserverI>> observers;
	
    public NumberProcessor() {
		this.observers = new HashMap<FilterI, List<ObserverI>>();
		}
	@Override
	public void process(Number n, Enum e) {
		notifyAll(n,e);
		}

	@Override
	public void register(ObserverI o, FilterI f) {
		if (!observers.containsKey(f)) {
			observers.put(f, new ArrayList<ObserverI>());
		}
		observers.get(f).add(o);
	}

	@Override
	public void notifyAll(Number n, Enum e) {

		for (Map.Entry<FilterI, List<ObserverI>> entry : observers.entrySet()) {
			if (entry.getKey().check(e)) {
				for (ObserverI o : entry.getValue()) {
					//System.out.println(n);
					o.update(n);
				}
			}
		}
	}

}
