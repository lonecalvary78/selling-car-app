package demo.app.car.test.common.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class TestCase<T extends Object> {
  private final T input;
  private final boolean isThrownException;
  private final Class thrownExceptionClass;
}
