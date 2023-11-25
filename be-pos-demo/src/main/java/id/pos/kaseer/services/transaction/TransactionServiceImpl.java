package id.pos.kaseer.services.transaction;

import id.pos.kaseer.DTOs.request.OrderReqDto;
import id.pos.kaseer.DTOs.request.ProdukOrderDto;
import id.pos.kaseer.DTOs.response.ChangeOrderResDto;
import id.pos.kaseer.DTOs.response.HistoryOrderResDto;
import id.pos.kaseer.models.Customer;
import id.pos.kaseer.models.Order;
import id.pos.kaseer.models.OrderDetail;
import id.pos.kaseer.models.Product;
import id.pos.kaseer.repository.CustomerRepo;
import id.pos.kaseer.repository.OrderDetailRepo;
import id.pos.kaseer.repository.OrderRepo;
import id.pos.kaseer.repository.ProductRepo;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.*;

@Service
@Transactional
public class TransactionServiceImpl implements TransactionService{

   @Autowired
   private CustomerRepo customerRepo;

   @Autowired
   private OrderDetailRepo orderDetailRepo;

   @Autowired
   private OrderRepo orderRepo;

   @Autowired
   private ProductRepo productRepo;

   @Override
   public ChangeOrderResDto makeOrder(OrderReqDto orderReqDto) {
      try {
         //create data cust
         Customer addCust = new Customer();
         addCust.setName(orderReqDto.getCustomer());
         addCust.setCreatedDate(LocalDateTime.now());
         this.customerRepo.save(addCust);

         //create data Order
         // hitung total
         Double total = 0.0;
         double tax = 500.0;
         Integer totalQty = 0;
         Double totalTax = 0.0;

         for (ProdukOrderDto prod : orderReqDto.getOrders()) {
            totalQty += prod.getQty();
            total += prod.getPrice();
         }

         totalTax = totalQty * tax;
         total += totalTax;

         Order addOrder = new Order();
         addOrder.setAmount(orderReqDto.getAmount());
         addOrder.setTotal(total > 0.0 ? total : 0);
         addOrder.setCustomer(addCust);
         addOrder.setOrderDate(LocalDateTime.now());
         this.orderRepo.save(addOrder);

         //create data Order detail
         for (ProdukOrderDto prod : orderReqDto.getOrders()) {
            OrderDetail addOrderDet = new OrderDetail();
            addOrderDet.setOrder(addOrder);
            addOrderDet.setOrder_date(LocalDateTime.now());
            addOrderDet.setQty(prod.getQty());
            addOrderDet.setPrice(prod.getPrice());
            addOrderDet.setProduct(prod.getProduk());
            addOrderDet.setPrice(prod.getPrice());
            Double taxPerProduct = prod.getQty() * tax;
            addOrderDet.setSubtotal(prod.getPrice() + taxPerProduct);
            addOrderDet.setTax(tax);

            Product getProd = this.productRepo.findByName(prod.getProduk());
            if (getProd != null) {
               addOrderDet.setProductOrder(getProd);

               // update stock
               Integer newStok = getProd.getStok() - prod.getQty();
               Integer isUpdate = this.productRepo.updateStockProductById(getProd.getId(), newStok);
               if (isUpdate == null) {
                  System.out.println("Gagal update Stock!!");
                  throw new RuntimeException();
               }
            }

            this.orderDetailRepo.save(addOrderDet);
         }

         // get orderId
         Order getIdOrder = this.orderRepo.findLatestOrder();
         Double change = orderReqDto.getAmount() - total;
         ChangeOrderResDto succesOrder = ChangeOrderResDto.builder()
                 .orderId(getIdOrder.getId())
                 .success(true)
                 .change(change)
                 .build();
         return succesOrder;
      } catch (RuntimeException e) {
         System.out.println(e.getMessage());
         System.out.println("Gagal update Stock!!");
         return ChangeOrderResDto.builder()
                 .orderId(null)
                 .success(true)
                 .change(0.0)
                 .build();
      }
   }

   @Override
   public List<HistoryOrderResDto> getHistoryOrders() {
      try {
         List<HistoryOrderResDto> getAllHistroy = new ArrayList<>();

         List<Object[]> callSp = this.orderRepo.spGetAllHistoryOrders();

         for (Object[] data : callSp) {
            HistoryOrderResDto histroy = new HistoryOrderResDto();
            histroy.setIdOrder((Integer) data[0]);
            histroy.setProduk((String) data[1]);
            histroy.setQty((Integer) data[2]);
            histroy.setCustomerName((String) data[3]);
            histroy.setOrderDate((Timestamp) data[4]);

            getAllHistroy.add(histroy);
         }

         if (getAllHistroy != null && getAllHistroy.size() > 0) {
            Collections.reverse(getAllHistroy);
            return getAllHistroy;
         }

         return getAllHistroy;
      } catch (RuntimeException e) {
         System.out.println(e.getMessage());
         throw new RuntimeException();
      }
   }
}
