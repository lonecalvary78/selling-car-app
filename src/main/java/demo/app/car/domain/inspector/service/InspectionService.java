package demo.app.car.domain.inspector.service;

import demo.app.car.domain.inspector.mapper.CarDetailMapper;
import demo.app.car.domain.inspector.mapper.custom.InspectionMapper;
import demo.app.car.domain.inspector.model.CarDetailDTO;
import demo.app.car.domain.inspector.model.InspectionDTO;
import demo.app.car.domain.inspector.model.InspectionRequestDTO;
import demo.app.car.domain.inspector.repository.InspectionRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.List;

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



  public void createNewInspection(InspectionRequestDTO inspectionRequestDTO) {
    inspectionRepository.save(InspectionMapper.getInstance().fromPayload(inspectionRequestDTO));
  }
}
