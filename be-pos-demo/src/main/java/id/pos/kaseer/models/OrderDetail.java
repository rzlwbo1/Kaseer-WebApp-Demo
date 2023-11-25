package id.pos.kaseer.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "order_details")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderDetail {

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Integer id;
   private String product;
   private Integer qty;
   private Double price;
   private Double subtotal;
   private Double tax;
   private LocalDateTime order_date;

   @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
   @JoinColumn(name = "order_id", referencedColumnName = "id")
   private Order order;

   @ManyToOne(fetch = FetchType.EAGER)
   @JoinColumn(name = "product_id", referencedColumnName = "id")
   private Product productOrder;

}
