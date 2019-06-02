package com.service;

import com.dao.AccountInfoDao;
import com.entity.AccountInfo;
import com.util.Account;
import com.util.ParityClient;
import com.util.Web3JClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

/**
 * eth api
 */
@Service
public class AccountInfoService {

    @Autowired
    AccountInfoDao accountInfoDao;

    public void save(AccountInfo account){
        account.setCreateTime(new Date());
        account.setLastUpdateTime(new Date());
        accountInfoDao.save(account);
    }
}
