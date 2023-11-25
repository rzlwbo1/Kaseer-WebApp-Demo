package id.pos.kaseer.models;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "products")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Product {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Integer id;
   @Column(unique = true)
   private String name;
   private String description;
   private Integer stok;
   private String pathGambar;
   private LocalDateTime createdDate;
   private LocalDateTime updatedDate;

   @ManyToOne(fetch = FetchType.EAGER)
   @JoinColumn(name = "category_id", referencedColumnName = "id")
   private Category category;

}
