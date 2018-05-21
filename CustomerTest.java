import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CustomerTest {
	Recipient r;
	Customer c;

	@BeforeEach
	void setUp() throws Exception {
		c = new Customer("Brett","Sullivan","2784 Ella Street","San Francisco","CA","94107","US",
				"BrettJSullivan@teleworm.us","650-262-4366",37.728912,-122.324225,45,"America/Los_Angeles");
		r = new Recipient("Tanya","Matthews","1780 Black Oak Hollow Road","San Francisco","CA","94107","US",
				"TanyaKMatthews@teleworm.us","408-702-0996",37.809052,-122.483365,7);
		ArrayList<Integer> al = new ArrayList<>();
		al.addAll(Arrays.asList(44536,44382,19514,12035,18094,41561,55924));
		r.setTimes(al);
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
	void testDistance() {
		
		
	}

	@Test
	void testMatchHelperFunctionConvertToInt() {
		assertEquals(0,  Customer.convertToInt("Sun"));
		assertEquals(-1,  Customer.convertToInt("RANDOM"));
	}
	
	@Test
	void testMatchHelperFunctionConvertToBinary() {
		int[] x = {0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0};
		//assertEquals(0,  Customer.convertToBinary(0));
		assertEquals(x,  Customer.convertToBinary(16));
	}
	
	
	
}
