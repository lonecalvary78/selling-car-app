package demo.app.car.domain.inspector.entity;

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
}
