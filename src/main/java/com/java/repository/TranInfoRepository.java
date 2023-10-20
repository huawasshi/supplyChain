package com.java.repository;

import com.java.entity.TranInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface TranInfoRepository extends JpaRepository<TranInfo, Integer> {
    @Transactional
    @Modifying
    @Query(value = "update tran_info set state = ?2 where traceability_id = ?1", nativeQuery = true)
    void updateState(String name, String state);

    List<TranInfo> findByName(String name);
}
