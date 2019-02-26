package Participation;
import org.junit.* ;
import static org.junit.Assert.* ;

public class Discount_5pack_test extends Discount_5pack{
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
        System.out.println("** Testing Discount_5pack applicable method");
        ApplicationLogic SUT = new ApplicationLogic() ;

        int c = SUT.addCustomer("name", "name@email.com");
        Customer C = SUT.findCustomer(c);
        Service one = new Service(1, "service one", 300);
        Service two = new Service(2, "service two", 350);
        Service three = new Service(2, "service two", 300);
        Service four = new Service(2, "service two", 300);
        Service five = new Service(2, "service two", 300);
        Service six = new Service(2, "service two", 300);
        C.participations.add(new Participation(C, one));
        C.participations.add(new Participation(C, two));
        C.participations.add(new Participation(C, three));
        C.participations.add(new Participation(C, four));
        C.participations.add(new Participation(C, five));
        C.participations.add(new Participation(C, six));

        assertTrue(applicable(C));
    }

    @Test
    public void calcDiscountTest(){
        setupDB() ;
        System.out.println("** Testing Discount_5pack calcDiscount method");
        ApplicationLogic SUT = new ApplicationLogic() ;

        int c = SUT.addCustomer("name", "name@email.com");
        Customer C = SUT.findCustomer(c);
        assertEquals(10, calcDiscount(C));

    }
}
