package id.pos.kaseer.repository;

import id.pos.kaseer.models.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepo  extends JpaRepository<Order, Integer> {
   @Query("SELECT o FROM Order o ORDER BY o.id DESC LIMIT 1")
   Order findLatestOrder();
   @Procedure
   List<Object[]> spGetAllHistoryOrders();
}
