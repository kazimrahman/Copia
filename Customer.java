import java.time.Instant;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class Customer extends User{
		
		private int categories;
		private String[] pickUp;
		private String timeZone;
		private ArrayList<Recipient> matches;
		
		public Customer(String firstName, String lastName, String street, String city,
				String state, String postal, String country,String email, String phone,
				double latitude,	double longitude, int categories, String timeZone) {
			super(firstName, lastName, street, city, state, postal, country, email, phone, latitude, longitude);
			this.categories = categories;
			this.pickUp = new String[8];
			this.timeZone = timeZone;
			this.matches = new ArrayList<Recipient>();
		}
		
		public Customer() {
		}

		public int getCategories() {
			return categories;
		}

		public void setCategories(int categories) {
			this.categories = categories;
		}

		public String[] getPickUp() {
			return pickUp;
		}

		public void setPickUp(String[] pickUp) {
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
					 if(conditionsMet(valid) && validTimes(recipients.get(i))) {
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
		
		boolean validTimes(Recipient r) {
			ArrayList<Integer> a = r.getTimes();
			String pDay = this.getPickUp()[0];
			System.out.println(pDay);
			int pD = convertToInt(pDay);
			int pTime = Integer.valueOf(this.getPickUp()[3]);
			int t = r.getTimes().get(pD);
			System.out.println("t: " + t + " DAY: " + pD);
			return true;
		}
		
		//takes csv value time and converts to an easy to parse string
		public String[] convertTime(String time) {
			DateTimeFormatter timeFormatter = DateTimeFormatter.ISO_DATE_TIME;
			Date date = Date.from(Instant.from(timeFormatter.parse(time)));
			String date1 = date.toString();
			String[] p = date1.split(":|\\s+");
			if(p[p.length-2].equals("PDT")) {
				int y = Integer.parseInt(p[3])+1;
				if(y>=24) {
					y = y%24;
					nextDay(p);
				}
				p[3] = Integer.toString(y);
				//System.out.println(Arrays.toString(p));
			}
			return p;
		}
		
		static int convertToInt(String day) {
			if(day.equals("Sun")) {
				return 0;
			}else if(day.equals("Mon")){
				return 1;
			}else if(day.equals("Tue")) {
				return 2;
			}else if(day.equals("Wed")) {
				return 3;
			}else if(day.equals("Thu")) {
				return 4;
			}else if(day.equals("Fri")) {
				return 5;
			}else{
				return 6;
			}
		}
		
		static void nextDay(String[] p) {
			if(p[0]=="Sun") {
				p[0] = "Mon";
			}else if(p[0]=="Mon") {
				p[0] = "Tue";
			}else if(p[0]=="Wed") {
				p[0] = "Thu";
			}else if(p[0]=="Thu") {
				p[0] = "Fri";
			}else if(p[0]=="Fri") {
				p[0] = "Sat";
			}else if(p[0]=="Tue") {
				p[0] = "Wed";
			}else if(p[0]=="Sat") {
				p[0] = "Sun";
			}
		}
	    static int toBinary(int n)
	    {
	        // array to store binary number
	        int[] binaryNum = new int[1000];
	  
	        // counter for binary array
	        int i = 0;
	        while (n > 0) 
	        {
	            // storing remainder in binary array
	            binaryNum[i] = n % 2;
	            n = n / 2;
	            i++;
	        }
	        System.out.println(Arrays.toString(binaryNum));
	        Collections.reverse(Arrays.asList(binaryNum));
	        System.out.println(Arrays.toString(binaryNum));
	        StringBuilder strNum = new StringBuilder();

	        for (int num : binaryNum) 
	        {
	             strNum.append(num);
	        }
	        int binary = Integer.parseInt(strNum.toString());
	        return binary;
	    }
	    
}
