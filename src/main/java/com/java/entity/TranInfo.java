package com.java.entity;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;

@ToString
@Entity
@Data
public class TranInfo implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column
    private String traceabilityId;     //溯源id
    @Column
    private String name;               //商品名称
    @Column
    private String traceDate;          //买入日期
    @Column
    private String deliveryDate;       //卖出日期
    @Column
    private String traceNumber;        //货运单号
    @Column
    private String outCompany;         //卖出公司名称
    @Column
    private String outTel;             //卖出公司电话
    @Column
    private String outPerson;          //卖出公司法人
    @Column
    private String outArea;            //卖出公司区域
    @Column
    private String inCompany;          //买入公司名称
    @Column
    private String inTel;              //买入公司电话
    @Column
    private String inPerson;           //买入公司法人
    @Column
    private String inArea;             //买入公司区域
    @Column
    private String state;              //交易状态

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTraceabilityId() {
        return traceabilityId;
    }

    public void setTraceabilityId(String traceabilityId) {
        this.traceabilityId = traceabilityId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTraceDate() {
        return traceDate;
    }

    public void setTraceDate(String traceDate) {
        this.traceDate = traceDate;
    }

    public String getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(String deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public String getTraceNumber() {
        return traceNumber;
    }

    public void setTraceNumber(String traceNumber) {
        this.traceNumber = traceNumber;
    }

    public String getOutCompany() {
        return outCompany;
    }

    public void setOutCompany(String outCompany) {
        this.outCompany = outCompany;
    }

    public String getOutTel() {
        return outTel;
    }

    public void setOutTel(String outTel) {
        this.outTel = outTel;
    }

    public String getOutPerson() {
        return outPerson;
    }

    public void setOutPerson(String outPerson) {
        this.outPerson = outPerson;
    }

    public String getOutArea() {
        return outArea;
    }

    public void setOutArea(String outArea) {
        this.outArea = outArea;
    }

    public String getInCompany() {
        return inCompany;
    }

    public void setInCompany(String inCompany) {
        this.inCompany = inCompany;
    }

    public String getInTel() {
        return inTel;
    }

    public void setInTel(String inTel) {
        this.inTel = inTel;
    }

    public String getInPerson() {
        return inPerson;
    }

    public void setInPerson(String inPerson) {
        this.inPerson = inPerson;
    }

    public String getInArea() {
        return inArea;
    }

    public void setInArea(String inArea) {
        this.inArea = inArea;
    }
}
