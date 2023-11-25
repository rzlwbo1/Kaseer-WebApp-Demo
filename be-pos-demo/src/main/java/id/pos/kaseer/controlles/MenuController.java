package id.pos.kaseer.controlles;

import id.pos.kaseer.DTOs.request.MenuReqDto;
import id.pos.kaseer.DTOs.response.MenuResDto;
import id.pos.kaseer.models.Menu;
import id.pos.kaseer.repository.MenuRepo;
import id.pos.kaseer.services.menu.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class MenuController {

   @Autowired
   private MenuService menuService;

   @PostMapping("/menus")
   private ResponseEntity<String> postMenu(@RequestBody MenuReqDto menuReqDto) {
      Boolean isSucces = this.menuService.addMenu(menuReqDto);
      if (isSucces.equals(true)) {return new ResponseEntity<>("Succes Added", HttpStatus.OK);}
      return null;
   }

   @GetMapping("/menus")
   private ResponseEntity<List<MenuResDto>> getAllMenus() {
      try {
         return new ResponseEntity<>(this.menuService.getAllMenus(), HttpStatus.OK);
      } catch (RuntimeException e) {
         System.out.println(e.getMessage());
         return new ResponseEntity<>(new ArrayList<>(), HttpStatus.OK);
      }
   }

   @GetMapping("/menus/{id}")
   private ResponseEntity<MenuResDto> getOneMenu(@PathVariable Integer id) {
      try {
         return new ResponseEntity<>(this.menuService.getSingleMenu(id), HttpStatus.OK);
      } catch (RuntimeException e) {
         System.out.println(e.getMessage());
         throw new RuntimeException();
      }
   }

   @PutMapping("/menus/{id}")
   private ResponseEntity<String> editMenu(@RequestBody MenuReqDto menuReqDto,
                                           @PathVariable Integer id) {
      try {
         Boolean isSucces = this.menuService.editMenu(menuReqDto, id);
         if (isSucces) {
            return new ResponseEntity<>("Succes Edit", HttpStatus.OK);
         }
         return null;
      } catch (RuntimeException e) {
         System.out.println(e.getMessage());
         throw new RuntimeException();
      }
   }

   @DeleteMapping("/menus/{id}")
   private ResponseEntity<String> deleteMenu(@PathVariable Integer id) {
      try {
         Boolean isSucces = this.menuService.deleteMenu(id);
         if (isSucces) {
            return new ResponseEntity<>("Succes Delete", HttpStatus.OK);
         }
         return null;
      } catch (RuntimeException e) {
         System.out.println(e.getMessage());
         throw new RuntimeException();
      }
   }

}
