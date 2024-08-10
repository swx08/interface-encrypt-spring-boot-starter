package com.encrypt.autoconfig;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * ClassName: EncryptProperties
 * Package: com.encrypt.config
 * Description:
 *
 * @Author: @weixueshi
 * @Create: 2024/8/10 - 15:12
 * @Version: v1.0
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "spring.encrypt")
@ComponentScan
public class EncryptProperties {

    private final static String DEFAULT_KEY = "this.default.key";

    private String key = DEFAULT_KEY;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    @Bean
    public EncryptProperties encryptProperties() {
        return new EncryptProperties();
    }
}
