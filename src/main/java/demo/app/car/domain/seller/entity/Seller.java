package demo.app.car.domain.seller.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Column;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.PrePersist;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import lombok.Data;

@Entity
@NamedQuery(name = "countSellerWhoHasTheSameProfileWith", query = "SELECT count(1) FROM Seller seller WHERE seller.firstName=:firstName AND lastName=:lastName AND email=:email")
@Data
public class Seller {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String firstName;
  private String lastName;
  private String email;

  @Column(name="created_at")
  private OffsetDateTime createdAt;

  @Column(name="last_modified_at")
  private OffsetDateTime lastModifiedAt;

  @PrePersist
  void beforeSave() {
    if (getId() == null) {
      setCreatedAt(OffsetDateTime.now(ZoneId.systemDefault()));
    } else {
      setLastModifiedAt(OffsetDateTime.now(ZoneId.systemDefault()));
    }
  }
}
