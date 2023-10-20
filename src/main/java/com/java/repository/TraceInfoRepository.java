package com.java.repository;

import com.java.entity.TraceInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface TraceInfoRepository extends JpaRepository<TraceInfo,Integer> {
    @Query(value = "SELECT * FROM trace_info where traceability_id=?1", nativeQuery = true)
    TraceInfo findByTraceabilityId(String traceability_id);

    @Transactional
    @Modifying
    @Query(value = "update trace_info set state = ?2 where traceability_id = ?1", nativeQuery = true)
    void updateState(String name, String state);
}
