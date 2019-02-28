package Participation;
import org.junit.* ;

import java.util.Map;

import static org.junit.Assert.* ;

/**
 * This is just a simple template for Junit test-class for testing
 * the class ApplicationLogic. Testing this class is a bit more
 * complicated. It uses database, which form an implicit part of
 * the state of ApplicationLogic. After each test case, you need to
 * reset the content of the database to the value it had, before
 * the test case. Otherwise, multiple test cases will interfere
 * with each other, which makes debugging hard should you find error.
 * 
 */
public class ApplicationLogic_test {

	/**
	 * Provide a functionality to reset the database. Here I will just
	 * delete the whole database file. 
	 */
	private void setupDB() {
		Persistence.wipedb() ;
	}

	@Test
	public void removeServiceTest(){
		setupDB() ;
		System.out.println("** Testing ApplicationLogic removeService method");
		ApplicationLogic SUT = new ApplicationLogic() ;

		int id = SUT.addCustomer("name", "name@email.com");

		int serviceOne = SUT.addService("service one", 100);
		int serviceTwo = SUT.addService("service two", 105);

		SUT.addParticipation(id, serviceOne);
		SUT.addParticipation(id, serviceTwo);

		assertTrue(SUT.serviceExists(id));
		SUT.removeService(id);
		assertFalse(SUT.serviceExists(id));

		int c = SUT.addCustomer("n", "n@email.com");
		int g = SUT.addCustomer("n", "n@email.com");

		int o = SUT.addService( "o", 3000);
		int t = SUT.addService( "t", 3500);
		int th = SUT.addService( "th", 3000);
		int f = SUT.addService( "f", 3000);
		int fv = SUT.addService( "fv", 100000);
		int s = SUT.addService( "s", 3000);

		SUT.addParticipation(c, o);
		SUT.addParticipation(c, t);
		SUT.addParticipation(c, th);
		SUT.addParticipation(c, f);
		SUT.addParticipation(c, fv);
		SUT.addParticipation(c, s);

		SUT.awardDiscount(c, "D1000eur");
		SUT.awardDiscount(c, "D5pack");

		assertTrue(SUT.serviceExists(c));
		SUT.removeService(c);
		assertFalse(SUT.serviceExists(c));
		SUT.removeService(g);



	}

	@Test
	public void resolveTest(){
		setupDB() ;
		System.out.println("** Testing ApplicationLogic resolve method");
		ApplicationLogic SUT = new ApplicationLogic() ;

		int c = SUT.addCustomer("name", "name@email.com");
		int g = SUT.addCustomer("guest", "guest@email.com");

		int o = SUT.addService( "o", 300);
		int t = SUT.addService( "t", 3500);
		int th = SUT.addService( "th", 3000);
		int f = SUT.addService( "f", 3000);
		int fv = SUT.addService( "fv", 100000);
		int s = SUT.addService( "s", 3000);

		SUT.addParticipation(c, o);
		SUT.addParticipation(c, t);
		SUT.addParticipation(c, th);
		SUT.addParticipation(c, f);
		SUT.addParticipation(c, fv);
		SUT.addParticipation(c, s);

		SUT.addParticipation(g, o);
		SUT.addParticipation(g, t);

		SUT.awardDiscount(g, "D1000eur");
		SUT.awardDiscount(g, "D5pack");
		SUT.awardDiscount(c, "D5pack");
		SUT.awardDiscount(c, "D1000eur");

		Customer C = SUT.findCustomer(c);
		Customer G = SUT.findCustomer(g);

		Map<Customer,Integer> test = SUT.resolve();
	}
}
