package net.polyv.live.other;

import org.junit.Test;

import net.polyv.common.util.ValidationUtil;
import net.polyv.live.entity.channel.operate.LiveChannelRequest;

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
