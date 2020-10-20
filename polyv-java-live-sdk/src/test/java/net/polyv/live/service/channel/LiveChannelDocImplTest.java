package net.polyv.live.service.channel;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

import org.junit.Assert;
import org.junit.Test;

import com.alibaba.fastjson.JSON;

import lombok.extern.slf4j.Slf4j;
import net.polyv.live.entity.channel.doc.LiveChannelDocStatusRequest;
import net.polyv.live.entity.channel.doc.LiveDeleteChannelDocRequest;
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
    
    /**
     * 测试查询频道文档转换状态
     * TODO 由于smallImages、images字段为数组类型，与后台讨论是否有必要后再开发
     * @throws IOException
     * @throws NoSuchAlgorithmException
     */
    @Test
    public void testChannelDocStatus() throws IOException, NoSuchAlgorithmException {
        Integer channelId = createChannel();
        LiveChannelDocStatusRequest liveChannelDocStatusRequest = new LiveChannelDocStatusRequest();
        liveChannelDocStatusRequest.setChannelId(channelId)
                .setFileId(
                        "c2d585857870f4eff024976e3a265c0b1965681common,6e0603f6c8ec6113b87f69a7191d22021965681common");
        new LiveChannelDocServiceImpl().channelDocStatus(liveChannelDocStatusRequest);
    }
    
    /**
     * 测试删除频道文档
     * @throws IOException
     * @throws NoSuchAlgorithmException
     */
    @Test
    public void testDeleteChannelDoc() throws IOException, NoSuchAlgorithmException {
        Integer channelId = createChannel();
        LiveDeleteChannelDocRequest liveDeleteChannelDocRequest = new LiveDeleteChannelDocRequest();
        liveDeleteChannelDocRequest.setChannelId(channelId)
                .setFileId("d2925eab9ac71da4d27d93bd8b3d0e821965681common")
                .setType("new");
        String liveDeleteChannelDocResponse = new LiveChannelDocServiceImpl().deleteChannelDoc(
                liveDeleteChannelDocRequest);
        Assert.assertNotNull(liveDeleteChannelDocResponse);
        if (liveDeleteChannelDocResponse != null) {
            //to do something ......
            log.debug("测试删除频道文档，{}", liveDeleteChannelDocResponse);
        }
    }
    
}
