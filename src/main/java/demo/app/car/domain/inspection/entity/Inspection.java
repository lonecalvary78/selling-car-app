package demo.app.car.domain.inspection.entity;

import demo.app.car.domain.inspection.model.CarDetailDTO;
import demo.app.car.domain.inspection.model.EvaluationDTO;
import demo.app.car.domain.inspection.model.status.InspectionStatus;
import demo.app.car.domain.inspection.type.CarDetailType;
import demo.app.car.domain.inspection.type.EvaluationType;
import jakarta.persistence.Entity;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Type;
import org.hibernate.type.descriptor.java.LocalDateJavaType;

import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class Inspection {
  @Id
  @GeneratedValue
  private Long id;

  @Type(CarDetailType.class)
  private CarDetailDTO carDetailDTO;
  @Type(EvaluationType.class)
  private EvaluationDTO evaluationDTO;

  @Enumerated
  private InspectionStatus status;

  private Long pickupByInspectorId;

  private LocalDateTime createdAt;
  private LocalDateTime evaluatedAt;
}
