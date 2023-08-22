package com.dukbab;

import com.dukbab.domain.Menu;
import com.dukbab.domain.Store;
import com.dukbab.domain.isOpen;
import com.dukbab.repository.MenuRepository;
import com.dukbab.repository.StoreRepository;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.time.LocalTime;

@Component
@Transactional
public class DataInitializer implements ApplicationRunner {

    // 오류 수정 test
    private final MenuRepository menuRepository;
    private final StoreRepository storeRepository;

    public DataInitializer(MenuRepository menuRepository, StoreRepository storeRepository) {
        this.menuRepository = menuRepository;
        this.storeRepository = storeRepository;
    }

    @Override
    public void run(ApplicationArguments args){

        // 실행 시 메뉴와 가게가 등록된다.

        // 가게 등록
        // 1. 아침의 밥상
        Store store1 = new Store();
        store1.setStoreId(1);
        store1.setName("아침의 밥상");
        store1.setLocalTime(LocalTime.now()); // 현재 시각 설정
        store1.setOperationHours("8:00-10:00");
        store1.setCongestion(1);

        LocalTime currentTime = store1.getLocalTime();
        LocalTime openingTime = LocalTime.parse("08:00");
        LocalTime closingTime = LocalTime.parse("10:00");

        if (currentTime.isBefore(openingTime) || currentTime.isAfter(closingTime)) {
            store1.setIsOpen(isOpen.CLOSE);
        } else {
            store1.setIsOpen(isOpen.OPEN);
        }
        storeRepository.save(store1);

        // 2. 오늘의 메뉴
        Store store2 = new Store();
        store2.setStoreId(2);
        store2.setName("오늘의 메뉴");
        store2.setLocalTime(LocalTime.now()); // 현재 시각 설정
        store2.setOperationHours("11:00-18:00");
        store2.setCongestion(1);

        currentTime = store2.getLocalTime();
        openingTime = LocalTime.parse("00:00");
        closingTime = LocalTime.parse("18:00");

        if (currentTime.isBefore(openingTime) || currentTime.isAfter(closingTime)) {
            store2.setIsOpen(isOpen.CLOSE);
        } else {
            store2.setIsOpen(isOpen.OPEN);
        }
        storeRepository.save(store2);




        // 메뉴 등록
        // 1. 아침의 밥상 - 아침의 밥상
        Menu menu1 = new Menu();
        menu1.setMenuId(1);
        menu1.setStore(store1);
        menu1.setName("1000원 밥상");
        menu1.setPrice(1000);
        menu1.setOriginIng("배추[국내산]");
        menu1.setAllergicIng("땅콩");
        menu1.setTime(5);
        menu1.setRating(0.0);
        menu1.setCnt(0);

        menuRepository.save(menu1);

        // 2. 오늘의 메뉴 - 오늘이 메뉴
    }

}
