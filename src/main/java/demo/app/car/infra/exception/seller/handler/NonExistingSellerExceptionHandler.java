package demo.app.car.infra.exception.seller.handler;

import demo.app.car.infra.exception.seller.NonExistingSellerException;
import demo.app.car.infra.exception.common.handler.GenericApplicationErrorHandler;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.Provider;

@Provider
public class NonExistingSellerExceptionHandler implements GenericApplicationErrorHandler<NonExistingSellerException> {
  @Override
  public Response toResponse(NonExistingSellerException nonExistingSellerException) {
    return Response.status(Response.Status.BAD_REQUEST).entity(constructErrorDetail(Response.Status.BAD_REQUEST,nonExistingSellerException)).build();
  }
}
