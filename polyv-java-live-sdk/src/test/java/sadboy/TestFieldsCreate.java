package sadboy;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author: sadboy
 **/
public class TestFieldsCreate {
    
    @Test
    public void testCreateFields(){
        Boolean req = null;//判断是生成请求还是响应
        String fieldsStr = readFileString("C:\\Users\\T460\\Desktop\\fields.txt");
        String[] lineStr = fieldsStr.split("\n");
        for(String temp:lineStr){
            ///System.out.println(temp);
            String[] lineArr = temp.split("\t");
            if(req == null){
                req = lineArr.length == 4;
            }
            String fieldName = lineArr[0].trim();
            String mustSelect,fieldType,descript;
            if(req){
                mustSelect = lineArr[1].trim().equals("是")?"true":"false";
                fieldType = lineArr[2].trim().toLowerCase();
                descript = lineArr[3].trim();
            }else{
                mustSelect = "false";
                fieldType = lineArr[1].trim().toLowerCase();
                descript = lineArr[2].trim();
            }
            
            String reallyType = getReallyType(fieldType);
            
            System.out.println("/**");
            System.out.println("* "+descript);
            System.out.println("*/");
            System.out.print("@ApiModelProperty(name = \""+fieldName+"\", value = \""+descript+"\", required = "+mustSelect+")");
            System.out.println();
            if("string".equals(fieldType) && "true".equals(mustSelect)){
                System.out.println("@NotNull(message = \""+fieldName+"不能为空\")");
            }
            System.out.print("private "+reallyType+" "+fieldName+";");
            System.out.println("");
            System.out.println("");
        }
    }
    
    private String getReallyType(String fieldType){
        switch (fieldType.toLowerCase()){
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
        throw new RuntimeException(fieldType+"类型错误");
    }
    
    private String readFileString(String path){
        StringBuffer stringBuffer = new StringBuffer();
        try {
            File file = new File(path);
            InputStreamReader inputReader = new InputStreamReader(new FileInputStream(file));
            BufferedReader bf = new BufferedReader(inputReader);
            // 按行读取字符串
            String str;
            while ((str = bf.readLine()) != null) {
                stringBuffer.append(str+"\n");
            }
            bf.close();
            inputReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return stringBuffer.toString();
    }
    
    @Test
    public void readFile(){
        System.out.print(readFileString("C:\\Users\\T460\\Desktop\\fields.txt"));
    }
}
