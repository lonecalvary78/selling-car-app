package demo.app.car.domain.seller.comment.test.model;

import demo.app.car.domain.seller.entity.Seller;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@RequiredArgsConstructor
@Getter
public class TestCase<T extends Object> {
  private final T input;
  private final boolean isThrownException;
  private final Class thrownExceptionClass;
}
