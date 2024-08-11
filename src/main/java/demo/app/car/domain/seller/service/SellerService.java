package demo.app.car.domain.seller.service;

import demo.app.car.domain.seller.exception.DuplicateSellerException;
import demo.app.car.domain.seller.exception.NonExistingSellerException;
import demo.app.car.domain.seller.mapper.SellerMapper;
import demo.app.car.domain.seller.model.SellerDTO;
import demo.app.car.domain.seller.repository.SellerRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class SellerService {
  private final SellerRepository sellerRepository;

  @Inject
  public SellerService(SellerRepository sellerRepository) {
    this.sellerRepository=sellerRepository;
  }

  public SellerDTO findProfileById(Long sellerId) throws NonExistingSellerException {
    return SellerMapper.getInstance().fromEntity(sellerRepository.findById(sellerId));
  }

  public SellerDTO createNewProfile(SellerDTO sellerDTO) throws DuplicateSellerException {
    var seller = SellerMapper.getInstance().fromDTO(sellerDTO);
    return SellerMapper.getInstance().fromEntity(sellerRepository.createNewProfile(seller));
  }
}
