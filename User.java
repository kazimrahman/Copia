
public abstract class User {
	
	private String firstName;
	private String lastName;
	private String street;
	private String city;
	private String state;
	private String postal;
	private String country;
	private String email;
	private String phone;
	private double latitude;
	private double longitude;
	
	
	public User(String firstName, String lastName, String street, String city,
			String state, String postal, String country,String email, String phone,
			double latitude,double longitude) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.street = street;
		this.city= city;
		this.state =state;
		this.postal = postal;
		this.country = country;
		this.email = email;
		this.phone = phone;
		this.latitude = latitude;
		this.longitude = longitude;
	}
	
	public User() {
		
	}
	
	// Calculates the distance between two Users
	public static double distance(User c, User r) {
		double lon1 = c.getLongitude();
		double lon2 = r.getLongitude();
		double lat1 = c.getLatitude();
		double lat2 = r.getLatitude();
		double theta = lon1 - lon2;
		double dist = Math.sin(deg2rad(lat1)) * Math.sin(deg2rad(lat2)) + Math.cos(deg2rad(lat1)) * Math.cos(deg2rad(lat2)) * Math.cos(deg2rad(theta));  dist = Math.acos(dist);  dist = rad2deg(dist);
		dist = dist * 60 * 1.1515;
		return dist;
	}

	// Converts decimal degrees to radians
	static double deg2rad(double deg) {
		return (deg * Math.PI / 180.0);
	}

	// Converts radians to decimal degrees
	static double rad2deg(double rad) {
		return (rad * 180.0 / Math.PI);
	}
	
	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getPostal() {
		return postal;
	}

	public void setPostal(String postal) {
		this.postal = postal;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}
	
}
