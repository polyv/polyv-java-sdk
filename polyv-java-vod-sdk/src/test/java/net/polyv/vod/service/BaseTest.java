package net.polyv.vod.service;

import java.util.Calendar;
import java.util.Date;

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
    
    
    /**
     * 获取Date对象
     * @param year 年
     * @param month 月
     * @param day 日
     * @param time 时分秒整形数组
     * @return
     */
    public Date getDate(int year, int month,int day, int... time) {
        Calendar instance = Calendar.getInstance();
        instance.set(year,month,day);
        if(time.length>0){
            instance.set(Calendar.HOUR_OF_DAY,time[0]);
        }
        if(time.length>1){
            instance.set(Calendar.MINUTE,time[1]);
        }
        if(time.length>2){
            instance.set(Calendar.SECOND,time[2]);
        }
        return instance.getTime();
    }
    
    /**
     * 获取Date对象
     * @param  timestamp 时间戳
     * @return
     */
    public Date getDate(Long timestamp) {
        Calendar instance = Calendar.getInstance();
        instance.setTimeInMillis(timestamp);
        return instance.getTime();
    }
    
    
}
