package id.pos.kaseer.controlles;

import id.pos.kaseer.DTOs.request.CategoryReqDto;
import id.pos.kaseer.DTOs.response.CategoryResDto;
import id.pos.kaseer.models.Category;
import id.pos.kaseer.services.category.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class CategoryController {

   @Autowired
   private CategoryService categoryService;

   @PostMapping("/categories")
   private ResponseEntity<String> postCategory(@RequestBody CategoryReqDto category) {
      Boolean isSucces = this.categoryService.addCategory(category);
      if (isSucces.equals(true)) {return new ResponseEntity<>("Succes Added", HttpStatus.OK);}
      throw new RuntimeException();
   }

   @GetMapping("/categories")
   private ResponseEntity<List<CategoryResDto>> getAllCategory() {
      try {
         return new ResponseEntity<>(this.categoryService.getAllCategories(), HttpStatus.OK);
      } catch (Exception e) {
         return new ResponseEntity<>(new ArrayList<>(), HttpStatus.NOT_FOUND);
      }
   }

   @GetMapping("/categories/{id}")
   private ResponseEntity<CategoryResDto> getOneCategory(@PathVariable Integer id) {
      try {
         return new ResponseEntity<>(this.categoryService.getSingleCategory(id), HttpStatus.OK);
      } catch (Exception e) {
         throw new RuntimeException("Data Not Found");
      }
   }

   @PutMapping("/category/{id}")
   private ResponseEntity<String> editCategory(@RequestBody CategoryReqDto categoryReqDto,
                                               @PathVariable Integer id) {
      Boolean isSucces = this.categoryService.editCategory(categoryReqDto, id);
      if (isSucces.equals(true)) {return new ResponseEntity<>("Succes Edit", HttpStatus.OK);}
      throw new RuntimeException();
   }

   @DeleteMapping("/category/{id}")
   private ResponseEntity<String> deleteCategory(@RequestBody CategoryReqDto categoryReqDto,
                                               @PathVariable Integer id) {
      Boolean isSucces = this.categoryService.deleteCategory(id);
      if (isSucces.equals(true)) {return new ResponseEntity<>("Succes Delete", HttpStatus.OK);}
      throw new RuntimeException();
   }

}
