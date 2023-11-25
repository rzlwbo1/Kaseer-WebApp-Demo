package id.pos.kaseer.services.category;

import id.pos.kaseer.DTOs.request.CategoryReqDto;
import id.pos.kaseer.DTOs.response.CategoryResDto;
import id.pos.kaseer.DTOs.response.ProductResDto;
import id.pos.kaseer.models.Category;
import id.pos.kaseer.repository.CategoryRepo;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class CategoryServiceImpl implements CategoryService{

   @Autowired
   private static final ModelMapper mapper = new ModelMapper();

   @Autowired
   private CategoryRepo categoryRepo;

   @Override
   public Boolean addCategory(CategoryReqDto category) {
      try {
         Category newCategory = new Category();
         newCategory.setUpdatedDate(LocalDateTime.now());
         newCategory.setCreatedDate(LocalDateTime.now());
         newCategory.setName(category.getName());

         this.categoryRepo.save(newCategory);
         return true;
      } catch (Exception e) {
         System.out.println(e.getMessage());
         return false;
      }
   }

   @Override
   public List<CategoryResDto> getAllCategories() {
      try {
         List<Category> allCat = this.categoryRepo.findAll();
         List<CategoryResDto> getAllCat = allCat.stream()
                 .map(cat -> mapper.map(cat, CategoryResDto.class))
                 .collect(Collectors.toList());

         return getAllCat;
      } catch (Exception e) {
         System.out.println(e.getMessage());
         return new ArrayList<>();
      }
   }

   @Override
   public CategoryResDto getSingleCategory(Integer id) {
      try {
        Optional<Category> getCat = this.categoryRepo.findById(id);
        if (getCat.isPresent()) {
           return mapper.map(getCat.get(), CategoryResDto.class);
        }
       throw new RuntimeException("Not found");
      } catch (RuntimeException e) {
         System.out.println(e.getMessage());
         throw new RuntimeException("Not found");
      }
   }

   @Override
   public Boolean editCategory(CategoryReqDto category, Integer id) {
      try {
         Optional<Category> getCat = this.categoryRepo.findById(id);
         if (getCat.isPresent()) {
            getCat.get().setUpdatedDate(LocalDateTime.now());
            getCat.get().setName(category.getName());
            this.categoryRepo.save(getCat.get());
            return true;
         }
         throw new RuntimeException("Not found");
      } catch (RuntimeException e) {
         System.out.println(e.getMessage());
         throw new RuntimeException("Not found");
      }
   }

   @Override
   public Boolean deleteCategory(Integer id) {
      try {
        this.categoryRepo.deleteById(id);

         Optional<Category> getCat = this.categoryRepo.findById(id);
         if (getCat.isEmpty()) {return true;};
        throw new RuntimeException("Not found");
      } catch (RuntimeException e) {
         System.out.println(e.getMessage());
         throw new RuntimeException("Not found");
      }
   }

}
