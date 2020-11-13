package net.polyv.live.service.web;

import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.alibaba.fastjson.JSON;

import lombok.extern.slf4j.Slf4j;
import net.polyv.common.exception.PloyvSdkException;
import net.polyv.common.util.FileUtil;
import net.polyv.live.constant.LiveConstant;
import net.polyv.live.entity.channel.operate.LiveChannelSettingRequest;
import net.polyv.live.entity.web.auth.LiveChannelAuthCustomRequest;
import net.polyv.live.entity.web.auth.LiveChannelAuthCustomResponse;
import net.polyv.live.entity.web.auth.LiveChannelAuthExternalRequest;
import net.polyv.live.entity.web.auth.LiveChannelAuthExternalResponse;
import net.polyv.live.entity.web.auth.LiveChannelAuthFieldRequest;
import net.polyv.live.entity.web.auth.LiveChannelAuthFieldResponse;
import net.polyv.live.entity.web.auth.LiveChannelAuthInfoRequest;
import net.polyv.live.entity.web.auth.LiveChannelAuthInfoResponse;
import net.polyv.live.entity.web.auth.LiveChannelAuthRequest;
import net.polyv.live.entity.web.auth.LiveChannelAuthResponse;
import net.polyv.live.entity.web.auth.LiveChannelAuthTypeRequest;
import net.polyv.live.entity.web.auth.LiveChannelWriteListRequest;
import net.polyv.live.entity.web.auth.LiveChannelWriteListResponse;
import net.polyv.live.entity.web.auth.LiveCreateChannelWriteListRequest;
import net.polyv.live.entity.web.auth.LiveDeleteChannelWriteListRequest;
import net.polyv.live.entity.web.auth.LiveDownloadChannelAuthInfoRequest;
import net.polyv.live.entity.web.auth.LiveUpdateChannelAuthRequest;
import net.polyv.live.entity.web.auth.LiveUpdateChannelAuthUrlRequest;
import net.polyv.live.entity.web.auth.LiveUpdateChannelWriteListRequest;
import net.polyv.live.service.BaseTest;
import net.polyv.live.service.web.impl.LiveWebAuthServiceImpl;
import net.polyv.live.util.LiveSignUtil;

/**
 * 页面观看条件
 * @author: sadboy
 **/
@Slf4j
public class LiveWebAuthImplTest extends BaseTest {
    
    /**
     * 测试添加单个白名单
     * 返回：true为添加成功，false为添加失败
     * @throws Exception
     * @throws NoSuchAlgorithmException
     */
//    @Test
    public void testCreateChannelWriteList() throws Exception, NoSuchAlgorithmException {
        LiveCreateChannelWriteListRequest liveCreateChannelWriteListRequest = new LiveCreateChannelWriteListRequest();
        Boolean liveCreateChannelWriteListResponse;
        try {
            liveCreateChannelWriteListRequest.setRank(1)
                    .setCode(String.valueOf(System.currentTimeMillis()))
                    .setName("sadboy")
                    .setRequestId(LiveSignUtil.generateUUID());
            liveCreateChannelWriteListResponse = new LiveWebAuthServiceImpl().createChannelWriteList(
                    liveCreateChannelWriteListRequest);
            Assert.assertNotNull(liveCreateChannelWriteListResponse);
            if (liveCreateChannelWriteListResponse) {
                //to do something ......
                log.debug("测试添加单个白名单-全局白名单成功");
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
     * 测试查询频道观看白名单列表
     * 用于获取全局或频道的观看条件白名单列表
     * @throws Exception
     * @throws NoSuchAlgorithmException
     */
    @Test
    public void testGetChannelWriteList() throws Exception, NoSuchAlgorithmException {
        LiveChannelWriteListRequest liveChannelWriteListRequest = new LiveChannelWriteListRequest();
        LiveChannelWriteListResponse liveChannelWriteListResponse;
        try {
            liveChannelWriteListRequest.setChannelId(null)
                    .setRank(1)
                    .setKeyword(null)
                    .setPageSize(1)
                    .setRequestId(LiveSignUtil.generateUUID());
            liveChannelWriteListResponse = new LiveWebAuthServiceImpl().getChannelWriteList(
                    liveChannelWriteListRequest);
            Assert.assertNotNull(liveChannelWriteListResponse);
            if (liveChannelWriteListResponse != null) {
                //to do something ......
                log.debug("测试查询频道观看白名单列表成功,{}", JSON.toJSONString(liveChannelWriteListResponse));
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
     * 测试查询直播频道观看条件
     * @throws Exception
     * @throws NoSuchAlgorithmException
     */
    @Test
    public void testGetChannelAuth() throws Exception, NoSuchAlgorithmException {
        LiveChannelAuthRequest liveChannelAuthRequest = new LiveChannelAuthRequest();
        LiveChannelAuthResponse liveChannelAuthResponse;
        try {
            liveChannelAuthRequest.setChannelId(createChannel()).setRequestId(LiveSignUtil.generateUUID());
            liveChannelAuthResponse = new LiveWebAuthServiceImpl().getChannelAuth(liveChannelAuthRequest);
            Assert.assertNotNull(liveChannelAuthResponse);
            if (liveChannelAuthResponse != null) {
                //to do something ......
                log.debug("测试查询直播频道观看条件成功,{}", JSON.toJSONString(liveChannelAuthResponse));
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
     * 测试设置观看条件
     * 返回：true为设置观看条件成功，false为设置失败
     * @throws Exception
     * @throws NoSuchAlgorithmException
     */
    @Test
    public void testUpdateChannelAuth() throws Exception, NoSuchAlgorithmException {
        LiveUpdateChannelAuthRequest liveUpdateChannelAuthRequest = new LiveUpdateChannelAuthRequest();
        Boolean liveUpdateChannelAuthResponse;
        try {
            LiveChannelSettingRequest.AuthSetting authSetting = new LiveChannelSettingRequest.AuthSetting().setAuthType(
                    LiveConstant.AuthType.CODE.getDesc())
                    .setRank(2)
                    .setEnabled("Y")
                    .setAuthCode("123456")
                    .setQcodeTips("提示文案测试2")
                    .setQcodeImg("https://live.polyv.net/static/images/live-header-logo.png");
            List<LiveChannelSettingRequest.AuthSetting> authSettings =
                    new ArrayList<LiveChannelSettingRequest.AuthSetting>();
            authSettings.add(authSetting);
            liveUpdateChannelAuthRequest.setChannelId(createChannel())
                    .setAuthSettings(authSettings)
                    .setRequestId(LiveSignUtil.generateUUID());
            liveUpdateChannelAuthResponse = new LiveWebAuthServiceImpl().updateChannelAuth(
                    liveUpdateChannelAuthRequest);
            Assert.assertNotNull(liveUpdateChannelAuthResponse);
            if (liveUpdateChannelAuthResponse) {
                log.debug("测试设置观看条件成功");
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
     * 测试通过接口设置外部授权
     * @throws Exception
     * @throws NoSuchAlgorithmException
     */
    @Test
    public void testUpdateChannelAuthExternal() throws Exception, NoSuchAlgorithmException {
        LiveChannelAuthExternalRequest liveChannelAuthExternalRequest = new LiveChannelAuthExternalRequest();
        LiveChannelAuthExternalResponse liveChannelAuthExternalResponse;
        try {
            liveChannelAuthExternalRequest.setChannelId(createChannel())
                    .setExternalUri("https://dev.polyv.net/")
                    .setRequestId(LiveSignUtil.generateUUID());
            liveChannelAuthExternalResponse = new LiveWebAuthServiceImpl().updateChannelAuthExternal(
                    liveChannelAuthExternalRequest);
            Assert.assertNotNull(liveChannelAuthExternalResponse);
            if (liveChannelAuthExternalResponse != null) {
                //to do something ......
                log.debug("测试通过接口设置外部授权成功,{}", JSON.toJSONString(liveChannelAuthExternalResponse));
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
     * 测试设置自定义授权地址
     * @throws Exception
     * @throws NoSuchAlgorithmException
     */
    @Test
    public void testUpdateChannelAuthCustom() throws Exception, NoSuchAlgorithmException {
        LiveChannelAuthCustomRequest liveChannelAuthCustomRequest = new LiveChannelAuthCustomRequest();
        LiveChannelAuthCustomResponse liveChannelAuthCustomResponse;
        try {
            liveChannelAuthCustomRequest.setChannelId(createChannel())
                    .setCustomUri("https://dev.polyv.net/")
                    .setRequestId(LiveSignUtil.generateUUID());
            liveChannelAuthCustomResponse = new LiveWebAuthServiceImpl().updateChannelAuthCustom(
                    liveChannelAuthCustomRequest);
            Assert.assertNotNull(liveChannelAuthCustomResponse);
            if (liveChannelAuthCustomResponse != null) {
                //to do something ......
                log.debug("测试设置自定义授权地址成功,{}", JSON.toJSONString(liveChannelAuthCustomResponse));
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
     * 测试设置授权认证URL
     * 返回：true为设置成功，false为设置失败
     * @throws Exception
     * @throws NoSuchAlgorithmException
     */
    @Test
    public void testUpdateChannelAuthUrl() throws Exception, NoSuchAlgorithmException {
        LiveUpdateChannelAuthUrlRequest liveUpdateChannelAuthUrlRequest = new LiveUpdateChannelAuthUrlRequest();
        Boolean liveUpdateChannelAuthUrlResponse;
        try {
            liveUpdateChannelAuthUrlRequest.setChannelId(createChannel())
                    .setUrl("http://www.polyv.net")
                    .setRequestId(LiveSignUtil.generateUUID());
            liveUpdateChannelAuthUrlResponse = new LiveWebAuthServiceImpl().updateChannelAuthUrl(
                    liveUpdateChannelAuthUrlRequest);
            Assert.assertNotNull(liveUpdateChannelAuthUrlResponse);
            if (liveUpdateChannelAuthUrlResponse != null) {
                //to do something ......
                log.debug("测试设置授权认证URL成功");
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
     * 测试设置授权观看类型
     * 返回：true为授权成功，false为授权失败
     * @throws Exception
     * @throws NoSuchAlgorithmException
     */
    @Test
    public void testUpdateChannelAuthType() throws Exception, NoSuchAlgorithmException {
        LiveChannelAuthTypeRequest liveChannelAuthTypeRequest = new LiveChannelAuthTypeRequest();
        Boolean liveChannelAuthTypeResponse;
        try {
            liveChannelAuthTypeRequest.setChannelId(createChannel())
                    .setAuthType("none")
                    .setRequestId(LiveSignUtil.generateUUID());
            liveChannelAuthTypeResponse = new LiveWebAuthServiceImpl().updateChannelAuthType(
                    liveChannelAuthTypeRequest);
            Assert.assertNotNull(liveChannelAuthTypeResponse);
            if (liveChannelAuthTypeResponse) {
                //to do something ......
                log.debug("测试设置授权观看类型成功");
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
     * 测试更新白名单
     * 返回：true为更新成功，false为失败
     * @throws Exception
     * @throws NoSuchAlgorithmException
     */
    @Test
    public void testUpdateChannelWriteList() throws Exception, NoSuchAlgorithmException {
        LiveUpdateChannelWriteListRequest liveUpdateChannelWriteListRequest = new LiveUpdateChannelWriteListRequest();
        Boolean liveUpdateChannelWriteListResponse;
        try {
            liveUpdateChannelWriteListRequest.setChannelId(null)
                    .setRank(1)
                    .setOldCode("1605067278063")
                    .setCode("1605067278063")
                    .setName("sadboyChange")
                    .setRequestId(LiveSignUtil.generateUUID());
            liveUpdateChannelWriteListResponse = new LiveWebAuthServiceImpl().updateChannelWriteList(
                    liveUpdateChannelWriteListRequest);
            Assert.assertNotNull(liveUpdateChannelWriteListResponse);
            if (liveUpdateChannelWriteListResponse) {
                //to do something ......
                log.debug("测试更新白名单成功");
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
     * 测试删除白名单
     * 描述：用于删除指定观看白名单（支持一键清空）
     * 返回：true为删除成功，false为失败
     * @throws Exception
     * @throws NoSuchAlgorithmException
     */
//    @Test
    public void testDeleteChannelWriteList() throws Exception, NoSuchAlgorithmException {
        LiveDeleteChannelWriteListRequest liveDeleteChannelWriteListRequest = new LiveDeleteChannelWriteListRequest();
        Boolean liveDeleteChannelWriteListResponse;
        try {
            liveDeleteChannelWriteListRequest.setChannelId(null)
                    .setRank(1)
                    .setIsClear("N")
                    .setCode("1605052902421")
                    .setRequestId(LiveSignUtil.generateUUID());
            liveDeleteChannelWriteListResponse = new LiveWebAuthServiceImpl().deleteChannelWriteList(
                    liveDeleteChannelWriteListRequest);
            Assert.assertNotNull(liveDeleteChannelWriteListResponse);
            if (liveDeleteChannelWriteListResponse) {
                //to do something ......
                log.debug("测试删除白名单成功");
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
     * 测试查询频道或全局登记观看字段
     * @throws Exception
     * @throws NoSuchAlgorithmException
     */
    @Test
    public void testGetChannelAuthField() throws Exception, NoSuchAlgorithmException {
        LiveChannelAuthFieldRequest liveChannelAuthFieldRequest = new LiveChannelAuthFieldRequest();
        LiveChannelAuthFieldResponse liveChannelAuthFieldResponse;
        try {
            liveChannelAuthFieldRequest.setChannelId(createChannel())
                    .setRank(1)
                    .setRequestId(LiveSignUtil.generateUUID());
            liveChannelAuthFieldResponse = new LiveWebAuthServiceImpl().getChannelAuthField(
                    liveChannelAuthFieldRequest);
            Assert.assertNotNull(liveChannelAuthFieldResponse);
            if (liveChannelAuthFieldResponse != null) {
                //to do something ......
                log.debug("测试查询频道或全局登记观看字段成功,{}", JSON.toJSONString(liveChannelAuthFieldResponse));
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
     * 测试查询页面登记观看列表
     * @throws Exception
     * @throws NoSuchAlgorithmException
     */
    @Test
    public void testGetChannelAuthInfo() throws Exception, NoSuchAlgorithmException {
        LiveChannelAuthInfoRequest liveChannelAuthInfoRequest = new LiveChannelAuthInfoRequest();
        LiveChannelAuthInfoResponse liveChannelAuthInfoResponse;
        try {
            liveChannelAuthInfoRequest.setChannelId(createChannel()).setRequestId(LiveSignUtil.generateUUID());
            liveChannelAuthInfoResponse = new LiveWebAuthServiceImpl().getChannelAuthInfo(liveChannelAuthInfoRequest);
            Assert.assertNotNull(liveChannelAuthInfoResponse);
            if (liveChannelAuthInfoResponse != null) {
                //to do something ......
                log.debug("测试查询页面登记观看列表成功,{}", JSON.toJSONString(liveChannelAuthInfoResponse));
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
     * 测试下载频道登记观看记录
     * 描述：接口用于下载频道的登记观看列表，包含登记观看记录字段和数据内容
     * @throws Exception
     * @throws NoSuchAlgorithmException
     */
    @Test
    public void testDownloadChannelAuthInfo() throws Exception, NoSuchAlgorithmException {
        LiveDownloadChannelAuthInfoRequest liveDownloadChannelAuthInfoRequest =
                new LiveDownloadChannelAuthInfoRequest();
        byte[] liveDownloadChannelAuthInfoResponse;
        try {
            //path设置为下载文件路径
            String path = getClass().getResource("/file/").getPath()+"downLoad.xlsx";
            liveDownloadChannelAuthInfoRequest.setChannelId(createChannel())
                    .setRank(1)
                    .setRequestId(LiveSignUtil.generateUUID());
            liveDownloadChannelAuthInfoResponse = new LiveWebAuthServiceImpl().downloadChannelAuthInfo(
                    liveDownloadChannelAuthInfoRequest);
            Assert.assertNotNull(liveDownloadChannelAuthInfoResponse);
            if (liveDownloadChannelAuthInfoResponse != null) {
                FileUtil.writeFile(liveDownloadChannelAuthInfoResponse,path);
                //to do something ......
                log.debug("测试下载频道登记观看记录成功, 文件长度 {}", liveDownloadChannelAuthInfoResponse.length);
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
