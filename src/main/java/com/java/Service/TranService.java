package com.java.Service;

import com.java.entity.PathInfo;
import com.java.entity.TraceInfo;
import com.java.entity.TranInfo;
import com.java.fabric.FabricClient;
import com.java.fabric.UserContext;
import com.java.fabric.UserUtils;
import com.java.neo4j.companyNeo;
import com.java.repository.TraceInfoRepository;
import com.java.repository.TranInfoRepository;
import com.java.repository.companyNeoRepository;
import com.java.repository.salerNeoRepository;
import net.sf.json.JSONObject;
import org.apache.commons.lang3.StringUtils;
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
public class TranService {
    @Autowired
    private PathInfo pathInfo;

    @Autowired
    private TranInfoRepository tranRepository;


    public String updateTran(TranInfo tranInfo) throws InvalidKeySpecException, NoSuchAlgorithmException, org.bouncycastle.crypto.CryptoException,
            IOException, IllegalAccessException, InvalidArgumentException, InstantiationException, ClassNotFoundException, NoSuchMethodException,
            InvocationTargetException, CryptoException, ProposalException, TransactionException {
        System.out.println("updateTran start......");
        TranInfo result = tranRepository.save(tranInfo);

        if(result != null){
            UserContext userContext = new UserContext();
            //userContext有某个组织的成员变量
            userContext.setAffiliation("Org1");//设置组织机构
            userContext.setMspId("Org1MSP");   //组织一的MSPID
            userContext.setAccount("dbh");
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
                    +"\"traceDate\""+":"+'"'+result.getTraceDate()+'"'+","+"\"deliveryDate\""+":"+'"'+result.getDeliveryDate()+'"'+","
                    +"\"traceNumber\""+":"+'"'+result.getTraceNumber()+'"'+","+"\"outCompany\""+":"+'"'+result.getOutCompany()+'"'+","+"\"outTel\""+":"+'"'+result.getOutTel()+'"'+","
                    +"\"outPerson\""+":"+'"'+result.getOutPerson()+'"'+","+"\"outArea\""+":"+'"'+result.getOutArea()+'"'+","
                    +"\"inCompany\""+":"+'"'+result.getInCompany()+'"'+","+"\"inTel\""+":"+'"'+result.getInTel()+'"'+","+"\"inArea\""+":"+'"'+result.getInArea()+'"'+","
                    +"\"inPerson\""+":"+'"' +result.getInPerson()+'"'+"}";
            String initArgs[] = {result.getTraceabilityId(),str};
            System.out.println(str);
            fabricClient.invoke("mychannel", TransactionRequest.Type.GO_LANG, "dbh","1.0",order,peers,"save",initArgs);

            return "success";
        }else{
            return "error";
        }
    }

    public JSONObject findTranByChainId(int id) throws IllegalAccessException, InvalidArgumentException, InstantiationException, ClassNotFoundException,
            NoSuchMethodException, InvocationTargetException, CryptoException, InvalidKeySpecException, NoSuchAlgorithmException,
            org.bouncycastle.crypto.CryptoException, IOException, ProposalException, TransactionException {
        System.out.println("findTranById start......");
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

        String str = Integer.toString(id);
        String initArgs[] = {str};

        peers.add(peer0);
        peers.add(peer1);
        Map map = fabricClient.queryChaincode(peers,"mychannel", TransactionRequest.Type.GO_LANG,"dbh","queryHistory",initArgs);

        String first = StringUtils.strip(map.values().toString(),"[{}]");
        first ="{"+"\"data\":[{"+first+"}}]}";
        System.out.println(first);

        net.sf.json.JSONObject jsonObject = net.sf.json.JSONObject.fromObject(first);
        System.out.println(jsonObject.getString("data"));

        return jsonObject;
    }

    public TranInfo findTranById(int id){
        System.out.println("findByIdEZ start......");
        return this.tranRepository.findById(id).get();
    }

    public Page<TranInfo> findTranAll(int page, int size){
        System.out.println("findAll start......");
        return this.tranRepository.findAll(PageRequest.of(page-1,size));
    }

    public void deleteTranById(int id){
        System.out.println("deleteById start......");
        this.tranRepository.deleteById(id);
    }

    public String saveTran(TranInfo tranInfo){
        System.out.println("save start......");
        System.out.println(tranInfo.toString());
        TranInfo result = tranRepository.save(tranInfo);
        if(result != null){
            return "success";
        }else{
            return "error";
        }
    }

    public void updateTranState(String name, String state){
        this.tranRepository.updateState(name, state);
    }

}
