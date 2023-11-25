package id.pos.kaseer.DTOs.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductResDto {
   private Integer idProduk;
   private String name;
   private String description;
   private Integer stok;
   private String pathGambar;
   private LocalDateTime createdDate;
   private LocalDateTime updatedDate;
   private CategoryResDto category;
}
