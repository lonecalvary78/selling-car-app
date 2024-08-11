package demo.app.car.domain.seller.repository;

import demo.app.car.domain.seller.comment.test.model.TestCase;
import demo.app.car.domain.seller.entity.Seller;
import demo.app.car.domain.seller.exception.DuplicateSellerException;
import demo.app.car.domain.seller.exception.NonExistingSellerException;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import java.security.SecureRandom;
import java.util.stream.Stream;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

@QuarkusTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class SellerRepositoryTest {
  @Inject
  SellerRepository sellerRepository;

  @Test
  @Order(1)
  @DisplayName("Find the Seller profile by unique ID")
  void findProfileById() throws Exception {
    var sellerId = SecureRandom.getInstanceStrong().nextLong();
    Assertions.assertThrows(NonExistingSellerException.class,()->sellerRepository.findById(sellerId));
  }

  @ParameterizedTest
  @Order(2)
  @DisplayName("Create new Seller profile")
  @MethodSource("getTestCases")
  void createNewSellerProfile(TestCase<Seller> testCase) {
    if(testCase != null) {
      var input = testCase.getInput();
      var isThrowException = testCase.isThrownException();
      var thrownException = testCase.getThrownExceptionClass();

      if(!isThrowException) {
        Assertions.assertDoesNotThrow(()->sellerRepository.createNewProfile(input));
      } else {
        Assertions.assertThrows(thrownException,()->sellerRepository.createNewProfile(input));
      }
    }
  }

  private Stream<TestCase<Seller>> getTestCases() {
    return Stream.of(
            ofTestCase(newSeller("John","Doe","john.doe@gmail.com"), false, null),
            ofTestCase(newSeller("John","Doe","john.doe@gmail.com"), true, DuplicateSellerException.class)
    );
  }
  private Seller newSeller(String firstName, String lastName, String email) {
    var seller = new Seller();
    seller.setFirstName(firstName);
    seller.setLastName(lastName);
    seller.setEmail(email);
    return seller;
  }

  private TestCase<Seller> ofTestCase(Seller input, boolean isThrownException, Class thrownExceptionClass) {
    return new TestCase<>(input, isThrownException, thrownExceptionClass);
  }
}