package demo.app.car.domain.inspection.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

public record CarDetailDTO(
        @NotBlank String brand,
        @NotBlank String model,
        @NotBlank String variant,
        @NotBlank String transmission,
        @Positive int yearOfManufacture) {
}
