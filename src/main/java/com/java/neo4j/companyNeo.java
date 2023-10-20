package com.java.neo4j;

import org.springframework.data.neo4j.core.schema.*;

import java.util.HashSet;
import java.util.Set;

@Node("company")
public class  companyNeo {
    @Id
    @GeneratedValue
    private Long id;
    @Property("name")
    private String name;
    @Property("person")
    private String person;
    @Property("tel")
    private String tel;
    @Property("area")
    private String area;

    @Relationship(type = "saler", direction = Relationship.Direction.OUTGOING)
    private Set<salerNeo> salers;

    public void addSalers(salerNeo saler){
        if(saler != null)
            this.salers.add(saler);
        else
            this.salers = new HashSet<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPerson() {
        return person;
    }

    public void setPerson(String person) {
        this.person = person;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    @Override
    public String toString() {
        return "companyNeo{" +
                "name='" + name + '\'' +
                ", person='" + person + '\'' +
                ", tel='" + tel + '\'' +
                ", area='" + area + '\'' +
                '}';
    }
}
