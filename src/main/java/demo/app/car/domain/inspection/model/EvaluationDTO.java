package demo.app.car.domain.inspection.model;

import jakarta.validation.constraints.Positive;

public record EvaluationDTO(@Positive int engineCondition,
                            @Positive int steeringCondition,
                            @Positive int brakeCondition,
                            @Positive int otherPartsCondition) {
}
