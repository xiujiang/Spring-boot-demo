package com.service;

import com.util.ParityClient;
import com.util.Web3JClient;
import org.web3j.crypto.CipherException;
import org.web3j.crypto.Credentials;
import org.web3j.crypto.WalletUtils;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.parity.Parity;

import java.io.File;
import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.util.List;

public class AccountService {
    private static Parity parity = ParityClient.getParity();

    private static Web3j web3j = Web3JClient.getClient();

    /**
     * 查询用户账户
     * xiujiang.iu
     */
    public List<String> getAccountlist(){

        try{
            return  parity.personalListAccounts().send().getAccountIds();
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }


    public String createAccountByWeb3j(String filePath,String password) throws NoSuchAlgorithmException, NoSuchProviderException, InvalidAlgorithmParameterException, CipherException, IOException {
        String fileName = WalletUtils.generateNewWalletFile(password,new File(filePath),false);
        System.out.println(fileName);
        Credentials ALICE = WalletUtils.loadCredentials(password,filePath+fileName);
        System.out.println(ALICE.getAddress());
        return ALICE.getAddress();
    }

    public String getEthGasPrice() {

        return null;
    }

    public String getEthBalance() {
        return null;
    }

}
