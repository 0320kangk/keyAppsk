package project.keyappsk.domain.orders.except;

public class BuyerSellerEqualException  extends RuntimeException{
    public BuyerSellerEqualException(String message) {
        super(message);
    }
}
