package net.polyv.common.base;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

/**
 * @author: thomas
 **/
public class CommonParseUtil {
    public static void main(String[] args) {
        testCreateFields();
    }
    
    public static void testCreateFields() {
        Boolean req = null;//判断是生成请求还是响应
        String fieldsStr = "userid\t是\tstring\tPOLYV用户ID， http://api.polyv.net/v2/user/{userid}/main中的{userid}需要替换为POLYV用户ID的值\n" +
                "ptime\t是\tstring\t当前时间的毫秒级时间戳（13位），3分钟内有效\n" + "date\t否\tstring\t要查询的日期，格式 ：yyyy-MM-dd\n" +
                "sign\t是\tstring\t签名，为40位大写的SHA1值";//readFileString("C:\\Users\\T460\\Desktop\\fields.txt");
        fieldsStr = "code      是    int\t    成功/失败代码\n" + "status    是    string\t成功/失败状态\n" +
                "message   是 \t string  成功/失败信息\n" + "totalFlow  否  long \t用户总流量\n" + "usedSpace  否  long\t已用空间\n" +
                "usedFlow   否\t  long   已用流量\n" + "totalSpace 否 long\t 用户总空间\n" + "userId\t   否   string  POLYV用户ID\n" +
                "email     否\t  string  POLYV用户邮箱";
        String[] lineStr = fieldsStr.split("\n");
        for (String temp : lineStr) {
            ///System.out.println(temp);
            temp = temp.trim();
            temp = temp.replaceAll("\\s+","\t");
            String[] lineArr = temp.split("\t");
            if (req == null) {
                req = lineArr.length == 4;
            }
            String fieldName = lineArr[0].trim();
            String mustSelect, fieldType, descript;
            if (req) {
                mustSelect = lineArr[1].trim().equals("是") ? "true" : "false";
                fieldType = lineArr[2].trim().toLowerCase();
                descript = lineArr[3].trim();
            } else {
                mustSelect = "false";
                fieldType = lineArr[1].trim().toLowerCase();
                descript = lineArr[2].trim();
            }
            
            String reallyType = getReallyType(fieldType);
            
            System.out.println("/**");
            System.out.println("* " + descript);
            System.out.println("*/");
            System.out.print(
                    "@ApiModelProperty(name = \"" + fieldName + "\", value = \"" + descript + "\", required = " +
                            mustSelect + ")");
            System.out.println();
            if ("string".equals(fieldType) && "true".equals(mustSelect)) {
                System.out.println("@NotNull(message = \"" + fieldName + "不能为空\")");
            }
            System.out.print("private " + reallyType + " " + fieldName + ";");
            System.out.println("");
            System.out.println("");
        }
    }
    
    private static String getReallyType(String fieldType) {
        switch (fieldType.toLowerCase()) {
            case "integer":
            case "int":
                return "Integer";
            case "string":
                return "String";
            case "boolean":
                return "Boolean";
            case "long":
            case "timestamp":
                return "Long";
            case "float":
                return "Float";
            default:
                break;
        }
        throw new RuntimeException(fieldType + "类型错误");
    }
    
    private String readFileString(String path) {
        StringBuffer stringBuffer = new StringBuffer();
        try {
            File file = new File(path);
            InputStreamReader inputReader = new InputStreamReader(new FileInputStream(file));
            BufferedReader bf = new BufferedReader(inputReader);
            // 按行读取字符串
            String str;
            while ((str = bf.readLine()) != null) {
                stringBuffer.append(str + "\n");
            }
            bf.close();
            inputReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return stringBuffer.toString();
    }
    
    
}
