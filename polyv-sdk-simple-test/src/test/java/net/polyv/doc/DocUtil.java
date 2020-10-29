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

import org.junit.internal.runners.TestMethod;

import com.alibaba.fastjson.JSON;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import net.polyv.live.service.chat.impl.LiveChatRoomServiceImpl;
import net.polyv.live.service.interact.impl.LiveAnswerRecordServiceImpl;
import net.polyv.live.service.interact.impl.LiveCheckinServiceImpl;
import net.polyv.live.service.interact.impl.LiveQuestionnaireServiceImpl;
import net.polyv.live.service.player.impl.LivePlayerServiceImpl;


/**
 * @author: sadboy
 **/
public class DocUtil {
    //Junit测试类的路径，用来找源码
    private static final String JUNIT_PATH = "D:\\project-yf\\polyv-java-sdk\\polyv-java-live-sdk\\src\\test\\java\\";
    //Junit测试类全路径
    private static String JUNIT_CLASS_NAME = "";
    
    //生成文档所在文件，不存在则自动创建
    private static final String filePath = "C:\\Users\\POLYV\\Desktop\\build\\code.txt";
    
    public static void main(String[] args) throws ClassNotFoundException, IOException, NoSuchFieldException {
//        JUNIT_CLASS_NAME="net.polyv.live.service.chat.LiveChatRoomServiceImplTest";
        JUNIT_CLASS_NAME = "net.polyv.live.service.interact." + LiveQuestionnaireServiceImpl.class.getSimpleName() + "Test";
//        JUNIT_CLASS_NAME = "net.polyv.live.service.interact." + LiveCheckinServiceImpl.class.getSimpleName() + "Test";
//        JUNIT_CLASS_NAME= "net.polyv.live.service.interact."+LiveAnswerRecordServiceImpl.class.getSimpleName()+"Test";
//        JUNIT_CLASS_NAME= "net.polyv.live.service.player."+ LivePlayerServiceImpl.class.getSimpleName()+"Test";
//        JUNIT_CLASS_NAME= "net.polyv.live.service.chat."+ LiveChatRoomServiceImpl.class.getSimpleName()+"Test";
        
        
        String path = JUNIT_PATH + JUNIT_CLASS_NAME.replace(".", "/") + ".java";
        List<TestMethod> testMethods = readFileLine(path);
        
        JavaDocUtil.PolyvClassDoc doc = JavaDocUtil.getDoc(path);
        System.out.println(JSON.toJSONString(doc));
        System.out.println(doc.getMethodDocs().size());
        if (testMethods.size() != doc.getMethodDocs().size()) {
            throw new RuntimeException("JavaDoc对应的方法个数与.java文件不一致，请检查方法是否被注释");
        }
        
        
        StringBuffer stringBuffer = new StringBuffer();
//        stringBuffer.append("## ").append(doc.getTitle()).append("\n\r");
        for (int i = 0; i < doc.getMethodDocs().size(); i++) {
            JavaDocUtil.PolyvMethodDoc methodDoc = doc.getMethodDocs().get(i);
            TestMethod testMethod = testMethods.get(i);
            stringBuffer.append("## ").append(i+1).append("、").append(methodDoc.getTitle()).append("\n\r");
            stringBuffer.append("### 描述").append("\n\r");
            stringBuffer.append("```").append("\n\r");
            stringBuffer.append(methodDoc.getDescription()).append("\n\r");
            stringBuffer.append("```").append("\n\r");
            stringBuffer.append("### 调用约束").append("\n\r");
            stringBuffer.append("1、接口调用有频率限制，[详细请查看](../limit.md)").append("\n\r");
            stringBuffer.append(methodDoc.getNote()).append("\n\r");
            stringBuffer.append("### 代码示例").append("\n\r");
            stringBuffer.append("```java").append("\n\r");
            stringBuffer.append(testMethod.getMethodCode()).append("\n\r");
            stringBuffer.append("```").append("\n\r");
            stringBuffer.append("### 单元测试说明").append("\n\r");
            stringBuffer.append("1、请求正确，返回");
            stringBuffer.append(testMethod.getResponseArgu().substring(testMethod.getResponseArgu().lastIndexOf(".")+1));
            stringBuffer.append("对象，B端依据此对象处理业务逻辑；").append("\n\r");
            stringBuffer.append("2、请求参数校验不合格，返回PloyvSdkException，错误信息见PloyvSdkException.getMessage()，如 [ 输入参数 [xxx.chat.LivexxxRequest]对象校验失败 ,失败字段 [pic不能为空 / msg不能为空] ]").append("\n\r");
            stringBuffer.append("3、请求服务器网络异常，返回PloyvSdkException，错误信息见PloyvSdkException.getMessage()，如 [ 保利威请求返回数据错误，请求流水号：66e7ad29fd04425a84c2b2b562d2025b ,错误原因： invalid signature. ]").append("\n\r");
//            stringBuffer.append("[swagger 程序接入-")
//                    .append(methodDoc.getTitle())
//                    .append("](http://47.115.173.234:8002/doc
//                    .html#/%E7%9B%B4%E6%92%ADSDK/%E7%9B%B4%E6%92%AD%E9%A2%91" +
//                            "%E9%81%93%E7%AE%A1%E7%90%86/createChannelUsingPOST)")
//                    .append("\n\r");
            stringBuffer.append("### 请求入参描述").append("\n\r");
            stringBuffer.append(Domain2DocUtil.domainCode(testMethod.getRequestArgu())).append("\n\r");
            stringBuffer.append("### 返回对象描述").append("\n\r");
            stringBuffer.append(testMethod.getResponseArguDesc()).append("\n\r");
            stringBuffer.append(Domain2DocUtil.domainCode(testMethod.getResponseArgu())).append("\n\r");
            stringBuffer.append("<br /><br />").append("\n\r");
            stringBuffer.append("------------------").append("\n\r");
            stringBuffer.append("<br /><br />").append("\n\r");
          
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
            out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file, false)));
            out.write(conent + "\n\r");
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
                    codeBuffer.append("\n\r").append(line);
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
                    String responsetType = line.split(" ")[0].trim();
                    if (line.trim().startsWith("new ")) {
                        continue;
                    }
                    if (responsetType.startsWith("List")) {
                        responsetType = responsetType.substring(responsetType.indexOf("<") + 1,
                                responsetType.indexOf(">"));
                        testMethod.setResponseArguDesc("返回对象是List,具体元素内容如下：");
                    }
                    String realResponse = responseMap.get(responsetType);
                    
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
@EqualsAndHashCode(callSuper = false)
    @Accessors(chain = true)
    @AllArgsConstructor
    @NoArgsConstructor
    public static class TestMethod {
        private String methodName;
        private String requestArgu;
        private String responseArgu;
        private String methodCode;
        private String responseArguDesc = "";
    }
    
    private static Map<String, String> getBaseTypeMap() {
        Map<String, String> baseMap = new HashMap<>();
        baseMap.put(Integer.class.getSimpleName(), Integer.class.getName());
        baseMap.put(String.class.getSimpleName(), String.class.getName());
        baseMap.put(Boolean.class.getSimpleName(), Boolean.class.getName());
        return baseMap;
    }
    
}
