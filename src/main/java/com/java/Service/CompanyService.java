package com.java.Service;

import com.java.entity.CompanyInfo;
import com.java.repository.CompanyInfoRepository;
import com.java.repository.companyNeoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompanyService {

    @Autowired
    private companyNeoRepository companyNeoRepository;
    @Autowired
    private CompanyInfoRepository companyInfoRepository;

    public CompanyInfo modifyCompany(CompanyInfo companyInfo){
        CompanyInfo result = this.companyInfoRepository.save(companyInfo);
        if(result != null){
            System.out.println("成功......");
            System.out.println(result.toString());
            return result;
        }else {
            System.out.println("失败......");
            return null;
        }
    }

    public List<CompanyInfo> findAllCompany(){
        return this.companyInfoRepository.findAll();
    }

    public List<CompanyInfo> findCompanyByArea(String area){
        return this.companyInfoRepository.findByArea(area);
    }

    public void deleteCompany(String name){
        this.companyInfoRepository.deleteByName(name);
    }

}
