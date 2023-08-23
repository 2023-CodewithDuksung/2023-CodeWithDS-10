package com.dukbab;

import com.dukbab.domain.Menu;
import com.dukbab.domain.Store;
import com.dukbab.domain.isOpen;
import com.dukbab.repository.MenuRepository;
import com.dukbab.repository.StoreRepository;
import net.bytebuddy.asm.Advice;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.time.format.DateTimeFormatter;

import static java.time.format.DateTimeFormatter.ofPattern;

@Component
@Transactional
public class DataInitializer implements ApplicationRunner {

    private final MenuRepository menuRepository;
    private final StoreRepository storeRepository;

    public DataInitializer(MenuRepository menuRepository, StoreRepository storeRepository) {
        this.menuRepository = menuRepository;
        this.storeRepository = storeRepository;
    }

    @Override
    public void run(ApplicationArguments args) {
        // store
        Store store0 = createStore("아침의 밥상", "08:00-10:00");
        Store store1 = createStore("오늘의메뉴", "11:00-18:00");
        Store store2 = createStore("파스타치요", "11:00-18:00");
        Store store3 = createStore("니나노덮밥", "11:00-18:00");
        Store store4 = createStore("일식/양식", "11:00-18:00");
        Store store5 = createStore("샌드위치카페", "11:00-18:00");
        Store store6 = createStore("토스트", "11:00-18:00");

        // menu
        Menu menu0 = createMenu(store0, "1000원 밥상", 1000, "/menu_img/item-1.jpg", null, null, 5, 0.0);
        Menu menu1 = createMenu(store1, "오늘의 메뉴", 6500, "/menu_img/item-2.jpg", null, null,5, 0.0);
        Menu menu2 = createMenu(store2, "4분돼지김치파스타", 7500, "/menu_img/item-3.jpg", null, null,10, 0.0);
        Menu menu3 = createMenu(store2, "고기리들기름파스타", 6000, "/menu_img/item-4.jpg", null, null,10, 0.0);
        Menu menu4 = createMenu(store2, "트러플버섯크림파스타", 6500, "/menu_img/item-5.jpg", null, null,10, 0.0);
        Menu menu5 = createMenu(store2, "대패삼겹크림파스타", 7500, "/menu_img/item-6.jpg", null, null,10, 0.0);
        Menu menu6 = createMenu(store2, "매콤로제파스타", 7500, "/menu_img/item-7.jpg", null, null,10, 0.0);
        Menu menu7 = createMenu(store2, "우삼겹알리오올리오파스타", 7000, "/menu_img/item-8.jpg", null, null,10, 0.0);
        Menu menu8 = createMenu(store2, "직화불막창토핑추가", 2900, "/menu_img/item-9.jpg", null, null,10, 0.0);
        Menu menu9 = createMenu(store2, "직화불쭈꾸미토핑추가", 2900, "/menu_img/item-10.jpg", null, null,10, 0.0);
        Menu menu10 = createMenu(store3, "치킨마요덮밥", 7000, "/menu_img/item-11.jpg", null, null,10, 0.0);
        Menu menu11 = createMenu(store3, "우동돈까스세트", 6200, "/menu_img/item-12.jpg", null, null,10, 0.0);
        Menu menu12 = createMenu(store3, "제육덮밥", 6500, "/menu_img/item-13.jpg", null, null,10, 0.0);
        Menu menu13 = createMenu(store3, "닭갈비덮밥", 7000, "/menu_img/item-14.jpg", null, null,10, 0.0);
        Menu menu14 = createMenu(store3, "오므라이스", 7000, "/menu_img/item-15.jpg", null, null,10, 0.0);
        Menu menu15 = createMenu(store3, "불닭마요", 6500, "/menu_img/item-16.jpg", null, null,10, 0.0);
        Menu menu16 = createMenu(store4, "제주흑돼지돈까스", 6500, "/menu_img/item-17.jpg", null, null,10, 0.0);
        Menu menu17 = createMenu(store4, "탄탄면", 6500, "/menu_img/item-18.jpg", null, null,10, 0.0);
        Menu menu18 = createMenu(store4, "냉모밀", 6500, "/menu_img/item-19.jpg", null, null,10, 0.0);
        Menu menu19 = createMenu(store4, "카레밥", 4500, "/menu_img/item-20.jpg", null, null,10, 0.0);
        Menu menu20 = createMenu(store5, "아메리카노", 2000, "/menu_img/item-21.jpg", null, null,10, 0.0);
        Menu menu21 = createMenu(store5, "카페라떼", 2900, "/menu_img/item-22.jpg", null, null,10, 0.0);
        Menu menu22 = createMenu(store5, "카페모카", 3200, "/menu_img/item-23.jpg", null, null,10, 0.0);
        Menu menu23 = createMenu(store5, "돌체라떼", 3200, "/menu_img/item-24.jpg", null, null,10, 0.0);
        Menu menu24 = createMenu(store5, "복숭아아이스티", 2300, "/menu_img/item-25.jpg", null, null,10, 0.0);
        Menu menu25 = createMenu(store6, "아샷추", 2800, "/menu_img/item-26.jpg", null, null,10, 0.0);
        Menu menu26 = createMenu(store6, "햄치즈토스트", 3900, "/menu_img/item-27.jpg", null, null,10, 0.0);
        Menu menu27 = createMenu(store6, "에그스트", 3900, "/menu_img/item-28.jpg", null, null,10, 0.0);


        storeRepository.saveAll(Arrays.asList(store0, store1, store2, store3, store4, store5, store6));
        menuRepository.saveAll(Arrays.asList(menu0, menu1, menu2, menu3, menu4, menu5, menu6, menu7, menu8, menu9, menu10,
        menu11, menu12, menu13, menu14, menu15, menu16, menu17, menu18, menu19, menu20, menu21, menu22, menu23, menu24,
                menu25, menu26, menu27));
    }

    private Store createStore(String name, String operationHours) {
        Store store = new Store();
        store.setName(name);
        store.setOperationHours(operationHours);
        store.setCongestion(1);

        String[] hours = operationHours.split("-");

        System.out.println("Opening time input: " + hours[0]); // 개시 시간 입력을 출력

        LocalTime openingTime = LocalTime.parse(hours[0], DateTimeFormatter.ofPattern("HH:mm"));
        LocalTime closingTime = LocalTime.parse(hours[1], DateTimeFormatter.ofPattern("HH:mm"));

        LocalTime currentTime = LocalTime.now();



//        LocalTime openingTime = LocalTime.parse(hours[0].trim(), formatter);
//        LocalTime closingTime = LocalTime.parse(hours[1].trim(), formatter);

        if (currentTime.isBefore(openingTime) || currentTime.isAfter(closingTime)) {
            store.setIsOpen(isOpen.CLOSE);
        } else {
            store.setIsOpen(isOpen.OPEN);
        }

        return store;
    }

    private Menu createMenu(Store store, String name, int price, String  url,String originIng,
                            String allergicIng, int time, double rating) {
        Menu menu = new Menu();
        menu.setStore(store);
        menu.setName(name);
        menu.setPrice(price);
        menu.setImageUrl(url);
        menu.setOriginIng(originIng);
        menu.setAllergicIng(allergicIng);
        menu.setTime(time);
        menu.setRating(rating);
        menu.setCnt(0);

        return menu;
    }
}
