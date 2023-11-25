package id.pos.kaseer.repository;

import id.pos.kaseer.DTOs.response.MenuResDto;
import id.pos.kaseer.models.Menu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MenuRepo extends JpaRepository<Menu, Integer> {
   @Procedure
   List<Object[]> spGetAllMenus();

}
