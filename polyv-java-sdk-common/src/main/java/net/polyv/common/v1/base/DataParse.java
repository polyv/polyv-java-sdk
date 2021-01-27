package net.polyv.common.v1.base;

import java.io.IOException;

import org.apache.http.HttpEntity;

/**
 * 公共数据解析接口
 * @param <T>
 */
public interface DataParse<T> {
    /**
     *  解析返回数据
     * @param httpEntity 返回实体
     * @param encoding 编码
     * @return 实际解析返回内容
     * @throws IOException io异常
     */
    T parseData(HttpEntity httpEntity, String encoding) throws IOException;
    
}
