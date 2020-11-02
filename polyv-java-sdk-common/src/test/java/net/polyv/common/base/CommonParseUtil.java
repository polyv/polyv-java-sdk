package net.polyv.common.base;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @author: thomas
 **/
public class CommonParseUtil {
    public static void main(String[] args) {
        testCreateFields();
    }
    
    public static void testCreateFields() {
        Boolean req = null;//判断是生成请求还是响应
        String fieldsStr = "";
        fieldsStr = "questionId\tstring\t题目ID\n" + "times\tint\t第几次发送题目，用于区分相同题目重复发送的情况\n" + "answer\tstring\t题目的答案\n" +
                "total\tint\t答题人数\n" + "options\tarray\t题目选项信息列表\n" + "title\tstring\t选项标题\n" +
                "count\tint\t选择该选项的人数\n" + "percent\tstring\t选择该选项的人数百分比\n" + "records\tarray\t答题的用户列表\n" +
                "viewerId\tstring\t答题的用户ID\n" + "nickname\tstring\t答题的用户昵称\n" + "answer\tstring\t答题的用户提交的答案\n" +
                "corrent\tboolean\t答题的用户提交的答案是否正确：false不正确，true正确\n" + "submitTime\ttimestamp\t答题的用户提交时间，13位毫秒级时间戳\n" +
                "type\tstring\t题目类型：R为单选，C为多选，Q为问答\n" + "itemType\tint\t答题类型：1表示问答，0表示答题卡";
        fieldsStr =
                "startDay\t是\tstring\t聊天记录的开始时间，格式要求为yyyy-MM-dd(如：2017-08-01)或者 yyyy-MM-dd HH:mm:ss （如：2017-08-01 " +
                        "16:30:12）\n" + "endDay\t是\tstring\t聊天记录的结束时间，要求同上\n" +
                        "appId\t是\tstring\t从API设置中获取，在直播系统登记的appId\n" + "page\t否\tint\t获取第几页聊天记录，默认为1\n" +
                        "limit\t否\tint\t每页记录数，默认为1000\n" + "timestamp\t是\tstring\t当前13位毫秒级时间戳，3分钟内有效\n" +
                        "sign\t是\tstring\t签名，32位大写MD5值\n" + "userType\t否\tstring\t用户类型，可以选择多个类型，用英文逗号隔开\n" +
                        "status\t否\tstring\t聊天记录状态，默认通过审核记录，censor：未审核或删除的聊天记录\n" +
                        "source\t否\tstring\t类型，不填默认为群聊，extend为管理员私聊";
        fieldsStr = "clientIP\tstring\t用户IP\n" + "content\tstring\t聊天内容\n" + "id\tstring\t聊天消息id\n" +
                "image\tstring\t图片消息的图片地址\n" + "roomId\tint\t频道号\n" + "time\ttimestamp\t发送消息时的时间戳\n" +
                "clientIp\tstring\t用户IP\n" + "nick\tstring\t观众昵称\n" + "pic\tstring\t观众头像\n" +
                "status\tstring\t审核状态，pass:已审核，censor：审核中，delete：删除\n" +
                "msgType\tstring\t目前取值：redpaper: 红包消息；get_redpaper：领取红包消息；chatImg：图片消息；custom：自定义消息（通过socket" +
                "发送的自定义消息）；reward：打赏消息；customerMessage：自定义消息（通过http接口发送的自定义消息）为空（msgType=\"\"）时表示普通聊天消息；\n" +
                "userId\tstring\t用户唯一标示\n" +
                "userType\tstring\t用户类型，目前有teacher(老师)、assistant（助教）、manager（管理员）、slice（云课堂学员）\n" +
                "sourceType\tstring\t消息来源，目前有public(群聊)、extend（管理员私聊）\n" + "\n";
        fieldsStr = "nickname\t是\tstring\t管理员昵称，长度不能超过8\n" + "actor\t是\tstring\t管理员头衔，长度不能超过4\n" +
                "avatar\t是\tstring\t管理员头像，支持jpg、jpeg、png三种格式，大小不能超过2Mb";
        
//        fieldsStr = readFileString("C:\\Users\\T460\\Desktop\\fields.txt");
        
        String[] lineStr = fieldsStr.split("\n");
        for (String temp : lineStr) {
            ///System.out.println(temp);
            temp = temp.trim();
            temp = temp.replaceAll("\\s+\t", "\t");
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
                mustSelect = lineArr[1].trim().equals("是") ? "true" : (lineArr[1].trim().equals("true")?"true":"false");
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
                System.out.println("@NotNull(message = \"属性" + fieldName + "不能为空\")");
            }
            if ("timestamp".equals(fieldType)) {
                System.out.print("@JSONField(format = \"yyyy-MM-dd HH:mm:ss\")");
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
            case "file":
                return "File";
            case "double":
                return "Double";
            default:
                break;
        }
        throw new RuntimeException(fieldType + "类型错误");
    }
    
    private static String readFileString(String path) throws IOException {
        StringBuffer stringBuffer = new StringBuffer();
        InputStreamReader inputReader =null;
        BufferedReader bf = null ;
        try {
            File file = new File(path);
              inputReader = new InputStreamReader(new FileInputStream(file));
              bf = new BufferedReader(inputReader);
            // 按行读取字符串
            String str;
            while ((str = bf.readLine()) != null) {
                stringBuffer.append(str + "\n");
            }
            bf.close();
            inputReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            inputReader.close();
            bf.close();
        }
        return stringBuffer.toString();
    }
    
    
}
