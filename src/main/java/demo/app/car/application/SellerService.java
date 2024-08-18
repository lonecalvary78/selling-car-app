package demo.app.car.application;

import demo.app.car.infra.exception.seller.DuplicateSellerException;
import demo.app.car.infra.exception.seller.NonExistingSellerException;
import demo.app.car.infra.mapper.seller.SellerMapper;
import demo.app.car.domain.seller.model.SellerDTO;
import demo.app.car.infra.repository.seller.SellerRepository;
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
