package net.polyv.common.v1.util;

/**
 * 字符串工具类
 * @author: sadboy
 **/
public class StringUtils {
    
    private StringUtils(){}
    
    public static boolean isNull(Object str){
        return null == str;
    }
    
    public static boolean isEmpty(String str){
        if(isNull(str)){
            return true;
        }
        return "".equals(str);
    }
    
    public static boolean isNotEmpty(String str){
        return !isEmpty(str);
    }
    
    public static boolean isBlank(String str){
        if(isNull(str)){
            return true;
        }
        return isEmpty(str.trim());
    }
    
    public static boolean isNotBlank(String str){
        return !isBlank(str);
    }
    
    public static String join(Object[] array, String separator) {
        if (array == null)
            return null;
        return join(array, separator, 0, array.length);
    }
    
    public static String join(Object[] array, String separator, int startIndex, int endIndex) {
        if (array == null)
            return null;
        if (separator == null)
            separator = "";
        int noOfItems = endIndex - startIndex;
        if (noOfItems <= 0)
            return "";
        StringBuilder buf = new StringBuilder(noOfItems * 16);
        for (int i = startIndex; i < endIndex; i++) {
            if (i > startIndex)
                buf.append(separator);
            if (array[i] != null)
                buf.append(array[i]);
        }
        return buf.toString();
    }
    
}
