package net.polyv.vod.v1.upload.utils;

import java.util.Collection;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;

/**
 * json处理工具
 */
public class JsonUtil {

    private static final Logger LOG = LoggerFactory.getLogger(JsonUtil.class);

	private static final ObjectMapper objectMapper = new ObjectMapper()
			.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
            .setVisibility(PropertyAccessor.FIELD, Visibility.ANY)
			.setSerializationInclusion(Include.NON_NULL);
    
	//用来解析将下划线参数转为驼峰格式字段的工具
    private static final ObjectMapper objectMapperUnderLine = new ObjectMapper()
            .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
            .setVisibility(PropertyAccessor.FIELD, Visibility.ANY)
            .setSerializationInclusion(Include.NON_NULL)
            .setPropertyNamingStrategy(PropertyNamingStrategy.CAMEL_CASE_TO_LOWER_CASE_WITH_UNDERSCORES);
	
    /**
     * 静态普通ObjectMapper对象
     */
    private static final ObjectMapper NORMAL_OBJECT_MAPPER = new ObjectMapper();

	private JsonUtil() {
	}

    /**
     * 取得ObjectMapper的对象
     * @return
     */
	public static final ObjectMapper getObjectMapper() {
		return objectMapper;
	}
    
    public static ObjectMapper getNormalObjectMapper() {
        return NORMAL_OBJECT_MAPPER;
    }

    /**
     * 将json格式的对象转为字符串
     * @param jsonValue json对象，可以是map
     * @return
     */
    public static final String jsonToString(Object jsonValue) {
        if (jsonValue == null) {
            return null;
        }
        try {
            return getObjectMapper().writeValueAsString(jsonValue);
        } catch (JsonProcessingException e) {
            LOG.error(e.getMessage(), e);
            return null;
        }
    }

    /**
     * 将json字符串转为实体类entity
     * @param jsonStr json字符串
     * @param clz 实体类型
     * @return
     */
    public static <T> T stringToBean(String jsonStr, Class<T> clz) {
        try {
            return getObjectMapper().readValue(jsonStr, clz);
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
        return null;
    }

    public static <T> T stringToBean(String jsonStr, JavaType javaType) {
        try {
            return getObjectMapper().readValue(jsonStr, javaType);
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
        return null;
    }
    
    /**
     * 将json字符串转为实体类entity, 支持下划线的变量
     * eg： json字符串有t_col, 解析成实体类里的字段是 tCol
     * @param jsonStr json字符串
     * @param clz 实体类型
     * @return
     */
    public static <T> T stringToBeanCompatible(String jsonStr, Class<T> clz){
        try {
            return objectMapperUnderLine.readValue(jsonStr, clz);
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
        return null;
    }

    /**
     * 对象转定义对象
     */
    public static <T> T convertObject(Object srcObject, Class<T> destObjectType) {
        return stringToBean(jsonToString(srcObject), destObjectType);
    }

    /**
     * 将json字符串转为复杂的集合类
     * @param jsonStr json字符串
     * @param parametrized 包装类的实例类型
     * @param parametersFor 包装类接口类型
     * @param parameterClasses 包装类里的实体类型
     * @return
     */
    public static final <T> T stringToComplicatedObject(String jsonStr, Class<?> parametrized, Class<?> parametersFor,
            Class<?> parameterClasses) {
        JavaType javaType = getObjectMapper().getTypeFactory().constructParametrizedType(parametrized, parametersFor,
                parameterClasses);
        try {
            return getObjectMapper().readValue(jsonStr, javaType);
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
        return null;
    }

    @SuppressWarnings("rawtypes")
    public static JavaType contructMapType(Class<? extends Map> mapClass, Class<?> keyClass, Class<?> valueClass) {
        return getNormalObjectMapper().getTypeFactory().constructMapType(mapClass, keyClass, valueClass);
    }

    /**
     * 构造Collection类型.
     */
    @SuppressWarnings("rawtypes")
    public static JavaType contructCollectionType(Class<? extends Collection> collectionClass, Class<?> elementClass) {
        return getNormalObjectMapper().getTypeFactory().constructCollectionType(collectionClass, elementClass);
    }

}
