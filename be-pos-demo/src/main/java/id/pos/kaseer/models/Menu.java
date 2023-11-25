package id.pos.kaseer.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "menus")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Menu {

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Integer id;
   private Double price;
   private Boolean isActive;

   @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
   @JoinColumn(name = "product_id", referencedColumnName = "id")
   private Product product;

}
