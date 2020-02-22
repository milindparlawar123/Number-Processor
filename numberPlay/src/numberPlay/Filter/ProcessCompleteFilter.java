package numberPlay.Filter;

public class ProcessCompleteFilter implements FilterI{

	@Override
	public boolean check(Enum tag) {
		if(tag.toString().equals("PROCESSING_COMPLETE")) {
			return true;
		}
		return false;
	}

	@Override
	public String toString() {
		return "ProcessCompleteFilter []";
	}

}
