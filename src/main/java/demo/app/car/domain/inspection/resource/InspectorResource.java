package demo.app.car.domain.inspection.resource;

import demo.app.car.domain.inspection.model.CarDetailDTO;
import demo.app.car.domain.inspection.model.EvaluationDTO;
import demo.app.car.domain.inspection.model.InspectionDTO;
import demo.app.car.domain.inspection.service.InspectionService;
import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import org.eclipse.microprofile.jwt.JsonWebToken;

import java.util.List;

@Path("/inspections")
public class InspectorResource {
  private final InspectionService inspectionService;
  private final JsonWebToken jsonWebToken;

  @Inject
  public InspectorResource(InspectionService inspectionService, JsonWebToken jsonWebToken) {
    this.inspectionService = inspectionService;
    this.jsonWebToken = jsonWebToken;
  }

  @GET
  public List<InspectionDTO> getAllNewlyCreatedInspections() {
    return inspectionService.getAllNewlyCreatedInspections();
  }

  @POST
  public void bookForNewInspection(@Valid CarDetailDTO carDetailDTO) {
    inspectionService.bookForNewInspection(carDetailDTO);
  }

  @PATCH
  @RolesAllowed("inspectors")
  public void pickupInspection(Long inspectionId) {
    var pickupByInspectorId = Long.valueOf(jsonWebToken.getClaim("inspectId").toString());
    inspectionService.pickupInspection(inspectionId, pickupByInspectorId);
  }

  @PUT
  @Path("/{inspectionId}")
  public void submitEvaluationReport(@PathParam("inspectionId") Long inspectionId,
                                     @Valid EvaluationDTO evaluationDTO) {
    inspectionService.submitEvaluationReport(inspectionId, evaluationDTO);
  }
}
