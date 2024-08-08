package demo.app.car.domain.inspector.model;

public record CarDetailDTO(String brand,
                           String model,
                           String variant,
                           String transmission,
                           int yearOfCarManufactured,
                           int mileage) {
  public static CarDetailDTO of(String brand,
                                String model,
                                String variant,
                                String transmission,
                                int yearOfCarManufactured,
                                int mileage) {
    return new CarDetailDTO(brand, model, variant, transmission, yearOfCarManufactured, mileage);
  }
}
