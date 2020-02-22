package numberPlay.Filter;

public class IntegerFilter implements FilterI {

	@Override
	public boolean check(Enum tag) {
		if(tag.toString().equals("INTEGER_EVENT")) {
			return true;
		}
		return false;
	}

	@Override
	public String toString() {
		return "IntegerFilter []";
	}

}
