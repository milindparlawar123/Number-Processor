package numberPlay.Filter;

/**
 * @author Milind 
 * below filter class is to check whether
 * provided tag is floating point event  or not, accordingly it will return 
 * true or false
 *
 */
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
