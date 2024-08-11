package demo.app.car.domain.inspector.resource;

import demo.app.car.domain.inspector.exception.NotFoundInspectionException;
import demo.app.car.domain.inspector.model.InspectionDTO;
import demo.app.car.domain.inspector.model.EvaluationReportDTO;
import demo.app.car.domain.inspector.service.InspectionService;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.PATCH;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;

import java.util.List;

@Path("/inspections")
public class InspectorResource {
  private InspectionService inspectionService;

  @Inject
  public InspectorResource(InspectionService inspectionService) {
    this.inspectionService = inspectionService;
  }

  public List<InspectionDTO> retrieveUnassignedInspections() {
    return inspectionService.retrieveAll();
  }

  @PATCH
  @Path("/{inspectionId}")
  public void pickUpInspection(@PathParam("inspectionId") Long inspectionId) throws NotFoundInspectionException {
    inspectionService.pickUpInspection(inspectionId, null);
  }

  @POST
  public void provideValuationOfPhysicalCondition(@Valid EvaluationReportDTO evaluationReportDTO) {
    inspectionService.provideValuationOfPhysicalCondition(evaluationReportDTO);
  }
}
