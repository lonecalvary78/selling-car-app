package demo.app.car.domain.inspector.model;

public record InspectionDTO(Long id, CarDetailDTO carDetailDTO, Long pickupBy, String status) {
  public static InspectionDTO of(Long id, CarDetailDTO carDetailDTO, Long pickupBy, String status) {
    return new InspectionDTO(id, carDetailDTO, pickupBy, status);
  }
}
