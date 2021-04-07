package net.polyv.vod.v1.upload.rest;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.polyv.vod.v1.upload.utils.EncryptUtil;


public class BasicRestApi {
    
    /**
     * 计算签名
     * @param parray
     * @param secretKey
     * @return
     */
    protected static String calculateSign(Map<String, String> parray, String secretKey) {
        
        // 过滤签名字段
        Map<String, String> params = paraFilter(parray);
        
        // 处理参数，计算SHA1哈希值
        String concatedStr = createLinkString(params);
        
        String encrypted = EncryptUtil.getSHA1(concatedStr + secretKey);
        return encrypted.toUpperCase();
    }
    
    /**
     * 除去数组中的空值和签名参数
     * @param sArray 签名参数组
     * @return 去掉空值与签名参数后的新签名参数组
     */
    private static Map<String, String> paraFilter(Map<String, String> sArray) {
        
        Map<String, String> result = new HashMap<>();
        
        if (sArray == null || sArray.size() <= 0) {
            return result;
        }
        
        String key;
        String value;
        for (Map.Entry<String, String> entry : sArray.entrySet()) {
            key = entry.getKey();
            value = sArray.get(key);
            if (value == null || "".equals(value) || "sign".equalsIgnoreCase(key) || "sign_type".equalsIgnoreCase(key)) {
                continue;
            }
            result.put(key, value);
        }
        
        return result;
    }
    
    /**
     * 把数组所有元素排序，并按照“参数=参数值”的模式用“&”字符拼接成字符串
     * @param params 需要排序并参与字符拼接的参数组
     * @return 拼接后字符串
     */
    private static String createLinkString(Map<String, String> params) {
        
        List<String> keys = new ArrayList<>(params.keySet());
        Collections.sort(keys);
        
        String prestr = "";
        
        for (int i = 0; i < keys.size(); i++) {
            String key = keys.get(i);
            String value = params.get(key);
            
            if (i == keys.size() - 1) {// 拼接时，不包括最后一个&字符
                prestr = prestr + key + "=" + value;
            } else {
                prestr = prestr + key + "=" + value + "&";
            }
        }
        
        return prestr;
    }
    
    
}
