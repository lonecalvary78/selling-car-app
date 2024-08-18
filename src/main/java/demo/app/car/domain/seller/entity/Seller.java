package demo.app.car.domain.seller.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.Data;

import java.time.OffsetDateTime;
import java.time.ZoneId;

@Entity
@Table(schema="sellers")
@NamedQuery(name = "countSellerWhoHasTheSameProfileWith", query = "SELECT count(seller) FROM Seller seller WHERE seller.firstName=:firstName AND seller.lastName=:lastName AND seller.email=:email")
@Data
public class Seller {
  @Id
  @GeneratedValue
  private Long id;

  @Column(name = "first_name")
  private String firstName;

  @Column(name = "last_name")
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
