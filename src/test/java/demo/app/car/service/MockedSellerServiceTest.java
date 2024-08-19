package demo.app.car.service;

import demo.app.car.application.SellerService;
import demo.app.car.domain.seller.entity.Seller;
import demo.app.car.model.SellerDTO;
import demo.app.car.infra.exception.DuplicateSellerException;
import demo.app.car.infra.exception.NonExistingSellerException;
import demo.app.car.infra.repository.SellerRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class MockedSellerServiceTest {
  @InjectMocks
  SellerService sellerService;

  @Mock
  SellerRepository sellerRepository;

  @Test
  @DisplayName("Find seller profile By Unique ID")
  void findProfileById() throws NonExistingSellerException {
    var sellerId = 1L;
    when(sellerRepository.findById(any())).thenReturn(newDummySeller());
    Assertions.assertAll(
            ()->Assertions.assertDoesNotThrow(()->sellerService.findProfileById(sellerId)),
            ()->Assertions.assertNotNull(sellerService.findProfileById(sellerId))
    );
    verify(sellerRepository, times(2)).findById(any());
  }

  @Test
  @DisplayName("Create new Seller profile")
  void createNewSellerProfile() throws DuplicateSellerException {
    var sellerDTO = new SellerDTO(null,"John","Doe", "john.doe@testmail.com");
    when(sellerRepository.createNewProfile(any())).thenReturn(newDummySeller());
    Assertions.assertDoesNotThrow(()->sellerService.createNewProfile(sellerDTO));
    verify(sellerRepository).createNewProfile(any());
  }
  private Seller newDummySeller() {
    var seller = new Seller();
    seller.setId(1L);
    seller.setFirstName("John");
    seller.setLastName("Doe");
    seller.setEmail("john.doe@testmail.com");
    return seller;
  }
}
