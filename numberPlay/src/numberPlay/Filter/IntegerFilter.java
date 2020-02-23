package numberPlay.Filter;

/**
 * @author Milind
 * below filter class is to check whether
 * provided tag is Integer Event or not, accordingly it will return 
 * true or false
 *
 */
public class IntegerFilter implements FilterI {

	/**
	 * incoming tag enum, below method will check whether incoming tag is integer
	 * event or not. If it is integer event then it will return true else false
	 */
	@Override
	public boolean check(Enum tag) {
		if (tag.toString().equals("INTEGER_EVENT")) {
			return true;
		}
		return false;
	}

	@Override
	public String toString() {
		return "IntegerFilter []";
	}

}
