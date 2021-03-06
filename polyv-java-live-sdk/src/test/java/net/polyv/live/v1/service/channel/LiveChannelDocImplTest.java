package net.polyv.live.v1.service.channel;

import java.io.File;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;

import org.junit.Assert;
import org.junit.Test;

import com.alibaba.fastjson.JSON;

import lombok.extern.slf4j.Slf4j;
import net.polyv.common.v1.exception.PloyvSdkException;
import net.polyv.live.v1.entity.channel.doc.LiveChannelDocStatusRequest;
import net.polyv.live.v1.entity.channel.doc.LiveChannelDocStatusResponse;
import net.polyv.live.v1.entity.channel.doc.LiveCreateChannelDocRequest;
import net.polyv.live.v1.entity.channel.doc.LiveCreateChannelDocResponse;
import net.polyv.live.v1.entity.channel.doc.LiveDeleteChannelDocRequest;
import net.polyv.live.v1.entity.channel.doc.LiveListChannelDocRequest;
import net.polyv.live.v1.entity.channel.doc.LiveListChannelDocResponse;
import net.polyv.live.v1.service.BaseTest;
import net.polyv.live.v1.service.channel.impl.LiveChannelDocServiceImpl;

/**
 * 文档管理
 * @author: sadboy
 **/
@Slf4j
public class LiveChannelDocImplTest extends BaseTest {
    
    /**
     * 测试上传频道文档
     * API地址：CREATE_CHANNEL_DOC_URL
     * @throws IOException
     * @throws NoSuchAlgorithmException
     */
    @Test
    public void testCreateChannelDoc() throws Exception, NoSuchAlgorithmException {
        LiveCreateChannelDocRequest liveCreateChannelDocRequest = new LiveCreateChannelDocRequest();
        LiveCreateChannelDocResponse liveCreateChannelDocResponse;
        try {
            String path = getClass().getResource("/file/PPT.pptx").getPath();
            liveCreateChannelDocRequest.setChannelId(createChannel())
                    .setType("common")
                    .setFile(new File(path))
                    .setDocName("葵花宝典")
                    .setCallbackUrl("http://www.baidu.com/callback");
            liveCreateChannelDocResponse = new LiveChannelDocServiceImpl().createChannelDoc(
                    liveCreateChannelDocRequest);
            Assert.assertNotNull(liveCreateChannelDocResponse);
            if (liveCreateChannelDocResponse != null) {
                //to do something ......
                log.debug("测试上传频道文档成功，{}", liveCreateChannelDocResponse);
            }
        } catch (PloyvSdkException e) {
            //参数校验不合格 或者 请求服务器端500错误，错误信息见PloyvSdkException.getMessage(),B
            log.error(e.getMessage(), e);
            // 异常返回做B端异常的业务逻辑，记录log 或者 上报到ETL 或者回滚事务
            throw e;
        } catch (Exception e) {
            log.error("SDK调用异常", e);
            throw e;
        }
    }
    
    /**
     * 测试查询频道文档转换状态
     * API地址：CHANNEL_DOC_STATUS_URL
     * @throws IOException
     * @throws NoSuchAlgorithmException
     */
    @Test
    public void testGetChannelDocStatus() throws Exception, NoSuchAlgorithmException {
        LiveChannelDocStatusRequest liveChannelDocStatusRequest = new LiveChannelDocStatusRequest();
        LiveChannelDocStatusResponse liveChannelDocStatusResponse;
        try {
            String channelId = super.createChannel();
            liveChannelDocStatusRequest.setChannelId(channelId)
                    .setFileId("c2d585857870f4eff024976e3a265c0b1965681common," +
                            "6e0603f6c8ec6113b87f69a7191d22021965681common");
            liveChannelDocStatusResponse = new LiveChannelDocServiceImpl().getChannelDocStatus(
                    liveChannelDocStatusRequest);
            Assert.assertNotNull(liveChannelDocStatusResponse);
            if (liveChannelDocStatusResponse != null) {
                //to do something ......
                log.debug("测试查询频道文档转换状态成功，{}", JSON.toJSONString(liveChannelDocStatusResponse));
            }
        } catch (PloyvSdkException e) {
            //参数校验不合格 或者 请求服务器端500错误，错误信息见PloyvSdkException.getMessage(),B
            log.error(e.getMessage(), e);
            // 异常返回做B端异常的业务逻辑，记录log 或者 上报到ETL 或者回滚事务
            throw e;
        } catch (Exception e) {
            log.error("SDK调用异常", e);
            throw e;
        }
    }
    
    /**
     * 测试获取频道文档列表
     * API地址：CHANNEL_DOC_LIST_URL
     * @throws IOException
     * @throws NoSuchAlgorithmException
     */
    @Test
    public void testListChannelDoc() throws Exception, NoSuchAlgorithmException {
        LiveListChannelDocRequest liveListChannelDocRequest = new LiveListChannelDocRequest();
        LiveListChannelDocResponse liveListChannelDocResponse;
        try {
            String channelId = super.createChannel();
            liveListChannelDocRequest.setChannelId(channelId)
                    .setIsShowUrl("Y")
                    .setStatus(null);
            liveListChannelDocResponse = new LiveChannelDocServiceImpl().listChannelDoc(liveListChannelDocRequest);
            Assert.assertNotNull(liveListChannelDocResponse);
            if (liveListChannelDocResponse != null) {
                //to do something ......
                log.debug("测试获取频道文档列表成功，{}", JSON.toJSONString(liveListChannelDocResponse));
            }
        } catch (PloyvSdkException e) {
            //参数校验不合格 或者 请求服务器端500错误，错误信息见PloyvSdkException.getMessage(),B
            log.error(e.getMessage(), e);
            // 异常返回做B端异常的业务逻辑，记录log 或者 上报到ETL 或者回滚事务
            throw e;
        } catch (Exception e) {
            log.error("SDK调用异常", e);
            throw e;
        }
    }
    
    /**
     * 测试删除频道文档
     * 返回：true为删除文档成功，false为删除文档失败
     * API地址：CHANNEL_DOC_DELETE_URL
     * @throws IOException
     * @throws NoSuchAlgorithmException
     */
    @Test
    public void testDeleteChannelDoc() throws Exception, NoSuchAlgorithmException {
        LiveDeleteChannelDocRequest liveDeleteChannelDocRequest = new LiveDeleteChannelDocRequest();
        Boolean liveDeleteChannelDocResponse;
        try {
            String channelId = super.createChannel();
            liveDeleteChannelDocRequest.setChannelId(channelId)
                    .setFileId("6897d12bd284dd1e9b8b8534b6af91c31965681common")
                    .setType("new");
            liveDeleteChannelDocResponse = new LiveChannelDocServiceImpl().deleteChannelDoc(
                    liveDeleteChannelDocRequest);
            Assert.assertTrue(liveDeleteChannelDocResponse);
            if (liveDeleteChannelDocResponse) {
                //to do something ......
                log.debug("测试删除频道文档成功");
            }
        } catch (PloyvSdkException e) {
            //参数校验不合格 或者 请求服务器端500错误，错误信息见PloyvSdkException.getMessage(),B
            log.error(e.getMessage(), e);
            // 异常返回做B端异常的业务逻辑，记录log 或者 上报到ETL 或者回滚事务
            throw e;
        } catch (Exception e) {
            log.error("SDK调用异常", e);
            throw e;
        }
    }
    
    /**
     * 测试用例结束
     */
}
