package demo.app.car.domain.car.mediator;

import demo.app.car.domain.car.model.CarSellingRequestDTO;
import demo.app.car.domain.inspector.model.CarDetailDTO;
import demo.app.car.domain.inspector.model.InspectionRequestDTO;
import demo.app.car.domain.inspector.service.InspectionService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class InspectionMediator {
  private final InspectionService inspectionService;

  @Inject
  public InspectionMediator(InspectionService inspectionService) {
    this.inspectionService = inspectionService;
  }

  public void submitCarProfileForNewInspection(CarSellingRequestDTO carSellingRequestDTO) {
    var inspectionRequestDTO = new InspectionRequestDTO(
            carSellingRequestDTO.sellerId(),
            CarDetailDTO.of(carSellingRequestDTO.brand(), carSellingRequestDTO.model(), carSellingRequestDTO.variant(), carSellingRequestDTO.transmission(), carSellingRequestDTO.yearOfCarManufactured(), carSellingRequestDTO.mileage())
    );
    inspectionService.createNewInspection(inspectionRequestDTO);
  }
}
