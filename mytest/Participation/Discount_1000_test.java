package Participation;
import org.junit.* ;

import java.util.Map;

import static org.junit.Assert.* ;

public class Discount_1000_test extends Discount_1000{
    /**
     * Provide a functionality to reset the database. Here I will just
     * delete the whole database file.
     */
    private void setupDB() {
        Persistence.wipedb() ;
    }
    @Test
    public void applicableTest(){
        setupDB() ;
        System.out.println("** Testing Discount_1000 applicable method");
        ApplicationLogic SUT = new ApplicationLogic() ;

        int c = SUT.addCustomer("name", "name@email.com");
        int g = SUT.addCustomer("guest", "guest@email.com");
        Customer C = SUT.findCustomer(c);
        Customer G = SUT.findCustomer(g);

        //Customer C = new Customer(0,"name", "name@email.com");
        Service one = new Service(1, "service one", 33333);
        Service two = new Service(2, "service two", 33334);
        Service three = new Service(2, "service two", 33333);

        Service four = new Service(1, "service one", 333);
        Service five = new Service(2, "service two", 333);
        Service six = new Service(2, "service two", 333);

        C.participations.add(new Participation(C, one));
        C.participations.add(new Participation(C, two));
        C.participations.add(new Participation(C, three));

        G.participations.add(new Participation(G, four));
        G.participations.add(new Participation(G, five));
        G.participations.add(new Participation(G, six));

        assertFalse(applicable(G));
        assertTrue(applicable(C));

        Map<Customer,Integer> test = SUT.resolve();
        System.out.println(test);
    }

    @Test
    public void calcDiscountTest(){
        setupDB() ;
        System.out.println("** Testing Discount_1000 calcDiscount method");
        ApplicationLogic SUT = new ApplicationLogic() ;

        int c = SUT.addCustomer("name", "name@email.com");
        Customer C = SUT.findCustomer(c);

        Service one = new Service(1, "service one", 33333);
        Service two = new Service(2, "service two", 33334);
        Service three = new Service(2, "service two", 33333);
        C.participations.add(new Participation(C, one));
        C.participations.add(new Participation(C, two));
        C.participations.add(new Participation(C, three));

        int expected = 50 * 100 * (C.participationValue() /(1000 * 100));
        int expected2 = 50000;

        assertNotEquals(expected2, calcDiscount(C));
        assertEquals(expected, calcDiscount(C));


    }

}
