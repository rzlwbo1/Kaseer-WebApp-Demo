package id.pos.kaseer.controlles;

import id.pos.kaseer.DTOs.request.ProductReqDto;
import id.pos.kaseer.DTOs.response.ProductResDto;
import id.pos.kaseer.models.Product;
import id.pos.kaseer.services.product.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class ProductController {

   @Autowired
   private ProductService productService;

   @PostMapping("/products")
   private ResponseEntity<String> postProduct(@RequestBody ProductReqDto product) {
      Boolean isSucces = this.productService.addProduct(product);
      if (isSucces.equals(true)) {return new ResponseEntity<>("Succes Added", HttpStatus.OK);}
      return null;
   }

   @GetMapping("/products")
   private ResponseEntity<List<ProductResDto>> getAllProducts() {
      try {
         List<ProductResDto> getAll = this.productService.getAllProducts();
         return new ResponseEntity<>(getAll, HttpStatus.OK);
      } catch (Exception e) {
         return new ResponseEntity<>(new ArrayList<>(), HttpStatus.NOT_FOUND);
      }
   }


   @GetMapping("/products/{id}")
   private ResponseEntity<ProductResDto> getOneProduct(@PathVariable Integer id) {
      try {
         ProductResDto getAll = this.productService.getSingleProduct(id);
         return new ResponseEntity<>(getAll, HttpStatus.OK);
      } catch (Exception e) {
         throw new RuntimeException("Data Not Found");
      }
   }

   @PutMapping("/products/{id}")
   private ResponseEntity<String> editProduct(@RequestBody ProductReqDto productReqDto,
                                              @PathVariable Integer id) {
      Boolean isSucces = this.productService.editProduct(productReqDto, id);
      if (isSucces.equals(true)) {return new ResponseEntity<>("Succes Added", HttpStatus.OK);}
      return null;
   }

   @DeleteMapping("/products/{id}")
   private ResponseEntity<String> deleteProduct(@PathVariable Integer id) {
      Boolean isSucces = this.productService.deleteProduct(id);
      if (isSucces.equals(true)) {return new ResponseEntity<>("Succes Deleted", HttpStatus.OK);}
      return null;
   }

}
