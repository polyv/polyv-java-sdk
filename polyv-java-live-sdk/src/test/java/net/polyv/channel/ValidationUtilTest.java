package net.polyv.channel;

import org.junit.Test;

import net.polyv.live.entity.channel.LiveChannelRequest;

/**
 * @author: thomas
 
 **/
public class ValidationUtilTest {
    
    @Test
    public void testValidationUtil(){
        javax.el.ExpressionFactory factory = null;
        LiveChannelRequest liveChannelRequest = LiveChannelRequest.builder().userId("kskskskssksksk").build();
        net.polyv.live.util.ValidationUtil.ValidResult validResult = net.polyv.live.util.ValidationUtil.validateBean(liveChannelRequest);
        if(validResult.hasErrors()){
            String errors = validResult.getErrors();
            System.out.println(errors);
        }
    }
    
}
