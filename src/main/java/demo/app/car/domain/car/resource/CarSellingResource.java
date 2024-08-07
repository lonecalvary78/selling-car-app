package demo.app.car.domain.car.resource;

import demo.app.car.domain.car.mediator.InspectionMediator;
import demo.app.car.domain.car.model.CarSellingRequestDTO;
import jakarta.inject.Inject;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;

@Path("cars")
public class CarSellingResource {
  private final InspectionMediator inspectionMediator;

  @Inject
  public CarSellingResource(InspectionMediator inspectionMediator) {
    this.inspectionMediator = inspectionMediator;
  }

  @POST
  public void submitCarProfileForNewInspection(CarSellingRequestDTO carSellingRequestDTO) {
    inspectionMediator.submitCarProfileForNewInspection(carSellingRequestDTO);
  }
}
