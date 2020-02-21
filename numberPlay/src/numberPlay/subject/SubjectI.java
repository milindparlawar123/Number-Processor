package numberPlay.subject;

import numberPlay.Filter.FilterI;
import numberPlay.observer.ObserverI;

public interface SubjectI {
	void process(Number n, Enum e);
	void register(ObserverI o, FilterI f);
	void notifyAll(Number n, Enum e);
}
