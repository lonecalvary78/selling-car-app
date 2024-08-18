package demo.app.car.infra.exception.seller;

public class DuplicateSellerException extends Exception {
  public DuplicateSellerException() {
    super("Unable to create a new profile since this seller already registered before");
  }
}
