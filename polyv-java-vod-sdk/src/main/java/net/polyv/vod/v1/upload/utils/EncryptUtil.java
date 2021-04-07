package net.polyv.vod.v1.upload.utils;

import java.security.MessageDigest;

import org.apache.commons.codec.digest.DigestUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EncryptUtil {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(EncryptUtil.class);
    
    /**
     * 对字符串做MD5加密，返回加密后的字符串。
     * @param text 待加密的字符串。
     * @return 加密后的字符串。
     */
    public static String md5Hex(String text) {
        return DigestUtils.md5Hex(text);
    }
    
    /**
     * 对字符串做SHA-1加密，返回加密后的字符串。
     * @param text 待加密的字符串。
     * @return 加密后的字符串。
     */
    public static String shaHex(String text) {
        return DigestUtils.sha256Hex(text);
    }
    
    public static String getSHA1(String str) {
        return encode(str, "SHA-1");
    }
    
    /**
     * 对字符串做SHA-1加密，然后截取前面20个字符（遗留OVP系统的密码验证方式）。
     * @param str 待加密的字符串。
     * @return 加密后的前20个字符。
     */
    public static String getLittleSHA1(String str) {
        String estr = encode(str, "SHA-1");
        return estr.substring(0, 20);
    }
    
    private static String encode(String str, String type) {
        try {
            MessageDigest alga = MessageDigest.getInstance(type);
            alga.update(str.getBytes("UTF-8"));
            byte[] digesta = alga.digest();
            return byte2hex(digesta);
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            return "";
        }
    }
    
    public static String byte2hex(byte[] b) {
        String hs = "";
        String stmp = "";
        for (int n = 0; n < b.length; n++) {
            stmp = (Integer.toHexString(b[n] & 0XFF));
            if (stmp.length() == 1)
                hs = hs + "0" + stmp;
            else
                hs = hs + stmp;
        }
        return hs.toUpperCase();
    }
    
}
