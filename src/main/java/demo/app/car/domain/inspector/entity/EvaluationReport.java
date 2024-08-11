package demo.app.car.domain.inspector.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
@Setter
public class EvaluationReport {
  @Id
  @GeneratedValue
  private Long id;

  @OneToOne(fetch = FetchType.LAZY)
  private Inspection inspection;

  @Column(name="engine_condition")
  private int engineCondition;

  @Column(name="steering_condition")
  private int steeringCondition;

  @Column(name="brake_condition")
  private int brakeCondition;

  @Column(name = "other_parts_condition")
  private int otherPartsCondition;

  private LocalDateTime evaluatedAt;
}
