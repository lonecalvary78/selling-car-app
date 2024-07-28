package demo.app.car.domain.seller.exception;

public class NonExistingSellerException extends Exception {
  public NonExistingSellerException() {
    super("Unable to find the Seller profile by the given reference since the seller is not exist");
  }
}
