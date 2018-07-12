package com.dwring.springboot.utils;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

/**   
* @Title: Base64Utils.java 
* @Package com.dwring.springboot.utils 
* @Description: base64工具
* @author haichangzhang   
* @date 2018年7月11日 下午5:13:51 
* @version V1.0   
*/
public class Base64Utils {



    /**
     * 将字符串加密为base64
     * @param before
     * @return
     */
    public static String toBase64(String before){
        return Base64.getEncoder().encodeToString(before.getBytes(StandardCharsets.UTF_8));
    }

    /**
     * 将加密为base64后的字符串解密为原文
     * @param base64
     * @return
     */
    public static String fromBase64(String base64){
        return new String(Base64.getDecoder().decode(base64),StandardCharsets.UTF_8);
    }
}
