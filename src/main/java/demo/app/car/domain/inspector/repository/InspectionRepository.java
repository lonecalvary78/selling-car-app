package demo.app.car.domain.inspector.repository;

import demo.app.car.domain.inspector.entity.Inspection;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;

import java.util.List;

@ApplicationScoped
public class InspectionRepository {
  @Inject
  private EntityManager entityManager;

  public List<Inspection> retrieveAll() {
    return entityManager.createQuery("select inspection from Inspection inspection", Inspection.class).getResultList();
  }

  public Inspection getInspectionDetailById(Long inspectionId) {
    return entityManager.find(Inspection.class, inspectionId);
  }

  public void save(Inspection inspection) {
    entityManager.persist(inspection);
  }
}
