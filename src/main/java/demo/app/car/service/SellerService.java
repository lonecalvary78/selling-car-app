package demo.app.car.service;

import demo.app.car.domain.seller.exception.DuplicateSellerException;
import demo.app.car.domain.seller.exception.NonExistingSellerException;
import demo.app.car.domain.seller.repository.SellerRepository;
import demo.app.car.mapper.SellerMapper;
import demo.app.car.model.SellerDTO;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.Optional;

@ApplicationScoped
public class SellerService {
  @Inject
  private SellerRepository sellerRepository;

  public SellerDTO findProfileById(Long sellerId) throws NonExistingSellerException {
    return SellerMapper.getInstance().fromEntity(sellerRepository.findById(sellerId));
  }

  public void createNewProfile(SellerDTO sellerDTO) throws DuplicateSellerException {
     var seller = SellerMapper.getInstance().fromDTO(sellerDTO);
     sellerRepository.createNewProfile(seller);
  }
}
