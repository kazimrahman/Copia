import java.io.*;
import java.util.*;
import java.util.Map.*;

public class Copia{

	public static void main(String[] args){
		ArrayList<Recipient> recipients = new ArrayList<>();
		ArrayList<Customer> customers = new ArrayList<>();
		
		try{
			File customerData = new File("Customers.csv");
			File recipientData = new File("Recipients.csv");
			File outFile = new File("Matches.csv");
			
			customers = initializeCustomer(customerData);
			recipients = initializeRecipient(recipientData);
			//output matches and stuff to csv
			PrintWriter pw = new PrintWriter(outFile);
			pw.println("CustomerFirstName,CustomerLastName,CustomerStreet,CustomerCity,"
					+ "CustomerState,CustomerPostal,CustomerEmail,CustomerPhone,PickUpDay,PickUpMonth,PickUpDate,"
					+ "PickUpHour,PickUpMinute,PickUpTimeZone,RecipientFirstName,"
					+ "RecipientLastName,RecipientStreet,RecipientCity,RecipientState,RecipientPostal,"
					+ "RecipientEmail,RecipientPhone,RecipientHours,Distance");
			for(Customer c : customers) {
				// matches customers to recipients
				c.match(recipients);
				// sorting by distance
				c.sortByComparator(c.getMatches());
				for (Entry<Recipient, Double> entry : c.getMatches().entrySet()){
					//writes output for each match
					writeOutputLine(outFile, c, entry, pw);
		        }
			}
			pw.close();
		}catch(Exception e){
			e.printStackTrace();
		}
		System.out.println("Done. Matches contained in 'Matches.csv'");
	}
	
	
	public static ArrayList<Customer> initializeCustomer(File customerFile) throws FileNotFoundException {
		ArrayList<Customer> customers = new ArrayList<>();
		BufferedReader br = new BufferedReader(new FileReader(customerFile));
		Scanner sc = new Scanner(br);
		
		String row = "";
		row = sc.nextLine(); //skip header in CSV
		
		while(sc.hasNext()) {
			row = sc.nextLine();
			String[] values = row.split(",");
			
			String firstName = values[0];
			String lastName = values[1];
			String street = values[2];
			String city = values[3];
			String state = values[4];
			String postal = values[5];
			String country = values[6];
			String email = values[7];
			String phone = values[8];
			double latitude = Double.valueOf(values[9]);
			double longitude = Double.valueOf(values[10]);
			int categories = Integer.valueOf(values[11]);
			String pickUp = values[12];
			String timeZone = values[13];
			
			Customer c = new Customer(firstName, lastName, street, city, state, postal,
					country, email, phone, latitude, longitude, categories, timeZone);
			
			c.setPickUp(c.convertTime(pickUp));
			//System.out.println(c.getFirstName() +" "+c.getPickUp()[3]);
			customers.add(c);
		}
		sc.close();
		return customers;
	}
	
	public static ArrayList<Recipient> initializeRecipient(File recipientData) throws FileNotFoundException {
		ArrayList<Recipient> recipients = new ArrayList<>();
		BufferedReader br1 = new BufferedReader(new FileReader(recipientData));
		
		Scanner sc1 = new Scanner(br1);
		String row1 = "";
		row1 = sc1.nextLine();
		while(sc1.hasNext()) {
			row1 = sc1.nextLine();
			String[] values = row1.split(",");
			
			String firstName = values[0];
			String lastName = values[1];
			String street = values[2];
			String city = values[3];
			String state = values[4];
			String postal = values[5];
			String country = values[6];
			String email = values[7];
			String phone = values[8];
			double latitude = Double.valueOf(values[9]);
			double longitude = Double.valueOf(values[10]);
			int restrictions = Integer.valueOf(values[11]);
			int Sun = Integer.valueOf(values[12]);
			int Mon = Integer.valueOf(values[13]);
			int Tue = Integer.valueOf(values[14]);
			int Wed = Integer.valueOf(values[15]);
			int Thu = Integer.valueOf(values[16]);
			int Fri = Integer.valueOf(values[17]);
			int Sat = Integer.valueOf(values[18]);
			Recipient r = new Recipient(firstName, lastName, street, city, state, postal,
					country, email, phone, latitude, longitude, restrictions);
			r.getTimes().add(Global.Sunday, Sun);
			r.getTimes().add(Global.Monday, Mon);
			r.getTimes().add(Global.Tuesday, Tue);
			r.getTimes().add(Global.Wednesday, Wed);
			r.getTimes().add(Global.Thursday, Thu);
			r.getTimes().add(Global.Friday, Fri);
			r.getTimes().add(Global.Saturday, Sat);
			recipients.add(r);		
		}
		sc1.close();
		return recipients;
	}
	
	public static void writeOutputLine(File out, Customer c, Entry<Recipient, Double> e, PrintWriter p) throws FileNotFoundException {
		Recipient r = e.getKey();
		int day = Customer.convertToInt(c.getPickUp()[0]);
		
		String custName = c.getFirstName() + "," + c.getLastName() + ",";
		String custAddress = c.getStreet() + "," + c.getCity() + "," + c.getState() + "," + c.getPostal() + ",";
		String custContactInfo = c.getEmail() + "," + c.getPhone() + ",";
		String custPickUpTime = c.getPickUp()[0] + "," + c.getPickUp()[1] + "," + c.getPickUp()[2] + "," 
				+ c.getPickUp()[3] + "," + c.getPickUp()[4] + "," + "PST" + ",";
		
		String recpName = r.getFirstName() + "," + r.getLastName() + ",";
		String recpAddress = r.getStreet() + "," + r.getCity() + "," + r.getState() + "," + r.getPostal() + ",";
		String recpContactInfo = r.getEmail() + "," + r.getPhone() + ",";
		String recpTime = String.valueOf(r.getTimes().get(day)) + ",";		
		
		double distance = e.getValue();
		
        p.println(custName  + custAddress + custContactInfo + custPickUpTime + recpName + recpAddress + recpContactInfo + recpTime + distance);
        p.flush();
	}
}
