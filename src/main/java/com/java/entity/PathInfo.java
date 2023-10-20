package com.java.entity;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@Data
@ConfigurationProperties(prefix = "pathinfo")
public class PathInfo {

    private String keyFolderPath;
    private String keyFileName;
    private String certFolderPath;
    private String certFileName;
    private String txfilePath;
    private String ordertlsFilePath;
    private String peer1tlsFilePath;
    private String peer0tlsFilePath;
    private String peerorg2tlsFilePath;

    public String getKeyFolderPath() {
        return keyFolderPath;
    }

    public void setKeyFolderPath(String keyFolderPath) {
        this.keyFolderPath = keyFolderPath;
    }

    public String getKeyFileName() {
        return keyFileName;
    }

    public void setKeyFileName(String keyFileName) {
        this.keyFileName = keyFileName;
    }

    public String getCertFolderPath() {
        return certFolderPath;
    }

    public void setCertFolderPath(String certFolderPath) {
        this.certFolderPath = certFolderPath;
    }

    public String getCertFileName() {
        return certFileName;
    }

    public void setCertFileName(String certFileName) {
        this.certFileName = certFileName;
    }

    public String getTxfilePath() {
        return txfilePath;
    }

    public void setTxfilePath(String txfilePath) {
        this.txfilePath = txfilePath;
    }

    public String getOrdertlsFilePath() {
        return ordertlsFilePath;
    }

    public void setOrdertlsFilePath(String ordertlsFilePath) {
        this.ordertlsFilePath = ordertlsFilePath;
    }

    public String getPeer1tlsFilePath() {
        return peer1tlsFilePath;
    }

    public void setPeer1tlsFilePath(String peer1tlsFilePath) {
        this.peer1tlsFilePath = peer1tlsFilePath;
    }

    public String getPeer0tlsFilePath() {
        return peer0tlsFilePath;
    }

    public void setPeer0tlsFilePath(String peer0tlsFilePath) {
        this.peer0tlsFilePath = peer0tlsFilePath;
    }

    public String getPeerorg2tlsFilePath() {
        return peerorg2tlsFilePath;
    }

    public void setPeerorg2tlsFilePath(String peerorg2tlsFilePath) {
        this.peerorg2tlsFilePath = peerorg2tlsFilePath;
    }

    @Override
    public String toString() {
        return "PathInfo{" +
                "keyFolderPath='" + keyFolderPath + '\'' +
                ", keyFileName='" + keyFileName + '\'' +
                ", certFolderPath='" + certFolderPath + '\'' +
                ", certFileName='" + certFileName + '\'' +
                ", txfilePath='" + txfilePath + '\'' +
                ", ordertlsFilePath='" + ordertlsFilePath + '\'' +
                ", peer1tlsFilePath='" + peer1tlsFilePath + '\'' +
                ", peer0tlsFilePath='" + peer0tlsFilePath + '\'' +
                ", peerorg2tlsFilePath='" + peerorg2tlsFilePath + '\'' +
                '}';
    }
}
