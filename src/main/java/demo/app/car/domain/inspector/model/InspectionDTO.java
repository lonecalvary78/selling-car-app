package demo.app.car.domain.inspector.model;

public record InspectionDTO(Long id, CarDetailDTO carDetailDTO) {
  public static InspectionDTO of(Long id, CarDetailDTO carDetailDTO) {
    return new InspectionDTO(id, carDetailDTO);
  }
}
