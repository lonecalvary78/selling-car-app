package demo.app.car.domain.seller.model;

import jakarta.json.bind.annotation.JsonbPropertyOrder;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@JsonbPropertyOrder({"id","firstName","lastName","email","createdAt","lastModifiedAt"})
public record SellerDTO(Long id,
                        @NotNull
                        @NotBlank
                        String firstName,
                        @NotNull
                        @NotBlank
                        String lastName,
                        @NotNull
                        @NotBlank
                        String email) {
}
