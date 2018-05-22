import static org.junit.jupiter.api.Assertions.*;

import java.util.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CopiaTest {
	Recipient r, r1;
	Customer c;

	@BeforeEach
	void setUp() throws Exception {
		c = new Customer("Brett","Sullivan","2784 Ella Street","San Francisco","CA","94107","US",
				"BrettJSullivan@teleworm.us","650-262-4366",37.728912,-122.324225,45,"America/Los_Angeles");
		String[] times = {"Tue","Nov","29","16","00","00","PST","2016"};
		c.setPickUp(times);
		
		r = new Recipient("Tanya","Matthews","1780 Black Oak Hollow Road","San Francisco","CA","94107","US",
				"TanyaKMatthews@teleworm.us","408-702-0996",37.809052,-122.483365,7);
		r1 = new Recipient("Elizabeth","Jimenez","4534 Wolf Pen Road","San Francisco","CA","94107"
				,"US","ElizabethIJimenez@gustr.com,"
				,"650-412-7569",37.76768,-122.32831,28);//,60335,15986,7301,52151,16879,25540,35418")
		ArrayList<Integer> al = new ArrayList<>();
		al.addAll(Arrays.asList(44536,44382,19514,12035,18094,41561,55924));
		r.setTimes(al);
		ArrayList<Integer> al1 = new ArrayList<>();
		al1.addAll(Arrays.asList(11325,44013,11907,19189,32596,43984,19658));
		r1.setTimes(al1);
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void testConditionsMet() {
		assertEquals(false, Customer.conditionsMet(0^0));
		assertEquals(true,Customer.conditionsMet(45^44));
		assertEquals(true,Customer.conditionsMet(-1^0));
	}
	
	@Test
	void testPromisedTimeToBit() {
		assertEquals(0,c.promisedTimetoBit(8));
		assertEquals(-1,c.promisedTimetoBit(12414));
		assertEquals(8,c.promisedTimetoBit(16));
	}
	
	@Test
	void testValidTimes() {
		assertEquals(false, c.validTimes(r));
		assertEquals(true, c.validTimes(r1));
	}

	@Test
	void testConvertDayToInt() {
		assertEquals(0,  Customer.convertToInt("Sun"));
		assertEquals(-1,  Customer.convertToInt("BLAH"));
		assertEquals(3, Customer.convertToInt("Wed"));
	}
	
	@Test
	void testConvertTime() {
		String date = "2016-11-29T16:00:00-08:00";
		String[] newDate = c.convertTime(date);
		assertEquals("Tue", newDate[0]);
		
		// This date should be a Wed, but when converting to PST, it should become Thu
		String date1 = "2016-11-02T23:00:00-07:00";
		String[] newDate1 = c.convertTime(date1);
		assertEquals("Thu", newDate1[0]);
		
		// successfully converts PDT time to PST
		String date2 = "2016-11-02T11:00:00-07:00";
		String[] newDate2 = c.convertTime(date2);
		assertEquals("12", newDate2[3]);
	}
	
	@Test
	void testDistance() {
		Double d = User.distance(c, r);
		assertEquals(10, d.intValue());
		
		Double d1 = User.distance(c, r1);
		boolean check = (d1.intValue() < 10);
		assertEquals(true, check);
	}
	
	@Test
	void TestDegreesToRadians() {
		assertEquals(Math.PI, User.deg2rad(180.00));
		assertEquals(0, User.deg2rad(0));
		assertEquals(0.17453292519943295, User.deg2rad(10));
	}
	
	@Test
	void TestRadiansToDegrees() {
		assertEquals(180.00, User.rad2deg(Math.PI));
		assertEquals(0, User.rad2deg(0));
	}
	
	@Test
	void testMatchHelperFunctionConvertToBinary() {
		//should return all zeros
		int[] y = Customer.convertToBinary(0);
		boolean r = true;
		for(Integer i : y) {
			if(i != 0) {
				r = false;
			}
		}
		assertEquals(r, true);
		
		//check if bit for binary 16 is 1
		int[] x = Customer.convertToBinary(16);
		boolean check = (x[x.length-5] == 1);
		assertEquals(check, true);
	}
	
	@Test
	void testHashMapSort() {
		Map<Recipient, Double> unsortMap = new HashMap<Recipient, Double>();
        unsortMap.put(r1, 11.0);
        unsortMap.put(r, 12.0);
        Map<Recipient, Double> n = c.sortByComparator(unsortMap);
        
        assertEquals(true, n.equals(unsortMap));
	}
	
	@Test
	void testNextDay() {
		String[] test = {"Sun"};
		Customer.nextDay(test);
		assertEquals("Mon", test[0]);
		
		String[] test1 = {"RANDOM", "VALUE"};
		Customer.nextDay(test1);
		assertEquals("RANDOM", test1[0]);
		
	}
}
