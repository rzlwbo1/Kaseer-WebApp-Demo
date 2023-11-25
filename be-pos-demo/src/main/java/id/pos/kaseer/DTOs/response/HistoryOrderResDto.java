package id.pos.kaseer.DTOs.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class HistoryOrderResDto {
   private Integer idOrder;
   private String produk;
   private Integer qty;
   private String customerName;
   private Timestamp orderDate;
}
