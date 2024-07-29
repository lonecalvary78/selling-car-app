package demo.app.car.domain.seller.model;

import jakarta.validation.constraints.NotBlank;

public record SellerDTO(Long id, @NotBlank String firstName, @NotBlank String lastName, @NotBlank String email) {
  public static SellerDTO of(Long id, String firstName, String lastName, String email) {
    return new SellerDTO(id, firstName, lastName, email);
  }
}
