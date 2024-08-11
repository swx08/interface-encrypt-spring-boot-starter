package com.encrypt.starter.config;

import com.encrypt.starter.config.EncryptProperties;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * ClassName: EncryptAutoConfiguration
 * Package: com.encrypt
 * Description:
 *
 * @Author: @weixueshi
 * @Create: 2024/8/10 - 21:23
 * @Version: v1.0
 */
@Configuration
@ComponentScan("com.encrypt.starter")
public class EncryptAutoConfiguration {
}
