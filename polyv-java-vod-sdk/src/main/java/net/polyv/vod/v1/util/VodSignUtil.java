package net.polyv.vod.v1.util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import com.alibaba.fastjson.JSON;

import lombok.extern.slf4j.Slf4j;
import net.polyv.common.v1.constant.Constant;
import net.polyv.common.v1.util.StringUtils;
import net.polyv.vod.v1.entity.VodCommonRequest;

/**
 * polyv 点播签名工具类
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
    public static String setVodSign(Map<String, String> params, String appSecret)
            throws NoSuchAlgorithmException, UnsupportedEncodingException {
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
    public static String getSign(Map<String, String> params, String secretKey)
            throws NoSuchAlgorithmException, UnsupportedEncodingException {
        log.debug("参与签名参数：{}", JSON.toJSONString(params));
        List<String> keys = new ArrayList<String>(params.keySet());
        Collections.sort(keys);
        String plain = "";
        for (String key : keys) {
            if (null != params.get(key) && params.get(key).length() > 0) {
                plain += key + params.get(key);
            }
        }
        plain = secretKey+plain+secretKey;
        log.debug("签名原始字符串：{}", plain);
        String sign = md5Hex(plain).toUpperCase();
        log.debug("签名结果：{}", sign);
        return sign;
    }
    
    /**
     * 对字符串做MD5加密，返回加密后的字符串。
     * @param text 待加密的字符串。
     * @return 加密后的字符串。
     * @throws NoSuchAlgorithmException 签名异常
     *  @throws UnsupportedEncodingException 编码异常
     */
    public static String md5Hex(String text) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        MessageDigest messageDigest = MessageDigest.getInstance("MD5");
        byte[] inputByteArray = text.getBytes(Constant.UTF8);
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
     * sha1算法签名
     * @param input 签名原始字符串
     * @return 签名
     * @throws NoSuchAlgorithmException 签名异常
     */
    public static String getSha1(String input) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        MessageDigest mDigest = MessageDigest.getInstance("SHA1");
        byte[] result = mDigest.digest(input.getBytes(Constant.UTF8));
        StringBuilder sb = new StringBuilder();
        for (int b : result) {
            sb.append(Integer.toString((b & 0xff) + 0x100, 16).substring(1));
        }
        return sb.toString();
        
    }
    
    /**
     * 获取签名字段，appId，timestamp，requestId的 map 集合,本方法不参与具体签名方法和sign字段设置
     * @param t 请求体
     * @param <T> LiveCommonRequest
     * @return map
     */
    public static <T extends VodCommonRequest> Map<String, String> getSignMap(T t) {
        if (StringUtils.isBlank(t.getRequestId())) {
            t.setRequestId(VodSignUtil.generateUUID());
        }
        if (StringUtils.isBlank(t.getTimestamp())) {
            t.setTimestamp(String.valueOf(System.currentTimeMillis()));
        }
        Map<String, String> tempMap = new HashMap<String, String>();
        tempMap.put("ptime", t.getTimestamp());
        tempMap.put("appId", t.getAppId());
        tempMap.put("requestId", t.getRequestId());
        return tempMap;
    }
    
}