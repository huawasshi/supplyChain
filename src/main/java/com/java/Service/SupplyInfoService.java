package com.java.Service;

import com.java.entity.SupplyInfo;
import com.java.repository.SupplyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.NoSuchElementException;

@Service
public class SupplyInfoService {

    @Autowired
    private SupplyRepository supplyRepository;

    public boolean supplyRegister(SupplyInfo supplyInfo) {
        System.out.println("-----------------------------------");
        System.out.println(supplyInfo.toString());
        SupplyInfo resultSupply = this.supplyRepository.save(supplyInfo);
        System.out.println("-----------------------------------");
        if(resultSupply != null)
            return true;
        else
            return false;
    }

    public SupplyInfo supplyLogin(SupplyInfo supplyInfo) {
        try{
            SupplyInfo resultInfo = this.supplyRepository.findByName(supplyInfo.getName());

            String result = resultInfo.Compare(supplyInfo);
            System.out.println(result);
            if("成功".equals(result)){
                return resultInfo;
            }else{
                return null;
            }
        }catch (NoSuchElementException e){
            System.out.println("用户不存在......");
        }
        return null;
    }
}
