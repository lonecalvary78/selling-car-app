package demo.app.car.domain.inspection.model;

import java.time.LocalDateTime;

public record InspectionDTO(
        Long id,
        CarDetailDTO carDetailDTO,
        EvaluationDTO evaluationDTO,
        Long pickupByInspectorId,
        LocalDateTime createdAt,
        LocalDateTime evaluatedAt) {
}
