package demo.app.car.domain.inspection.repository;

import demo.app.car.domain.inspection.entity.Inspection;
import demo.app.car.domain.inspection.model.status.InspectionStatus;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;

import java.util.List;

@ApplicationScoped
public class InspectionRepository {
  @Inject
  private EntityManager entityManager;

  @Transactional
  public List<Inspection> getAllNewlyCreatedInspections() {
    return entityManager.createQuery("select inspection from Inspection inspection where inspection.status=:status",Inspection.class).setParameter("status", InspectionStatus.CREATED).getResultList();
  }

  @Transactional
  public Inspection findById(Long inspectionId) {
    return entityManager.find(Inspection.class, inspectionId);
  }

  @Transactional
  public void save(Inspection inspection) {
    entityManager.persist(inspection);
  }
}
