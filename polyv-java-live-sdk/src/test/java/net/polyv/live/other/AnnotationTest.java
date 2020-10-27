package net.polyv.live.other;

import java.util.Date;

import javax.validation.constraints.NotNull;

import org.junit.Test;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.annotation.JSONField;

import edu.emory.mathcs.backport.java.util.Arrays;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import net.polyv.live.util.MapUtil;

/**
 * @author: thomas
 **/
public class AnnotationTest {
    
    @Test
    public void testList(){
        System.out.println(JSON.toJSONString(new String[]{"123","234","34"}));
        System.out.println(JSON.toJSONString(Arrays.asList( new String[]{"123","234","34"})));
        System.out.println( new String[]{"123","234","34"});
        System.out.println( Arrays.asList( new String[]{"123","234","34"}));
    }
    
    
    @Test
    public void parseTest() {
        DateBean student = new DateBean().setBirthday(new Date()).setCurrentDay(new Date());
        System.out.println(JSON.toJSONString(student));
        System.out.println(student.getCurrentDay().toString());
        student = JSON.parseObject("{\"birthday\":\"2020-10-16 05:05:47\",\"currentDay\":\"20201016050547\"}",
                DateBean.class);
        System.out.println(student);
//        JSON.parseObject("");
    }
    
    
    @Test
    public void beanParseUtil() {
        DateBean student = new DateBean().setBirthday(new Date()).setCurrentDay(new Date());
        System.out.println(MapUtil.objectToMap(student));
    }
    
    
}

@Data
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
class DateBean {
    @JSONField(name = "birth", format = "yyyy-MM-dd hh:mm:ss")
    @NotNull(message = "属性bunengwei")
    private Date birthday;
    
    @NotNull(message = "属性bunengwei")
    private Date currentDay;
    
    private String name;
}