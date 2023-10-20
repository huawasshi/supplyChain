package com.java.neo4j;

import org.springframework.data.neo4j.core.schema.*;

@RelationshipProperties
public class salerNeo {
    @Id
    @GeneratedValue
    private Long id;

    @Property("name")
    private String name;

    @TargetNode
    private companyNeo company;

    public salerNeo() {
    }

    public salerNeo(String name, companyNeo company) {
        this.name = name;
        this.company = company;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public companyNeo getCompany() {
        return company;
    }

    public void setCompany(companyNeo company) {
        this.company = company;
    }

}