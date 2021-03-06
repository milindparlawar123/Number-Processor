package numberPlay.Filter;

import numberPlay.util.Constants;

/**
 * @author Milind
 * below filter class is to check whether
 * provided tag is processing complete event  or not, accordingly it will return 
 * true or false
 *
 */
public class ProcessCompleteFilter implements FilterI {

	/**
	 * incoming tag enum, below method will check whether incoming tag is processing
	 * complete event or not. If it is processing complete then it will return true
	 * else false
	 */
	@Override
	public boolean check(Enum tag) {
		if (tag.toString().equals(Constants.PROCESSING_COMPLETE)) {
			return true;
		}
		return false;
	}

	@Override
	public String toString() {
		return "ProcessCompleteFilter []";
	}

}
