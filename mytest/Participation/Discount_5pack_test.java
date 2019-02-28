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
        int g = SUT.addCustomer("guest", "guest@email.com");
        Customer G = SUT.findCustomer(g);

        Discount D = new Testing_Discount();
        SUT.awardDiscount(c, "D5pack");

        Service one = new Service(1, "service one", 100);
        Service two = new Service(2, "service two", 150);
        Service three = new Service(3, "service two", 100);
        Service four = new Service(4, "service two", 150);
        Service five = new Service(5, "service two", 100);
        Service six = new Service(6, "service two", 100);

        Service seven = new Service(7, "service two", 99);
        Service eight = new Service(8, "service two", 99);
        Service nine = new Service(9, "service two", 99);
        Service ten = new Service(10, "service two", 99);
        Service ele = new Service(11, "service two", 99);

        C.participations.add(new Participation(C, one));
        C.participations.add(new Participation(C, two));
        C.participations.add(new Participation(C, three));
        C.participations.add(new Participation(C, four));
        C.participations.add(new Participation(C, five));
        //C.participations.add(new Participation(C, six));

        G.participations.add(new Participation(G, seven));
        G.participations.add(new Participation(G, eight));
        G.participations.add(new Participation(G, nine));
        G.participations.add(new Participation(G, ten));
        G.participations.add(new Participation(G, ele));


        //SUT.awardDiscount(c, "D5pack");
        //assertFalse(applicable(C));
        assertTrue(applicable(C));
        assertFalse(applicable(G));
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
