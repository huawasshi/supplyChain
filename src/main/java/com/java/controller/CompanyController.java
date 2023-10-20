package com.java.controller;

import com.java.Service.CompanyService;
import com.java.entity.CompanyInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/fabricCompany")
public class CompanyController {

    @Autowired
    private CompanyService companyService;

    @PostMapping("/modifyCompany")
    public CompanyInfo modifyCompany(@RequestBody CompanyInfo companyInfo){
        return this.companyService.modifyCompany(companyInfo);
    }

    @GetMapping("/findAllCompany")
    public List<CompanyInfo> findAllCompany(){
        return this.companyService.findAllCompany();
    }

    @GetMapping("/findCompanyByArea")
    public List<CompanyInfo> findCompanyByArea(@RequestParam String area){
        return this.companyService.findCompanyByArea(area);
    }

    @PostMapping("/deleteCompany")
    public void deleteCompany(@RequestParam String name){
        this.companyService.deleteCompany(name);
    }

}
