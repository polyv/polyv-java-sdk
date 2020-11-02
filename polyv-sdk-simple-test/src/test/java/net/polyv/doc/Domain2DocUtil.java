package net.polyv.doc;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.swagger.annotations.ApiModelProperty;
import net.polyv.live.entity.interact.LiveQuestionnaireListResponse;

/**
 * 根据实体类生成字段说明文档
 * @author: sadboy
 **/
public class Domain2DocUtil {
    
    private static final String[] tableHead = new String[]{"参数名", "必选", "类型", "说明"};
    public static String fileName = "";
    
    public static void main(String[] args) throws ClassNotFoundException, NoSuchFieldException {
        String className = LiveQuestionnaireListResponse.class.getName();
        Class objClass = Class.forName(className);
        List<Class> classList = new ArrayList<Class>();
        classList.add(objClass);
        analysisClass(classList);
    }
    
    private static StringBuffer domainCode = new StringBuffer();
    
    public static String domainCode(String className) throws ClassNotFoundException, NoSuchFieldException {
//        String className = "net.polyv.live.entity.web.auth.LiveUpdateChannelAuthRequest";
        Class objClass = Class.forName(className);
        List<Class> classList = new ArrayList<>();
        classList.add(objClass);
        analysisClass(classList);
        String string = domainCode.toString();
        domainCode = new StringBuffer();
        return string;
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
        boolean appendArgu = false;//是否添加了字段，未添加则不添加表头
        System.out.println(">>>>>" + objClass.getName());
        List<Class> classList = new ArrayList<>();
        StringBuffer stringBuffer = new StringBuffer();
        if (!"".equals(domainCode.toString())) {
            stringBuffer.append(queryDomainRandom(objClass.getSimpleName()) + " <!-- {docsify-ignore} -->")
                    .append("\n\n");
        }
        stringBuffer = appendBuffer(stringBuffer, tableHead);
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
                        if (annotation == null) {
                            throw new RuntimeException(f.getName() + "字段未设置ApiModelProperty");
                        }
                        if (!annotation.hidden()) {
                            appendArgu = true;
                            if (isSimpleType(accountPrincipalApproveClazz)) {
                                appendBuffer(stringBuffer, f.getName(), annotation.required() + "", "Array",
                                        annotation.value());
                            } else {
                                appendBuffer(stringBuffer, f.getName(), annotation.required() + "", "Array",
                                        annotation.value() + "【详见[" + simpleName + "参数描述](" + fileName + ".md?id=" +
                                                getRandom(simpleName) + ")】");
                                classList.add(accountPrincipalApproveClazz);
                            }
                        }
                    }
                } else {
                    if (annotation == null) {
                        continue;
                    }
                    if (!annotation.hidden()) {
                        appendArgu = true;
                        try {
                            String simpleType = getSimpleType(fieldType);
                            appendBuffer(stringBuffer, f.getName(), annotation.required() + "", simpleType,
                                    annotation.value());
                        } catch (Exception e) {
                            String simpleName = fieldType.getSimpleName();
                            appendBuffer(stringBuffer, f.getName(), annotation.required() + "", simpleName,
                                    annotation.value() + "【详见[" + simpleName + "参数描述](" + fileName + ".md?id=" +
                                            getRandom(simpleName) + ")】");
                            classList.add(fieldType);
                            e.printStackTrace();
                        }
                        
                    }
                }
                
            }
            objClass = objClass.getSuperclass();
        }
        System.out.println(stringBuffer.toString());
        if (appendArgu) {
            domainCode.append("\n").append(stringBuffer.toString());
        }
        return classList;
    }
    
    private static Map<String, String> domainRandom = new HashMap<>();
    private static int defaultInteger = 0;
    
    private static String getRandom(String simpleName) {
        String value = "polyv"+defaultInteger++;
        domainRandom.put(simpleName, value);
        return value;
    }
    
    private static String queryDomainRandom(String simpleName) {
        String value = domainRandom.get(simpleName);
        if (value == null) {
            throw new RuntimeException(simpleName + "未能获取RandomId");
        }
        return "<h6 id=\"" + value + "\"><a href=\"#/channelOperate?id=" + value + "\"data-id=\"" + simpleName +
                "参数描述\"class=\"anchor\"><span>"+simpleName+"参数描述</span></a></h6>";
    }
    
    public static StringBuffer appendBuffer(StringBuffer stringBuffer, String... strings) {
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
            case "boolean":
            case "Boolean":
            case "File":
                return simpleName;
            default:
        }
        throw new RuntimeException(clazz + "不能解析");
    }
    
    private static Boolean isSimpleType(Class<?> clazz) {
        String simpleName = clazz.getSimpleName();
        switch (simpleName) {
            case "String":
            case "Integer":
            case "Long":
            case "Float":
            case "Date":
                return true;
            default:
        }
        return false;
    }
    
}
