package com.java.repository;

import com.java.entity.SupplyInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface SupplyRepository extends JpaRepository<SupplyInfo,Integer> {

    @Query(value = "select * from supply_info where name = ?", nativeQuery = true)
    SupplyInfo findByName(String name);
}
