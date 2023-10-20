package com.java.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
public class SupplyInfo implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column
    private String name;
    @Column
    private String password;
    @Column
    private String registration_type;

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public String getRegistration_type() {
        return registration_type;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setRegistration_type(String registration_type) {
        this.registration_type = registration_type;
    }

    @Override
    public String toString() {
        return "SupplyInfo{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", registration_type='" + registration_type + '\'' +
                '}';
    }

    public String Compare(SupplyInfo supplyInfo){
        System.out.println(this.toString());
        System.out.println(supplyInfo.toString());

        if(supplyInfo == null)
            return "程序异常......";

        if(!this.name.trim().equals(supplyInfo.getName().trim()))
            return "姓名错误......";
        if(!this.password.trim().equals(supplyInfo.getPassword().trim()))
            return "密码错误......";
        /*if(!this.registration_type.trim().equals(supplyInfo.getRegistration_type().trim()))
            return "角色错误......";*/

        return "成功";

    }
}