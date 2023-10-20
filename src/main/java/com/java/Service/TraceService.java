package com.java.Service;

import com.java.entity.CompanyInfo;
import com.java.entity.PathInfo;
import com.java.entity.TraceInfo;
import com.java.entity.TranInfo;
import com.java.fabric.FabricClient;
import com.java.fabric.UserContext;
import com.java.fabric.UserUtils;
import com.java.repository.*;
import net.sf.json.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.weaver.tools.Trace;
import org.hyperledger.fabric.sdk.Enrollment;
import org.hyperledger.fabric.sdk.Orderer;
import org.hyperledger.fabric.sdk.Peer;
import org.hyperledger.fabric.sdk.TransactionRequest;
import org.hyperledger.fabric.sdk.exception.CryptoException;
import org.hyperledger.fabric.sdk.exception.InvalidArgumentException;
import org.hyperledger.fabric.sdk.exception.ProposalException;
import org.hyperledger.fabric.sdk.exception.TransactionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class TraceService {

    @Autowired
    private PathInfo pathInfo;

    @Autowired
    private TraceInfoRepository traceRepository;
    @Autowired
    private TranInfoRepository tranRepository;
    @Autowired
    private companyNeoRepository companyNeoRepository;
    @Autowired
    private salerNeoRepository salerNeoRepository;
    @Autowired
    private CompanyInfoRepository companyInfoRepository;

    public Page<TraceInfo> findAll(int page, int size){
        System.out.println("findAll start......");
        return this.traceRepository.findAll(PageRequest.of(page-1,size));
    }

    public TraceInfo findByCainId(String id) throws IllegalAccessException, InvalidArgumentException, InstantiationException, ClassNotFoundException,
            NoSuchMethodException, InvocationTargetException, CryptoException, InvalidKeySpecException, NoSuchAlgorithmException,
            org.bouncycastle.crypto.CryptoException, IOException, ProposalException, TransactionException {
        System.out.println("findByCainId start......");
        UserContext userContext = new UserContext();
        userContext.setAffiliation("Org1");//设置组织机构
        userContext.setMspId("Org1MSP");   //组织一的MSPID
        userContext.setAccount("eight");
        userContext.setName("admin");
        Enrollment enrollment = UserUtils.getEnrollment(pathInfo.getKeyFolderPath(),pathInfo.getKeyFileName(),pathInfo.getCertFolderPath(),pathInfo.getCertFileName());
        userContext.setEnrollment(enrollment);
        FabricClient fabricClient = new FabricClient(userContext);
        Peer peer0 = fabricClient.getPeer("peer0.org1.example.com","grpcs://peer0.org1.example.com:7051",pathInfo.getPeer0tlsFilePath());
        Peer peer1 = fabricClient.getPeer("peer1.org1.example.com","grpcs://peer1.org1.example.com:8051",pathInfo.getPeer1tlsFilePath());
        List<Peer> peers = new ArrayList<Peer>();

        String str = id;
        String initArgs[] = {str};

        peers.add(peer0);
        peers.add(peer1);

        System.out.println("chain query......");
        Map map = fabricClient.queryChaincode(peers,"mychannel", TransactionRequest.Type.GO_LANG,"eight","queryHistory",initArgs);

        String first = StringUtils.strip(map.values().toString(),"[{}]");
        first ="{"+"\"data\":[{"+first+"}}]}";
        System.out.println(first);

        net.sf.json.JSONObject jsonObject = net.sf.json.JSONObject.fromObject(first);
        System.out.println(jsonObject.getString("data"));
        Object object = jsonObject.getJSONArray("data").get(0);
        jsonObject = net.sf.json.JSONObject.fromObject(object.toString()).getJSONObject("Value");

        TraceInfo result = new TraceInfo();
        result.setTraceabilityId(jsonObject.get("traceabilityId").toString());
        result.setName(jsonObject.get("name").toString());
        result.setManufactureDate(jsonObject.get("manufactureDate").toString());
        result.setManufactor(jsonObject.get("manufactor").toString());
        result.setPerson(jsonObject.get("person").toString());
        result.setTelephone(jsonObject.get("telephone").toString());
        result.setListingDate(jsonObject.get("listingDate").toString());
        result.setCourierNumber(jsonObject.get("courierNumber").toString());
        result.setValidityDate(jsonObject.get("validityDate").toString());
        result.setCompany(jsonObject.get("company").toString());
        result.setTel(jsonObject.get("tel").toString());
        result.setDeliveryDate(jsonObject.get("deliveryDate").toString());
        result.setArea(jsonObject.get("area").toString());
        result.setState(jsonObject.get("state").toString());
        System.out.println(result.toString());

        return result;
    }

    public void upToChain() throws IOException, TransactionException, NoSuchAlgorithmException, InstantiationException, NoSuchMethodException, org.bouncycastle.crypto.CryptoException, InvalidArgumentException, InvocationTargetException, IllegalAccessException, CryptoException, InvalidKeySpecException, ClassNotFoundException, ProposalException {
        List<TraceInfo> traceInfos = this.traceRepository.findAll();
        for(TraceInfo traceInfo : traceInfos){
            update(traceInfo);
        }
    }

    public TraceInfo findById(int id){
        System.out.println("findById start......");
        return this.traceRepository.findById(id).get();
    }

    public void deleteById(int id){
        System.out.println("deleteById start......");
        this.traceRepository.deleteById(id);
    }

    public String update(TraceInfo traceInfo) throws InvalidKeySpecException, NoSuchAlgorithmException, org.bouncycastle.crypto.CryptoException,
            IOException, IllegalAccessException, InvalidArgumentException, InstantiationException, ClassNotFoundException, NoSuchMethodException,
            InvocationTargetException, CryptoException, ProposalException, TransactionException {
        System.out.println("update start......");
        System.out.println(traceInfo.toString());
        TraceInfo temp = traceRepository.findByTraceabilityId(traceInfo.getTraceabilityId());
        if(temp != null){
            traceInfo.setId(temp.getId());
            traceInfo.setName(temp.getName());
            traceInfo.setManufactor(temp.getManufactor());
            traceInfo.setManufactureDate(temp.getManufactureDate());
            traceInfo.setPerson(temp.getPerson());
            traceInfo.setTelephone(temp.getTelephone());
            traceInfo.setState("0");
            traceInfo.setValidityDate(temp.getValidityDate());
            traceInfo.setAmount(temp.getAmount() - traceInfo.getAmount());
            traceInfo.setArea(temp.getArea());
        }
        TraceInfo result = traceRepository.save(traceInfo);
        System.out.println(result.toString());

        if(result != null){
            UserContext userContext = new UserContext();
            //userContext有某个组织的成员变量
            userContext.setAffiliation("Org1");//设置组织机构
            userContext.setMspId("Org1MSP");   //组织一的MSPID
            userContext.setAccount("eight");
            userContext.setName("admin");
            Enrollment enrollment = UserUtils.getEnrollment(pathInfo.getKeyFolderPath(),pathInfo.getKeyFileName(),pathInfo.getCertFolderPath(),pathInfo.getCertFileName());
            userContext.setEnrollment(enrollment);
            FabricClient fabricClient = new FabricClient(userContext);
            Peer peer0 = fabricClient.getPeer("peer0.org1.example.com","grpcs://peer0.org1.example.com:7051",pathInfo.getPeer0tlsFilePath());
            Peer peer1 = fabricClient.getPeer("peer1.org1.example.com","grpcs://peer1.org1.example.com:8051",pathInfo.getPeer1tlsFilePath());
            Orderer order = fabricClient.getOrderer("orderer.example.com","grpcs://orderer.example.com:7050",pathInfo.getOrdertlsFilePath());
            List<Peer> peers =new ArrayList<>();
            peers.add(peer0);
            peers.add(peer1);

            String str = "{\"traceabilityId\""+":"+'"'+result.getTraceabilityId()+'"'+","+"\"name\""+":"+'"'+result.getName()+'"'+","
                    +"\"manufactureDate\""+":"+'"'+result.getManufactureDate()+'"'+","+"\"manufactor\""+":"+'"'+result.getManufactor()+'"'+","
                    +"\"telephone\""+":"+'"'+result.getTelephone()+'"'+","+"\"listingDate\""+":"+'"'+result.getListingDate()+'"'+","+"\"person\""+":"+'"'+result.getPerson()+'"'+","
                    +"\"courierNumber\""+":"+'"'+result.getCourierNumber()+'"'+","+"\"validityDate\""+":"+'"'+result.getValidityDate()+'"'+","
                    +"\"company\""+":"+'"'+result.getCompany()+'"'+","+"\"tel\""+":"+'"'+result.getTel()+'"'+","+"\"area\""+":"+'"'+result.getArea()+'"'+","
                    +"\"deliveryDate\""+":"+'"' +result.getDeliveryDate()+'"'+","+"\"state\""+":"+'"' +result.getState()+'"'+"}";
            String initArgs[] = {result.getTraceabilityId(),str};
            System.out.println(str);
            fabricClient.invoke("mychannel", TransactionRequest.Type.GO_LANG, "eight","1.0",order,peers,"save",initArgs);

            return "success";
        }else{
            return "error";
        }
    }

    public String save(TraceInfo traceInfo){
        System.out.println("save start......");
        System.out.println(traceInfo.toString());
        TraceInfo result = traceRepository.save(traceInfo);
        if(result != null){
            return "success";
        }else{
            return "error";
        }
    }

    public void updateState(String name, String state){
        this.traceRepository.updateState(name, state);
    }

    public List<CompanyInfo> showAllTrans(String id){
        TraceInfo traceInfo = this.traceRepository.findByTraceabilityId(id);
        if(StringUtils.isEmpty(traceInfo.getName()) || StringUtils.isEmpty(traceInfo.getManufactor()) || StringUtils.isEmpty(traceInfo.getPerson()) ||StringUtils.isEmpty(traceInfo.getTelephone())){
            System.out.println("生产商记录不全......");
            return null;
        }
        if(StringUtils.isEmpty(traceInfo.getCompany())){
            System.out.println("承销商记录不全......");
            return null;
        }
        List<TranInfo> tranInfos = this.tranRepository.findByName(traceInfo.getName());
        List<CompanyInfo> result = new ArrayList<>();
        CompanyInfo companyInfo = new CompanyInfo();
        companyInfo = this.companyInfoRepository.findByName(traceInfo.getManufactor());
        result.add(companyInfo);
        for(TranInfo tranInfo : tranInfos){
            companyInfo = this.companyInfoRepository.findByName(tranInfo.getOutCompany());
            result.add(companyInfo);
        }
        return result;
    }
}
