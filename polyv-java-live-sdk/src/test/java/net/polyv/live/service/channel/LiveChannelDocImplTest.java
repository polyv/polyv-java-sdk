package net.polyv.live.service.channel;

import java.io.File;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;

import org.junit.Assert;
import org.junit.Test;

import com.alibaba.fastjson.JSON;

import lombok.extern.slf4j.Slf4j;
import net.polyv.live.entity.channel.doc.LiveChannelDocStatusRequest;
import net.polyv.live.entity.channel.doc.LiveChannelDocStatusResponse;
import net.polyv.live.entity.channel.doc.LiveCreateChannelDocRequest;
import net.polyv.live.entity.channel.doc.LiveCreateChannelDocResponse;
import net.polyv.live.entity.channel.doc.LiveDeleteChannelDocRequest;
import net.polyv.live.entity.channel.doc.LiveListChannelDocRequest;
import net.polyv.live.entity.channel.doc.LiveListChannelDocResponse;
import net.polyv.live.service.BaseTest;
import net.polyv.live.service.channel.impl.LiveChannelDocServiceImpl;

/**
 * 直播频道操作
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
        LiveListChannelDocRequest liveListChannelDocRequest = new LiveListChannelDocRequest();
        LiveListChannelDocResponse liveListChannelDocResponse;
        Integer channelId = createChannel();
        liveListChannelDocRequest.setChannelId(channelId).setStatus(null);
        liveListChannelDocResponse = new LiveChannelDocServiceImpl().listChannelDoc(
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
        LiveChannelDocStatusRequest liveChannelDocStatusRequest = new LiveChannelDocStatusRequest();
        LiveChannelDocStatusResponse liveChannelDocStatusResponse;
        Integer channelId = createChannel();
        liveChannelDocStatusRequest.setChannelId(channelId)
                .setFileId(
                        "c2d585857870f4eff024976e3a265c0b1965681common,6e0603f6c8ec6113b87f69a7191d22021965681common");
        liveChannelDocStatusResponse = new LiveChannelDocServiceImpl().channelDocStatus(
                liveChannelDocStatusRequest);
        Assert.assertNotNull(liveChannelDocStatusResponse);
        if (liveChannelDocStatusResponse != null) {
            //to do something ......
            log.debug("测试查询频道文档转换状态成功，{}", JSON.toJSONString(liveChannelDocStatusResponse));
        }
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
            log.debug("测试删除频道文档成功，{}", liveDeleteChannelDocResponse);
        }
    }
    
    /**
     * 测试上传频道文档
     * @throws IOException
     * @throws NoSuchAlgorithmException
     */
//    @Test
    public void testCreateChannelDoc() throws IOException, NoSuchAlgorithmException {
        LiveCreateChannelDocRequest liveCreateChannelDocRequest = new LiveCreateChannelDocRequest();
        LiveCreateChannelDocResponse liveCreateChannelDocResponse;
        File file = new File("C:\\Users\\T460\\Desktop\\葵花宝典PPT.pptx");
        liveCreateChannelDocRequest.setChannelId(createChannel())
                .setType("common")
                .setFile(file)
                .setDocName("葵花宝典")
                .setCallbackUrl("http://www.baidu.com/callback");
        liveCreateChannelDocResponse = new LiveChannelDocServiceImpl().createChannelDoc(liveCreateChannelDocRequest);
        Assert.assertNotNull(liveCreateChannelDocResponse);
        if (liveCreateChannelDocResponse != null) {
            //to do something ......
            log.debug("测试上传频道文档成功，{}", liveCreateChannelDocResponse);
        }
    }
    
}
