package com.java.fabric;

import org.bouncycastle.crypto.CryptoException;
import org.hyperledger.fabric.sdk.exception.InvalidArgumentException;
import org.hyperledger.fabric.sdk.exception.ProposalException;
import org.hyperledger.fabric.sdk.exception.TransactionException;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

public class initChain {
    public static void main(String[] args) throws IOException, NoSuchAlgorithmException, InstantiationException, NoSuchMethodException, IllegalAccessException, InvalidArgumentException, InvocationTargetException, CryptoException, org.hyperledger.fabric.sdk.exception.CryptoException, InvalidKeySpecException, ClassNotFoundException, ProposalException, TransactionException {
        SdkMain sdkMain = new SdkMain();
        sdkMain.install();
        sdkMain.init();
        sdkMain.invode();
        //sdkMain.query();
        sdkMain.install1();
        sdkMain.init1();
        sdkMain.invode1();
    }
}
