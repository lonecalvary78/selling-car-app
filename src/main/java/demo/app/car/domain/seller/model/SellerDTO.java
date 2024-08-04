package demo.app.car.domain.seller.model;

import jakarta.validation.constraints.NotBlank;

public record SellerDTO(Long id, @NotBlank String firstName, @NotBlank String lastName, @NotBlank String email) {
}
