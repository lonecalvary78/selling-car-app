package demo.app.car.ui.seller;

import demo.app.car.application.seller.SellerService;
import demo.app.car.domain.seller.model.SellerDTO;
import demo.app.car.infra.exception.common.model.ErrorDetailDTO;
import demo.app.car.infra.exception.seller.DuplicateSellerException;
import demo.app.car.infra.exception.seller.NonExistingSellerException;
import io.micrometer.core.annotation.Counted;
import io.micrometer.core.annotation.Timed;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponses;

@Path("/sellers")
public class SellerController {
  private final SellerService sellerService;

  @Inject
  public SellerController(SellerService sellerService) {
    this.sellerService=sellerService;
  }


  @GET
  @Operation(description = "To retrieve detail of Seller profile based on the given unique Id")
  @Path("/{sellerId}")
  @APIResponses({
    @APIResponse(responseCode = "200", description = "display the profile of the seller based on the given unique Id", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(oneOf = SellerDTO.class))),
    @APIResponse(responseCode = "400", description = "Unable to get the profile of seller when the profile is not exist", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(oneOf = ErrorDetailDTO.class)))
  })
  @Produces(MediaType.APPLICATION_JSON)
  @Counted("findProfileById")
  @Timed("findProfileById")
  public SellerDTO findProfileById(@PathParam("sellerId") Long sellerId) throws NonExistingSellerException {
    return sellerService.findProfileById(sellerId);
  }

  @POST
  @Operation(description = "To create a new Seller profile")
  @APIResponses({
    @APIResponse(responseCode = "200", description = "The profile of new Seller is successfully created", content = @Content(mediaType = MediaType.APPLICATION_JSON)),
    @APIResponse(responseCode = "409", description = "The creation for new Seller profile is fail when the profile already created", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(oneOf = ErrorDetailDTO.class)))

  })
  @Produces(MediaType.APPLICATION_JSON)
  @Counted("newProfile")
  @Timed("newProfile")
  public SellerDTO newProfile(@Valid SellerDTO sellerDTO) throws DuplicateSellerException {
      return sellerService.createNewProfile(sellerDTO);
  }
}
