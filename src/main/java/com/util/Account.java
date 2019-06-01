package com.util;

import org.web3j.crypto.CipherException;
import org.web3j.crypto.Credentials;
import org.web3j.crypto.WalletUtils;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameter;
import org.web3j.protocol.core.methods.response.EthAccounts;
import org.web3j.protocol.core.methods.response.EthGasPrice;
import org.web3j.protocol.core.methods.response.EthGetBalance;
import org.web3j.protocol.core.methods.response.Web3ClientVersion;
import org.web3j.protocol.parity.Parity;

import java.io.File;
import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.util.List;

public class Account {

    private static Parity parity = ParityClient.getParity();

    private static Web3j web3j = Web3JClient.getClient();


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
        System.out.println(ALICE);
        System.out.println(ALICE);
        return ALICE.getAddress();
    }

    public void testWebej(){
        Web3ClientVersion web3ClientVersion;
        try {
            web3ClientVersion = web3j.web3ClientVersion().send();
            String clientVersion = web3ClientVersion.getWeb3ClientVersion();
            System.out.println(clientVersion);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void Trade() {

    }

    public void getBalance() throws IOException {
        EthGetBalance ethGetBalance1 = web3j.ethGetBalance("0x416124709cfE45d19Ac10f512C57d54DA67F1Fc9", DefaultBlockParameter.valueOf("latest")).send();
        System.out.println(ethGetBalance1.getBalance());
        System.out.println(ethGetBalance1.getResult());
        System.out.println(ethGetBalance1.toString());
        System.out.println(ethGetBalance1.getError());
    }

    public void getGasPrice() throws IOException {
        EthGasPrice ethGetBalance1 = web3j.ethGasPrice().send();
        System.out.println(ethGetBalance1.getGasPrice());

    }
    public void getAccounts() throws IOException {
        EthAccounts ethGetBalance1 = web3j.ethAccounts().send();
        System.out.println(ethGetBalance1.getAccounts());

    }
}