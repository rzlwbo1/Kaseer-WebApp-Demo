package id.pos.kaseer.services.product;

import id.pos.kaseer.DTOs.request.ProductReqDto;
import id.pos.kaseer.DTOs.request.ProductReqWebDto;
import id.pos.kaseer.DTOs.response.ProductResDto;
import id.pos.kaseer.models.Category;
import id.pos.kaseer.models.Product;
import id.pos.kaseer.repository.CategoryRepo;
import id.pos.kaseer.repository.ProductRepo;
import id.pos.kaseer.services.FileUploadService;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class ProductServiceImpl implements ProductService{

   @Autowired
   private static final ModelMapper mapper = new ModelMapper();

   @Autowired
   private ProductRepo productRepo;

   @Autowired
   private CategoryRepo categoryRepo;

   @Autowired
   private FileUploadService fileUploadService;

   protected static final PropertyMap<Product, ProductResDto> mapIdProduk = new PropertyMap<Product, ProductResDto>() {
      @Override
      protected void configure() {
         map().setIdProduk(source.getId());
      }
   };

   static {
      mapper.addMappings(mapIdProduk);
   }

   @Override
   public Boolean addProduct(ProductReqDto product) {
      try {
         Product newProd = new Product();
         newProd.setName(product.getName());
         newProd.setDescription(product.getDescription());
         newProd.setStok(product.getStok());

         String sanitizeImage = product.getPathGambar().replaceAll(" ", "_");

         newProd.setPathGambar(sanitizeImage);
         newProd.setCreatedDate(LocalDateTime.now());
         newProd.setUpdatedDate(LocalDateTime.now());

         Category foundCategory = this.categoryRepo.findByName(product.getCategoryName());
         newProd.setCategory(foundCategory);

         this.productRepo.save(newProd);
         return true;
      } catch (Exception e) {
         System.out.println(e.getMessage());
         return false;
      }
   }

   @Override
   public Boolean addProductImage(ProductReqWebDto product, MultipartFile image) {
      try {
         // save image
         this.fileUploadService.save(image);

         Product newProd = new Product();
         newProd.setName(product.getName());
         newProd.setDescription(product.getDescription());
         newProd.setStok(product.getStok());
         String sanitizeImage = image.getOriginalFilename().replaceAll(" ", "_");
         newProd.setPathGambar(sanitizeImage);
         newProd.setCreatedDate(LocalDateTime.now());
         newProd.setUpdatedDate(LocalDateTime.now());

         Category foundCategory = this.categoryRepo.findByName(product.getCategoryName());
         newProd.setCategory(foundCategory);

         this.productRepo.save(newProd);
         return true;
      } catch (Exception e) {
         System.out.println(e.getMessage());
         return false;
      }
   }

   @Override
   public List<ProductResDto> getAllProducts() {
      try {
         List<Product> allProd = this.productRepo.findAll();
         List<ProductResDto> getAllProd = allProd.stream()
                 .map(prod -> mapper
                         .map(prod, ProductResDto.class))
                 .collect(Collectors.toList());

         return getAllProd;
      } catch (Exception e) {
         System.out.println(e.getMessage());
         return new ArrayList<>();
      }
   }

   @Override
   public ProductResDto getSingleProduct(Integer id) {
      try {
         Optional<Product> getProduct = this.productRepo.findById(id);
         if (getProduct.isPresent()) {
            return mapper.map(getProduct, ProductResDto.class);
         }
         throw new RuntimeException();
      } catch (RuntimeException e) {
         System.out.println(e.getMessage());
         throw new RuntimeException();
      }
   }

   @Override
   public Boolean editProduct(ProductReqDto product, Integer id) {
      try {
         Optional<Product> getProduct = this.productRepo.findById(id);
         Category getCat = this.categoryRepo.findByName(product.getCategoryName());
         if (getProduct.isPresent()) {
            getProduct.get().setName(product.getName());
            getProduct.get().setDescription(product.getDescription());
            String sanitizeImage = product.getPathGambar().replaceAll(" ", "_");
            getProduct.get().setPathGambar(sanitizeImage);
            getProduct.get().setId(id);
            getProduct.get().setStok(product.getStok());
            getProduct.get().setUpdatedDate(LocalDateTime.now());
            getProduct.get().setCategory(getCat);
            return true;
         }
         throw new RuntimeException();
      } catch (RuntimeException e) {
         System.out.println(e.getMessage());
         throw new RuntimeException();
      }
   }

   @Override
   public Boolean editProductImage(ProductReqWebDto product, MultipartFile image) {
      try {
         Optional<Product> getProduct = this.productRepo.findById(product.getIdProduk());
         Category getCat = this.categoryRepo.findByName(product.getCategoryName());
         if (getProduct.isPresent()) {
            getProduct.get().setName(product.getName());
            getProduct.get().setDescription(product.getDescription());

            if (image.isEmpty()) {
               getProduct.get().setPathGambar(getProduct.get().getPathGambar());
            } else {
               this.fileUploadService.save(image);
               String sanitizeImage = image.getOriginalFilename().replaceAll(" ", "_");
               getProduct.get().setPathGambar(sanitizeImage);
            }

            getProduct.get().setId(product.getIdProduk());
            getProduct.get().setStok(product.getStok());
            getProduct.get().setUpdatedDate(LocalDateTime.now());
            getProduct.get().setCategory(getCat);
            return true;
         }
         throw new RuntimeException();
      } catch (RuntimeException e) {
         System.out.println(e.getMessage());
         throw new RuntimeException();
      }
   }

   @Override
   public Boolean deleteProduct(Integer id) {
      try {
         this.productRepo.deleteById(id);

         Optional<Product> getProd = this.productRepo.findById(id);
         if (getProd.isEmpty()) {return true;}
         throw new RuntimeException("Not found");
      } catch (RuntimeException e) {
         System.out.println(e.getMessage());
         throw new RuntimeException("Not found");
      }
   }
}
