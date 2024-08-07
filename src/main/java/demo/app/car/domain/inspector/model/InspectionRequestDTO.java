package demo.app.car.domain.inspector.model;

public record InspectionRequestDTO(
        Long sellerId,
        CarDetailDTO carDetailDTO) {
}
