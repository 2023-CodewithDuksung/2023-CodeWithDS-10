package com.dukbab.service;

import com.dukbab.configuration.SecurityUtil;
import com.dukbab.domain.Member;
import com.dukbab.domain.Menu;
import com.dukbab.domain.Store;
import com.dukbab.domain.isOpen;
import com.dukbab.dto.MenuDTO;
import com.dukbab.dto.MenuRequestDto;
import com.dukbab.dto.StoreDto;
import com.dukbab.dto.StoreIsOpenDto;
import com.dukbab.repository.MemberRepository;
import com.dukbab.repository.MenuRepository;
import com.dukbab.repository.StoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class MenuService {

    private final MenuRepository menuRepository;
    private final MemberRepository memberRepository;

    private final StoreRepository storeRepository;


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
        // 모든 가게의 정보 파악하기
        List<StoreDto> storeDtoList = new ArrayList<>();
        for(Store store: storeRepository.findAll()){
            StoreDto storeDto = StoreDto.builder()
                    .isOpen(store.getIsOpen())
                    .congestion(store.getCongestion())
                    .build();
            storeDtoList.add(storeDto);
        }

        Member member = memberRepository.findById(SecurityUtil.getCurrentMemberId()).orElseThrow(()-> new RuntimeException("로그인 유저 정보가 없습니다."));
        MenuRequestDto menuRequestDto = MenuRequestDto.builder()
                .nickname(member.getNickname())
                .storeDtoList(storeDtoList)
                .menuDTOList(getAllMenuDTOs())
                .build();
//                        .storeIsOpen(StoreIsOpenDto.builder()
//                        .store_1(storeRepository.findByStoreId(1).getIsOpen())
//                        .store_2(storeRepository.findByStoreId(2).getIsOpen())
//                        .store_3(storeRepository.findByStoreId(3).getIsOpen())
//                        .store_4(storeRepository.findByStoreId(4).getIsOpen())
//                        .store_5(storeRepository.findByStoreId(5).getIsOpen())
//                        .store_6(storeRepository.findByStoreId(6).getIsOpen())
//                        .store_7(storeRepository.findByStoreId(7).getIsOpen())
//                        .build())
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
//        List<isOpen> isOpenList = new ArrayList<>();
//        for(Store store: storeRepository.findAll()){
//            isOpenList.add(store.getIsOpen());
//            System.out.println(store.getIsOpen());
//        }
        Member member = memberRepository.findById(SecurityUtil.getCurrentMemberId()).orElseThrow(()-> new RuntimeException("로그인 유저 정보가 없습니다."));
        MenuRequestDto menuRequestDto = MenuRequestDto.builder()
                        .nickname(member.getNickname()) // .storeIsOpen 값 넣어야 함.
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

    @Transactional
    @Scheduled(cron = "0 0/30 * * * ?") // 매 30분마다 실행
    public void storeIsOpen(){
        for(Store store: storeRepository.findAll()){
            String operationHours = store.getOperationHours();
            String[] hours = operationHours.split("-");
            LocalTime openingTime = LocalTime.parse(hours[0], DateTimeFormatter.ofPattern("HH:mm"));
            LocalTime closingTime = LocalTime.parse(hours[1], DateTimeFormatter.ofPattern("HH:mm"));

            LocalTime currentTime = LocalTime.now();

            if (currentTime.isBefore(openingTime) || currentTime.isAfter(closingTime)) {
                store.setIsOpen(isOpen.CLOSE);
            } else {
                store.setIsOpen(isOpen.OPEN);
            }
        }
    }

    // 특정 시간에 스케줄링하여 실행되도록 설정
    @Transactional
    @Scheduled(fixedRate = 1000) // 매 1초마다 실행
    public void setCongestion(){
        for(Store store:storeRepository.findAll()){
            int totalLimit = 0;
            int totalOrder = 0;
            for(Menu menu: store.getMenus()){
                totalLimit += 15;
                totalOrder += menu.getCnt();
            }
            if(store.getStoreId() == 1){
                totalLimit = 100;
            }
            double congestionRation = (double) totalOrder/totalLimit; // 실수형으로 나누기
            if(congestionRation <= 1.0/3){
                store.setCongestion(1); // 원활
            }else if (congestionRation <= 2.0/3){
                store.setCongestion(2); // 보통
            }else{
                store.setCongestion(3); // 혼잡
            }
        }
    }




}
