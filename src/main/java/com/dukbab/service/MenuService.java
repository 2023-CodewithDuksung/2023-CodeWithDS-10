package com.dukbab.service;

import com.dukbab.domain.Menu;
import com.dukbab.dto.MenuDTO;
import com.dukbab.repository.MenuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class MenuService {

    private final MenuRepository menuRepository;


    @Autowired
    public MenuService(MenuRepository menuRepository){
        this.menuRepository = menuRepository;
    }

    // 모든 메뉴 조회
    public List<Menu> getAllMenus(){
        return menuRepository.findAll();
    }


    // 모든 메뉴 정보를 DTO 형식으로 조회
    public List<MenuDTO> getAllMenuDTOs() {
        List<Menu> menus = menuRepository.findAll();
        return menus.stream()
                .map(menu -> {
                    MenuDTO menuDTO = new MenuDTO();
                    menuDTO.setName(menu.getName());
                    menuDTO.setPrice(menu.getPrice());
                    menuDTO.setTime(menu.getTime());
                    return menuDTO;
                })
                .collect(Collectors.toList());
    }

    public List<MenuDTO> getDetailMenuDTOs(int menuId) {
        Optional<Menu> menus = menuRepository.findById(menuId);

        return menus.stream()
                .map(menu -> {
                    MenuDTO menuDTO = new MenuDTO();
                    menuDTO.setName(menu.getName());
                    menuDTO.setPrice(menu.getPrice());
                    menuDTO.setCnt(menu.getCnt());
                    menuDTO.setContent(menu.getContent());
                    menuDTO.setAllergicIng(menu.getAllergicIng());
                    menuDTO.setOriginIng(menu.getOriginIng());
                    menuDTO.setTime(menu.getTime());

                    return menuDTO;
                })
                .collect(Collectors.toList());
    }

    public Optional<Menu> getMenuById(int menuId){
        return menuRepository.findById(menuId);}

    // menu 추가
    public Menu createMenu(Menu menu){
        return menuRepository.save(menu);
    }

    // menu 수정
    public Menu updateMenu(Menu menu) {
        return menuRepository.save(menu);
    }

    // menu 삭제
    public void deleteMenu(int menuId) {
        menuRepository.deleteById(menuId);
    }

}
