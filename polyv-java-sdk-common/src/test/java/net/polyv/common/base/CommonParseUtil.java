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
        String fieldsStr =
                "code\tint\t响应代码，成功为200，失败为400，签名错误为403，异常错误500\n" + "status\tstring\t成功为success，失败为error\n" +
                        "message\tstring\t错误时为错误提示消息\n" + "questionnaireId\tint\t问卷ID\n" +
                        "customQuestionnaireId\tint\t用户自定义问卷ID\n" + "channelId\tint\t频道ID\n" + "userId\tint\t用户ID\n" +
                        "name\tstring\t问卷名称\n" + "status\tstring\t问卷状态\n" + "createdTime\tdate\t问卷创建时间\n" +
                        "endTime\tdate\t停止问卷时间\n" + "questions\tstring\t问卷问题列表\n" + " \n" + "questionId\tint\t问题ID\n" +
                        "name\tstring\t问题题目\n" + "type\tstring\t问题类型，取值：R 单选；C 多选；S 评星题；Q 问答\n" +
                        "option1~10\tstring\t选项A~G 的内容\n" + "createdTime\tdate\t创建时间\n" +
                        "lastModified\tdate\t最后修改时间\n" + "scoreEnabled\tstring\t是否计分，取值Y、N\n" + "score\tint\t题目分值\n" +
                        "required\tstring\t是否必填，取值Y、N\n" + "answer\tstring\t问题答案";
        
        fieldsStr = "contents\tstring\t查询的结果列表\n" + "questionnaireId\tstring\t问卷ID\n" + "channelId\tint\t频道ID\n" +
                "userId\tint\t用户ID\n" + "name\tstring\t问卷名称\n" +
                "status\tstring\t问卷状态，取值：saved(已保存)，published （已发布），forbidden （问卷已完成填写）\n" +
                "createdTime\tlong\t问卷创建时间\n" + "lastModified\tlong\t问卷最后修改时间\n" + "endTime\tlong\t停止提交问卷时间\n" +
                "customQuestionnaireId\tstring\t用户自定义问卷ID";
        
        fieldsStr = "\n" + "\n" + "channelId\t是\tstring\t频道号\n" + "questionnaireId\t否\tstring\t问卷id,修改问卷时需要\n" +
                "customQuestionnaireId\t否\tstring\t客户自定义问卷id\n" + "questionnaireTitle\t是\tstring\t问卷标题\n" +
                "questions\t是\tarray\t题目数组\n" + "questionId\t否\tstring\t题目id，修改问卷时需要传\n" + "name\t是\tstring\t题目\n" +
                "type\t是\tstring\t题目类型,R为单选，C为多选，Q为问答\n" + "scoreEnabled\t否\tstring\t题目是否需要评分，Y为需要，N为不需要\n" +
                "answer\t否\tstring\t需要评分的选择题才有答案，填入对应选项序号，如：A或AB\n" + "required\t否\tstring\t题目是否为必答，Y为必答，N为非必答\n" +
                "options\t否\tarray\t题目为单选题或多选题为必填，选项数组下标0-9对应答案A-J\n" + "options[]\t否\tstring\t选项描述\n" + "\n" + " ";
        
        fieldsStr = "questionnaireId\tstring\t问卷id\n" + "questionIds[]\tarray\t题目的id数组";
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
