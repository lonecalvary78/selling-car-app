package demo.app.car.domain.inspector.repository;

import demo.app.car.domain.inspector.entity.Inspection;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;

@ApplicationScoped
public class InspectionRepository {
  @Inject
  private EntityManager entityManager;

  public void save(Inspection inspection) {
    entityManager.persist(inspection);
  }
}
