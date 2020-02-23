package numberPlay.subject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import numberPlay.Filter.FilterI;
import numberPlay.observer.ObserverI;

/**
 * @author milind this class is to notify observers
 * based on the type of event triggered
 *
 */
public class NumberProcessor implements SubjectI {
	/**
	 * observers is to store filter as key and observers as values
	 */
	Map<FilterI, List<ObserverI>> observers;

	/**
	 * below constructor initializes observers 
	 */
	public NumberProcessor() {
		this.observers = new HashMap<FilterI, List<ObserverI>>();
	}

	/**
	 *process method will be called from driver class to notify
	 *observers
	 */
	@Override
	public void process(Number n, Enum e) {
		notifyAll(n, e);
	}

	/**
	 *below method is to register observers
	 */
	@Override
	public void register(ObserverI o, FilterI f) {
		if (!observers.containsKey(f)) {
			observers.put(f, new ArrayList<ObserverI>());
		}
		observers.get(f).add(o);
	}

	/**
	 *based on filter like if filter returns true then observers of particular key
	 *will be notified else not.
	 */
	@Override
	public void notifyAll(Number n, Enum e) {

		for (Map.Entry<FilterI, List<ObserverI>> entry : observers.entrySet()) {
			if (entry.getKey().check(e)) {
				for (ObserverI o : entry.getValue()) {
					o.update(n);
				}
			}
		}
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((observers == null) ? 0 : observers.hashCode());
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
		NumberProcessor other = (NumberProcessor) obj;
		if (observers == null) {
			if (other.observers != null)
				return false;
		} else if (!observers.equals(other.observers))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "NumberProcessor [observers=" + observers + "]";
	}

}
