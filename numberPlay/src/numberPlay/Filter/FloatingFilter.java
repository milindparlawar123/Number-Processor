package numberPlay.Filter;

public class FloatingFilter implements FilterI {

	@Override
	public boolean check(Enum tag) {
		if(tag.toString().equals("FLOATING_POINT_EVENT")) {
			//System.out.println(" flo ");
			return true;
		}
		return false;
	}

}
