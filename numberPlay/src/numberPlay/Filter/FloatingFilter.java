package numberPlay.Filter;

public class FloatingFilter implements FilterI {

	/**
	 * incoming tag enum, below method will check that if it is floating point event
	 * then it will return true else false
	 */
	@Override
	public boolean check(Enum tag) {
		if (tag.toString().equals("FLOATING_POINT_EVENT")) {
			return true;
		}
		return false;
	}

	@Override
	public String toString() {
		return "FloatingFilter []";
	}

}
