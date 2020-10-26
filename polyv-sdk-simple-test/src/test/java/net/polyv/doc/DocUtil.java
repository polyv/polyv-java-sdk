package net.polyv.doc;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSON;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;


/**
 * @author: sadboy
 **/
public class DocUtil {
    //Junit测试类的路径，用来找源码
    private static final String JUNIT_PATH = "D:\\project-yf\\polyv-java-sdk\\polyv-java-live-sdk\\src\\test\\java\\";
    //Junit测试类全路径
    private static final String JUNIT_CLASS_NAME = "net.polyv.live.service.interact.LiveQuestionnaireServiceImplTest";
    //生成文档所在文件，不存在则自动创建
    private static final String filePath = "C:\\Users\\POLYV\\Desktop\\build\\code.txt";
    
    public static void main(String[] args) throws ClassNotFoundException, IOException, NoSuchFieldException {
        String path = JUNIT_PATH + JUNIT_CLASS_NAME.replace(".", "/") + ".java";
        List<TestMethod> testMethods = readFileLine(path);
        
        JavaDocUtil.PolyvClassDoc doc = JavaDocUtil.getDoc(path);
        System.out.println(JSON.toJSONString(doc));
        System.out.println(doc.getMethodDocs().size());
        if (testMethods.size() != doc.getMethodDocs().size()) {
            throw new RuntimeException("JavaDoc对应的方法个数与.java文件不一致，请检查方法是否被注释");
        }
        
        
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("## ").append(doc.getTitle()).append("\n");
        for (int i = 0; i < doc.getMethodDocs().size(); i++) {
            JavaDocUtil.PolyvMethodDoc methodDoc = doc.getMethodDocs().get(i);
            TestMethod testMethod = testMethods.get(i);
            stringBuffer.append("### ").append(methodDoc.getTitle()).append("\n");
            stringBuffer.append("#### 描述").append("\n");
            stringBuffer.append("```").append("\n");
            stringBuffer.append(methodDoc.getDescription()).append("\n");
            stringBuffer.append("```").append("\n");
            stringBuffer.append("#### 调用约束").append("\n");
            stringBuffer.append("1.接口调用有频率限制，[详细请查看](../limit.md)").append("\n");
            stringBuffer.append(methodDoc.getNote()).append("\n");
            stringBuffer.append("#### 代码示例").append("\n");
            stringBuffer.append("```java").append("\n");
            stringBuffer.append(testMethod.getMethodCode()).append("\n");
            stringBuffer.append("```").append("\n");
            stringBuffer.append("#### 单元测试流程").append("\n");
//            stringBuffer.append("[swagger 程序接入-")
//                    .append(methodDoc.getTitle())
//                    .append("](http://47.115.173.234:8002/doc.html#/%E7%9B%B4%E6%92%ADSDK/%E7%9B%B4%E6%92%AD%E9%A2%91" +
//                            "%E9%81%93%E7%AE%A1%E7%90%86/createChannelUsingPOST)")
//                    .append("\n");
            stringBuffer.append("#### 请求入参描述").append("\n");
            stringBuffer.append(Domain2DocUtil.domainCode(testMethod.getRequestArgu())).append("\n");
            stringBuffer.append("#### 返回对象描述").append("\n");
            stringBuffer.append(Domain2DocUtil.domainCode(testMethod.getResponseArgu())).append("\n");
        }
        System.out.println(stringBuffer.toString());
        File f = new File(filePath);
        if (!f.exists()) {
            f.createNewFile();
        }
        appendFile(filePath, stringBuffer.toString());
    }
    
    public static void appendFile(String file, String conent) {
        BufferedWriter out = null;
        try {
            out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file, true)));
            out.write(conent + "\r\n");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    
    
    public static List<TestMethod> readFileLine(String path) throws IOException, IOException {
        int lineNum = 0;
        List<String> lineList = new ArrayList<>();
        Map<String, String> requestMap = getBaseTypeMap();
        Map<String, String> responseMap = getBaseTypeMap();
        FileReader fr = new FileReader(path);
        BufferedReader br = new BufferedReader(fr);
        String line = "";
        TestMethod testMethod = null;
        StringBuffer codeBuffer = new StringBuffer();
        Boolean inFunction = false;
        List<TestMethod> testMethods = new ArrayList<>();
        while ((line = br.readLine()) != null) {
            if (inFunction) {
                if (line.trim().startsWith("/**")) {
                    inFunction = false;
//                    System.out.println(codeBuffer.toString());
                    codeBuffer = new StringBuffer();
                } else if (!"".equals(line.trim())) {
                    codeBuffer.append("\n").append(line);
                    testMethod.setMethodCode(codeBuffer.toString());
                }
            }
            line = line.trim();
            lineList.add(line);
            if (line.startsWith("import ")) {
                String packageName = line.replace("import ", "").replace(";", "");
                if (line.endsWith("Request;")) {
                    requestMap.put(packageName.substring(packageName.lastIndexOf(".") + 1), packageName);
                } else if (line.endsWith("Response;")) {
                    responseMap.put(packageName.substring(packageName.lastIndexOf(".") + 1), packageName);
                }
            } else if (line.startsWith("public void ")) {//方法行
//                if(testMethod != null){
//                    System.out.println(codeBuffer.toString());
//                }
                testMethod = new TestMethod();
                testMethod.setMethodName(line.substring(line.indexOf("public void ") + 12, line.indexOf("(")));
                lineNum = 1;
                inFunction = true;
                codeBuffer.append("\t").append(line);
            } else {
                if (lineNum == 1) {//request
                    String realRequest = requestMap.get(line.split(" ")[0]);
                    if (realRequest == null) {
                        throw new RuntimeException("类型" + line.split(" ")[0] + "异常");
                    }
                    testMethod.setRequestArgu(realRequest);
                    lineNum = 2;
                } else if (lineNum == 2) {//request
                    String realResponse = responseMap.get(line.split(" ")[0]);
                    if (realResponse == null) {
                        throw new RuntimeException("类型" + line.split(" ")[0] + "异常");
                    }
                    testMethod.setResponseArgu(realResponse);
                    lineNum = 0;
                    testMethods.add(testMethod);
                }
            }
        }
        br.close();
        fr.close();
        System.out.println("--------------------------------------------------------");
        System.out.println(JSON.toJSONString(testMethods));
        System.out.println(testMethods.size());
        return testMethods;
    }
    
    @Data
    @Accessors(chain = true)
    @AllArgsConstructor
    @NoArgsConstructor
    public static class TestMethod {
        private String methodName;
        private String requestArgu;
        private String responseArgu;
        private String methodCode;
    }
    
    private static Map<String, String> getBaseTypeMap() {
        Map<String, String> baseMap = new HashMap<>();
        baseMap.put(Integer.class.getSimpleName(), Integer.class.getName());
        baseMap.put(String.class.getSimpleName(), String.class.getName());
        baseMap.put(Boolean.class.getSimpleName(), Boolean.class.getName());
        return baseMap;
    }
    
}
