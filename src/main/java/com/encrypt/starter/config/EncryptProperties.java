package com.encrypt.starter.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * ClassName: EncryptProperties
 * Package: com.encrypt.config
 * Description:
 *
 * @Author: @weixueshi
 * @Create: 2024/8/10 - 15:12
 * @Version: v1.0
 */
@ConfigurationProperties(prefix = "spring.encrypt")
public class EncryptProperties {

    private final static String DEFAULT_KEY = "this.default.key";

    private String key = DEFAULT_KEY;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
