package demo.app.car.domain.inspector.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@Setter
@Getter
public class CarDetail {
  @Id
  @GeneratedValue
  private Long id;
  private String brand;
  private String model;
  private String variant;
  private String transmission;
  private int yearOfCarManufactured;
  private int mileage;
}
