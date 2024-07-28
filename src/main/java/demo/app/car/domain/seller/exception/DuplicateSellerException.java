package demo.app.car.domain.seller.exception;

public class DuplicateSellerException extends Exception {
  public DuplicateSellerException() {
    super("Unable to create a new profile since this seller already registered before");
  }
}
