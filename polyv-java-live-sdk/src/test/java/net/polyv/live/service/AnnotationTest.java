package net.polyv.live.service;

import java.util.Date;

import javax.validation.constraints.NotNull;

import org.junit.Test;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.annotation.JSONField;

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
    @NotNull(message = "bunengwei")
    private Date birthday;
    
    @NotNull(message = "bunengwei")
    private Date currentDay;
    
    private String name;
}