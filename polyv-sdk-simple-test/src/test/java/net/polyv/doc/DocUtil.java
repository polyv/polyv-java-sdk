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
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSON;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;


/**
 * @author: sadboy
 **/
@Slf4j
public class DocUtil extends BaseUtil {
    
    public static void main(String[] args) throws NoSuchFieldException, IOException, ClassNotFoundException {
        StringBuffer s = new StringBuffer();
        File baseFile = new File(JUNIT_PATH);
        List<File> files = listFile(baseFile);
        for (File file : files) {
            String fileName = file.getName();
            if (fileName.startsWith("Live") && fileName.endsWith("ImplTest.java")) {
                String classname = file.getPath().replace(JUNIT_PATH, "").replace(".java", "").replace("\\", ".");
                System.out.println(classname);
                System.out.println(fileName.replace(".java", ""));
                String mdName = lowerFirst(fileName.replace("Live", "").replace("ImplTest", "")).replace(".java", "");
                s.append("  * [").append(mdName).append("]").append("(").append(mdName).append(")").append("\n");
                String realFilePath = filePath;
                createSingleFile(JUNIT_PATH, classname, realFilePath, mdName);
            }
        }
        System.out.println(s.toString());
        
        //创建菜单md文件(生成_sidebar.md文件)
        String menuMdFile = filePath + "_sidebar.md";
        File f = new File(menuMdFile);
        if (!f.exists()) {
            f.createNewFile();
        }
        appendFile(menuMdFile, menuMdDemo);
        
        System.out.println("0000000000000000000000");
        System.out.println(JSON.toJSONString(functionMap));
        
        String[] split = functionMdDemo.split("\n");
        String[] insertData = new String[split.length];
        
        
        Iterator<Map.Entry<String, List<String>>> iterator = functionMap.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<String, List<String>> next = iterator.next();
            String key = next.getKey();
            for (int i = 0; i < split.length; i++) {
                String line = split[i];
                if (line.contains("▼" + key)) {
                    StringBuffer tempBuffer = new StringBuffer();
                    List<String> value = next.getValue();
                    for (String t : value) {
                        Domain2DocUtil.appendBuffer(tempBuffer, "           ", "           ", t, "           ");
                    }
                    insertData[i] = tempBuffer.toString();
                }
            }
        }
        StringBuffer functionBuffer = new StringBuffer();
        for (int i = 0; i < split.length; i++) {
            if (insertData[i] != null) {
                System.out.print(split[i] + "\n" + insertData[i]);
                functionBuffer.append(split[i]).append("\n").append(insertData[i]);
            } else {
                System.out.print(split[i] + "\n");
                functionBuffer.append(split[i]).append("\n");
            }
        }
        String functionFile = filePath + "function_index.md";
        f = new File(functionFile);
        if (!f.exists()) {
            f.createNewFile();
        }
        appendFile(functionFile, functionBuffer.toString());
        //创建单个markdown文档
//        createSingleFile(JUNIT_PATH,getJunitClassName(),filePath);
    }
    
    public static List<File> listFile(File filePath) {
        List<File> fileList = new ArrayList<>();
        if (filePath.isDirectory()) {
            File[] files = filePath.listFiles();
            for (File f : files) {
                if (f.isDirectory()) {
                    fileList.addAll(listFile(f));
                } else {
                    fileList.add(f);
                }
            }
        } else {
            fileList.add(filePath);
        }
        return fileList;
    }
    
    /**
     * @param testPath 测试类的文件夹路径，如JUNIT_PATH
     * @param classPath 类的包路径
     * @param createFilePath markdown创建位置和文件名称
     * @throws NoSuchFieldException
     * @throws ClassNotFoundException
     * @throws IOException
     */
    public static void createSingleFile(String testPath, String classPath, String createFilePath, String fileName)
            throws NoSuchFieldException, ClassNotFoundException, IOException {
        Domain2DocUtil.fileName = fileName;
        String path = testPath + classPath.replace(".", "/") + ".java";
        List<TestMethod> testMethods = readFileLine(path);
        
        JavaDocUtil.PolyvClassDoc doc = JavaDocUtil.getDoc(path);
        System.out.println(JSON.toJSONString(doc));
        System.out.println(doc.getMethodDocs().size());
        if (testMethods.size() != doc.getMethodDocs().size()) {
            throw new RuntimeException("JavaDoc对应的方法个数与.java文件不一致，请检查方法是否被注释");
        }
        
        StringBuffer stringBuffer = new StringBuffer();
        if (!"".equals(doc.getTitle())) {
            menuMdDemo = menuMdDemo.replace("[" + doc.getTitle() + "]()", "[" + doc.getTitle() + "](" + fileName + ")");
        } else {
            throw new RuntimeException("请给类" + classPath + "添加类级别的注释");
        }
//        stringBuffer.append("## ").append(doc.getTitle()).append("\n");
        List<String> tempFunctionList = tempFunctionList = new ArrayList<>();
        for (int i = 0; i < doc.getMethodDocs().size(); i++) {
            JavaDocUtil.PolyvMethodDoc methodDoc = doc.getMethodDocs().get(i);
            tempFunctionList.add(
                    "[" + methodDoc.getTitle() + "](" + fileName + "?id=_" + (i + 1) + "、" + methodDoc.getTitle() +
                            ")");
            TestMethod testMethod = testMethods.get(i);
            stringBuffer.append("## ").append(i + 1).append("、").append(methodDoc.getTitle()).append("\n");
            stringBuffer.append("### 描述").append("\n");
            stringBuffer.append("```").append("\n");
            stringBuffer.append(methodDoc.getDescription()).append("\n");
            stringBuffer.append("```").append("\n");
            stringBuffer.append("### 调用约束").append("\n");
            stringBuffer.append("1、接口调用有频率限制，[详细请查看](/limit.md)").append("\n");
            stringBuffer.append(methodDoc.getNote()).append("\n");
            stringBuffer.append("### 单元测试").append("\n");
            stringBuffer.append("```java").append("\n");
            stringBuffer.append("\t").append("@Test").append("\n");
            stringBuffer.append(testMethod.getMethodCode()).append("\n");
            stringBuffer.append("```").append("\n");
            stringBuffer.append("### 单元测试说明").append("\n");
            stringBuffer.append("1、请求正确，返回");
            stringBuffer.append(
                    testMethod.getResponseArgu().substring(testMethod.getResponseArgu().lastIndexOf(".") + 1));
            stringBuffer.append("对象，B端依据此对象处理业务逻辑；").append("\n\n");
            stringBuffer.append(
                    "2、请求参数校验不合格，返回PloyvSdkException，错误信息见PloyvSdkException.getMessage()，如 [ 输入参数 [xxx.chat" +
                            ".LivexxxRequest]对象校验失败 ,失败字段 [pic不能为空 / msg不能为空] ]").append("\n\n");
            stringBuffer.append("3、服务器处理异常，返回PloyvSdkException，错误信息见PloyvSdkException.getMessage()，如 [ " +
                    "保利威请求返回数据错误，请求流水号：66e7ad29fd04425a84c2b2b562d2025b ,错误原因： invalid signature. ]").append("\n");
//            stringBuffer.append("[swagger 程序接入-")
//                    .append(methodDoc.getTitle())
//                    .append("](http://47.115.173.234:8002/doc
//                    .html#/%E7%9B%B4%E6%92%ADSDK/%E7%9B%B4%E6%92%AD%E9%A2%91" +
//                            "%E9%81%93%E7%AE%A1%E7%90%86/createChannelUsingPOST)")
//                    .append("\n");
            stringBuffer.append("### 请求入参描述").append("\n");
            stringBuffer.append(Domain2DocUtil.domainCode(testMethod.getRequestArgu())).append("\n");
            stringBuffer.append("### 返回对象描述").append("\n");
            stringBuffer.append(testMethod.getResponseArguDesc()).append("\n");
            String responseTable = Domain2DocUtil.domainCode(testMethod.getResponseArgu());//返回对象描述表格
            responseTable = "".equals(responseTable) ? methodDoc.getRtDesc() : responseTable;
            stringBuffer.append(responseTable).append("\n");
            if (methodDoc.getCallbackResponse() != null && methodDoc.getCallbackDesc() != null) {//回调说明
                stringBuffer.append("### 回调说明").append("\n");
                stringBuffer.append("&emsp;&emsp;").append(methodDoc.getCallbackDesc()).append("\n");
                stringBuffer.append(Domain2DocUtil.domainCode(methodDoc.getCallbackResponse())).append("\n");
            }
            stringBuffer.append("<br /><br />").append("\n\n");
            stringBuffer.append("------------------").append("\n\n");
            stringBuffer.append("<br /><br />").append("\n\n");
            
        }
        functionMap.put(doc.getTitle(), tempFunctionList);
        System.out.println(stringBuffer.toString());
        File f = new File(createFilePath + "\\" + fileName + ".md");
        if (!f.exists()) {
            if (!f.getParentFile().exists()) {
                f.getParentFile().mkdirs();
            }
            f.createNewFile();
        }
        appendFile(createFilePath + "\\" + fileName + ".md", stringBuffer.toString());
    }
    
    public static void appendFile(String file, String content) {
        BufferedWriter out = null;
        try {
            out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file, false)));
            out.write(content + "\n");
        } catch (Exception e) {
            log.error("",e);
        } finally {
            try {
                out.close();
            } catch (IOException e) {
                log.error("",e);
            }
        }
    }
    
    
    public static String readFileString(String path) throws IOException, IOException {
        StringBuffer stringBuffer = new StringBuffer();
        FileReader fr = new FileReader(path);
        BufferedReader br = new BufferedReader(fr);
        String line = "";
        while ((line = br.readLine()) != null) {
            stringBuffer.append(line).append("\n");
        }
        br.close();
        fr.close();
        return stringBuffer.toString();
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
