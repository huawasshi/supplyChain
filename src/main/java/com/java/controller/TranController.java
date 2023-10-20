package com.java.controller;

import com.java.Service.TranService;
import com.java.entity.TraceInfo;
import com.java.entity.TranInfo;
import com.java.neo4j.companyNeo;
import net.sf.json.JSONObject;
import org.bouncycastle.crypto.CryptoException;
import org.hyperledger.fabric.sdk.exception.InvalidArgumentException;
import org.hyperledger.fabric.sdk.exception.ProposalException;
import org.hyperledger.fabric.sdk.exception.TransactionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.List;

@RestController
@RequestMapping("/fabricTran")
public class TranController {

    @Autowired
    private TranService tranService;


    @GetMapping("/findTranByChainId/{id}")
    public JSONObject findTranByChainId(@PathVariable("id") Integer id) throws InvalidKeySpecException, NoSuchAlgorithmException, CryptoException, IOException, IllegalAccessException, InvalidArgumentException, InstantiationException, ClassNotFoundException, NoSuchMethodException, InvocationTargetException, org.hyperledger.fabric.sdk.exception.CryptoException, ProposalException, TransactionException {
        return this.tranService.findTranByChainId(id);
    }

    @PostMapping("/updateTran")
    public String updateTran(@RequestBody TranInfo tran) throws InvalidKeySpecException, NoSuchAlgorithmException, CryptoException, IOException, IllegalAccessException, InvalidArgumentException, InstantiationException, ClassNotFoundException, NoSuchMethodException, InvocationTargetException, org.hyperledger.fabric.sdk.exception.CryptoException, ProposalException, TransactionException {
        return this.tranService.updateTran(tran);
    }

    @GetMapping("/findTranById/{id}")
    public TranInfo findTranById(@PathVariable("id") Integer id){
        return this.tranService.findTranById(id);
    }

    @GetMapping("/findTranAll/{page}/{size}")
    public Page<TranInfo> findAll(@PathVariable("page") Integer page, @PathVariable("size") Integer size){
        return this.tranService.findTranAll(page, size);
    }

    @PostMapping("/saveTran")
    public String saveTran(@RequestBody TranInfo tranInfo){
        return this.tranService.saveTran(tranInfo);
    }

    @DeleteMapping("/deleteTranById/{id}")
    public void deleteTranById(@PathVariable("id") Integer id){
        this.tranService.deleteTranById(id);
    }

    @PostMapping("/updateTranState")
    public void updateTranState(@RequestParam String name, @RequestParam String state){
        this.tranService.updateTranState(name, state);
    }

}
