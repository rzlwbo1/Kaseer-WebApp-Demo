package id.pos.kaseer.DTOs.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ChangeOrderResDto {
   private Integer orderId;
   private Boolean success;
   private Double change;
}
