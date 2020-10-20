package net.polyv.live.service.channel;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

import org.junit.Assert;
import org.junit.Test;

import com.alibaba.fastjson.JSON;

import lombok.extern.slf4j.Slf4j;
import net.polyv.live.entity.channel.doc.LiveListChannelDocRequest;
import net.polyv.live.entity.channel.doc.LiveListChannelDocResponse;
import net.polyv.live.service.BaseTest;
import net.polyv.live.service.channel.impl.LiveChannelDocServiceImpl;

/**
 * @author: sadboy
 **/
@Slf4j
public class LiveChannelDocImplTest extends BaseTest {
    
    /**
     * 测试获取频道文档列表
     * @throws IOException
     * @throws NoSuchAlgorithmException
     */
    @Test
    public void testListChannelDoc() throws IOException, NoSuchAlgorithmException {
        Integer channelId = createChannel();
        LiveListChannelDocRequest liveListChannelDocRequest = new LiveListChannelDocRequest();
        liveListChannelDocRequest.setChannelId(channelId).setStatus(null);
        LiveListChannelDocResponse liveListChannelDocResponse = new LiveChannelDocServiceImpl().listChannelDoc(
                liveListChannelDocRequest);
        Assert.assertNotNull(liveListChannelDocResponse);
        if (liveListChannelDocResponse != null) {
            //to do something ......
            log.debug("测试获取频道文档列表成功，{}", JSON.toJSONString(liveListChannelDocResponse));
        }
    }
    
}
