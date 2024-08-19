package demo.app.car.infra.exception.handler;

import demo.app.car.model.error.ErrorDetailDTO;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;

public interface GenericApplicationErrorHandler<E extends Exception> extends ExceptionMapper<E> {
  default ErrorDetailDTO constructErrorDetail(Response.Status responseStatus, E throwException) {
    return ErrorDetailDTO.of(responseStatus.getStatusCode(), throwException.getMessage());
  }
}
