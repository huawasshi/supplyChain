package com.java.fabric;

import org.hyperledger.fabric.sdk.*;
import org.hyperledger.fabric.sdk.exception.CryptoException;
import org.hyperledger.fabric.sdk.exception.InvalidArgumentException;
import org.hyperledger.fabric.sdk.exception.ProposalException;
import org.hyperledger.fabric.sdk.exception.TransactionException;
import org.hyperledger.fabric.sdk.security.CryptoSuite;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.*;


public class FabricClient {

    private static  final Logger log = LoggerFactory.getLogger(FabricClient.class);

    private HFClient hfClient;

    public  FabricClient(UserContext userContext) throws IllegalAccessException, InvocationTargetException, InvalidArgumentException, InstantiationException, NoSuchMethodException, CryptoException, ClassNotFoundException {
        hfClient = HFClient.createNewInstance();
        CryptoSuite cryptoSuite = CryptoSuite.Factory.getCryptoSuite();
        hfClient.setCryptoSuite(cryptoSuite);
        hfClient.setUserContext(userContext);

    }

    //创建channel
    public void createChannel(String channelName, Orderer order, String txPath)throws IOException, InvalidArgumentException, TransactionException {
        ChannelConfiguration channelConfiguration = new ChannelConfiguration(new File(txPath));
        hfClient.newChannel(channelName,order,channelConfiguration,hfClient.getChannelConfigurationSignature(channelConfiguration,hfClient.getUserContext()));
    }

    //得到orderer节点
    public Orderer getOrderer(String name ,String grpcUrl,String tlsFilePath) throws InvalidArgumentException {
        Properties preperties = new Properties();
        preperties.setProperty("pemFile",tlsFilePath);

        Orderer orderer = hfClient.newOrderer(name,grpcUrl,preperties);
        return orderer;
    }

    //得到peer节点
    public Peer getPeer(String name , String grpcUrl, String tlsFilePath) throws InvalidArgumentException {
        Properties preperties = new Properties();
        preperties.setProperty("pemFile",tlsFilePath);
        Peer peer = hfClient.newPeer(name,grpcUrl,preperties);
        return peer;
    }

    //获得channel
    public Channel getChannel(String channelName) throws TransactionException, InvalidArgumentException {
        Channel test = hfClient.newChannel(channelName);
        return test;
    }

    //安装合约
    public void installChaincode(TransactionRequest.Type lang, String chaincodeName, String chaincodeVersion, String chaincodeLocation,String chaincodePath, List<Peer> peers) throws InvalidArgumentException, ProposalException {
        InstallProposalRequest installProposalRequest = hfClient.newInstallProposalRequest();
        ChaincodeID.Builder builder = ChaincodeID.newBuilder().setName(chaincodeName).setVersion(chaincodeVersion);
        installProposalRequest.setChaincodeLanguage(lang);
        installProposalRequest.setChaincodeID(builder.build());
        installProposalRequest.setChaincodeSourceLocation(new File(chaincodeLocation));
        installProposalRequest.setChaincodePath(chaincodePath);
        Collection<ProposalResponse> responses = hfClient.sendInstallProposal(installProposalRequest,peers);
        for (ProposalResponse response:responses){
            if (response.getStatus().getStatus()==200){
                log.info("{} installed success",response.getPeer().getName());
            }else{
                log.info("{} installed fail" ,response.getPeer().getName());
            }
        }
    }

    //实例化合约
    public  void  initChaincode(String channelName, TransactionRequest.Type lang, String chaincodeName, String chaincodeVersion, Orderer order, Peer peer, String funcName, String args[]) throws InvalidArgumentException, TransactionException, ProposalException {
        Channel channel = getChannel(channelName);
        channel.addPeer(peer);
        channel.addOrderer(order);
        channel.initialize();
        InstantiateProposalRequest instantiateProposalRequest = hfClient.newInstantiationProposalRequest();
        instantiateProposalRequest.setArgs(args);
        instantiateProposalRequest.setFcn(funcName);
        instantiateProposalRequest.setChaincodeLanguage(lang);
        ChaincodeID.Builder builder = ChaincodeID.newBuilder().setName(chaincodeName).setVersion(chaincodeVersion);
        instantiateProposalRequest.setChaincodeID(builder.build());
        Collection<ProposalResponse> responses = channel.sendInstantiationProposal(instantiateProposalRequest);
        channel.sendTransaction(responses);
    }

    //触发合约,调用合约函数
    public void invoke(String channelName, TransactionRequest.Type lang, String chaincodeName, String chaincodeVersion, Orderer order, List<Peer> peers, String funcName, String args[]) throws InvalidArgumentException, TransactionException, ProposalException {
        Channel channel = getChannel(channelName);
        channel.addOrderer(order);
        for(Peer p:peers){
            channel.addPeer(p);
        }
        channel.initialize();
        TransactionProposalRequest transactionProposalRequest = hfClient.newTransactionProposalRequest();
        transactionProposalRequest.setChaincodeLanguage(lang);
        transactionProposalRequest.setArgs(args);
        transactionProposalRequest.setFcn(funcName);
        ChaincodeID.Builder builder = ChaincodeID.newBuilder().setName(chaincodeName).setVersion(chaincodeVersion);
        transactionProposalRequest.setChaincodeID(builder.build());
        //往哪几个节点发送提案
        Collection<ProposalResponse> responses = channel.sendTransactionProposal(transactionProposalRequest,peers);
        for (ProposalResponse response:responses){
            if (response.getStatus().getStatus()==200){
                log.info("{} invoke proposal {} success",response.getPeer().getName(),funcName);
            }else{
                String logArgs[] = {response.getMessage(),funcName,response.getPeer().getName()};
                log.error("{} invoke proposal {} fail on {}" ,logArgs);
            }
        }
        channel.sendTransaction(responses);
    }

    //调用合约查询函数
    public Map queryChaincode(List<Peer> peers, String channelName, TransactionRequest.Type lang, String chaincodeName, String funcName, String args[]) throws InvalidArgumentException, TransactionException, ProposalException {
        Channel channel = getChannel(channelName);
        for(Peer p:peers){
            channel.addPeer(p);
        }
        channel.initialize();
        HashMap map = new HashMap();
        QueryByChaincodeRequest queryByChaincodeRequest = hfClient.newQueryProposalRequest();
        ChaincodeID.Builder builder = ChaincodeID.newBuilder().setName(chaincodeName);
        queryByChaincodeRequest.setChaincodeID(builder.build());
        queryByChaincodeRequest.setArgs(args);
        queryByChaincodeRequest.setFcn(funcName);
        queryByChaincodeRequest.setChaincodeLanguage(lang);
        Collection<ProposalResponse> responses = channel.queryByChaincode(queryByChaincodeRequest);
        for(ProposalResponse response:responses){
            if (response.getStatus().getStatus()==200){
                log.info("data is {}",response.getProposalResponse().getPayload());
                map.put(response.getStatus().getStatus(),new String(response.getProposalResponse().getResponse().getPayload().toByteArray()));
                return map;
            }else{
                log.error("data get error {}",response.getMessage());
                map.put(response.getStatus().getStatus(),response.getMessage());
                return map;
            }
        }
        map.put("code","404");
        return map;
    }




}
