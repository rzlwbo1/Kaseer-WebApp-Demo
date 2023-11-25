package id.pos.kaseer.repository;

import id.pos.kaseer.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepo extends JpaRepository<Product, Integer> {
   Product findByName(String name);
   @Modifying
   @Query("UPDATE Product p set p.stok = :newstock where p.id = :id")
   Integer updateStockProductById(@Param("id") Integer id, @Param("newstock") Integer newstock);
}
