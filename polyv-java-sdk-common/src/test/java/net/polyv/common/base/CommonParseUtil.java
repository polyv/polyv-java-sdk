package net.polyv.common.base;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.annotation.JSONField;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * @author: thomas
 **/
public class CommonParseUtil {
    public static void main(String[] args) {
        testCreateFields();
    }
    
    public static void testCreateFields() {
        Boolean req = null;//判断是生成请求还是响应
        String fieldsStr = "code\t\t\t响应代码，成功为200，失败为400，签名错误为403，异常错误500";

//        fieldsStr = readFileString("C:\\Users\\T460\\Desktop\\fields.txt");
        
        String[] lineStr = fieldsStr.split("\n");
        for (String temp : lineStr) {
            ///System.out.println(temp);
            temp = temp.trim();
            temp = temp.replaceAll("\\s+", "\t");
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
            
            String reallyType = getReallyType(fieldType , req);
            
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
            if ("timestamp".equals(fieldType) ) {
                System.out.print("@JSONField(format = \"yyyy-MM-dd hh:mm:ss\")");
            }
            System.out.print("private " + reallyType + " " + fieldName + ";");
            System.out.println("");
            System.out.println("");
        }
    }
    
    private static String getReallyType(String fieldType,boolean isRequest) {
        switch (fieldType.toLowerCase()) {
            case "integer":
            case "int":
                return "Integer";
            case "string":
                return "String";
            case "boolean":
                return "Boolean";
            case "long":
                return "Long";
            case "timestamp":
                if(isRequest)
                    return "Long";
                else
                    return "Date";
            case "float":
                return "Float";
            default:
                break;
        }
        throw new RuntimeException(fieldType + "类型错误");
    }
    
    private static String readFileString(String path) {
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
