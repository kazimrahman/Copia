import java.time.Instant;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.Map.Entry;

public class Customer extends User{
		
		private int categories;
		private String[] pickUp;
		private String timeZone;
		private Map<Recipient, Double> matches = new HashMap<Recipient, Double>();
		
		public Customer(String firstName, String lastName, String street, String city,
				String state, String postal, String country,String email, String phone,
				double latitude,	double longitude, int categories, String timeZone) {
			super(firstName, lastName, street, city, state, postal, country, email, phone, latitude, longitude);
			this.categories = categories;
			this.pickUp = new String[8];
			this.timeZone = timeZone;
			//this.matches = new LinkedHashMap<Recipient, Double>();
		}
		
		public Customer() {
		}
		
		//main algorithm, matches based on distance, food type, pickup timing
		public Map<Recipient, Double> match(ArrayList<Recipient> recipients){
			 for(int i = 0; i < recipients.size();i++){
				 //check distance
				 double d = User.distance(this, recipients.get(i));
				 if(d <=10.0) {
					 //if distance is close, check if food conditions are met
					 //XOR the food bits with restrictions to see if valid
					 int valid = this.categories^recipients.get(i).getRestrictions();
					 if(conditionsMet(valid) && validTimes(recipients.get(i))) {
						 this.matches.put(recipients.get(i), d);
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
			String day = this.getPickUp()[0];
			int numericDay = convertToInt(day);
			int hoursOpen = r.getTimes().get(numericDay);
			int[] binaryTime = convertToBinary(hoursOpen);
			int promisedTime = Integer.valueOf(this.getPickUp()[3]);
			int promisedWindow = promisedTimetoBit(promisedTime);
			if(binaryTime[promisedWindow] == 1) {
				return true;
			}else {
				return false;
			}
		}
		
		int promisedTimetoBit(int promised) {
			int result = -1;
			switch(promised) {
				case 8: result = 0; 
						break;
				case 9: result = 1;
						break;
				case 10: result = 2;
						break;
				case 11: result = 3;
						break;
				case 12: result = 4; 
						break;
				case 13: result = 5; 
						break;
				case 14: result = 6; 
						break;
				case 15: result = 7; 
						break;
				case 16: result = 8; 
						break;
				case 17: result = 9; 
						break; 
				case 18: result = 10; 
						break; 
				case 19: result = 11; 
						break; 
				case 20: result = 12; 
						break; 
				case 21: result = 13; 
						break; 
				case 22: result = 14; 
						break;
				case 23: result = 15; 
						break; 
			}
			return result;
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
			}
			return p;
		}
		
		static int[] convertToBinary(int no){
		    int container[] = new int[16];
		    int i = 0;
		    while (no > 0){
		        container[i] = no%2;
		        i++;
		        no = no/2;
		    }
		    int temp = 0;
		    for (int j = 0 ; j < container.length/2 ; j++){
		    	temp = container[j];
		    	container[j] = container[container.length-j-1];
		    	container[container.length-j-1] = temp;
		    }
		    return container;
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
			}else if(day.equals("Sat")){
				return 6;
			}else {return -1;}
		}
		
		static void nextDay(String[] p) {
			if(p[0].equals("Sun")) {
				p[0] = "Mon";
			}else if(p[0].equals("Mon")) {
				p[0] = "Tue";
			}else if(p[0].equals("Tue")) {
				p[0] = "Wed";
			}else if(p[0].equals("Wed")) {
				p[0] = "Thu";
			}else if(p[0].equals("Thu")) {
				p[0] = "Fri";
			}else if(p[0].equals("Fri")) {
				p[0] = "Sat";
			}else if(p[0].equals("Sat")) {
				p[0] = "Sun";
			}
		}
		
		public Map<Recipient, Double> sortByComparator(Map<Recipient, Double> unsortMap){
	        List<Entry<Recipient, Double>> list = new LinkedList<Entry<Recipient, Double>>(unsortMap.entrySet());
	        // Sorting the list based on values
	        Collections.sort(list, new Comparator<Entry<Recipient, Double>>(){
	            public int compare(Entry<Recipient, Double> o1,Entry<Recipient, Double> o2){
	            	return o1.getValue().compareTo(o2.getValue());   
	            }
	        });

	        // Maintaining insertion order with LinkedList
	        Map<Recipient, Double> sortedMap = new LinkedHashMap<Recipient, Double>();
	        for (Entry<Recipient, Double> entry : list){
	            sortedMap.put(entry.getKey(), entry.getValue());
	        }

	        return sortedMap;
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
		
		public Map<Recipient, Double> getMatches() {
			return matches;
		}
		
		public void setMatches(Map<Recipient, Double> matches) {
			this.matches = matches;
		}
}
