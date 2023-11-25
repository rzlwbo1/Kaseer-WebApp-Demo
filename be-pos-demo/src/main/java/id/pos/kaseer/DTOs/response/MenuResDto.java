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
public class MenuResDto {
   private Integer idMenu;
   private String name;
   private String description;
   private String pathGambar;
   private Integer stok;
   private Double price;
   private Boolean isActive;
   private Integer productId;
}
