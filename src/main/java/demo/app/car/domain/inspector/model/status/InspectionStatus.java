package demo.app.car.domain.inspector.model.status;

import lombok.Getter;

@Getter
public enum InspectionStatus {
  CREATED("created"),
  ASSIGNED("assigned"),
  COMPLETED("completed");

  private final String name;

  InspectionStatus(String name) {
    this.name = name;
  }
}
