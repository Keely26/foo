package Participation;
import org.junit.* ;
import java.util.* ;
import static org.junit.Assert.* ;

/**
 * This is just a simple template for a JUnit test-class for testing 
 * the class Customer.
 */
public class Customer_test {

//	@Test
//	public void test1() {
//		System.out.println("Provide here a short description of your test purpose here...") ;
//		Customer C = new Customer(0,"Duffy Duck","") ;
//		assertTrue(C.getCostToPayTest() == 0) ;
//	}
//
//	@Test
//	public void test2() {
//		System.out.println("Provide a short description...") ;
//		// an example of test that fails, in this case trivially:
//		assertTrue(1/0 == 0) ;
//	}

	@Test
	public void getParticipationValueTest(){
		Customer C = new Customer(0,"name", "name@email.com");
		Service one = new Service(1, "service one", 300);
		Service two = new Service(2, "service two", 350);
		C.participations.add(new Participation(C, one));
		C.participations.add(new Participation(C, two));

		int actual = C.participationValue();
		assertEquals(650, actual);
	}

	@Test
	public void getDiscountValueTest(){
		Customer C = new Customer(0,"name", "name@email.com");
		Service one = new Service(1, "service one", 300);
		C.participations.add(new Participation(C, one));
		Discount D = new Testing_Discount();
		C.discounts.add(D);

		int actual = C.getDiscountValue();
		assertEquals(100, actual);
	}

	@Test
	public void getCostToPayTest() {
		Customer C = new Customer(0,"name", "name@email.com");
		Service one = new Service(1, "service one", 300);
		Discount D = new Testing_Discount();
		C.participations.add(new Participation(C, one));
		C.discounts.add(D);

		int actualServices = C.getCostToPay();
		assertEquals(200, actualServices);
	}

	@Test
	public void getParticipationGroupsTest(){
		Customer C = new Customer(0,"name", "name@email.com");
		Service one = new Service(1, "service one", 300);
		Service two = new Service(2, "service two", 350);
		Discount D = new Testing_Discount();
		C.participations.add(new Participation(C, one));
		C.participations.add(new Participation(C, two));
		C.discounts.add(D);

		Map<Service, Customer.ServiceInfo> actual = C.getParticipationGroups();
		Map<Service, Integer> expected = new HashMap<>();
		expected.put(one, 1);
		expected.put(two, 1);

		assertEquals(actual.keySet(), expected.keySet());
	}
}
