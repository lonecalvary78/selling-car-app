package demo.app.car.domain.car.model;

public record CarSellingRequestDTO(
   Long sellerId,
   String brand,
   String model,
   String variant,
   String transmission,
   String yearOfCarManufactured
) {
}
