package com.mini.auction.config;

import org.jasypt.encryption.StringEncryptor;
import org.jasypt.encryption.pbe.PooledPBEStringEncryptor;
import org.jasypt.encryption.pbe.config.SimpleStringPBEConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class JasyptConfig {

    @Value("${encrypt.key}")
    private String KEY;

    private final static String ALGORITHM = "PBEWithMD5AndDES";
    private final static String CNT = "1000";
    private final static String POOL_SIZE = "1";
    private final static String BASE64 = "base64";

    @Bean(name = "jasyptStringEncryptor")
    public StringEncryptor stringEncryptor(){

        SimpleStringPBEConfig simpleStringPBEConfig = new SimpleStringPBEConfig();
        simpleStringPBEConfig.setPassword(KEY);
        simpleStringPBEConfig.setAlgorithm(ALGORITHM);
        simpleStringPBEConfig.setKeyObtentionIterations(CNT);
        simpleStringPBEConfig.setPoolSize(POOL_SIZE);
        simpleStringPBEConfig.setStringOutputType(BASE64);

        PooledPBEStringEncryptor pooledPBEStringEncryptor = new PooledPBEStringEncryptor();
        pooledPBEStringEncryptor.setConfig(simpleStringPBEConfig);

        return pooledPBEStringEncryptor;
    }
}
