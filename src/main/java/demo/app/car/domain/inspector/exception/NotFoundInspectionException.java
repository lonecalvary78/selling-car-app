package demo.app.car.domain.inspector.exception;

public class NotFoundInspectionException extends Exception {
  public NotFoundInspectionException() {
    super("Unable to find the detail of inspection since it was not not exist");
  }
}
