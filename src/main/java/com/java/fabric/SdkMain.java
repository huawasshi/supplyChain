package com.java.fabric;

import net.sf.json.JSONException;
import net.sf.json.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.hyperledger.fabric.sdk.Enrollment;
import org.hyperledger.fabric.sdk.Orderer;
import org.hyperledger.fabric.sdk.Peer;
import org.hyperledger.fabric.sdk.TransactionRequest;
import org.hyperledger.fabric.sdk.exception.CryptoException;
import org.hyperledger.fabric.sdk.exception.InvalidArgumentException;
import org.hyperledger.fabric.sdk.exception.ProposalException;
import org.hyperledger.fabric.sdk.exception.TransactionException;
import org.springframework.beans.factory.annotation.Value;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class SdkMain {

    private String keyFolderPath = "D:\\\\Idea\\\\IdeaProjects\\\\supplyChain\\\\src\\\\main\\\\resources\\\\crypto-config\\\\peerOrganizations\\\\org1.example.com\\\\users\\\\Admin@org1.example.com\\\\msp\\\\keystore";

    private String keyFileName = "8e891e40aabe661c9f8cf6e5c7d21f4801f7b0268a9a1a472182e13203baa412_sk";

    private String certFolderPath = "D:\\\\Idea\\\\IdeaProjects\\\\supplyChain\\\\src\\\\main\\\\resources\\\\crypto-config\\\\peerOrganizations\\\\org1.example.com\\\\users\\\\Admin@org1.example.com\\\\msp\\\\signcerts";

    private String certFileName = "Admin@org1.example.com-cert.pem";

    private String txfilePath = "D:\\\\Idea\\\\IdeaProjects\\\\supplyChain\\\\src\\\\main\\\\resources\\\\dbh.tx";

    private String ordertlsFilePath = "D:\\\\Idea\\\\IdeaProjects\\\\supplyChain\\\\src\\\\main\\\\resources\\\\crypto-config\\\\ordererOrganizations\\\\example.com\\\\tlsca\\\\tlsca.example.com-cert.pem";

    private String peer1tlsFilePath = "D:\\\\Idea\\\\IdeaProjects\\\\supplyChain\\\\src\\\\main\\\\resources\\\\crypto-config\\\\peerOrganizations\\\\org1.example.com\\\\peers\\\\peer1.org1.example.com\\\\msp\\\\tlscacerts\\\\tlsca.org1.example.com-cert.pem";

    private String peer0tlsFilePath = "D:\\\\Idea\\\\IdeaProjects\\\\supplyChain\\\\src\\\\main\\\\resources\\\\crypto-config\\\\peerOrganizations\\\\org1.example.com\\\\peers\\\\peer0.org1.example.com\\\\msp\\\\tlscacerts\\\\tlsca.org1.example.com-cert.pem";

    private String peerorg2tlsFilePath = "D:\\\\Idea\\\\IdeaProjects\\\\supplyChain\\\\src\\\\main\\\\resources\\\\crypto-config\\\\peerOrganizations\\\\org2.example.com\\\\tlsca\\\\tlsca.org2.example.com-cert.pem";

    //安装合约
    public void install() throws InvalidKeySpecException, NoSuchAlgorithmException, IOException, IllegalAccessException, InvalidArgumentException,
            InstantiationException, ClassNotFoundException, NoSuchMethodException, InvocationTargetException, ProposalException, CryptoException,
            org.bouncycastle.crypto.CryptoException {
        UserContext userContext = new UserContext();
        //userContext有某个组织的成员变量
        userContext.setAffiliation("Org1");//设置组织机构
        userContext.setMspId("Org1MSP");   //组织一的MSPID
        userContext.setAccount("dbh");
        userContext.setName("admin");
        Enrollment enrollment = UserUtils.getEnrollment(keyFolderPath,keyFileName,certFolderPath,certFileName);
        userContext.setEnrollment(enrollment);
        FabricClient fabricClient = new FabricClient(userContext);
        Peer peer0 = fabricClient.getPeer("peer0.org1.example.com","grpcs://peer0.org1.example.com:7051",peer0tlsFilePath);
        Peer peer1 = fabricClient.getPeer("peer1.org1.example.com","grpcs://peer1.org1.example.com:8051",peer1tlsFilePath);
        List<Peer> peers = new ArrayList<Peer>();
        peers.add(peer0);
        peers.add(peer1);

        fabricClient.installChaincode(TransactionRequest.Type.GO_LANG,"eight","1.0","D:\\Idea\\IdeaProjects\\supplyChain\\src\\main\\resources","eight",peers);
    }

    //实例化合约
    public void init() throws InvalidKeySpecException, NoSuchAlgorithmException, IOException, IllegalAccessException,
            InvalidArgumentException, InstantiationException, ClassNotFoundException, NoSuchMethodException, InvocationTargetException,
            TransactionException, ProposalException, CryptoException, org.bouncycastle.crypto.CryptoException {
        UserContext userContext = new UserContext();
        //userContext有某个组织的成员变量
        userContext.setAffiliation("Org1");//设置组织机构
        userContext.setMspId("Org1MSP");   //组织一的MSPID
        userContext.setAccount("dbh");
        userContext.setName("admin");
        Enrollment enrollment = UserUtils.getEnrollment(keyFolderPath,keyFileName,certFolderPath,certFileName);
        userContext.setEnrollment(enrollment);
        FabricClient fabricClient = new FabricClient(userContext);
        Peer peer = fabricClient.getPeer("peer0.org1.example.com","grpcs://peer0.org1.example.com:7051",peer0tlsFilePath);
        Orderer order = fabricClient.getOrderer("orderer.example.com","grpcs://orderer.example.com:7050",ordertlsFilePath);
        String initArgs[] = {""};

        fabricClient.initChaincode("mychannel", TransactionRequest.Type.GO_LANG, "eight","1.0",order,peer,"init",initArgs);

    }


    //invoke合约调用合约函数
    public void invode() throws InvalidKeySpecException, NoSuchAlgorithmException, IOException, IllegalAccessException,
            InvalidArgumentException, InstantiationException, ClassNotFoundException, NoSuchMethodException, InvocationTargetException,
            TransactionException, ProposalException, CryptoException, org.bouncycastle.crypto.CryptoException {
        UserContext userContext = new UserContext();
        //userContext有某个组织的成员变量
        userContext.setAffiliation("Org1");//设置组织机构
        userContext.setMspId("Org1MSP");   //组织一的MSPID
        userContext.setAccount("dbh");
        userContext.setName("admin");
        Enrollment enrollment = UserUtils.getEnrollment(keyFolderPath,keyFileName,certFolderPath,certFileName);
        userContext.setEnrollment(enrollment);
        FabricClient fabricClient = new FabricClient(userContext);
        Peer peer0 = fabricClient.getPeer("peer0.org1.example.com","grpcs://peer0.org1.example.com:7051",peer0tlsFilePath);
        Peer peer1 = fabricClient.getPeer("peer1.org1.example.com","grpcs://peer1.org1.example.com:8051",peer1tlsFilePath);
        Orderer order = fabricClient.getOrderer("orderer.example.com","grpcs://orderer.example.com:7050",ordertlsFilePath);
        List<Peer> peers =new ArrayList<>();
        peers.add(peer0);
        peers.add(peer1);

//        String initArgs[] = {"555","{\"traceability_id\":\"555\",\"name\":\"老八秘制小汉堡\",\"date_of_manufacture\":\"2022/8/5\",\"manufactor\":\"米奇妙妙屋\",\"telephone\":\"12345678910\",\"listing_date\":\"2022/8/11\",\"courier_number\":\"123\",\"term_of_validity\":\"8个月\",\"company\":\"芜湖起飞\",\"tel\":\"12345678910\",\"delivery_date\":\"2022/8/5\" }"};

//        String initArgs[] = {"1212","{\"traceability_id\":\"1212\",\"name\":\"老八秘制小汉堡\",\"date_of_manufacture\":\"2022/8/5\",\"manufactor\":\"米奇妙妙屋\",\"telephone\":\"12345678910\"}"};
        String initArgs[] = {"1213","{\"traceability_id\":\"1213\",\"listing_date\":\"2022/8/11\",\"courier_number\":\"123\",\"term_of_validity\":\"8个月\",\"company\":\"芜湖起飞\",\"tel\":\"12345678910\",\"delivery_date\":\"2022/8/5\" }"};
//        String initArgs[] = {'"'+"Args"+'"'+","+"\"initMarble\",\"marbles9\",\"bule\",\"35\",\"tom\""};

        fabricClient.invoke("mychannel", TransactionRequest.Type.GO_LANG, "eight","1.0",order,peers,"save",initArgs);
    }

    //查询合约
    public void query() throws InvalidKeySpecException, NoSuchAlgorithmException, IOException, IllegalAccessException, InvalidArgumentException,
            InstantiationException, ClassNotFoundException, NoSuchMethodException, InvocationTargetException, ProposalException, TransactionException,
            JSONException, CryptoException, org.bouncycastle.crypto.CryptoException {
        UserContext userContext = new UserContext();
        //userContext有某个组织的成员变量
        userContext.setAffiliation("Org1");//设置组织机构
        userContext.setMspId("Org1MSP");   //组织一的MSPID
        userContext.setAccount("dbh");
        userContext.setName("admin");
        Enrollment enrollment = UserUtils.getEnrollment(keyFolderPath,keyFileName,certFolderPath,certFileName);
        userContext.setEnrollment(enrollment);
        FabricClient fabricClient = new FabricClient(userContext);
        Peer peer0 = fabricClient.getPeer("peer0.org1.example.com","grpcs://peer0.org1.example.com:7051",peer0tlsFilePath);
        Peer peer1 = fabricClient.getPeer("peer1.org1.example.com","grpcs://peer1.org1.example.com:8051",peer1tlsFilePath);
        List<Peer> peers = new ArrayList<Peer>();
        String initArgs[] = {"1213"};
        peers.add(peer0);
        peers.add(peer1);

        Map map = fabricClient.queryChaincode(peers,"mychannel",TransactionRequest.Type.GO_LANG,"dbh","queryHistory",initArgs);
        String first = StringUtils.strip(map.values().toString(),"[{}]");
        first ="{"+"\"data\":[{"+first+"}}]}";
        System.out.println(first);
        System.out.println("===============");
        JSONObject jsonObject = JSONObject.fromObject(first);
        System.out.println(jsonObject.getString("data"));

    }

    //安装合约
    public void install1() throws InvalidKeySpecException, NoSuchAlgorithmException, IOException, IllegalAccessException, InvalidArgumentException,
            InstantiationException, ClassNotFoundException, NoSuchMethodException, InvocationTargetException, ProposalException, CryptoException,
            org.bouncycastle.crypto.CryptoException {
        UserContext userContext = new UserContext();
        //userContext有某个组织的成员变量
        userContext.setAffiliation("Org1");//设置组织机构
        userContext.setMspId("Org1MSP");   //组织一的MSPID
        userContext.setAccount("dbh");
        userContext.setName("admin");
        Enrollment enrollment = UserUtils.getEnrollment(keyFolderPath,keyFileName,certFolderPath,certFileName);
        userContext.setEnrollment(enrollment);
        FabricClient fabricClient = new FabricClient(userContext);
        Peer peer0 = fabricClient.getPeer("peer0.org1.example.com","grpcs://peer0.org1.example.com:7051",peer0tlsFilePath);
        Peer peer1 = fabricClient.getPeer("peer1.org1.example.com","grpcs://peer1.org1.example.com:8051",peer1tlsFilePath);
        List<Peer> peers = new ArrayList<Peer>();
        peers.add(peer0);
        peers.add(peer1);

        fabricClient.installChaincode(TransactionRequest.Type.GO_LANG,"dbh","1.0","D:\\Idea\\IdeaProjects\\supplyChain\\src\\main\\resources","dbh",peers);
    }

    //实例化合约
    public void init1() throws InvalidKeySpecException, NoSuchAlgorithmException, IOException, IllegalAccessException,
            InvalidArgumentException, InstantiationException, ClassNotFoundException, NoSuchMethodException, InvocationTargetException,
            TransactionException, ProposalException, CryptoException, org.bouncycastle.crypto.CryptoException {
        UserContext userContext = new UserContext();
        //userContext有某个组织的成员变量
        userContext.setAffiliation("Org1");//设置组织机构
        userContext.setMspId("Org1MSP");   //组织一的MSPID
        userContext.setAccount("dbh");
        userContext.setName("admin");
        Enrollment enrollment = UserUtils.getEnrollment(keyFolderPath,keyFileName,certFolderPath,certFileName);
        userContext.setEnrollment(enrollment);
        FabricClient fabricClient = new FabricClient(userContext);
        Peer peer = fabricClient.getPeer("peer0.org1.example.com","grpcs://peer0.org1.example.com:7051",peer0tlsFilePath);
        Orderer order = fabricClient.getOrderer("orderer.example.com","grpcs://orderer.example.com:7050",ordertlsFilePath);
        String initArgs[] = {""};

        fabricClient.initChaincode("mychannel", TransactionRequest.Type.GO_LANG, "dbh","1.0",order,peer,"init",initArgs);

    }


    //invoke合约调用合约函数
    public void invode1() throws InvalidKeySpecException, NoSuchAlgorithmException, IOException, IllegalAccessException,
            InvalidArgumentException, InstantiationException, ClassNotFoundException, NoSuchMethodException, InvocationTargetException,
            TransactionException, ProposalException, CryptoException, org.bouncycastle.crypto.CryptoException {
        UserContext userContext = new UserContext();
        //userContext有某个组织的成员变量
        userContext.setAffiliation("Org1");//设置组织机构
        userContext.setMspId("Org1MSP");   //组织一的MSPID
        userContext.setAccount("dbh");
        userContext.setName("admin");
        Enrollment enrollment = UserUtils.getEnrollment(keyFolderPath,keyFileName,certFolderPath,certFileName);
        userContext.setEnrollment(enrollment);
        FabricClient fabricClient = new FabricClient(userContext);
        Peer peer0 = fabricClient.getPeer("peer0.org1.example.com","grpcs://peer0.org1.example.com:7051",peer0tlsFilePath);
        Peer peer1 = fabricClient.getPeer("peer1.org1.example.com","grpcs://peer1.org1.example.com:8051",peer1tlsFilePath);
        Orderer order = fabricClient.getOrderer("orderer.example.com","grpcs://orderer.example.com:7050",ordertlsFilePath);
        List<Peer> peers =new ArrayList<>();
        peers.add(peer0);
        peers.add(peer1);

//        String initArgs[] = {"555","{\"traceability_id\":\"555\",\"name\":\"老八秘制小汉堡\",\"date_of_manufacture\":\"2022/8/5\",\"manufactor\":\"米奇妙妙屋\",\"telephone\":\"12345678910\",\"listing_date\":\"2022/8/11\",\"courier_number\":\"123\",\"term_of_validity\":\"8个月\",\"company\":\"芜湖起飞\",\"tel\":\"12345678910\",\"delivery_date\":\"2022/8/5\" }"};

//        String initArgs[] = {"1212","{\"traceability_id\":\"1212\",\"name\":\"老八秘制小汉堡\",\"date_of_manufacture\":\"2022/8/5\",\"manufactor\":\"米奇妙妙屋\",\"telephone\":\"12345678910\"}"};
        String initArgs[] = {"1213","{\"traceability_id\":\"1213\",\"listing_date\":\"2022/8/11\",\"courier_number\":\"123\",\"term_of_validity\":\"8个月\",\"company\":\"芜湖起飞\",\"tel\":\"12345678910\",\"delivery_date\":\"2022/8/5\" }"};
//        String initArgs[] = {'"'+"Args"+'"'+","+"\"initMarble\",\"marbles9\",\"bule\",\"35\",\"tom\""};

        fabricClient.invoke("mychannel", TransactionRequest.Type.GO_LANG, "dbh","1.0",order,peers,"save",initArgs);
    }

}
