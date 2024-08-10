package com.encrypt.core;

import com.encrypt.annotation.Decrypt;
import com.encrypt.autoconfig.EncryptProperties;
import com.encrypt.utils.AESUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.RequestBodyAdviceAdapter;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;

/**
 * ClassName: DecryptRequest
 * Package: com.encrypt.core
 * Description:
 *
 * @Author: @weixueshi
 * @Create: 2024/8/10 - 15:26
 * @Version: v1.0
 */

/**
 * 启用EncryptProperties配置属性
 * 作为全局控制器增强，用于处理请求体的解密
 */
@EnableConfigurationProperties(EncryptProperties.class)
@ControllerAdvice
public class DecryptRequest extends RequestBodyAdviceAdapter {

    /**
     * 注入加密配置属性
     */
    @Autowired
    private EncryptProperties encryptProperties;

    /**
     * 确定是否支持处理当前请求
     * 如果方法或其参数上存在Decrypt注解，则返回true
     * @param methodParameter
     * @param targetType
     * @param converterType
     * @return
     */
    @Override
    public boolean supports(MethodParameter methodParameter, Type targetType, Class<? extends HttpMessageConverter<?>> converterType) {
        return methodParameter.hasMethodAnnotation(Decrypt.class) || methodParameter.hasParameterAnnotation(Decrypt.class);
    }

    /**
     * 在读取请求体之前进行处理，主要用于解密请求体
     * 参数inputMessage：原始的Http输入消息
     * 返回处理后的Http输入消息，这里主要用于替换加密的请求体为解密后的
     * @param inputMessage
     * @param parameter
     * @param targetType
     * @param converterType
     * @return
     * @throws IOException
     */
    @Override
    public HttpInputMessage beforeBodyRead(final HttpInputMessage inputMessage, MethodParameter parameter, Type targetType, Class<? extends HttpMessageConverter<?>> converterType) throws IOException {
        // 读取原始请求体的所有数据到字节数组
        byte[] body = new byte[inputMessage.getBody().available()];
        inputMessage.getBody().read(body);
        try {
            // 使用AES解密请求体
            byte[] decrypt = AESUtil.decrypt(body, encryptProperties.getKey().getBytes());
            // 将解密后的数据放入ByteArrayInputStream，以便后续处理
            final ByteArrayInputStream basic = new ByteArrayInputStream(decrypt);
            // 返回一个新的HttpInputMessage，其中包含解密后的请求体
            return new HttpInputMessage() {
                @Override
                public InputStream getBody() throws IOException {
                    return basic;
                }

                @Override
                public HttpHeaders getHeaders() {
                    // 使用原始请求的HTTP头部
                    return inputMessage.getHeaders();
                }
            };
        } catch (Exception e) {
            // 异常处理：打印堆栈跟踪，实际应用中应考虑更合适的异常处理策略
            e.printStackTrace();
        }
        // 如果解密失败，调用父类方法返回原始请求体
        return super.beforeBodyRead(inputMessage, parameter, targetType, converterType);
    }
}

