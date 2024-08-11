package com.encrypt.starter.utils;

/**
 * ClassName: AESUtil
 * Package: com.encrypt.utils
 * Description:
 *
 * @Author: @weixueshi
 * @Create: 2024/8/10 - 14:53
 * @Version: v1.0
 */

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

/**
 * 对称加密工具类
 */
public class AESUtil {

    // 定义AES算法的加密方式
    private static final String AES_ALGORITHM = "AES/ECB/PKCS5Padding";

    /**
     * 获取cipher实例
     * @param key 加密/解密的密钥
     * @param mode Cipher的工作模式，加密或解密
     * @return Cipher实例
     * @throws Exception 如果初始化失败，抛出异常
     */
    private static Cipher getCipher(byte[] key,int mode) throws Exception {
        // 创建AES密钥
        SecretKeySpec secretKeySpec = new SecretKeySpec(key, "AES");
        // 获取Cipher实例
        Cipher cipher = Cipher.getInstance(AES_ALGORITHM);
        // 初始化Cipher
        cipher.init(mode, secretKeySpec);
        return cipher;
    }

    /**
     * AES加密方法
     * @param data 需要加密的数据
     * @param key 加密的密钥
     * @return 加密后的数据，以Base64编码的字符串形式表示
     * @throws Exception 如果加密过程中出现错误，抛出异常
     */
    public static String encrypt(byte[] data, byte[] key) throws Exception {
        System.out.println("加密数据为：" + new String(data));
        // 获取Cipher实例并设置为加密模式
        Cipher cipher = getCipher(key, Cipher.ENCRYPT_MODE);
        // 执行加密操作，并将结果用Base64编码后返回
        return Base64.getEncoder().encodeToString(cipher.doFinal(data));
    }

    /**
     * AES解密方法
     * @param data 需要解密的数据，Base64编码的字符串形式
     * @param key 解密的密钥
     * @return 解密后的原始数据
     * @throws Exception 如果解密过程中出现错误，抛出异常
     */
    public static byte[] decrypt(byte[] data, byte[] key) throws BadPaddingException {
    try {
        System.out.println("解密数据为：" + new String(data));
        // 获取Cipher实例并设置为解密模式
        Cipher cipher = getCipher(key, Cipher.DECRYPT_MODE);

        // 尝试解码Base64数据
        byte[] decodedData;
        try {
            decodedData = Base64.getDecoder().decode(data);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("数据不是有效的Base64编码", e);
        }

        // 执行解密操作，并返回解密后的数据
        return cipher.doFinal(decodedData);
    } catch (BadPaddingException e) {
        throw new BadPaddingException("解密失败，请检查密钥");
    } catch (InvalidKeyException | IllegalBlockSizeException e) {
        throw new RuntimeException("解密过程中发生错误", e);
    } catch (Exception e) {
        throw new RuntimeException("解密过程中发生未知错误", e);
    }
}

}
