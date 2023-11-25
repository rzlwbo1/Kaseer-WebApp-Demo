package id.pos.kaseer.controlles;

import id.pos.kaseer.DTOs.request.OrderReqDto;
import id.pos.kaseer.DTOs.request.ProductReqWebDto;
import id.pos.kaseer.services.FileUploadService;
import id.pos.kaseer.services.category.CategoryService;
import id.pos.kaseer.services.menu.MenuService;
import id.pos.kaseer.services.product.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class WebController {

   @Autowired
   private MenuService menuService;

   @Autowired
   private ProductService productService;

   @Autowired
   private CategoryService categoryService;

   @Autowired
   private FileUploadService fileUploadService;

   @GetMapping("/")
   public String index(Model model) {

      model.addAttribute("menus", this.menuService.getAllMenus());
      return "index.html";
   }

   @GetMapping("/manage-produk")
   public String pageManage(Model model) {

      model.addAttribute("products", this.productService.getAllProducts());
      model.addAttribute("categories", this.categoryService.getAllCategories());
      model.addAttribute("newProduct", new ProductReqWebDto());
      return "pages/manage-produk.html";
   }

   @PostMapping("/post-produk")
   public String postProduk(@ModelAttribute("newProduct") ProductReqWebDto reqDto,
                            @RequestParam(value = "image", required = false) MultipartFile image) {
      try {
         Boolean isSucces = this.productService.addProductImage(reqDto, image);
         if (isSucces.equals(true)) return "redirect:/manage-produk";
         throw new RuntimeException();
      } catch (Exception e) {
         System.out.println(e.getMessage());
         throw new RuntimeException();
      }
   }

   @PostMapping("/put-produk")
   public String updateProduk(@ModelAttribute("newProduct") ProductReqWebDto reqDto,
                            @RequestParam(value = "image", required = false) MultipartFile image) {
      try {
         Boolean isSucces = this.productService.editProductImage(reqDto, image);
         if (isSucces.equals(true)) return "redirect:/manage-produk";
         throw new RuntimeException();
      } catch (Exception e) {
         System.out.println(e.getMessage());
         throw new RuntimeException();
      }
   }

   @GetMapping("/image/{filename}")
   public ResponseEntity<Resource> getImage(@PathVariable("filename") String filename) {
      Resource image = this.fileUploadService.load(filename);
      return ResponseEntity.ok()
              .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" +image.getFilename() + "\"")
              .body(image);
   }

   @PostMapping("/test")
   public ResponseEntity<String> testingAPI(@RequestBody OrderReqDto orderReqDto) {
      return new ResponseEntity<>("Success", HttpStatus.OK);
   }
}
