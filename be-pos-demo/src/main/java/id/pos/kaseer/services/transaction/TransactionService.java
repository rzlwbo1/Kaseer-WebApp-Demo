package id.pos.kaseer.services.transaction;

import id.pos.kaseer.DTOs.request.OrderReqDto;
import id.pos.kaseer.DTOs.response.ChangeOrderResDto;
import id.pos.kaseer.DTOs.response.HistoryOrderResDto;

import java.util.List;

public interface TransactionService {
   ChangeOrderResDto makeOrder(OrderReqDto orderReqDto);
   List<HistoryOrderResDto> getHistoryOrders();
}
