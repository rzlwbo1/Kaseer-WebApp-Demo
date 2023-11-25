package id.pos.kaseer.services.product;

import id.pos.kaseer.DTOs.request.ProductReqDto;
import id.pos.kaseer.DTOs.request.ProductReqWebDto;
import id.pos.kaseer.DTOs.response.ProductResDto;
import id.pos.kaseer.models.Product;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ProductService {
   Boolean addProduct(ProductReqDto product);
   Boolean addProductImage(ProductReqWebDto product, MultipartFile image);
   List<ProductResDto> getAllProducts();
   ProductResDto getSingleProduct(Integer id);
   Boolean editProduct(ProductReqDto product, Integer id);
   Boolean editProductImage(ProductReqWebDto product, MultipartFile image);
   Boolean deleteProduct(Integer id);
}
