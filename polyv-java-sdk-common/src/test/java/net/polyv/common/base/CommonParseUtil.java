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
        String fieldsStr="";
        fieldsStr = "questionId\tstring\t题目ID\n" + "times\tint\t第几次发送题目，用于区分相同题目重复发送的情况\n" + "answer\tstring\t题目的答案\n" +
                "total\tint\t答题人数\n" + "options\tarray\t题目选项信息列表\n" + "title\tstring\t选项标题\n" +
                "count\tint\t选择该选项的人数\n" + "percent\tstring\t选择该选项的人数百分比\n" + "records\tarray\t答题的用户列表\n" +
                "viewerId\tstring\t答题的用户ID\n" + "nickname\tstring\t答题的用户昵称\n" + "answer\tstring\t答题的用户提交的答案\n" +
                "corrent\tboolean\t答题的用户提交的答案是否正确：false不正确，true正确\n" + "submitTime\ttimestamp\t答题的用户提交时间，13位毫秒级时间戳\n" +
                "type\tstring\t题目类型：R为单选，C为多选，Q为问答\n" + "itemType\tint\t答题类型：1表示问答，0表示答题卡";
        
 
        //        fieldsStr = readFileString("C:\\Users\\T460\\Desktop\\fields.txt");
        
        String[] lineStr = fieldsStr.split("\n");
        for (String temp : lineStr) {
            ///System.out.println(temp);
            temp = temp.trim();
            temp = temp.replaceAll("\\s+", "\t");
            String[] lineArr = temp.split("\t");
            if (lineArr.length < 2) {
                continue;
            }
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
            
            String reallyType = getReallyType(fieldType, req);
            
            System.out.println("/**");
            System.out.println("* " + descript);
            System.out.println("*/");
            System.out.print(
                    "@ApiModelProperty(name = \"" + fieldName + "\", value = \"" + descript + "\", required = " +
                            mustSelect + ")");
            System.out.println();
            if (("string".equals(fieldType) || "int".equals(fieldType)) && "true".equals(mustSelect)) {
                System.out.println("@NotNull(message = \"" + fieldName + "不能为空\")");
            }
            if ("timestamp".equals(fieldType)) {
                System.out.print("@JSONField(format = \"yyyy-MM-dd hh:mm:ss\")");
            }
            System.out.print("private " + reallyType + " " + fieldName + ";");
            System.out.println("");
            System.out.println("");
        }
    }
    
    private static String getReallyType(String fieldType, boolean isRequest) {
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
            case "date":
                if (isRequest) {
                    return "Long";
                } else {
                    return "Date";
                }
            case "float":
                return "Float";
            case "array":
                return "List";
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
