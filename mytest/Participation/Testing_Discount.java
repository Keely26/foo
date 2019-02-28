package Participation;

public class Testing_Discount extends Discount {
    @Override
    public boolean applicable(Customer c) {
        return true;
    }

    @Override
    public int calcDiscount(Customer c) {
        return 200;
    }

    @Override
    public String description() {
        return null;
    }
}
