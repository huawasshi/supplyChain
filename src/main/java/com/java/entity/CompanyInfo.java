package com.java.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Data
public class CompanyInfo implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column
    private String company_name;                //名称
    @Column
    private String company_person;              //法人
    @Column
    private String company_tel;                 //电话
    @Column
    private String company_account;             //账户
    @Column
    private String company_area;                //注册地

    @Override
    public String toString() {
        return "CompanyInfo{" +
                "id=" + id +
                ", company_name='" + company_name + '\'' +
                ", company_person='" + company_person + '\'' +
                ", company_tel='" + company_tel + '\'' +
                ", company_account='" + company_account + '\'' +
                ", company_area='" + company_area + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CompanyInfo that = (CompanyInfo) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(company_name, that.company_name) &&
                Objects.equals(company_person, that.company_person) &&
                Objects.equals(company_tel, that.company_tel) &&
                Objects.equals(company_account, that.company_account) &&
                Objects.equals(company_area, that.company_area);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, company_name, company_person, company_tel, company_account, company_area);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCompany_name() {
        return company_name;
    }

    public void setCompany_name(String company_name) {
        this.company_name = company_name;
    }

    public String getCompany_person() {
        return company_person;
    }

    public void setCompany_person(String company_person) {
        this.company_person = company_person;
    }

    public String getCompany_tel() {
        return company_tel;
    }

    public void setCompany_tel(String company_tel) {
        this.company_tel = company_tel;
    }

    public String getCompany_account() {
        return company_account;
    }

    public void setCompany_account(String company_account) {
        this.company_account = company_account;
    }

    public String getCompany_area() {
        return company_area;
    }

    public void setCompany_area(String company_area) {
        this.company_area = company_area;
    }
}
