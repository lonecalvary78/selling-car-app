package demo.app.car.domain.inspection.service;

import demo.app.car.domain.inspection.entity.Inspection;
import demo.app.car.domain.inspection.mapper.InspectionMapper;
import demo.app.car.domain.inspection.model.CarDetailDTO;
import demo.app.car.domain.inspection.model.EvaluationDTO;
import demo.app.car.domain.inspection.model.InspectionDTO;
import demo.app.car.domain.inspection.model.status.InspectionStatus;
import demo.app.car.domain.inspection.repository.InspectionRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class InspectionService {
  private final InspectionRepository inspectionRepository;

  @Inject
  public InspectionService(InspectionRepository inspectionRepository) {
    this.inspectionRepository = inspectionRepository;
  }

  public List<InspectionDTO> getAllNewlyCreatedInspections() {
    return inspectionRepository.getAllNewlyCreatedInspections().stream().map(inspection -> InspectionMapper.INSTANCE.fromEntity(inspection)).toList();
  }

  public void bookForNewInspection(CarDetailDTO carDetailDTO) {
    var inspection = new Inspection();
    inspection.setCarDetailDTO(carDetailDTO);
    inspection.setStatus(InspectionStatus.CREATED);
    inspection.setCreatedAt(LocalDateTime.now());
    inspectionRepository.save(inspection);
  }

  public void pickupInspection(Long inspectionId, Long inspectorId) {
    var inspection = Optional.ofNullable(inspectionRepository.findById(inspectionId)).orElseThrow();
    inspection.setPickupByInspectorId(inspectorId);
    inspection.setStatus(InspectionStatus.PENDING_INSPECTION);
    inspectionRepository.save(inspection);
  }

  public void submitEvaluationReport(Long inspectionId, EvaluationDTO evaluationDTO) {
    if(evaluationDTO == null)
      throw new IllegalArgumentException("Missing evaluation");
    else {
      var inspection = Optional.ofNullable(inspectionRepository.findById(inspectionId)).orElseThrow();
      inspection.setEvaluationDTO(evaluationDTO);
      inspection.setStatus(InspectionStatus.COMPLETED);
      inspection.setEvaluatedAt(LocalDateTime.now());
    }
  }
}
