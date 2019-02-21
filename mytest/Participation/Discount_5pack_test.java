package Participation;
import org.junit.* ;
import static org.junit.Assert.* ;

public class Discount_5pack_test extends Discount_5pack{

    @Test
    public void applicableTest(){
        Customer C = new Customer(0,"name", "name@email.com");
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
        Customer C = new Customer(0,"name", "name@email.com");
        assertEquals(10, calcDiscount(C));
    }


}
