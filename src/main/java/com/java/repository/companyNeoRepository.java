package com.java.repository;

import com.java.neo4j.companyNeo;
import org.springframework.data.neo4j.repository.Neo4jRepository;

import java.util.List;

public interface companyNeoRepository extends Neo4jRepository<companyNeo, Long> {

    List<companyNeo> findByArea(String area);
}
