package net.polyv.vod.service;

import lombok.extern.slf4j.Slf4j;
import net.polyv.vod.config.InitConfig;

/**
 * @author: thomas
 **/
@Slf4j
public class BaseTest {
    /**
     * 系统默认初始化
     */
    BaseTest() {
        InitConfig.initPolyvVodByFile(null);
    }
    
   
    
    
}
