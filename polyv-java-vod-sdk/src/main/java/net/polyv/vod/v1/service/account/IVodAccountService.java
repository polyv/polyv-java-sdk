package net.polyv.vod.v1.service.account;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

import net.polyv.vod.v1.entity.account.VodAccountSpaceDataRequest;
import net.polyv.vod.v1.entity.account.VodAccountSpaceDataResponse;

/**
 * @author: thomas
 **/
public interface IVodAccountService {

     VodAccountSpaceDataResponse getAccountSpaceFlow(VodAccountSpaceDataRequest vodAccountSpaceDataRequest)
             throws IOException, NoSuchAlgorithmException;

}
