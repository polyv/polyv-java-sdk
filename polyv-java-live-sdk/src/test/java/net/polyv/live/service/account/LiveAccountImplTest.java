package net.polyv.live.service.account;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

import org.junit.Assert;
import org.junit.Test;

import com.alibaba.fastjson.JSON;

import lombok.extern.slf4j.Slf4j;
import net.polyv.common.exception.PloyvSdkException;
import net.polyv.live.constant.LiveConstant;
import net.polyv.live.entity.account.LiveAccountInfoRequest;
import net.polyv.live.entity.account.LiveAccountInfoResponse;
import net.polyv.live.entity.account.LiveAccountMicDurationRequest;
import net.polyv.live.entity.account.LiveAccountMicDurationResponse;
import net.polyv.live.entity.account.LiveAccountPlaybackCallbackRequest;
import net.polyv.live.entity.account.LiveAccountRecordCallbackRequest;
import net.polyv.live.entity.account.LiveAccountStreamCallbackRequest;
import net.polyv.live.entity.account.LiveAccountSwitchRequest;
import net.polyv.live.entity.account.LiveAccountSwitchResponse;
import net.polyv.live.entity.account.LiveAccountUserDurationsRequest;
import net.polyv.live.entity.account.LiveAccountUserDurationsResponse;
import net.polyv.live.entity.account.LiveCreateAccountTokenRequest;
import net.polyv.live.entity.account.LiveCreateCategoryRequest;
import net.polyv.live.entity.account.LiveCreateCategoryResponse;
import net.polyv.live.entity.account.LiveDeleteCategoryRequest;
import net.polyv.live.entity.account.LiveListAccountChannelBasicRequest;
import net.polyv.live.entity.account.LiveListAccountChannelBasicResponse;
import net.polyv.live.entity.account.LiveListAccountDetailRequest;
import net.polyv.live.entity.account.LiveListAccountDetailResponse;
import net.polyv.live.entity.account.LiveListAccountRequest;
import net.polyv.live.entity.account.LiveListAccountResponse;
import net.polyv.live.entity.account.LiveListCategoryRequest;
import net.polyv.live.entity.account.LiveListCategoryResponse;
import net.polyv.live.entity.account.LiveUpdateAccountSwitchRequest;
import net.polyv.live.entity.account.LiveUpdateCategoryRequest;
import net.polyv.live.entity.account.LiveUpdateCategorySortRequest;
import net.polyv.live.service.BaseTest;
import net.polyv.live.service.account.impl.LiveAccountServiceImpl;
import net.polyv.live.util.LiveSignUtil;

/**
 * 账户管理
 * @author: sadboy
 **/
@Slf4j
public class LiveAccountImplTest extends BaseTest {
    
    /**
     * 测试创建账号下直播分类
     * @throws Exception
     */
//    @Test
    public void testCreateCategory() throws Exception {
        LiveCreateCategoryRequest liveCreateCategoryRequest = new LiveCreateCategoryRequest();
        LiveCreateCategoryResponse liveCreateCategoryResponse;
        try {
            liveCreateCategoryRequest.setCategoryName("分类1").setRequestId(LiveSignUtil.generateUUID());
            liveCreateCategoryResponse = new LiveAccountServiceImpl().createCategory(liveCreateCategoryRequest);
            Assert.assertNotNull(liveCreateCategoryRequest);
            log.debug("测试创建账号下直播分类成功,{}", JSON.toJSONString(liveCreateCategoryResponse));
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
     * 测试查询账号下直播分类
     * @throws Exception
     */
    @Test
    public void testListCategory() throws Exception {
        LiveListCategoryRequest liveListCategoryRequest = new LiveListCategoryRequest();
        LiveListCategoryResponse liveListCategoryResponse;
        try {
            liveListCategoryRequest.setRequestId(LiveSignUtil.generateUUID());
            liveListCategoryResponse = new LiveAccountServiceImpl().listCategory(liveListCategoryRequest);
            Assert.assertNotNull(liveListCategoryResponse);
            log.debug("测试查询账号下直播分类成功,{}", JSON.toJSONString(liveListCategoryResponse));
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
     * 测试修改直播频道分类名称
     * 返回：true为修改成功，false为修改失败
     * @throws Exception
     */
    @Test
    public void testUpdateCategory() throws Exception {
        LiveUpdateCategoryRequest liveUpdateCategoryRequest = new LiveUpdateCategoryRequest();
        Boolean liveUpdateCategoryResponse;
        try {
            liveUpdateCategoryRequest.setCategoryId(345111)
                    .setCategoryName("测试分类")
                    .setRequestId(LiveSignUtil.generateUUID());
            liveUpdateCategoryResponse = new LiveAccountServiceImpl().updateCategory(liveUpdateCategoryRequest);
            Assert.assertTrue(liveUpdateCategoryResponse);
            log.debug("测试修改直播频道分类名称成功");
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
     * 测试修改直播频道分类顺序
     * 返回：true为修改排序成功，false为修改排序失败
     * @throws Exception
     */
    @Test
    public void testUpdateCategorySort() throws Exception {
        LiveUpdateCategorySortRequest liveUpdateCategorySortRequest = new LiveUpdateCategorySortRequest();
        Boolean liveUpdateCategorySortResponse;
        try {
            liveUpdateCategorySortRequest.setCategoryId(345111)
                    .setAfterCategoryId(345134)
                    .setRequestId(LiveSignUtil.generateUUID());
            liveUpdateCategorySortResponse = new LiveAccountServiceImpl().updateCategorySort(
                    liveUpdateCategorySortRequest);
            Assert.assertTrue(liveUpdateCategorySortResponse);
            log.debug("测试修改直播频道分类顺序成功");
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
     * 测试删除直播频道分类
     * 返回：true为删除成功，false为删除失败
     * @throws Exception
     */
//    @Test
    public void testDeleteCategory() throws Exception {
        LiveDeleteCategoryRequest liveDeleteCategoryRequest = new LiveDeleteCategoryRequest();
        Boolean liveDeleteCategoryResponse;
        try {
            liveDeleteCategoryRequest.setCategoryId(345128).setRequestId(LiveSignUtil.generateUUID());
            liveDeleteCategoryResponse = new LiveAccountServiceImpl().deleteCategory(liveDeleteCategoryRequest);
            Assert.assertTrue(liveDeleteCategoryResponse);
            log.debug("测试删除直播频道分类成功");
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
     * 测试获取直播用户账号信息接口
     * @throws Exception
     */
    @Test
    public void testAccountInfo() throws Exception {
        LiveAccountInfoRequest liveAccountInfoRequest = new LiveAccountInfoRequest();
        LiveAccountInfoResponse liveAccountInfoResponse;
        try {
            liveAccountInfoRequest.setRequestId(LiveSignUtil.generateUUID());
            liveAccountInfoResponse = new LiveAccountServiceImpl().accountInfo(liveAccountInfoRequest);
            Assert.assertNotNull(liveAccountInfoResponse);
            log.debug("测试获取直播用户账号信息接口成功,{}",JSON.toJSONString(liveAccountInfoResponse));
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
     * 查询账号下所有频道详细信息
     * @throws IOException
     */
    @Test
    public void testListAccountDetail() throws Exception, NoSuchAlgorithmException {
        LiveListAccountDetailRequest liveListAccountDetailRequest = new LiveListAccountDetailRequest();
        LiveListAccountDetailResponse liveListAccountDetailResponse;
        try {
            liveListAccountDetailRequest.setCurrentPage(1).setRequestId(LiveSignUtil.generateUUID());
            liveListAccountDetailResponse = new LiveAccountServiceImpl().listAccountDetail(
                    liveListAccountDetailRequest);
            Assert.assertNotNull(liveListAccountDetailResponse);
            if (liveListAccountDetailResponse != null) {
                //to do something ......
                log.debug("分页查询账号下所有频道详细信息成功,{}", JSON.toJSONString(liveListAccountDetailResponse));
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
     * 测试查询账号下的频道列表
     * @throws IOException
     * @throws NoSuchAlgorithmException
     */
    @Test
    public void testListAccount() throws Exception, NoSuchAlgorithmException {
        LiveListAccountRequest liveListAccountRequest = new LiveListAccountRequest();
        LiveListAccountResponse liveListAccountResponse;
        try {
            liveListAccountRequest.setCategoryId(null).setKeyword(null).setRequestId(LiveSignUtil.generateUUID());
            liveListAccountResponse = new LiveAccountServiceImpl().listAccount(liveListAccountRequest);
            Assert.assertNotNull(liveListAccountResponse);
            if (liveListAccountResponse != null) {
                //to do something ......
                log.debug("测试查询账号下的频道列表成功,{}", JSON.toJSONString(liveListAccountResponse));
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
     * 测试获取账号连麦分钟数使用量与剩余量
     * @throws IOException
     * @throws NoSuchAlgorithmException
     */
    @Test
    public void testMicDuration() throws Exception, NoSuchAlgorithmException {
        LiveAccountMicDurationRequest liveAccountMicDurationRequest = new LiveAccountMicDurationRequest();
        LiveAccountMicDurationResponse liveAccountMicDurationResponse;
        try {
            liveAccountMicDurationRequest.setRequestId(LiveSignUtil.generateUUID());
            liveAccountMicDurationResponse = new LiveAccountServiceImpl().micDuration(liveAccountMicDurationRequest);
            Assert.assertNotNull(liveAccountMicDurationResponse);
            if (liveAccountMicDurationResponse != null) {
                //to do something ......
                log.debug("测试获取账号连麦分钟数使用量与剩余量成功,{}", JSON.toJSONString(liveAccountMicDurationResponse));
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
     * 测试设置账号单点登录的token
     * 返回：true为设置成功，false为设置失败
     * 约束：2、token 参数请勿过于简单，建议使用16位随机字符串
     * @throws IOException
     * @throws NoSuchAlgorithmException
     */
    @Test
    public void testCreateAccountToken() throws Exception, NoSuchAlgorithmException {
        LiveCreateAccountTokenRequest liveCreateAccountTokenRequest = new LiveCreateAccountTokenRequest();
        Boolean liveCreateAccountTokenResponse;
        try {
            liveCreateAccountTokenRequest.setToken(LiveSignUtil.generateUUID())
                    .setRequestId(LiveSignUtil.generateUUID());
            liveCreateAccountTokenResponse = new LiveAccountServiceImpl().createAccountToken(
                    liveCreateAccountTokenRequest);
            Assert.assertNotNull(liveCreateAccountTokenResponse);
            if (liveCreateAccountTokenResponse) {
                //to do something ......
                log.debug("测试设置账号单点登录的token成功");
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
     * 测试设置直播状态回调通知url
     * 约束：2、不提交地址参数url，则回调地址为空，表示关闭回调功能，如果要提交的地址参数url，必须以 http:// 或者 https:// 开头
     * 返回：true为设置回调成功，false为设置回调失败
     * 回调说明：设置接口地址后，如果账号下频道有进行推流、断流操作导致频道直播状态改变，直播系统会将以下参数channelId（频道号）和status（直播状态：live表示开始直播，end表示直播结束）以GET
     * 回调说明：方式提交到给用户自定义的回调接口进行通知，如：http://abc.com/test.do?channelId=123456&status=live&timestamp=1557976774000
     * 回调说明：&sign=xxdxxxxx&sessionId=xxxxxddd&startTime=1557976777111&endTime=1557976777111
     * 回调对象：net.polyv.live.entity.account.LiveAccountStreamCallbackRequest$LiveStateChangeCallback
     * @throws IOException
     * @throws NoSuchAlgorithmException
     */
    @Test
    public void testUpdateStreamCallbackUrl() throws Exception, NoSuchAlgorithmException {
        LiveAccountStreamCallbackRequest liveAccountStreamCallbackRequest = new LiveAccountStreamCallbackRequest();
        Boolean liveAccountStreamCallbackResponse;
        try {
            liveAccountStreamCallbackRequest.setUrl("http://www.abc.com/callback")
                    .setRequestId(LiveSignUtil.generateUUID());
            liveAccountStreamCallbackResponse = new LiveAccountServiceImpl().updateStreamCallbackUrl(
                    liveAccountStreamCallbackRequest);
            Assert.assertNotNull(liveAccountStreamCallbackResponse);
            if (liveAccountStreamCallbackResponse) {
                //to do something ......
                log.debug("测试设置直播状态回调通知url成功");
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
     * 测试设置转存成功回调通知url
     * 约束：2、不提交地址参数url，则回调地址为空，表示关闭回调功能，如果要提交的地址参数url，必须以 http:// 或者 https:// 开头
     * 返回：true为设置回调成功，false为设置回调失败
     * 回调说明：设置接口地址后，如果账号有进行暂存视频转存，即将直播生成的录制文件转存到点播中，如果转存视频处理完毕为已完成状态，直播系统会将以下参数channelId(频道号)、vid(转存成功的视频ID)、title
     * 回调说明：(视频标题)、duration(视频时长)和fileSize（视频文件大小）以GET方式提交到给用户自定义的回调接口进行通知，如：http://abc.com/test
     * 回调说明：.do?channelId=123456&vid=e6b23c6f5134943a015bc117e2854eae_e&title=视频标题&duration=01:23:45&fileSize=123400
     * 回调说明：&timestamp=1557976774000&sign=xxxxxxxxxx&fileId=359a81ed8fd8cb83d88ddcd97d9e8a2b&videoId=b1c6f3ad2c&origin
     * 回调说明：=auto&sessionIds=["20190703145126,4,fdqbopvtnv","20190703145126,8,fdqbopvtnv"]
     * 回调对象：net.polyv.live.entity.account.LiveAccountPlaybackCallbackRequest$PlaybackCallBack
     * @throws IOException
     * @throws NoSuchAlgorithmException
     */
    @Test
    public void testUpdatePlaybackCallbackUrl() throws Exception, NoSuchAlgorithmException {
        LiveAccountPlaybackCallbackRequest liveAccountPlaybackCallbackRequest =
                new LiveAccountPlaybackCallbackRequest();
        Boolean liveAccountPlaybackCallbackResponse;
        try {
            liveAccountPlaybackCallbackRequest.setUrl("http://www.abc.com/callback")
                    .setRequestId(LiveSignUtil.generateUUID());
            liveAccountPlaybackCallbackResponse = new LiveAccountServiceImpl().updatePlaybackCallbackUrl(
                    liveAccountPlaybackCallbackRequest);
            Assert.assertNotNull(liveAccountPlaybackCallbackResponse);
            if (liveAccountPlaybackCallbackResponse != null) {
                //to do something ......
                log.debug("测试设置转存成功回调通知url成功,{}", liveAccountPlaybackCallbackResponse);
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
     * 测试设置录制回调通知url
     * 约束：2、不提交地址参数url，则回调地址为空，表示关闭回调功能，如果要提交的地址参数url，必须以 http:// 或者 https:// 开头
     * 返回：true为设置回调成功，false为设置回调失败
     * 回调说明：设置接口地址后，如果账号有开通录制功能，账号下各频道在推流结束，生成m3u8录制视频后，直播系统会将参数channelId（频道号）和fileUrl（录制文件地址）以GET
     * 回调说明：方式提交到给用户自定义的回调接口进行通知，如：http://abc.com/test.do?channelId=104400&fileUrl=http://rflive.videocc
     * 回调说明：.net/i6ro0hxj0020150529112242035/recordf.i6ro0hxj0020150529112242035_20170120184803
     * 回调说明：.m3u8&origin=auto&fileId=072c36138cfbd3e546cda227dc273951&timestamp=1557976774000&sign=xxxxxxxxxx
     * 回调对象：net.polyv.live.entity.account.LiveAccountRecordCallbackRequest$RecordCallback
     * @throws IOException
     * @throws NoSuchAlgorithmException
     */
    @Test
    public void testUpdateRecordCallbackUrl() throws Exception, NoSuchAlgorithmException {
        LiveAccountRecordCallbackRequest liveAccountRecordCallbackRequest = new LiveAccountRecordCallbackRequest();
        Boolean liveAccountRecordCallbackResponse;
        try {
            liveAccountRecordCallbackRequest.setUrl("http://www.abc.com/callback")
                    .setRequestId(LiveSignUtil.generateUUID());
            liveAccountRecordCallbackResponse = new LiveAccountServiceImpl().updateRecordCallbackUrl(
                    liveAccountRecordCallbackRequest);
            Assert.assertNotNull(liveAccountRecordCallbackResponse);
            if (liveAccountRecordCallbackResponse) {
                //to do something ......
                log.debug("测试设置录制回调通知url成功");
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
     * 测试设置功能开关状态
     * 返回：true为设置成功，false为设置失败
     * 约束：2、isClosePreview当enabled值为Y时，表示的是关闭系统观看页;closeDanmu当enabled值为Y时，表示的是关闭弹幕;
     * 约束：closeChaterList当enabled值为Y时，表示的是关闭在线列表
     * @throws IOException
     * @throws NoSuchAlgorithmException
     */
    @Test
    public void testUpdateAccountSwitch() throws Exception, NoSuchAlgorithmException {
        LiveUpdateAccountSwitchRequest liveUpdateAccountSwitchRequest = new LiveUpdateAccountSwitchRequest();
        Boolean liveUpdateAccountSwitchResponse;
        try {
            liveUpdateAccountSwitchRequest.setType(LiveConstant.ChannelSwitch.AUTO_PLAY.getDesc())
                    .setEnabled("N")
                    .setRequestId(LiveSignUtil.generateUUID());
            liveUpdateAccountSwitchResponse = new LiveAccountServiceImpl().updateAccountSwitch(
                    liveUpdateAccountSwitchRequest);
            Assert.assertNotNull(liveUpdateAccountSwitchResponse);
            if (liveUpdateAccountSwitchResponse) {
                //to do something ......
                log.debug("设置功能开关状态成功");
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
     * 测试查询功能开关状态接口
     * 描述：接口用于获取开关设置，可获取全局开关设置或频道开关设置
     * 约束：2、isClosePreview当enabled值为Y时，表示的是关闭系统观看页;closeDanmu当enabled值为Y时，表示的是关闭弹幕;
     * 约束：closeChaterList当enabled值为Y时，表示的是关闭在线列表
     * @throws IOException
     * @throws NoSuchAlgorithmException
     */
    @Test
    public void testAccountSwitch() throws Exception, NoSuchAlgorithmException {
        LiveAccountSwitchRequest liveAccountSwitchRequest = new LiveAccountSwitchRequest();
        LiveAccountSwitchResponse liveAccountSwitchResponse;
        try {
            liveAccountSwitchRequest.setChannelId(null).setRequestId(LiveSignUtil.generateUUID());
            liveAccountSwitchResponse = new LiveAccountServiceImpl().accountSwitch(liveAccountSwitchRequest);
            Assert.assertNotNull(liveAccountSwitchResponse);
            if (liveAccountSwitchResponse != null) {
                //to do something ......
                log.debug("测试查询功能开关状态接口成功,{}", JSON.toJSONString(liveAccountSwitchResponse));
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
     * 测试查询账号下所有频道缩略信息
     * @throws IOException
     * @throws NoSuchAlgorithmException
     */
    @Test
    public void testListChannelBasic() throws Exception, NoSuchAlgorithmException {
        LiveListAccountChannelBasicRequest liveListAccountChannelBasicRequest =
                new LiveListAccountChannelBasicRequest();
        LiveListAccountChannelBasicResponse liveListAccountChannelBasicResponse;
        try {
            liveListAccountChannelBasicRequest.setCategoryId(null)
                    .setWatchStatus("end")
                    .setKeyword("勿删")
                    .setPageSize(null)
                    .setCurrentPage(1)
                    .setRequestId(LiveSignUtil.generateUUID());
            liveListAccountChannelBasicResponse = new LiveAccountServiceImpl().listChannelBasic(
                    liveListAccountChannelBasicRequest);
            Assert.assertNotNull(liveListAccountChannelBasicResponse);
            if (liveListAccountChannelBasicResponse != null) {
                //to do something ......
                log.debug("测试查询账号下所有频道缩略信息成功,{}", JSON.toJSONString(liveListAccountChannelBasicResponse));
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
     * 测试查询账户分钟数
     * @throws IOException
     * @throws NoSuchAlgorithmException
     */
    @Test
    public void testUserDurations() throws Exception, NoSuchAlgorithmException {
        LiveAccountUserDurationsRequest liveAccountUserDurationsRequest = new LiveAccountUserDurationsRequest();
        LiveAccountUserDurationsResponse liveAccountUserDurationsResponse;
        try {
            liveAccountUserDurationsRequest.setRequestId(LiveSignUtil.generateUUID());
            liveAccountUserDurationsResponse = new LiveAccountServiceImpl().userDurations(
                    liveAccountUserDurationsRequest);
            Assert.assertNotNull(liveAccountUserDurationsResponse);
            if (liveAccountUserDurationsResponse != null) {
                //to do something ......
                log.debug("测试查询账户分钟数成功,{}", JSON.toJSONString(liveAccountUserDurationsResponse));
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
    
}
