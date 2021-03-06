package com.github.sparkzxl.core.utils;

import cn.hutool.crypto.Mode;
import cn.hutool.crypto.Padding;
import cn.hutool.crypto.symmetric.DES;

import java.nio.charset.Charset;

/**
 * description: DES加密
 *
 * @author zhouxinlei
 * @date 2020-05-24 12:51:14
 */
public class DesUtils {

    private static final DES des;

    private DesUtils() {

    }

    static {
        String key = "0CoJUm6Qyw8W8jud";
        String iv = "01020304";
        des = new DES(Mode.CTS, Padding.PKCS5Padding, key.getBytes(), iv.getBytes());
    }

    public static String encryptHex(String content) {
        return des.encryptHex(content);
    }

    public static String decryptStr(String content, Charset charset) {
        return des.decryptStr(content, charset);
    }

    public static String encrypt(String content) {
        return new String(des.encrypt(content));
    }

    public static String decrypt(String content) {
        return new String(des.decrypt(content));
    }
}
