package demo.app.car.domain.inspector.model;

import jakarta.validation.constraints.Positive;

public record EvaluationReportDTO(Long inspectionId,
                                  @Positive int engineCondition,
                                  @Positive int steeringCondition,
                                  @Positive int brakeCondition,
                                  @Positive int otherPartsCondition) {
  public static EvaluationReportDTO of(Long inspectionId,
                                       int engineCondition,
                                       int steeringCondition,
                                       int brakeCondition,
                                       int otherPartsCondition) {
    return new EvaluationReportDTO(inspectionId, engineCondition, steeringCondition, brakeCondition, otherPartsCondition);
  }
}
