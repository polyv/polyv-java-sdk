package net.polyv.live.v1.service.web;

import java.io.File;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.alibaba.fastjson.JSON;

import lombok.extern.slf4j.Slf4j;
import net.polyv.common.v1.exception.PloyvSdkException;
import net.polyv.common.v1.util.FileUtil;
import net.polyv.live.v1.constant.LiveConstant;
import net.polyv.live.v1.entity.channel.operate.LiveChannelSettingRequest;
import net.polyv.live.v1.entity.web.auth.LiveChannelAuthCustomRequest;
import net.polyv.live.v1.entity.web.auth.LiveChannelAuthCustomResponse;
import net.polyv.live.v1.entity.web.auth.LiveChannelAuthExternalRequest;
import net.polyv.live.v1.entity.web.auth.LiveChannelAuthExternalResponse;
import net.polyv.live.v1.entity.web.auth.LiveChannelAuthFieldRequest;
import net.polyv.live.v1.entity.web.auth.LiveChannelAuthFieldResponse;
import net.polyv.live.v1.entity.web.auth.LiveChannelAuthInfoRequest;
import net.polyv.live.v1.entity.web.auth.LiveChannelAuthInfoResponse;
import net.polyv.live.v1.entity.web.auth.LiveChannelAuthRequest;
import net.polyv.live.v1.entity.web.auth.LiveChannelAuthResponse;
import net.polyv.live.v1.entity.web.auth.LiveChannelAuthTypeRequest;
import net.polyv.live.v1.entity.web.auth.LiveChannelWhiteListRequest;
import net.polyv.live.v1.entity.web.auth.LiveChannelWhiteListResponse;
import net.polyv.live.v1.entity.web.auth.LiveCreateChannelWhiteListRequest;
import net.polyv.live.v1.entity.web.auth.LiveDeleteChannelWhiteListRequest;
import net.polyv.live.v1.entity.web.auth.LiveDownloadChannelAuthInfoRequest;
import net.polyv.live.v1.entity.web.auth.LiveDownloadChannelWhiteListRequest;
import net.polyv.live.v1.entity.web.auth.LiveUpdateChannelAuthRequest;
import net.polyv.live.v1.entity.web.auth.LiveUpdateChannelAuthUrlRequest;
import net.polyv.live.v1.entity.web.auth.LiveUpdateChannelWhiteListRequest;
import net.polyv.live.v1.entity.web.auth.LiveUploadWhiteListRequest;
import net.polyv.live.v1.service.BaseTest;
import net.polyv.live.v1.service.web.impl.LiveWebAuthServiceImpl;


/**
 * 页面观看条件
 * @author: sadboy
 **/
@Slf4j
public class LiveWebAuthImplTest extends BaseTest {
    
    /**
     * 测试添加单个白名单
     * 返回：true为添加成功，false为添加失败
     * API地址：CHANNEL_WHITE_LIST_ADD_URL
     * @throws Exception
     * @throws NoSuchAlgorithmException
     */
//    @Test
    public void testCreateChannelWhiteList() throws Exception, NoSuchAlgorithmException {
        LiveCreateChannelWhiteListRequest liveCreateChannelWhiteListRequest = new LiveCreateChannelWhiteListRequest();
        Boolean liveCreateChannelWhiteListResponse;
        try {
            liveCreateChannelWhiteListRequest.setRank(1)
                    .setCode(String.valueOf(System.currentTimeMillis()))
                    .setName("sadboy");
            liveCreateChannelWhiteListResponse = new LiveWebAuthServiceImpl().createChannelWhiteList(
                    liveCreateChannelWhiteListRequest);
            Assert.assertNotNull(liveCreateChannelWhiteListResponse);
            if (liveCreateChannelWhiteListResponse) {
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
     * API地址：CHANNEL_WHITE_LIST_GET_URL
     * @throws Exception
     * @throws NoSuchAlgorithmException
     */
    @Test
    public void testGetChannelWhiteList() throws Exception, NoSuchAlgorithmException {
        LiveChannelWhiteListRequest liveChannelWhiteListRequest = new LiveChannelWhiteListRequest();
        LiveChannelWhiteListResponse liveChannelWhiteListResponse;
        try {
            liveChannelWhiteListRequest.setChannelId(null)
                    .setRank(1)
                    .setKeyword(null)
                    .setPageSize(1);
            liveChannelWhiteListResponse = new LiveWebAuthServiceImpl().getChannelWhiteList(
                    liveChannelWhiteListRequest);
            Assert.assertNotNull(liveChannelWhiteListResponse);
            if (liveChannelWhiteListResponse != null) {
                //to do something ......
                log.debug("测试查询频道观看白名单列表成功,{}", JSON.toJSONString(liveChannelWhiteListResponse));
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
     * API地址：CHANNEL_AUTH_GET_URL
     * @throws Exception
     * @throws NoSuchAlgorithmException
     */
    @Test
    public void testGetChannelAuth() throws Exception, NoSuchAlgorithmException {
        LiveChannelAuthRequest liveChannelAuthRequest = new LiveChannelAuthRequest();
        LiveChannelAuthResponse liveChannelAuthResponse;
        try {
            liveChannelAuthRequest.setChannelId(createChannel());
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
     * API地址：CHANNEL_AUTH_UPDATE_URL
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
                    .setAuthSettings(authSettings);
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
     * API地址：CHANNEL_AUTH_EXTERNAL_URL
     * @throws Exception
     * @throws NoSuchAlgorithmException
     */
    @Test
    public void testUpdateChannelAuthExternal() throws Exception, NoSuchAlgorithmException {
        LiveChannelAuthExternalRequest liveChannelAuthExternalRequest = new LiveChannelAuthExternalRequest();
        LiveChannelAuthExternalResponse liveChannelAuthExternalResponse;
        try {
            liveChannelAuthExternalRequest.setChannelId(createChannel())
                    .setExternalUri("https://dev.polyv.net/");
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
     * API地址：CHANNEL_AUTH_CUSTOM_URL
     * @throws Exception
     * @throws NoSuchAlgorithmException
     */
    @Test
    public void testUpdateChannelAuthCustom() throws Exception, NoSuchAlgorithmException {
        LiveChannelAuthCustomRequest liveChannelAuthCustomRequest = new LiveChannelAuthCustomRequest();
        LiveChannelAuthCustomResponse liveChannelAuthCustomResponse;
        try {
            liveChannelAuthCustomRequest.setChannelId(createChannel())
                    .setCustomUri("https://dev.polyv.net/");
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
     * API地址：CHANNEL_AUTH_URL_UPDATE_URL
     * @throws Exception
     * @throws NoSuchAlgorithmException
     */
    @Test
    public void testUpdateChannelAuthUrl() throws Exception, NoSuchAlgorithmException {
        LiveUpdateChannelAuthUrlRequest liveUpdateChannelAuthUrlRequest = new LiveUpdateChannelAuthUrlRequest();
        Boolean liveUpdateChannelAuthUrlResponse;
        try {
            liveUpdateChannelAuthUrlRequest.setChannelId(createChannel())
                    .setUrl("http://www.polyv.net");
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
     * API地址：CHANNEL_AUTH_TYPE_URL
     * @throws Exception
     * @throws NoSuchAlgorithmException
     */
    @Test
    public void testUpdateChannelAuthType() throws Exception, NoSuchAlgorithmException {
        LiveChannelAuthTypeRequest liveChannelAuthTypeRequest = new LiveChannelAuthTypeRequest();
        Boolean liveChannelAuthTypeResponse;
        try {
            liveChannelAuthTypeRequest.setChannelId(createChannel())
                    .setAuthType("none");
            liveChannelAuthTypeResponse = new LiveWebAuthServiceImpl().updateChannelAuthType(
                    liveChannelAuthTypeRequest);
            Assert.assertTrue(liveChannelAuthTypeResponse);
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
     * API地址：UPDATE_CHANNEL_WHITE_LIST_URL
     * @throws Exception
     * @throws NoSuchAlgorithmException
     */
    @Test
    public void testUpdateChannelWhiteList() throws Exception, NoSuchAlgorithmException {
        LiveUpdateChannelWhiteListRequest liveUpdateChannelWhiteListRequest = new LiveUpdateChannelWhiteListRequest();
        Boolean liveUpdateChannelWhiteListResponse;
        try {
            liveUpdateChannelWhiteListRequest.setChannelId(null)
                    .setRank(1)
                    .setOldCode("1605067278063")
                    .setCode("1605067278063")
                    .setName("sadboyChange");
            liveUpdateChannelWhiteListResponse = new LiveWebAuthServiceImpl().updateChannelWhiteList(
                    liveUpdateChannelWhiteListRequest);
            Assert.assertNotNull(liveUpdateChannelWhiteListResponse);
            if (liveUpdateChannelWhiteListResponse) {
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
     * API地址：DELETE_CHANNEL_WHITE_LIST_URL
     * @throws Exception
     * @throws NoSuchAlgorithmException
     */
//    @Test
    public void testDeleteChannelWhiteList() throws Exception, NoSuchAlgorithmException {
        LiveDeleteChannelWhiteListRequest liveDeleteChannelWhiteListRequest = new LiveDeleteChannelWhiteListRequest();
        Boolean liveDeleteChannelWhiteListResponse;
        try {
            liveDeleteChannelWhiteListRequest.setChannelId(null)
                    .setRank(1)
                    .setIsClear("N")
                    .setCode("1605052902421");
            liveDeleteChannelWhiteListResponse = new LiveWebAuthServiceImpl().deleteChannelWhiteList(
                    liveDeleteChannelWhiteListRequest);
            Assert.assertNotNull(liveDeleteChannelWhiteListResponse);
            if (liveDeleteChannelWhiteListResponse) {
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
     * API地址：CHANNEL_AUTH_FIELD_GET_URL
     * @throws Exception
     * @throws NoSuchAlgorithmException
     */
    @Test
    public void testGetChannelAuthField() throws Exception, NoSuchAlgorithmException {
        LiveChannelAuthFieldRequest liveChannelAuthFieldRequest = new LiveChannelAuthFieldRequest();
        LiveChannelAuthFieldResponse liveChannelAuthFieldResponse;
        try {
            liveChannelAuthFieldRequest.setChannelId(createChannel())
                    .setRank(1);
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
     * API地址：CHANNEL_AUTH_INFO_GET_URL
     * @throws Exception
     * @throws NoSuchAlgorithmException
     */
    @Test
    public void testGetChannelAuthInfo() throws Exception, NoSuchAlgorithmException {
        LiveChannelAuthInfoRequest liveChannelAuthInfoRequest = new LiveChannelAuthInfoRequest();
        LiveChannelAuthInfoResponse liveChannelAuthInfoResponse;
        try {
            liveChannelAuthInfoRequest.setChannelId(createChannel());
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
     * 返回：返回的byte[]可以按照单元测试示例进行保存，也可以自行处理。
     * API地址：DOWNLOAD_CHANNEL_AUTH_INFO_URL
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
            String path = getClass().getResource("/file/").getPath() + "downLoad.xlsx";
            liveDownloadChannelAuthInfoRequest.setChannelId(createChannel())
                    .setRank(1);
            liveDownloadChannelAuthInfoResponse = new LiveWebAuthServiceImpl().downloadChannelAuthInfo(
                    liveDownloadChannelAuthInfoRequest);
            Assert.assertNotNull(liveDownloadChannelAuthInfoResponse);
            if (liveDownloadChannelAuthInfoResponse != null) {
                FileUtil.writeFile(liveDownloadChannelAuthInfoResponse, path);
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
     * 测试新增白名单
     * 描述：用于设置频道或全局观看条件中的白名单列表
     * 返回：如导入数据与现有列表数据会员码一致，则会以导入昵称覆盖现有昵称。
     * API地址：UPLOAD_WHITE_LIST_URL
     * @throws Exception
     * @throws NoSuchAlgorithmException
     */
//    @Test
    public void testUploadWhiteList() throws Exception, NoSuchAlgorithmException {
        LiveUploadWhiteListRequest liveUploadWhiteListRequest = new LiveUploadWhiteListRequest();
        Boolean liveUploadWhiteListResponse;
        try {
            //path设置为模板文件路径(已填写完数据)
            String path = getClass().getResource("/file/WhiteListTemplate.xls").getPath();
            liveUploadWhiteListRequest.setChannelId(createChannel())
                    .setRank(1)
                    .setFile(new File(path));
            liveUploadWhiteListResponse = new LiveWebAuthServiceImpl().uploadWhiteList(liveUploadWhiteListRequest);
            Assert.assertTrue(liveUploadWhiteListResponse);
            if (liveUploadWhiteListResponse) {
                //to do something ......
                log.debug("测试新增白名单成功");
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
     * 测试下载频道观看白名单列表
     * 描述：用于下载全局或频道的观看条件白名单列表
     * 返回：返回的byte[]可以按照单元测试示例进行保存，也可以自行处理。
     * API地址：DOWNLOAD_CHANNEL_WHITE_LIST_URL
     * @throws Exception
     * @throws NoSuchAlgorithmException
     */
    @Test
    public void testDownloadChannelWhiteList() throws Exception, NoSuchAlgorithmException {
       LiveDownloadChannelWhiteListRequest liveDownloadChannelWhiteListRequest = new LiveDownloadChannelWhiteListRequest();
        byte[] liveDownloadChannelWhiteListResponse;
        try {
            //path设置为下载文件路径
            String path = getClass().getResource("/file/").getPath() + "downLoadWhiteList.xlsx";
            liveDownloadChannelWhiteListRequest.setChannelId(createChannel())
                    .setRank(1);
            liveDownloadChannelWhiteListResponse = new LiveWebAuthServiceImpl().downloadChannelWhiteList(
                    liveDownloadChannelWhiteListRequest);
            Assert.assertNotNull(liveDownloadChannelWhiteListResponse);
            if (liveDownloadChannelWhiteListResponse != null) {
                FileUtil.writeFile(liveDownloadChannelWhiteListResponse, path);
                //to do something ......
                log.debug("测试下载频道观看白名单列表成功, 文件长度 {}", liveDownloadChannelWhiteListResponse.length);
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
