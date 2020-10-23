package net.polyv.live.service;

import java.io.File;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.annotation.JSONField;

import io.swagger.annotations.ApiModelProperty;
import net.polyv.live.entity.channel.operate.LiveChannelSettingRequest;
import net.polyv.live.entity.interact.LiveQuestionnaireDetailSetRequest;
import net.polyv.live.entity.interact.LiveQuestionnaireListResponse;
import net.polyv.live.entity.web.auth.LiveUpdateChannelAuthRequest;

/**
 * @author: sadboy
 **/
public class CommonDocUtil {
    
    private static final String[] tableHead = new String[]{"参数名", "必选", "类型", "说明"};
    
    public static void main(String[] args) throws ClassNotFoundException, NoSuchFieldException {
        String className = LiveQuestionnaireListResponse.class.getName();
        Class objClass = Class.forName(className);
        List<Class> classList = new ArrayList<>();
        classList.add(objClass);
        analysisClass(classList);
    }
    
    
    private static List<Class> analysisClass(List<Class> classList) throws ClassNotFoundException {
        if (classList != null && classList.size() > 0) {
            for (Class temp : classList) {
                classList.remove(temp);
                classList.addAll(analysisClass(temp));
                return analysisClass(classList);
            }
        }
        return null;
    }
    
    private static List<Class> analysisClass(Class objClass) throws ClassNotFoundException {
        List<Class> classList = new ArrayList<>();
        StringBuffer stringBuffer = appendBuffer(new StringBuffer(), tableHead);
        appendBuffer(stringBuffer, "--", "--", "--", "--");
        
        System.out.println("**" + objClass.getSimpleName() + "参数描述**");
        while (null != objClass && objClass != Object.class) {
            Field[] fields = objClass.getDeclaredFields();
            for (Field f : fields) {
                Class<?> fieldType = f.getType();
                ApiModelProperty annotation = f.getAnnotation(ApiModelProperty.class);
                if (fieldType == List.class) {
                    // 如果是List类型，得到其Generic的类型
                    Type genericType = f.getGenericType();
                    if (genericType == null) {
                        continue;
                    }
                    // 如果是泛型参数的类型
                    if (genericType instanceof ParameterizedType) {
                        ParameterizedType pt = (ParameterizedType) genericType;
                        //得到泛型里的class类型对象
                        Class<?> accountPrincipalApproveClazz = (Class<?>) pt.getActualTypeArguments()[0];
//                        System.out.println(accountPrincipalApproveClazz);
                        String simpleName = accountPrincipalApproveClazz.getSimpleName();
//                        System.out.println(simpleName);
                        if (!annotation.hidden()) {
                            appendBuffer(stringBuffer, f.getName(), annotation.required() + "", "Array",
                                    annotation.value() + "【详见**" + simpleName + "参数描述**】");
                            classList.add(accountPrincipalApproveClazz);
                        }
                    }
                } else {
                    if (annotation == null) {
                        continue;
                    }
                    if (!annotation.hidden()) {
                        appendBuffer(stringBuffer, f.getName(), annotation.required() + "", getSimpleType(fieldType),
                                annotation.value());
                    }
                }
                
            }
            objClass = objClass.getSuperclass();
        }
        System.out.println(stringBuffer.toString());
        return classList;
    }
    
    private static StringBuffer appendBuffer(StringBuffer stringBuffer, String... strings) {
        stringBuffer.append("| ");
        for (String string : strings) {
            stringBuffer.append(string).append(" | ");
        }
        return stringBuffer.append("\n");
    }
    
    private static String getSimpleType(Class<?> clazz) {
        String simpleName = clazz.getSimpleName();
        switch (simpleName) {
            case "String":
            case "Integer":
            case "Long":
            case "Float":
            case "Date":
                return simpleName;
            default:
        }
        throw new RuntimeException(clazz + "不能解析");
    }
    
}
