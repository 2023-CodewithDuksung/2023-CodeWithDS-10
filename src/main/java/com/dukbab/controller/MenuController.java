package com.dukbab.controller;

import com.dukbab.dto.MenuDTO;
import com.dukbab.dto.MenuRequestDto;
import com.dukbab.service.MenuService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/menus")
public class MenuController {
    private final MenuService menuService;

    public MenuController(MenuService menuService){
        this.menuService = menuService;
    }
    // 모든 메뉴 정보 조회
    @GetMapping
    public MenuRequestDto getAllMenus(){
        return menuService.getAllMenusAndUserNickname();
    }

    // 특정 메뉴의 상세 정보를 조회
    @GetMapping("/{menuId}")
    public MenuRequestDto getMenuDetail(@PathVariable int menuId) {
        return menuService.getDetailsMenuAndUserNickname(menuId);
    }



}
