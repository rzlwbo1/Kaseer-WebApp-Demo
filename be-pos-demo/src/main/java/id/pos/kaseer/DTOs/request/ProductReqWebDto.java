package id.pos.kaseer.DTOs.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductReqWebDto {
   private Integer idProduk;
   private String name;
   private String description;
   private Integer stok;
   private String pathGambar;
   private String categoryName;
}
