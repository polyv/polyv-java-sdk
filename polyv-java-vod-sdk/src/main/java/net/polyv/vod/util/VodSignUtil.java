package net.polyv.vod.util;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.commons.lang3.StringUtils;

import com.alibaba.fastjson.JSON;

import lombok.extern.slf4j.Slf4j;

/**
 * polyv 直播签名工具类
 * @author: thomas
 **/
@Slf4j
public class VodSignUtil {
    
    public static String generateUUID() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }
    
    /**
     * 获取加密串
     */
    public static String setVodSign(Map<String, String> params, String appSecret) throws NoSuchAlgorithmException {
        String sign = getSign(params, appSecret);
        params.put("sign", sign);
        return sign;
    }
    
    /**
     * 点播签名方法
     * @param params 签名参数
     * @param secretKey 签名密钥
     * @return 签名
     * @throws NoSuchAlgorithmException 异常异常
     */
    public static String getSign(Map<String, String> params, String secretKey) throws NoSuchAlgorithmException {
        log.debug("参与签名参数：{}" , JSON.toJSONString(params));
        List<String> keys = new ArrayList<>(params.keySet());
        List<String> tmp = new ArrayList<>();
        Collections.sort(keys);
        for (String key : keys) {
            if (null != params.get(key) && params.get(key).length() > 0) {
                tmp.add(key + "=" + params.get(key));
            }
        }
        String plain = String.join("&", tmp) + secretKey;
        log.debug("签名原始字符串：" + plain);
        String sign = getSha1(plain).toUpperCase();
        log.debug("签名结果：" + sign);
        return sign;
    }
    
    /**
     * sha1算法签名
     * @param input 签名原始字符串
     * @return 签名
     * @throws NoSuchAlgorithmException 签名异常
     */
    public static String getSha1(String input) throws NoSuchAlgorithmException {
         
            MessageDigest mDigest = MessageDigest.getInstance("SHA1");
            byte[] result = mDigest.digest(input.getBytes(StandardCharsets.UTF_8));
            StringBuilder sb = new StringBuilder();
            for (int b : result) {
                sb.append(Integer.toString((b & 0xff) + 0x100, 16).substring(1));
            }
            return sb.toString();
         
    }
    
    
    /**
     * url 参数串连但是不进行参数Encode
     * @param map map
     * @return string
     */
    public static String mapJoinNotEncode(Map<String, String> map) {
        StringBuilder stringBuilder = new StringBuilder();
        int i = 0;
        for (Map.Entry<String, String> entry : map.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();
            if (StringUtils.isBlank(value)) {
                continue;
            }
            if (0 != i) {
                stringBuilder.append("&");
            }
            stringBuilder.append(key).append("=").append(map.get(key));
            i++;
        }
        return stringBuilder.toString();
    }
    
    /**
     * url 参数串连
     * @param map map
     * @param keyLower keyLower
     * @param valueUrlEncode valueUrlEncode
     * @return string
     */
    public static String mapJoinEncode(Map<String, String> map, boolean keyLower, boolean valueUrlEncode)
            throws UnsupportedEncodingException {
        StringBuilder stringBuilder = new StringBuilder();
        for (Map.Entry<String, String> entry : map.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();
            if (StringUtils.isBlank(value)) {
                continue;
            }
            String temp = (key.endsWith("_") && key.length() > 1) ? key.substring(0, key.length() - 1) : key;
            stringBuilder.append(keyLower ? temp.toLowerCase() : temp)
                    .append("=")
                    .append(valueUrlEncode ? URLEncoder.encode(value,  StandardCharsets.UTF_8.name()).replace("+", "%20") : value)
                    .append("&");
        }
        if (stringBuilder.length() > 0) {
            stringBuilder.deleteCharAt(stringBuilder.length() - 1);
        }
        return stringBuilder.toString();
    }
    
}