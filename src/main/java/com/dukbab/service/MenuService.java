package com.dukbab.service;

import com.dukbab.configuration.SecurityUtil;
import com.dukbab.domain.Member;
import com.dukbab.domain.Menu;
import com.dukbab.dto.MenuDTO;
import com.dukbab.dto.MenuRequestDto;
import com.dukbab.repository.MemberRepository;
import com.dukbab.repository.MenuRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class MenuService {

    private final MenuRepository menuRepository;
    private final MemberRepository memberRepository;


    @Transactional
    // 모든 메뉴 조회
    public List<Menu> getAllMenus(){
        return menuRepository.findAll();
    }


    // 모든 메뉴 정보를 DTO 형식으로 조회
    @Transactional
    public List<MenuDTO> getAllMenuDTOs() {
        List<Menu> menus = menuRepository.findAll();
        return menus.stream()
                .map(menu -> {
                    MenuDTO menuDTO = new MenuDTO();
                    menuDTO.setMenuId(menu.getMenuId());
                    menuDTO.setName(menu.getName());
                    menuDTO.setPrice(menu.getPrice());
                    menuDTO.setTime(menu.getTime());
                    menuDTO.setMenuStatus(menu.getMenuStatus());
                    menuDTO.setStore(menu.getStore().getStoreId());
                    menuDTO.setCnt(menu.getCnt());
                    return menuDTO;
                })
                .collect(Collectors.toList());
    }

    // 모든 메뉴 정보를 Dto 형식으로 조회 + 사용자 닉네임
    @Transactional
    public MenuRequestDto getAllMenusAndUserNickname(){
        Member member = memberRepository.findById(SecurityUtil.getCurrentMemberId()).orElseThrow(()-> new RuntimeException("로그인 유저 정보가 없습니다."));
        MenuRequestDto menuRequestDto = MenuRequestDto.builder()
                .nickname(member.getNickname())
                .menuDTOList(getAllMenuDTOs())
                .build();
        return menuRequestDto;
    }

    @Transactional
    public List<MenuDTO> getDetailMenuDTOs(int menuId) {
        Optional<Menu> menus = menuRepository.findById(menuId);

        return menus.stream()
                .map(menu -> {
                    MenuDTO menuDTO = new MenuDTO();
                    menuDTO.setMenuId(menu.getMenuId());
                    menuDTO.setName(menu.getName());
                    menuDTO.setPrice(menu.getPrice());
                    menuDTO.setStore(menu.getStore().getStoreId());
                    menuDTO.setCnt(menu.getCnt());
                    menuDTO.setContent(menu.getContent());
                    menuDTO.setAllergicIng(menu.getAllergicIng());
                    menuDTO.setOriginIng(menu.getOriginIng());
                    menuDTO.setTime(menu.getTime());
                    menuDTO.setMenuStatus(menu.getMenuStatus());
                    menuDTO.setRating(menu.getRating());
                    menuDTO.setImageUrl(menu.getImageUrl());

                    return menuDTO;
                })
                .collect(Collectors.toList());
    }

    @Transactional
    public MenuRequestDto getDetailsMenuAndUserNickname(int menuId){
        Member member = memberRepository.findById(SecurityUtil.getCurrentMemberId()).orElseThrow(()-> new RuntimeException("로그인 유저 정보가 없습니다."));
        MenuRequestDto menuRequestDto = MenuRequestDto.builder()
                .nickname(member.getNickname())
                .menuDTOList(getDetailMenuDTOs(menuId))
                .build();
        return menuRequestDto;
    }

    @Transactional
    public Optional<Menu> getMenuById(int menuId){
        return menuRepository.findById(menuId);}

    // menu 추가
    @Transactional
    public Menu createMenu(Menu menu){
        return menuRepository.save(menu);
    }

    // menu 수정
    @Transactional
    public Menu updateMenu(Menu menu) {
        return menuRepository.save(menu);
    }

    // menu 삭제
    @Transactional
    public void deleteMenu(int menuId) {
        menuRepository.deleteById(menuId);
    }

    @Transactional
    public Menu findMenuByName(String name){
        return menuRepository.findByName(name)
                .orElseThrow(() -> new RuntimeException("찾는 메뉴가 없습니다: " + name));
    }

}
