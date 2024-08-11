package demo.app.car.domain.inspector.entity;

import demo.app.car.domain.inspector.model.status.InspectionStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class Inspection {
  @Id
  @GeneratedValue
  private Long id;
  @OneToOne(mappedBy = "inspection", cascade = CascadeType.ALL, orphanRemoval = true)
  private CarDetail carDetail;

  @OneToOne(mappedBy = "inspection", cascade = CascadeType.ALL, orphanRemoval = true)
  private EvaluationReport evaluationReport;

  @Column(name = "pickup_by")
  private Long pickupBy;

  @Enumerated(EnumType.ORDINAL)
  private InspectionStatus inspectionStatus;
}
