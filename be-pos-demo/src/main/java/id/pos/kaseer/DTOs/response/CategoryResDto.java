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
public class CategoryResDto {
   private String name;
   private LocalDateTime createdDate;
   private LocalDateTime updatedDate;
}
