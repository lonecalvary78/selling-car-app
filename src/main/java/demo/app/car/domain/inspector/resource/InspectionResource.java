package demo.app.car.domain.inspector.resource;

import demo.app.car.domain.inspector.model.InspectionDTO;
import demo.app.car.domain.inspector.service.InspectionService;
import jakarta.inject.Inject;
import jakarta.ws.rs.Path;

import java.util.List;

@Path("/inspections")
public class InspectionResource {
  private final InspectionService inspectionService;

  @Inject
  public InspectionResource(InspectionService inspectionService) {
    this.inspectionService = inspectionService;
  }
  public List<InspectionDTO> retrieveAll() {
    inspectionService.
  }
}
