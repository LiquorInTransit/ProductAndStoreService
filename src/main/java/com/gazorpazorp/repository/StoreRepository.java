package com.gazorpazorp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gazorpazorp.model.Store;

public interface StoreRepository extends JpaRepository<Store, Long>{

}
