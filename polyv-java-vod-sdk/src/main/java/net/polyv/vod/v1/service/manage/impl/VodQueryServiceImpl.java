package net.polyv.vod.v1.service.manage.impl;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

import net.polyv.vod.v1.constant.VodURL;
import net.polyv.vod.v1.entity.manage.query.VodQueryVideoListRequest;
import net.polyv.vod.v1.entity.manage.query.VodQueryVideoListResponse;
import net.polyv.vod.v1.service.VodBaseService;
import net.polyv.vod.v1.service.manage.IVodQueryService;

/**
 * @author: sadboy
 **/
public class VodQueryServiceImpl extends VodBaseService implements IVodQueryService {
    
    @Override
    public VodQueryVideoListResponse queryVideoList(VodQueryVideoListRequest vodQueryVideoListRequest)
            throws IOException, NoSuchAlgorithmException {
        String url = VodURL.getRealUrl(VodURL.QUERY_VIDEO_LIST_URL);
        return super.getReturnOne(url,vodQueryVideoListRequest,VodQueryVideoListResponse.class);
    }
}
