package id.pos.kaseer.controlles;

import id.pos.kaseer.DTOs.request.OrderReqDto;
import id.pos.kaseer.DTOs.response.ChangeOrderResDto;
import id.pos.kaseer.DTOs.response.HistoryOrderResDto;
import id.pos.kaseer.services.transaction.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class KasirController {

   @Autowired
   private TransactionService transactionService;

   @PostMapping("/new-order")
   private ResponseEntity<ChangeOrderResDto> postOrder(@RequestBody OrderReqDto reqDto) {
      try {
         ChangeOrderResDto isSucces = this.transactionService.makeOrder(reqDto);
         if (isSucces != null) {
            return new ResponseEntity<>(isSucces, HttpStatus.OK);
         }
         return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
      } catch (RuntimeException e) {
         System.out.println(e.getMessage());
         return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
      }
   }

   @GetMapping("/order-history")
   private ResponseEntity<List<HistoryOrderResDto>> postOrder() {
      try {
         List<HistoryOrderResDto> histories = this.transactionService.getHistoryOrders();
         return new ResponseEntity<>(histories, HttpStatus.OK);
      } catch (RuntimeException e) {
         System.out.println(e.getMessage());
         return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
      }
   }

}
