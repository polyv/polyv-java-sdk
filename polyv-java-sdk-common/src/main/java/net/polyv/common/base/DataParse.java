package net.polyv.common.base;

import java.io.IOException;

import org.apache.http.HttpEntity;

/**
 * @author: thomas
 **/
public interface DataParse<T> {
     T parseData(HttpEntity httpEntity ,String encoding) throws IOException;
}
