package net.polyv.vod.v1.util;

import com.alibaba.fastjson.JSON;
/**
 * JSON 工具类
 * @author thomas
 */
public class JsonUtil {

	private JsonUtil(){}

	public static <T> T parseObject(String json, Class<T> clazz){
		return JSON.parseObject(json, clazz);
	}

	public static String toJSONString(Object object){
		return JSON.toJSONString(object);
	}
}
