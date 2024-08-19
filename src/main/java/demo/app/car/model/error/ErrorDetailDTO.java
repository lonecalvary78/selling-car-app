package demo.app.car.model.error;

import jakarta.json.bind.annotation.JsonbPropertyOrder;

@JsonbPropertyOrder({"statusCode","errorMessage"})
public record ErrorDetailDTO(int statusCode, String errorMessage) {
  public static ErrorDetailDTO of(int statusCode, String errorMessage) {
    return new ErrorDetailDTO(statusCode, errorMessage);
  }
}
