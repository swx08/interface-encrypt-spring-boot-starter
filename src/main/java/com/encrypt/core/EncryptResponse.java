package com.encrypt.core;

import com.encrypt.annotation.Encrypt;
import com.encrypt.autoconfig.EncryptProperties;
import com.encrypt.response.ResultData;
import com.encrypt.utils.AESUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

/**
 * ClassName: EncryptResponse
 * Package: com.encrypt.core
 * Description:
 *
 * @Author: @weixueshi
 * @Create: 2024/8/10 - 15:17
 * @Version: v1.0
 */

/**
 * 启用配置属性
 */
@EnableConfigurationProperties(EncryptProperties.class)
/**
 * 全局处理加密响应
 */
@ControllerAdvice
public class EncryptResponse implements ResponseBodyAdvice<ResultData> {

    /**
     * 对象映射器，用于将对象转换为JSON格式
     */
    private ObjectMapper objMapper = new ObjectMapper();

    /**
     * 注入加密属性
     */
    @Autowired
    EncryptProperties encryptProperties;

    /**
     * 判断是否支持当前方法和转换器进行加密处理
     * @param returnType 方法参数
     * @param converterType 转换器类型
     * @return 如果方法上有Encrypt注解，则支持
     */
    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
        // 判断方法上是否有Encrypt注解
        return returnType.hasMethodAnnotation(Encrypt.class);
    }

    /**
     * 在写入响应体之前进行处理，主要用于加密响应数据
     * @param body 响应体内容
     * @param returnType 方法参数
     * @param selectedContentType 选择的内容类型
     * @param selectedConverterType 选择的转换器类型
     * @param request 请求对象
     * @param response 响应对象
     * @return 加密后的响应体
     */
    @Override
    public ResultData beforeBodyWrite(ResultData body, MethodParameter returnType, MediaType selectedContentType, Class<? extends HttpMessageConverter<?>> selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {
        // 获取加密密钥字节数组
        byte[] keyBytes = encryptProperties.getKey().getBytes();
        try {
            // 如果消息不为空，则对消息进行加密
            //if (body.getMessage()!=null) {
            //    body.setMessage(AESUtil.encrypt(body.getMessage().getBytes(),keyBytes));
            //}

            // 如果数据不为空，则对数据进行加密
            if (body.getData() != null) {
                // 将数据对象转换为JSON字节数组后进行加密
                body.setData(AESUtil.encrypt(objMapper.writeValueAsBytes(body.getData()), keyBytes));
            }
        } catch (Exception e) {
            // 异常处理，打印堆栈跟踪
            e.printStackTrace();
        }
        // 返回加密后的响应体
        return body;
    }
}

