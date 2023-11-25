package id.pos.kaseer.services.menu;

import id.pos.kaseer.DTOs.request.MenuReqDto;
import id.pos.kaseer.DTOs.response.MenuResDto;

import java.util.List;

public interface MenuService {
   Boolean addMenu(MenuReqDto menu);
   List<MenuResDto> getAllMenus();
   MenuResDto getSingleMenu(Integer id);
   Boolean editMenu(MenuReqDto menu, Integer id);
   Boolean deleteMenu(Integer id);
}
