package net.polyv.live.v1.service.interact;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

import org.junit.Assert;
import org.junit.Test;

import com.alibaba.fastjson.JSON;

import lombok.extern.slf4j.Slf4j;
import net.polyv.common.v1.exception.PloyvSdkException;
import net.polyv.common.v1.util.FileUtil;
import net.polyv.live.v1.entity.interact.LiveDownloadLotteryDetailRequest;
import net.polyv.live.v1.entity.interact.LiveListLotteryRequest;
import net.polyv.live.v1.entity.interact.LiveListLotteryResponse;
import net.polyv.live.v1.entity.interact.LiveLotteryWinnerDetailRequest;
import net.polyv.live.v1.entity.interact.LiveLotteryWinnerDetailResponse;
import net.polyv.live.v1.entity.interact.LiveSetLotteryWinnerInfoRequest;
import net.polyv.live.v1.service.BaseTest;
import net.polyv.live.v1.service.interact.impl.LiveLotteryServiceImpl;
import net.polyv.live.v1.util.LiveSignUtil;

/**
 * 抽奖管理
 * @author: sadboy
 **/
@Slf4j
public class LiveLotteryServiceImplTest extends BaseTest {
    
    
    /**
     * 测试获取频道抽奖记录列表
     * TODO 等待数据验证
     * @throws IOException
     * @throws NoSuchAlgorithmException
     */
    @Test
    public void testListLottery() throws Exception, NoSuchAlgorithmException {
        LiveListLotteryRequest liveListLotteryRequest = new LiveListLotteryRequest();
        LiveListLotteryResponse liveListLotteryResponse;
        try {
            liveListLotteryRequest.setChannelId(super.createChannel())
                    .setStartTime(super.getDate(1601481600000l))
                    .setEndTime(super.getDate(1605024000000l))
                    .setPageSize(1)
                    .setRequestId(LiveSignUtil.generateUUID());
            liveListLotteryResponse = new LiveLotteryServiceImpl().listLottery(liveListLotteryRequest);
            Assert.assertNotNull(liveListLotteryResponse);
            if (liveListLotteryResponse != null) {
                //to do something ......
                log.debug("测试获取频道抽奖记录列表成功，{}", JSON.toJSONString(liveListLotteryResponse));
            }
        } catch (PloyvSdkException e) {
            //参数校验不合格 或者 请求服务器端500错误，错误信息见PloyvSdkException.getMessage()
            log.error(e.getMessage(), e);
            // 异常返回做B端异常的业务逻辑，记录log 或者 上报到ETL 或者回滚事务
            throw e;
        } catch (Exception e) {
            log.error("SDK调用异常", e);
            throw e;
        }
    }
    
    /**
     * 测试获取频道单场抽奖的中奖记录
     * TODO 等待数据验证
     * @throws IOException
     * @throws NoSuchAlgorithmException
     */
    @Test
    public void testGetLotteryWinnerDetail() throws Exception, NoSuchAlgorithmException {
        LiveLotteryWinnerDetailRequest liveLotteryWinnerDetailRequest = new LiveLotteryWinnerDetailRequest();
        LiveLotteryWinnerDetailResponse liveLotteryWinnerDetailResponse;
        try {
            liveLotteryWinnerDetailRequest.setChannelId(super.createChannel())
                    .setLotteryId("1211")
                    .setRequestId(LiveSignUtil.generateUUID());
            liveLotteryWinnerDetailResponse = new LiveLotteryServiceImpl().getLotteryWinnerDetail(
                    liveLotteryWinnerDetailRequest);
            Assert.assertNotNull(liveLotteryWinnerDetailResponse);
            if (liveLotteryWinnerDetailResponse != null) {
                //to do something ......
                log.debug("测试获取频道单场抽奖的中奖记录成功，{}", JSON.toJSONString(liveLotteryWinnerDetailResponse));
            }
        } catch (PloyvSdkException e) {
            //参数校验不合格 或者 请求服务器端500错误，错误信息见PloyvSdkException.getMessage()
            log.error(e.getMessage(), e);
            // 异常返回做B端异常的业务逻辑，记录log 或者 上报到ETL 或者回滚事务
            throw e;
        } catch (Exception e) {
            log.error("SDK调用异常", e);
            throw e;
        }
    }
    
    /**
     * 测试设置抽奖中奖者信息
     * TODO 等待数据验证
     * 描述：用于提交中奖者填写的信息
     * 约束：2.只能成功保存一次观众中奖信息
     * 约束：3.中奖信息需在7天内提交保存，否则会失效
     * @throws IOException
     * @throws NoSuchAlgorithmException
     */
//    @Test
    public void testSetLotteryWinnerInfo() throws Exception, NoSuchAlgorithmException {
        LiveSetLotteryWinnerInfoRequest liveSetLotteryWinnerInfoRequest = new LiveSetLotteryWinnerInfoRequest();
        Boolean liveSetLotteryWinnerInfoResponse;
        try {
            liveSetLotteryWinnerInfoRequest.setChannelId(super.createChannel())
                    .setLotteryId("")
                    .setWinnerCode("")
                    .setViewerId("")
                    .setName("")
                    .setTelephone("")
                    .setReceiveInfo("")
                    .setRequestId(LiveSignUtil.generateUUID());
            liveSetLotteryWinnerInfoResponse = new LiveLotteryServiceImpl().setLotteryWinnerInfo(
                    liveSetLotteryWinnerInfoRequest);
            Assert.assertNotNull(liveSetLotteryWinnerInfoResponse);
            if (liveSetLotteryWinnerInfoResponse) {
                //to do something ......
                log.debug("测试设置抽奖中奖者信息成功");
            }
        } catch (PloyvSdkException e) {
            //参数校验不合格 或者 请求服务器端500错误，错误信息见PloyvSdkException.getMessage()
            log.error(e.getMessage(), e);
            // 异常返回做B端异常的业务逻辑，记录log 或者 上报到ETL 或者回滚事务
            throw e;
        } catch (Exception e) {
            log.error("SDK调用异常", e);
            throw e;
        }
    }
    
    /**
     * 测试导出频道单场抽奖的中奖记录
     * 描述：用于下载频道的单场抽奖的中奖记录
     * 返回：返回的byte[]可以按照单元测试示例进行保存，也可以自行处理。
     * TODO 未测试通过
     * @throws Exception
     * @throws NoSuchAlgorithmException
     */
//    @Test
    public void testDownloadLotteryDetail() throws Exception, NoSuchAlgorithmException {
        LiveDownloadLotteryDetailRequest liveDownloadLotteryDetailRequest = new LiveDownloadLotteryDetailRequest();
        byte[] liveDownloadLotteryDetailResponse;
        try {
            //path设置为下载文件路径
            String path = getClass().getResource("/file/").getPath() + "downLoadLotteryWinner.xlsx";
            liveDownloadLotteryDetailRequest.setChannelId(createChannel())
                    .setLotteryId("")
                    .setRequestId(LiveSignUtil.generateUUID());
            liveDownloadLotteryDetailResponse = new LiveLotteryServiceImpl().downloadLotteryDetail(
                    liveDownloadLotteryDetailRequest);
            Assert.assertNotNull(liveDownloadLotteryDetailResponse);
            if (liveDownloadLotteryDetailResponse != null) {
                FileUtil.writeFile(liveDownloadLotteryDetailResponse, path);
                //to do something ......
                log.debug("测试导出频道单场抽奖的中奖记录成功, 文件长度 {}", liveDownloadLotteryDetailResponse.length);
            }
        } catch (PloyvSdkException e) {
            //参数校验不合格 或者 请求服务器端500错误，错误信息见PloyvSdkException.getMessage()
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
