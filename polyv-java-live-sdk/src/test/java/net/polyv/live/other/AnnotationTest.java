package net.polyv.live.other;

import java.util.ArrayList;
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
import lombok.extern.slf4j.Slf4j;
import net.polyv.live.entity.account.LiveListAccountResponse;
import net.polyv.live.util.MapUtil;

/**
 * @author: thomas
 **/
@Slf4j
public class AnnotationTest {
    
    @Test
    public void testList(){
        System.out.println(JSON.toJSONString(new String[]{"123","234","34"}));
        System.out.println(JSON.toJSONString(Arrays.asList( new String[]{"123","234","34"})));
        System.out.println( new String[]{"123","234","34"});
        System.out.println( Arrays.asList( new String[]{"123","234","34"}));
    }
    
    @Test
    public void testJson() {
        LiveListAccountResponse liveListAccountResponse = new LiveListAccountResponse();
        ArrayList<Integer> channelList = new ArrayList<>();
        channelList.add(2);
        channelList.add(3);
        channelList.add(4);
        channelList.add(5);
        log.debug(JSON.toJSONString(liveListAccountResponse.setChannels(channelList)));
        System.out.println(JSON.parseObject("{\"result\":[\"2\",\"3\",\"4\",\"5\"]}", LiveListAccountResponse.class)
                .getChannels());
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
    @NotNull(message = "bunengwei")
    private Date birthday;
    
    @NotNull(message = "bunengwei")
    private Date currentDay;
    
    private String name;
}