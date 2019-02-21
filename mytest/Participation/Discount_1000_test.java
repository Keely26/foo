package Participation;
import org.junit.* ;
import static org.junit.Assert.* ;

public class Discount_1000_test extends Discount_1000{
    @Test
    public void applicableTest(){
        Customer C = new Customer(0,"name", "name@email.com");
        Service one = new Service(1, "service one", 33333);
        Service two = new Service(2, "service two", 33334);
        Service three = new Service(2, "service two", 33333);
        C.participations.add(new Participation(C, one));
        C.participations.add(new Participation(C, two));
        C.participations.add(new Participation(C, three));

        assertTrue(applicable(C));
    }

    @Test
    public void calcDiscountTest(){
        Customer C = new Customer(0,"name", "name@email.com");
        Service one = new Service(1, "service one", 33333);
        Service two = new Service(2, "service two", 33334);
        Service three = new Service(2, "service two", 33333);
        C.participations.add(new Participation(C, one));
        C.participations.add(new Participation(C, two));
        C.participations.add(new Participation(C, three));

        int expected = 50 * 100 * (C.participationValue() /(1000 * 100));
        int expected2 = 5000;

        assertEquals(expected2, calcDiscount(C));
    }

}
