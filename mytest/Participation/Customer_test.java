package Participation;
import com.sun.source.tree.NewArrayTree;
import org.junit.* ;
import java.util.* ;
import static org.junit.Assert.* ;

/**
 * This is just a simple template for a JUnit test-class for testing 
 * the class Customer.
 */
public class Customer_test {
	/**
	 * Provide a functionality to reset the database. Here I will just
	 * delete the whole database file.
	 */
	private void setupDB() {
		Persistence.wipedb() ;
	}

	@Test
	public void getParticipationValueTest() {
		setupDB() ;
		System.out.println("** Testing Customer getParticipationValue method");
		ApplicationLogic SUT = new ApplicationLogic() ;

		int c = SUT.addCustomer("name", "name@email.com");
		int serviceOne = SUT.addService("service one", 300);
		int serviceTwo = SUT.addService("service two", 300);

		SUT.addParticipation(c, serviceOne);
		SUT.addParticipation(c, serviceTwo);
		Customer C = SUT.findCustomer(c);
		int actual = C.participationValue();
		assertEquals(600, actual);
	}

	@Test
	public void getDiscountValueTest(){
		setupDB() ;
		System.out.println("** Testing Customer getDiscountValue method");
		ApplicationLogic SUT = new ApplicationLogic() ;
		int c = SUT.addCustomer("name", "name@email.com");
		int g = SUT.addCustomer("guest", "guest@email.com");
		int serviceOne = SUT.addService("service one", 100000);
		int serviceTwo = SUT.addService("service two", 0);
		int serviceO = SUT.addService("service 1", 0);
		int serviceT = SUT.addService("service 2", 0);
		SUT.addParticipation(c, serviceOne);
		SUT.addParticipation(c, serviceTwo);
		Discount D = new Testing_Discount();
		SUT.addParticipation(g, serviceO);
		SUT.addParticipation(g, serviceT);
		Discount Dg = new Testing_Discount();

		SUT.awardDiscount(c, "D1000eur");

		Customer C = SUT.findCustomer(c);
		C.discounts.add(Dg);
		C.discounts.add(D);
		int actual = C.getDiscountValue();
		assertEquals(5400, actual);

	}

	@Test
	public void getCostToPayTest() {
		setupDB() ;
		System.out.println("** Testing Customer getCostToPay method");
		ApplicationLogic SUT = new ApplicationLogic() ;
		int c = SUT.addCustomer("name", "name@email.com");

		int serviceOne = SUT.addService("service one", 201);
		Discount D = new Testing_Discount();
		SUT.addParticipation(c, serviceOne);
		Customer C = SUT.findCustomer(c);
		C.discounts.add(D);

		int g = SUT.addCustomer("guest", "name@email.com");

		int serviceTwo = SUT.addService("service two", 199);
		Discount D2 = new Testing_Discount();
		SUT.addParticipation(c, serviceTwo);
		Customer G = SUT.findCustomer(g);
		G.discounts.add(D2);

		int actualServices = G.getCostToPay();
		assertEquals(0, actualServices);
	}

	@Test
	public void getParticipationGroupsTest(){
		setupDB() ;
		System.out.println("** Testing Customer getParticipationGroups method");
		ApplicationLogic SUT = new ApplicationLogic() ;

		Customer C = new Customer(0,"name", "name@email.com");

		Service serviceOne = new Service(1, "service one", 100);
		Service serviceTwo = new Service(2, "service two", 150);
		Service serviceThree = new Service(3, "service three", 150);

		Discount D = new Testing_Discount();
		C.participations.add(new Participation(C, serviceOne));
		C.participations.add(new Participation(C, serviceOne));
		C.participations.add(new Participation(C, serviceTwo));
		C.participations.add(new Participation(C, serviceTwo));
		C.discounts.add(D);

		Map<Service, Customer.ServiceInfo> actual = C.getParticipationGroups();

		assertTrue(actual.containsKey(serviceOne));
		assertTrue(actual.containsKey(serviceTwo));
		assertFalse(actual.containsKey(serviceThree));

	}
}
