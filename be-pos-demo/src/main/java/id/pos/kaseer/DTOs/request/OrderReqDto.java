package id.pos.kaseer.DTOs.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderReqDto {
   private String customer;
   private List<ProdukOrderDto> orders;
   private Double amount;
}
