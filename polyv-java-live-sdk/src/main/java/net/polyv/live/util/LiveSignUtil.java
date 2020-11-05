package net.polyv.live.util;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
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
import net.polyv.live.constant.LiveConstant;

/**
 *  polyv 直播签名工具类
 * @author: thomas
 **/
@Slf4j
public class LiveSignUtil {
    private LiveSignUtil(){}
    public static String generateUUID(){
        return UUID.randomUUID().toString().replaceAll("-","");
    }
    
    /**
     * 获取直播加密字符串，并且假如到参数params中
     * @param params 加密参数
     * @param appId  保利威用户ID
     * @param appSecret 保利威用户签名密钥
     * @return  MD5签名字符串
     * @throws NoSuchAlgorithmException 签名异常
     */
    public static String setLiveSign(Map<String, String> params, String appId, String appSecret)
            throws NoSuchAlgorithmException, UnsupportedEncodingException {
        String sign = getSign(params, appId, appSecret);
        params.put("sign", sign);
        return sign;
    }
    
    /**
     * 获取直播加密字符串，并且假如到参数params中
     * @param params 加密参数
     * @param appId  保利威用户ID
     * @param appSecret 保利威用户签名密钥
     * @return  MD5签名字符串
     * @throws NoSuchAlgorithmException 签名异常
     */
    public static String getSign(Map<String, String> params, String appId, String appSecret)
            throws NoSuchAlgorithmException, UnsupportedEncodingException {
        params.put("appId", appId);
        // 处理参数，计算MD5哈希值
        log.debug("参与签名参数：{}" , JSON.toJSONString(params));
        String concatStr = concatParams(params);
        String plain = appSecret + concatStr + appSecret;
        log.debug("签名原始字符串：{}" , plain);
        String encrypted = md5Hex(plain).toUpperCase();
        log.debug("签名结果： {}" , encrypted);
        // 32位大写MD5值
        return encrypted;
    }
    
    /**
     * 把数组所有元素排序，并按照“参数参数值”的模式成字符串，用于计算MD5哈希值
     * @param params 需要排序并参与字符拼接的参数组
     * @return 拼接后字符串
     */
    public static String concatParams(Map<String, String> params) {
        List<String> keys = new ArrayList<String>(params.keySet());
        Collections.sort(keys);
        
        StringBuilder sb = new StringBuilder();
        for (String key : keys) {
            String value = params.get(key);
            if (StringUtils.isBlank(value)) {
                continue;
            }
            sb.append(key).append(value);
        }
        return sb.toString();
    }
    
    /**
     * 对字符串做MD5加密，返回加密后的字符串。
     * @param text 待加密的字符串。
     * @return 加密后的字符串。
     * @throws NoSuchAlgorithmException 签名异常
     */
    public static String md5Hex(String text) throws NoSuchAlgorithmException, UnsupportedEncodingException {
         
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            byte[] inputByteArray = text.getBytes(LiveConstant.UTF8);
            messageDigest.update(inputByteArray);
            byte[] resultByteArray = messageDigest.digest();
            return byteArrayToHex(resultByteArray).toLowerCase();
         
    }
    
    /**
     * 将字节数组换成成16进制的字符串
     * @param byteArray 字节
     * @return 字符串
     */
    public static String byteArrayToHex(byte[] byteArray) {
        // 初始化一个字符数组用来存放每个16进制字符
        char[] hexDigits = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
        // new一个字符数组，这个就是用来组成结果字符串的（一个byte是八位二进制，也就是2位十六进制字符（2的8次方等于16的2次方））
        char[] resultCharArray = new char[byteArray.length * 2];
        // 遍历字节数组，通过位运算（位运算效率高），转换成字符放到字符数组中去
        int index = 0;
        for (byte b : byteArray) {
            resultCharArray[index++] = hexDigits[b >>> 4 & 0xf];
            resultCharArray[index++] = hexDigits[b & 0xf];
        }
        // 字符数组组合成字符串返回
        return new String(resultCharArray);
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
     * @throws UnsupportedEncodingException 编码异常
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
                    .append(valueUrlEncode ? URLEncoder.encode(value,  LiveConstant.UTF8).replace("+", "%20") : value)
                    .append("&");
        }
        if (stringBuilder.length() > 0) {
            stringBuilder.deleteCharAt(stringBuilder.length() - 1);
        }
        return stringBuilder.toString();
    }
    
 
}