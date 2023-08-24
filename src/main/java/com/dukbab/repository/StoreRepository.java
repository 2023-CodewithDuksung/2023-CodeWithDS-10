package com.dukbab.repository;

import com.dukbab.domain.Store;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StoreRepository extends JpaRepository<Store, Integer> {
    Store findByName(String storeName);
    Store findByStoreId(int storeId);
}
