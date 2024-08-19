package demo.app.car.ui;

import demo.app.car.application.SellerService;
import demo.app.car.model.SellerDTO;
import demo.app.car.infra.exception.DuplicateSellerException;
import demo.app.car.infra.exception.NonExistingSellerException;
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
class MockedSellerControllerTest {
  @InjectMocks
  SellerController sellerController;

  @Mock
  SellerService sellerService;

  @Test
  @DisplayName("Find seller profile By Unique ID")
  void findProfileById() throws NonExistingSellerException {
    var sellerId = 1L;
    when(sellerService.findProfileById(any())).thenReturn(newDummySellerDTO());
    Assertions.assertAll(
            ()->Assertions.assertDoesNotThrow(()-> sellerController.findProfileById(sellerId)),
            ()->Assertions.assertNotNull(sellerController.findProfileById(sellerId))
    );
    verify(sellerService, times(2)).findProfileById(any());
  }

  @Test
  @DisplayName("Create new Seller profile")
  void createNewSellerProfile() throws DuplicateSellerException {
    var sellerDTO = new SellerDTO(null,"John","Doe", "john.doe@testmail.com");
    when(sellerService.createNewProfile(any())).thenReturn(newDummySellerDTO());
    Assertions.assertDoesNotThrow(()-> sellerController.newProfile(sellerDTO));
    verify(sellerService).createNewProfile(any());
  }

  private SellerDTO newDummySellerDTO() {
    return new SellerDTO(1L,"John","Doe", "john.doe@testmail.com");
  }
}