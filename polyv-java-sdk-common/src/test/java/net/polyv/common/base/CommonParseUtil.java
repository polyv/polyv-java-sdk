package net.polyv.common.base;

import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

/**
 * @author: thomas
 **/
public class CommonParseUtil {
    @Test
    public   void parse(){
        String str = "categoryId\t是\tinte\t分类id\n" + "appId\t是\tstring\t从API设置中获取，在直播系统登记的appId\n" +
                "sign\t是\tstring\t签名，为32位大写的MD5值\n" + "timestamp\t是\tlong\t当前13位毫秒级时间戳，3分钟内有效";
        String[] split = str.split("\n");
        for (int i = 0; i < split.length; i++) {
            System.out.println(split[i]);
            Set set = new HashSet();
            set.add(1);
        }
    }
}
