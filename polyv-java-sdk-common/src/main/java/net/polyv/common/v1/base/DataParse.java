package net.polyv.common.v1.base;

import java.io.IOException;

import org.apache.http.HttpEntity;

/**
 * 公共数据解析接口
 * @param <T>
 */
public interface DataParse<T> {
    
    T parseData(HttpEntity httpEntity, String encoding) throws IOException;
    
}
