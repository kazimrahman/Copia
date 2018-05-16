import java.util.ArrayList;

public class Customer extends User{
		
		private int categories;
		private String pickUp;
		private String timeZone;
		private ArrayList<Recipient> matches;
		
		public Customer(String firstName, String lastName, String street, String city,
				String state, String postal, String country,String email, String phone,
				double latitude,	double longitude, int categories, String pickUp, String timeZone) {
			super(firstName, lastName, street, city, state, postal, country, email, phone, latitude, longitude);
			this.categories = categories;
			this.pickUp = pickUp;
			this.timeZone = timeZone;
			this.matches = new ArrayList<Recipient>();
		}
		
		public Customer() {
			// TODO Auto-generated constructor stub
		}

		public int getCategories() {
			return categories;
		}

		public void setCategories(int categories) {
			this.categories = categories;
		}

		public String getPickUp() {
			return pickUp;
		}

		public void setPickUp(String pickUp) {
			this.pickUp = pickUp;
		}

		public String getTimeZone() {
			return timeZone;
		}

		public void setTimeZone(String timeZone) {
			this.timeZone = timeZone;
		}
		public ArrayList<Recipient> getMatches() {
			return matches;
		}
		public void setMatches(ArrayList<Recipient> matches) {
			this.matches = matches;
		}
		
		//only criteria are distance, food conditions at this point
		public ArrayList<Recipient> match(ArrayList<Recipient> recipients){
			 for(int i = 0; i < recipients.size();i++){
				 //check distance
				 double d = User.distance(this, recipients.get(i));
				 if(d <=10.0) {
					 //if distance is close, check if food conditions are met
					 int valid = this.categories^recipients.get(i).getRestrictions();
					 if(conditionsMet(valid)) {
						 this.matches.add(recipients.get(i));
						 System.out.println(recipients.get(i).getFirstName());
					 }
				 }
			 }
			return this.matches;
		}
		
		static boolean conditionsMet(int n){
			// all bits are not set
			if (n == 0) {
				return false;
			}
			// loop till n becomes '0'
			while (n > 0){
			// if the last bit is not set
				if ((n & 1) == 0) {
					return false;
				}
				// right shift 'n' by 1
				n = n >> 1;
			}
			// all bits are set
			return true;
		}
}
