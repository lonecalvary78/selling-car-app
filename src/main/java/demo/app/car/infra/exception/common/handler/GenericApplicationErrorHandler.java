package demo.app.car.infra.exception.common.handler;

import demo.app.car.infra.exception.common.model.ErrorDetailDTO;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;

public interface GenericApplicationErrorHandler<E extends Exception> extends ExceptionMapper<E> {
  default ErrorDetailDTO constructErrorDetail(Response.Status responseStatus, E throwException) {
    return ErrorDetailDTO.of(responseStatus.getStatusCode(), throwException.getMessage());
  }
}
