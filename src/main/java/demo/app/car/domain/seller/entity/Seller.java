package demo.app.car.domain.seller.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.PrePersist;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import lombok.Data;

@Entity
@NamedQuery(name = "countSellerWhoHasTheSameProfileWith", query = "SELECT count(1) FROM Seller seller WHERE seller.firstName=:firstName AND lastName=:lastName AND email=:email")
@Data
public class Seller {
  @Id
  @GeneratedValue
  private Long id;
  private String firstName;
  private String lastName;
  private String email;

  OffsetDateTime createdAt;
  OffsetDateTime lastModifiedAt;

  @PrePersist
  void preDataPresist() {
    if (getId() == null) {
      setCreatedAt(OffsetDateTime.now(ZoneId.systemDefault()));
    } else {
      setLastModifiedAt(OffsetDateTime.now(ZoneId.systemDefault()));
    }
  }
}
