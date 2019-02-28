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
        int n = SUT.addCustomer("new", "new@gmail.com");
        Customer C = SUT.findCustomer(c);
        Customer G = SUT.findCustomer(g);
        Customer N = SUT.findCustomer(n);

        //Customer C = new Customer(0,"name", "name@email.com");
        Service one = new Service(1, "service one", 333);
        Service two = new Service(2, "service two", 99333);
        Service three = new Service(3, "service two", 334);

        Service four = new Service(4, "service one", 333);
        Service five = new Service(5, "service two", 333);
        Service six = new Service(6, "service two", 333);

        Service seven = new Service(7,"service", 98999);

        C.participations.add(new Participation(C, one));
        C.participations.add(new Participation(C, two));
        C.participations.add(new Participation(C, three));

        G.participations.add(new Participation(G, four));
        G.participations.add(new Participation(G, five));
        G.participations.add(new Participation(G, six));

        N.participations.add(new Participation(N, one));
        N.participations.add(new Participation(N, three));
        N.participations.add(new Participation(N, six));
        N.participations.add(new Participation(N, seven));

        assertEquals(99999, N.participationValue());
        assertEquals(100000, C.participationValue());
        assertFalse(applicable(G));
        assertTrue(applicable(C));
        assertFalse(applicable(N));
        Map<Customer,Integer> test = SUT.resolve();
    }

    @Test
    public void calcDiscountTest(){
        setupDB() ;
        ApplicationLogic SUT = new ApplicationLogic() ;

        int c = SUT.addCustomer("name", "name@email.com");
        Customer C = SUT.findCustomer(c);

        int g = SUT.addCustomer("guest", "name@email.com");
        Customer G = SUT.findCustomer(g);

        Service one = new Service(1, "service one", 33333);
        Service two = new Service(2, "service two", 33334);
        Service three = new Service(3, "service three", 33333);
        Service four = new Service(4, "service four", 33332);
        C.participations.add(new Participation(C, one));
        C.participations.add(new Participation(C, two));
        C.participations.add(new Participation(C, three));

        G.participations.add(new Participation(G, one));
        G.participations.add(new Participation(G, two));
        G.participations.add(new Participation(G, four));

        int expected = 50 * 100 * (C.participationValue() /(1000 * 100));
        int expected2 = 50000;

        assertNotEquals(expected2, calcDiscount(C));
        assertEquals(expected, calcDiscount(C));
        assertEquals(0, calcDiscount(G));


    }

}
