package com.java.controller;

import com.java.Service.TraceService;
import com.java.entity.TraceInfo;
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

@RestController
@RequestMapping("/fabricTrace")
public class TraceController {

    @Autowired
    private TraceService traceService;

    @GetMapping("/findAll/{page}/{size}")
    public Page<TraceInfo> findAll(@PathVariable("page") Integer page, @PathVariable("size") Integer size){
        return this.traceService.findAll(page, size);
    }

    @GetMapping("/findByCainId")
    public TraceInfo findByCainId(@RequestParam String id) throws InvalidKeySpecException, NoSuchAlgorithmException, CryptoException, IOException, IllegalAccessException, InvalidArgumentException, InstantiationException, ClassNotFoundException, NoSuchMethodException, InvocationTargetException, org.hyperledger.fabric.sdk.exception.CryptoException, ProposalException, TransactionException {
        return this.traceService.findByCainId(id);
    }

    @GetMapping("/findById/{id}")
    public TraceInfo findById(@PathVariable("id") Integer id){
        return this.traceService.findById(id);
    }


    @DeleteMapping("/deleteById/{id}")
    public void deleteById(@PathVariable("id") Integer id){
        this.traceService.deleteById(id);
    }


    @PostMapping("/updata")
    public String updata(@RequestBody TraceInfo traceInfo) throws InvalidKeySpecException, NoSuchAlgorithmException, CryptoException, IOException, IllegalAccessException, InvalidArgumentException, InstantiationException, ClassNotFoundException, NoSuchMethodException, InvocationTargetException, org.hyperledger.fabric.sdk.exception.CryptoException, ProposalException, TransactionException {
        return this.traceService.update(traceInfo);
    }

    @GetMapping("/upToChain")
    public void upToChain() throws IOException, NoSuchAlgorithmException, InstantiationException, NoSuchMethodException, CryptoException, InvalidArgumentException, InvocationTargetException, IllegalAccessException, ProposalException, org.hyperledger.fabric.sdk.exception.CryptoException, InvalidKeySpecException, TransactionException, ClassNotFoundException {
        this.traceService.upToChain();
    }

    @PostMapping("/save")
    public String save(@RequestBody TraceInfo traceInfo){
        return this.traceService.save(traceInfo);
    }

    @PostMapping("/updateState")
    public void updateState(@RequestParam String name, @RequestParam String state){
        this.traceService.updateState(name, state);
    }

}
