import java.util.ArrayList;

public class Recipient extends User {
	private int restrictions;
	private ArrayList<Integer> times;
	
	public Recipient(String firstName, String lastName, String street, String city,
			String state, String postal, String country,String email, String phone,
			double latitude, double longitude, int restrictions) {
		super(firstName, lastName, street, city, state, postal, country, email, phone, latitude, longitude);
		this.restrictions = restrictions;
		times = new ArrayList<>();
	}
	
	public Recipient() {
	}
	
	public ArrayList<Integer> getTimes() {
		return times;
	}
	
	public int getRestrictions() {
		return restrictions;
	}

	public void setRestrictions(int restrictions) {
		this.restrictions = restrictions;
	}
	public void setTimes(ArrayList<Integer> times) {
		this.times = times;
	}
}
