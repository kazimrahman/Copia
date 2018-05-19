import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CustomerTest {
	Recipient r;
	Customer c;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
		c = new Customer("Brett","Sullivan","2784 Ella Street","San Francisco","CA","94107","US",
				"BrettJSullivan@teleworm.us","650-262-4366",37.728912,-122.324225,45,"America/Los_Angeles");
		r = new Recipient("Tanya","Matthews","1780 Black Oak Hollow Road","San Francisco","CA","94107","US",
				"TanyaKMatthews@teleworm.us","408-702-0996",37.809052,-122.483365,7);
				//,44536,44382,19514,12035,18094,41561,55924);
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void testConditionsMet() {
		assertEquals(false, c.conditionsMet(0^0));
	}
	
	@Test
	void testDistance() {
		
	}

}
