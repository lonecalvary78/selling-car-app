package demo.app.car.infra.repository.seller;

import demo.app.car.domain.seller.entity.Seller;
import demo.app.car.infra.exception.seller.DuplicateSellerException;
import demo.app.car.infra.exception.seller.NonExistingSellerException;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;

import java.util.Optional;

@ApplicationScoped
public class SellerRepository {
  @Inject
  private EntityManager entityManager;

  public Seller findById(Long sellerId) throws NonExistingSellerException {
    return Optional.ofNullable(entityManager.find(Seller.class, sellerId)).orElseThrow(NonExistingSellerException::new);
  }

  @Transactional
  public Seller createNewProfile(Seller seller) throws DuplicateSellerException {
    if (anyOtherSellerHasTheSameProfileWith(seller)) {
      throw new DuplicateSellerException();
    } else {
      entityManager.persist(seller);
    }
    return seller;
  }

  private boolean anyOtherSellerHasTheSameProfileWith(Seller seller) {
    return entityManager.createNamedQuery("countSellerWhoHasTheSameProfileWith", Long.class)
            .setParameter("firstName", seller.getFirstName())
            .setParameter("lastName", seller.getLastName())
            .setParameter("email", seller.getEmail())
            .getSingleResult() > 0;
  }
}
