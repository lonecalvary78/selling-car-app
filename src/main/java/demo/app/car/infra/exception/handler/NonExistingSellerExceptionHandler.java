package demo.app.car.infra.exception.handler;

import demo.app.car.infra.exception.NonExistingSellerException;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.Provider;

@Provider
public class NonExistingSellerExceptionHandler implements GenericApplicationErrorHandler<NonExistingSellerException> {
  @Override
  public Response toResponse(NonExistingSellerException nonExistingSellerException) {
    return Response.status(Response.Status.BAD_REQUEST).entity(constructErrorDetail(Response.Status.BAD_REQUEST,nonExistingSellerException)).build();
  }
}
