package net.polyv.vod.service.account;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

import net.polyv.vod.entity.account.VodAccountSpaceDataRequest;
import net.polyv.vod.entity.account.VodAccountSpaceDataResponse;

/**
 * @author: thomas
 **/
public interface IVodAccountService {

     VodAccountSpaceDataResponse getAccountSpaceFlow(VodAccountSpaceDataRequest vodAccountSpaceDataRequest)
             throws IOException, NoSuchAlgorithmException;

}
