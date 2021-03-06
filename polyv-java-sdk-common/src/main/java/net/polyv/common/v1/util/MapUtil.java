package net.polyv.common.v1.util;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.annotation.JSONField;

import net.polyv.common.v1.constant.Constant;

/**
 * MAP对象和HTTP参数 互相转换对象
 * @author: thomas
 **/
public class MapUtil {
    
    private static final Logger LOG = LoggerFactory.getLogger(MapUtil.class);
    
    private MapUtil() {
    }
    
    /**
     * Map key排序
     * @param map map
     * @return map
     */
    public static Map<String, String> order(Map<String, String> map) {
        HashMap<String, String> tempMap = new LinkedHashMap<String, String>();
        List<Map.Entry<String, String>> infoIds = new ArrayList<Map.Entry<String, String>>(map.entrySet());
        Collections.sort(infoIds, new Comparator<Map.Entry<String, String>>() {
            @Override
            public int compare(Map.Entry<String, String> o1, Map.Entry<String, String> o2) {
                return o1.getKey().compareTo(o2.getKey());
            }
        });
        
        for (Map.Entry<String, String> keySet : infoIds) {
            tempMap.put(keySet.getKey(), keySet.getValue());
        }
        return tempMap;
    }
    
    
    /**
     * 转换对象为map
     * @param obj object
     * @return map
     */
    public static Map<String, String> objectToMap(Object obj) {
        Map<String, String> reMap = new HashMap<String, String>();
        if (null == obj) {
            return null;
        }
        try {
            Class<?> objClass = obj.getClass();
            while (null != objClass) {
                Field[] fields = objClass.getDeclaredFields();
                for (int i = 0; i < fields.length; i++) {
                    Field f = objClass.getDeclaredField(fields[i].getName());
                    f.setAccessible(true);
                    Object o = f.get(obj);
                    if (o instanceof File) {
                        continue;
                    }
                    JSONField jsonField = f.getAnnotation(JSONField.class);
                    String key = jsonField != null && StringUtils.isNotBlank(jsonField.name()) ? jsonField.name() :
                            fields[i].getName();
                    String value = null;
                    if (o != null) {
//                        value = o instanceof List ? JSON.toJSONString(o) : o.toString();
                        if (o instanceof List) {
                            value = JSON.toJSONString(o);
                        } else if (o instanceof Date) {
                            if (jsonField != null && StringUtils.isNotBlank(jsonField.format())) {
                                //按照固定格式格式化数据
                                value = JSON.toJSONStringWithDateFormat(o, jsonField.format());
                                //待优化
                                value = value.replace("\"", "");
                            } else {
                                value = JSON.toJSONString(o);
                            }
                        } else {
                            //基本数据类型采用默认的,其他复合数据对象必须采用JSON序列化方式
                            value = o.toString();
                        }
                    }
                    reMap.put(key, (null == o) ? null : value);
                }
                objClass = objClass.getSuperclass();
            }
        } catch (NoSuchFieldException e) {
            LOG.error(e.getMessage(), e);
        } catch (IllegalArgumentException e) {
            LOG.error(e.getMessage(), e);
        } catch (IllegalAccessException e) {
            LOG.error(e.getMessage(), e);
        } catch (SecurityException e) {
            LOG.error(e.getMessage(), e);
        }
        return reMap;
    }
    
    
    /**
     * 获取所有Fields,包含父类field
     * @param clazz clazz
     * @return list
     */
    private static List<Field> getAllFields(Class<?> clazz) {
        if (!clazz.equals(Object.class)) {
            return new ArrayList();
        }
        List<Field> fields = new ArrayList<Field>(Arrays.asList(clazz.getDeclaredFields()));
        List<Field> fields2 = getAllFields(clazz.getSuperclass());
        if (null != fields2) {
            fields.addAll(fields2);
        }
        return fields;
    }
    
    /**
     * url 参数串连
     * @param map map
     * @param keyLower keyLower
     * @param valueUrlEncode valueUrlEncode
     * @return string
     */
    public static String mapJoin_noUser(Map<String, String> map, boolean keyLower, boolean valueUrlEncode) {
        StringBuilder stringBuilder = new StringBuilder();
        for (Map.Entry<String, String> entry : map.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();
            if (StringUtils.isBlank(value)) {
                continue;
            }
            
            try {
                String temp = (key.endsWith("_") && key.length() > 1) ? key.substring(0, key.length() - 1) : key;
                stringBuilder.append(keyLower ? temp.toLowerCase() : temp)
                        .append("=")
                        .append(valueUrlEncode ? URLEncoder.encode(value, Constant.UTF8).replace("+", "%20") : value)
                        .append("&");
            } catch (UnsupportedEncodingException e) {
                LOG.error(e.getMessage(), e);
            }
        }
        if (stringBuilder.length() > 0) {
            stringBuilder.deleteCharAt(stringBuilder.length() - 1);
        }
        return stringBuilder.toString();
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
     * 过滤值为NULL的key
     * @param sourceMap 源MAP
     * @return 目标MAP
     */
    public static Map<String, String> filterNullValue(Map<String, String> sourceMap) {
        Map<String, String> destMap = new HashMap<String, String>();
        
        for (Map.Entry<String, String> entry : sourceMap.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();
            if (StringUtils.isBlank(value) && !"password".equals(key)) {
                continue;
            }
            destMap.put(key, value);
        }
        return destMap;
    }
    
    
    /**
     * 将url与map拼接成HTTP查询字符串
     * @param url 请求url
     * @param paramMap 需要拼装的map
     * @return 拼装好的url
     */
    public static String appendUrl(String url, Map<String, String> paramMap) throws UnsupportedEncodingException {
        if (paramMap == null) {
            return url;
        }
        StringBuffer paramStringBuffer = new StringBuffer();
        Iterator<Map.Entry<String, String>> mapIterator = paramMap.entrySet().iterator();
        while (mapIterator.hasNext()) {
            Map.Entry<String, String> next = mapIterator.next();
            String value = next.getValue();
            if (StringUtils.isNotBlank(value)) {
                paramStringBuffer.append(next.getKey())
                        .append("=")
                        .append(URLEncoder.encode(value, Constant.UTF8))
                        .append("&");
            }
        }
        String paramStr = paramStringBuffer.toString();
//        String paramStr = MapUtil.mapJoinNotEncode(paramMap);
        if (StringUtils.isNotBlank(paramStr)) {
            if (url.indexOf("?") > 0) {
                if (url.endsWith("&")) {
                    url += paramStr.substring(0, paramStr.length() - 1);
                } else {
                    url += "&" + paramStr.substring(0, paramStr.length() - 1);
                }
            } else {
                url += "?" + paramStr.substring(0, paramStr.length() - 1);
            }
        }
        return url;
    }
}
