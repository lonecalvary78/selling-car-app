package demo.app.car.infra.exception.handler;

import demo.app.car.infra.exception.DuplicateSellerException;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.Provider;

@Provider
public class DuplicateSellerExceptionHandler
        implements GenericApplicationErrorHandler<DuplicateSellerException> {
  @Override
  public Response toResponse(DuplicateSellerException duplicateSellerException) {
    return Response.status(Response.Status.CONFLICT).entity(constructErrorDetail(Response.Status.CONFLICT, duplicateSellerException)).build();
  }
}
