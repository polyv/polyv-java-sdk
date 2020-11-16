package net.polyv.live.v1.other;

import org.junit.Test;

import net.polyv.common.v1.util.ValidationUtil;
import net.polyv.live.v1.entity.channel.operate.LiveChannelRequest;

/**
 * @author: thomas
 **/
public class ValidationUtilTest {
    
    @Test
    public void testValidationUtil() {
        javax.el.ExpressionFactory factory = null;
        LiveChannelRequest liveChannelRequest = new LiveChannelRequest().setUserId("kskskskssksksk");
        ValidationUtil.ValidResult validResult =  ValidationUtil.validateBean(
                liveChannelRequest);
        if (validResult.hasErrors()) {
            String errors = validResult.getErrors();
            System.out.println(errors);
        }
    }
    
   
}
