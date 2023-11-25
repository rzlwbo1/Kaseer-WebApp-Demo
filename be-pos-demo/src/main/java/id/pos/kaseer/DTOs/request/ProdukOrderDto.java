package id.pos.kaseer.DTOs.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProdukOrderDto {
   private String produk;
   private Integer qty;
   private Double price;
}
