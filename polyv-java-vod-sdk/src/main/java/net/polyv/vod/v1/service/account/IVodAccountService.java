package net.polyv.vod.v1.service.account;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

import net.polyv.vod.v1.entity.account.VodAccountSpaceDataRequest;
import net.polyv.vod.v1.entity.account.VodAccountSpaceDataResponse;

/**
 * @author: thomas
 **/
public interface IVodAccountService {
     /**
      * 获取用户空间及流量情况 , API地址：https://dev.polyv.net/2013/videoproduct/v-api/v-api-main/getspaceandflow/
      * @param vodAccountSpaceDataRequest 获取用户空间及流量情况请求实体
      * @return 获取用户空间及流量详细数据
      * @throws IOException IO异常
      * @throws NoSuchAlgorithmException 签名算法异常
      */
     VodAccountSpaceDataResponse getAccountSpaceFlow(VodAccountSpaceDataRequest vodAccountSpaceDataRequest)
             throws IOException, NoSuchAlgorithmException;

}
