package demo.app.car.domain.inspector.exception.handler;

import demo.app.car.domain.inspector.exception.NotFoundInspectionException;
import demo.app.car.infra.exception.handler.GenericApplicationErrorHandler;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.Provider;

@Provider
public class NotFoundInspectionExceptionHandler implements GenericApplicationErrorHandler<NotFoundInspectionException> {
  @Override
  public Response toResponse(NotFoundInspectionException notFoundInspectionException) {
    return Response.status(Response.Status.BAD_REQUEST).entity(constructErrorDetail(Response.Status.BAD_REQUEST, notFoundInspectionException)).build();
  }
}
