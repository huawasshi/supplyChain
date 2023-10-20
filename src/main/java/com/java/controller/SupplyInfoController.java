package com.java.controller;

import com.java.Service.SupplyInfoService;
import com.java.entity.SupplyInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
@RequestMapping("/fabricUser")
public class SupplyInfoController {
    @Autowired
    private SupplyInfoService supplyInfoService;

    //注册用户
    @PostMapping(value = "/register")
    public boolean register(@RequestBody SupplyInfo supplyInfo) throws IOException {
        return this.supplyInfoService.supplyRegister(supplyInfo);
    }

    //登录
    @PostMapping(value = "/login")
    public Object login(@RequestBody SupplyInfo supplyInfo) throws IOException {
        return this.supplyInfoService.supplyLogin(supplyInfo);
    }

}
