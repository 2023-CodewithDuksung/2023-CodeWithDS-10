package com.dukbab.controller;

import com.dukbab.dto.MenuDTO;
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

    @GetMapping
    public List<MenuDTO> getAllMenus(){
        return menuService.getAllMenuDTOs();
    }

    @GetMapping("/{menuId}")
    public List<MenuDTO> getMenuDetail(@PathVariable int menuId) {
        return menuService.getDetailMenuDTOs(menuId);
    }



}
