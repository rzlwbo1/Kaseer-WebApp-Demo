package id.pos.kaseer.services.menu;

import id.pos.kaseer.DTOs.request.MenuReqDto;
import id.pos.kaseer.DTOs.response.MenuResDto;
import id.pos.kaseer.DTOs.response.ProductResDto;
import id.pos.kaseer.models.Menu;
import id.pos.kaseer.models.Product;
import id.pos.kaseer.repository.MenuRepo;
import id.pos.kaseer.repository.ProductRepo;
import id.pos.kaseer.services.product.ProductService;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class MenuServiceImpl implements MenuService{

   @Autowired
   private static final ModelMapper mapper = new ModelMapper();

   @Autowired
   private MenuRepo menuRepo;

   @Autowired
   private ProductRepo productRepo;

   @Autowired
   private ProductService productService;

   @Override
   public Boolean addMenu(MenuReqDto menu) {
      try {
         Product getSingleProd = this.productRepo.findByName(menu.getProdukName());
         if (getSingleProd != null) {
            Menu newMenu = new Menu();
            newMenu.setProduct(getSingleProd);
            newMenu.setPrice(menu.getPrice());
            newMenu.setIsActive(menu.getIsActive());
            this.menuRepo.save(newMenu);
            return true;
         }
         throw new RuntimeException();
      } catch (RuntimeException e) {
         throw new RuntimeException();
      }
   }

   @Override
   public List<MenuResDto> getAllMenus() {
      try {
         List<Object[]> callSp = this.menuRepo.spGetAllMenus();
         List<MenuResDto> dataMenus = new ArrayList<>();

         for (Object[] data : callSp) {
            MenuResDto menu = new MenuResDto();
            menu.setIdMenu((Integer) data[0]);
            menu.setName((String) data[1]);
            menu.setDescription((String) data[2]);
            menu.setPathGambar((String) data[3]);
            menu.setStok((Integer) data[4]);
            menu.setPrice((Double) data[5]);
            menu.setIsActive((Boolean) data[6]);
            menu.setProductId((Integer) data[7]);

            dataMenus.add(menu);
         }

         if (dataMenus.size() > 0) {
            return dataMenus;
         }
         throw new RuntimeException();
      } catch (RuntimeException e) {
         System.out.println(e.getMessage());
         throw new RuntimeException();
      }
   }

   @Override
   public MenuResDto getSingleMenu(Integer id) {
      try {
         Optional<Menu> oneMenu = this.menuRepo.findById(id);
         if (oneMenu.isPresent()) {
            ProductResDto oneProd = this.productService.getSingleProduct(oneMenu.get().getProduct().getId());
            MenuResDto menu = MenuResDto.builder()
                    .idMenu(oneMenu.get().getId())
                    .name(oneProd.getName())
                    .description(oneProd.getDescription())
                    .stok(oneProd.getStok())
                    .pathGambar(oneProd.getPathGambar())
                    .price(oneMenu.get().getPrice())
                    .isActive(oneMenu.get().getIsActive())
                    .productId(oneMenu.get().getProduct().getId())
                    .build();
            return menu;
         }
         throw new RuntimeException();
      } catch (RuntimeException e) {
         System.out.println(e.getMessage());
         throw new RuntimeException();
      }
   }

   @Override
   public Boolean editMenu(MenuReqDto menu, Integer id) {
      try {
         Optional<Menu> getMenu = this.menuRepo.findById(id);
         Product getProd = this.productRepo.findByName(menu.getProdukName());
         if (getMenu.isPresent() && (getProd != null)) {
            getMenu.get().setProduct(getProd);
            getMenu.get().setId(id);
            getMenu.get().setPrice(menu.getPrice());
            getMenu.get().setIsActive(menu.getIsActive());
            this.menuRepo.save(getMenu.get());
            return true;
         }
         throw new RuntimeException();
      } catch (RuntimeException e) {
         System.out.println(e.getMessage());
         throw new RuntimeException();
      }
   }

   @Override
   public Boolean deleteMenu(Integer id) {
      try {
         this.menuRepo.deleteById(id);
         Optional<Menu> oneMenu = this.menuRepo.findById(id);
         if (oneMenu.isEmpty()) {
            return true;
         }
         throw new RuntimeException();
      } catch (RuntimeException e) {
         System.out.println(e.getMessage());
         throw new RuntimeException();
      }
   }
}
