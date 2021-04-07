package net.polyv.vod.v1.upload.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang3.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 时间转换工具
 */
public class DateTimeUtil {
    
    private static final Logger logger = LoggerFactory.getLogger(DateTimeUtil.class);
    
    /**
     * convert utc date to est date
     */
    public static Date utcDate2EstDate(String dateStr) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
            Date date = sdf.parse(dateStr);
            return DateUtils.addHours(date, 8);
        } catch (ParseException e) {
            logger.error(e.getMessage(), e);
        }
        return null;
    }
}
