package net.polyv.live.service;

import org.junit.Test;

import net.polyv.common.util.ValidationUtil;
import net.polyv.live.entity.channel.LiveChannelRequest;

/**
 * @author: thomas
 **/
public class ValidationUtilTest {
    
    @Test
    public void testValidationUtil() {
        javax.el.ExpressionFactory factory = null;
        LiveChannelRequest liveChannelRequest = LiveChannelRequest.builder().userId("kskskskssksksk").build();
        ValidationUtil.ValidResult validResult =  ValidationUtil.validateBean(
                liveChannelRequest);
        if (validResult.hasErrors()) {
            String errors = validResult.getErrors();
            System.out.println(errors);
        }
    }
    
}
