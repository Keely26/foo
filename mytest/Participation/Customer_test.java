package Participation;
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
	public void getParticipationValueTest(){
		setupDB() ;
		System.out.println("** Testing Customer getParticipationValue method");
		ApplicationLogic SUT = new ApplicationLogic() ;

		int c = SUT.addCustomer("name", "name@email.com");
		int serviceOne = SUT.addService("service one", 300);
		int serviceTwo = SUT.addService("service two", 305);

		SUT.addParticipation(c, serviceOne);
		SUT.addParticipation(c, serviceTwo);
		Customer C = SUT.findCustomer(c);
		int actual = C.participationValue();
		assertEquals(605, actual);
	}

	@Test
	public void getDiscountValueTest(){
		setupDB() ;
		System.out.println("** Testing Customer getDiscountValue method");
		ApplicationLogic SUT = new ApplicationLogic() ;
		int c = SUT.addCustomer("name", "name@email.com");
		int serviceOne = SUT.addService("service one", 300);
		int serviceTwo = SUT.addService("service two", 305);
		SUT.addParticipation(c, serviceOne);
		SUT.addParticipation(c, serviceTwo);
		Discount D = new Testing_Discount();

		Customer C = SUT.findCustomer(c);
		C.discounts.add(D);
		int actual = C.getDiscountValue();
		assertEquals(100, actual);
	}

	@Test
	public void getCostToPayTest() {
		setupDB() ;
		System.out.println("** Testing Customer getCostToPay method");
		ApplicationLogic SUT = new ApplicationLogic() ;
		int c = SUT.addCustomer("name", "name@email.com");

		int serviceOne = SUT.addService("service one", 300);
		Discount D = new Testing_Discount();
		SUT.addParticipation(c, serviceOne);
		Customer C = SUT.findCustomer(c);
		C.discounts.add(D);

		int actualServices = C.getCostToPay();
		assertEquals(200, actualServices);
	}

	@Test
	public void getParticipationGroupsTest(){
		setupDB() ;
		System.out.println("** Testing Customer getParticipationGroups method");
		ApplicationLogic SUT = new ApplicationLogic() ;

		Customer C = new Customer(0,"name", "name@email.com");
		Service serviceOne = new Service(1, "service one", 300);
		Service serviceTwo = new Service(2, "service two", 350);
		Discount D = new Testing_Discount();
		C.participations.add(new Participation(C, serviceOne));
		C.participations.add(new Participation(C, serviceTwo));
		C.discounts.add(D);

		Map<Service, Customer.ServiceInfo> actual = C.getParticipationGroups();
		Map<Service, Integer> expected = new HashMap<>();
		expected.put(serviceOne, 1);
		expected.put(serviceTwo, 1);
		assertTrue(actual.containsKey(serviceOne));
		assertTrue(actual.containsKey(serviceTwo));
	}
}
