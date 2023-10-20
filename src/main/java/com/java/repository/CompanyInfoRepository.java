package com.java.repository;

import com.java.entity.CompanyInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface CompanyInfoRepository extends JpaRepository<CompanyInfo, Long> {
    @Query(value = "select * from company_info where company_area = ?1", nativeQuery = true)
    List<CompanyInfo> findByArea(String area);

    @Transactional
    @Modifying
    @Query(value = "delete from company_info where company_name = ?1", nativeQuery = true)
    void deleteByName(String name);

    @Query(value = "select * from company_info where company_name = ?1", nativeQuery = true)
    CompanyInfo findByName(String name);
}
