package demo.app.car.domain.inspector.service;

import demo.app.car.domain.inspector.exception.NotFoundInspectionException;
import demo.app.car.domain.inspector.mapper.CarDetailMapper;
import demo.app.car.domain.inspector.mapper.custom.InspectionMapper;
import demo.app.car.domain.inspector.model.CarDetailDTO;
import demo.app.car.domain.inspector.model.InspectionDTO;
import demo.app.car.domain.inspector.model.EvaluationReportDTO;
import demo.app.car.domain.inspector.model.InspectionRequestDTO;
import demo.app.car.domain.inspector.model.status.InspectionStatus;
import demo.app.car.domain.inspector.repository.InspectionRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class InspectionService {
  private final InspectionRepository inspectionRepository;

  @Inject
  public InspectionService(InspectionRepository inspectionRepository) {
    this.inspectionRepository = inspectionRepository;
  }

  public List<InspectionDTO> retrieveAll() {
    return inspectionRepository.retrieveAll().stream().map(inspection -> InspectionDTO.of(inspection.getId(), CarDetailDTO.of(inspection.getCarDetail().getBrand(),inspection.getCarDetail().getModel(),inspection.getCarDetail().getVariant(),inspection.getCarDetail().getTransmission(),inspection.getCarDetail().getYearOfCarManufactured(),inspection.getCarDetail().getMileage()))).toList();
  }

  public InspectionDTO getInspectionDetailById(Long inspectionId) {
    return Optional.ofNullable(inspectionRepository.getInspectionDetailById(inspectionId)).map(inspection -> new InspectionDTO(inspection.getId(), CarDetailMapper.INSTANCE.fromEntity(inspection.getCarDetail()))).orElseThrow();
  }

  public void createNewInspection(InspectionRequestDTO inspectionRequestDTO) {
    inspectionRepository.save(InspectionMapper.getInstance().fromPayload(inspectionRequestDTO));
  }

  public void pickUpInspection(Long inspectionId, Long inspectorId) throws NotFoundInspectionException {
    var inspection = Optional.ofNullable(inspectionRepository.getInspectionDetailById(inspectionId)).orElseThrow(NotFoundInspectionException::new);
    inspection.setPickupBy(inspectorId);
    inspection.setInspectionStatus(InspectionStatus.ASSIGNED);
    inspectionRepository.save(inspection);
  }

  public void provideValuationOfPhysicalCondition(EvaluationReportDTO evaluationReportDTO) {
    var inspection = inspectionRepository.getInspectionDetailById(evaluationReportDTO.inspectionId());
    inspection.setInspectionStatus(InspectionStatus.COMPLETED);
  }
}
