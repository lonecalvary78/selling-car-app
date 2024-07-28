package demo.app.car.domain.seller.repository;

import demo.app.car.domain.seller.entity.Seller;
import demo.app.car.domain.seller.exception.DuplicateSellerException;
import demo.app.car.domain.seller.exception.NonExistingSellerException;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import lombok.Builder;
import lombok.Data;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.security.SecureRandom;
import java.util.Arrays;
import java.util.stream.Stream;

@QuarkusTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class SellerRepositoryTest {
  @Inject
  SellerRepository sellerRepository;

  @Test
  @DisplayName("Find the Seller profile by unique ID")
  void findProfileById() throws Exception {
    var sellerId = SecureRandom.getInstanceStrong().nextLong();
    Assertions.assertThrows(NonExistingSellerException.class,()->sellerRepository.findById(sellerId));
  }

  @ParameterizedTest
  @DisplayName("Create new Seller profile")
  @MethodSource("getTestCases")
  void createNewSellerProfile(TestCase testCase) {
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

  private Stream<TestCase> getTestCases() {
    return Arrays.asList(
            TestCase.builder().input(newSeller("John","Doe","john.doe@gmail.com"))
                    .isThrownException(false).thrownExceptionClass(null).build(),
            TestCase.builder().input(newSeller("John","Doe","john.doe@gmail.com"))
                    .isThrownException(true).thrownExceptionClass(DuplicateSellerException.class).build()
    ).stream();
  }
  private Seller newSeller(String firstName, String lastName, String email) {
    var seller = new Seller();
    seller.setFirstName(firstName);
    seller.setLastName(lastName);
    seller.setEmail(email);
    return seller;
  }
}

@Data
@Builder
class TestCase {
  private Seller input;
  private boolean isThrownException;
  private Class thrownExceptionClass;
}