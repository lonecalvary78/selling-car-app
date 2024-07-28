package demo.app.car.mapper;

import demo.app.car.domain.seller.entity.Seller;
import demo.app.car.model.SellerDTO;
import lombok.Getter;

public class SellerMapper {
  @Getter
  private static SellerMapper instance = new SellerMapper();

  private SellerMapper() {}

  public Seller fromDTO(SellerDTO sellerDTO) {
    var seller = new Seller();
    if(sellerDTO.id() != null)
      seller.setId(sellerDTO.id());
    seller.setFirstName(sellerDTO.firstName());
    seller.setLastName(sellerDTO.lastName());
    seller.setEmail(sellerDTO.email());
    return seller;
  }

  public SellerDTO fromEntity(Seller seller) {
    return SellerDTO.of(seller.getId(), seller.getFirstName(), seller.getLastName(), seller.getEmail());
  }
}
