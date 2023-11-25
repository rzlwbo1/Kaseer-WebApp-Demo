package id.pos.kaseer.services.category;

import id.pos.kaseer.DTOs.request.CategoryReqDto;
import id.pos.kaseer.DTOs.response.CategoryResDto;

import java.util.List;

public interface CategoryService {
   Boolean addCategory(CategoryReqDto category);
   List<CategoryResDto> getAllCategories();
   CategoryResDto getSingleCategory(Integer id);
   Boolean editCategory(CategoryReqDto category, Integer id);
   Boolean deleteCategory(Integer id);
}
