package demo.app.car.domain.car.resource;

import demo.app.car.domain.car.model.CarSellingRequestDTO;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;

@Path("cars")
public class CarSellingResource {
  @POST
  public void submitCarProfileForNewInspection(CarSellingRequestDTO carSellingRequestDTO) {

  }
}
