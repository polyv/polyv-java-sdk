package net.polyv.common.util;

import java.io.IOException;

import org.apache.commons.lang3.StringUtils;

import com.alibaba.fastjson.JSON;

import cn.hutool.core.io.file.FileReader;
import lombok.extern.slf4j.Slf4j;
import net.polyv.common.entity.AccountInfo;

/**
 * @author: thomas
 **/
@Slf4j
public class FileUtil {
    /**
     * 私有构造方法，防止类的实例化，因为工具类不需要实例化。
     */
    private FileUtil() {
    
    }
    
    /**
     * 读取文件内容
     * @param path 文件绝对路径
     * @return
     */
    public static String readFile(String path) throws IOException {
        String encoding = "UTF-8";
        FileReader fileReader = new FileReader(path);
        String result = fileReader.readString();
        return result;
    }
    
    /**
     * 初始化配置
     */
    public static AccountInfo readConfigFromFile(String path) {
        if (StringUtils.isBlank(path)) {
            path = "/data/password/password.txt";
        }
        String accountInfo = null;
        try {
            accountInfo = FileUtil.readFile(path);
        } catch (IOException e) {
            log.error("读取文件错误，文件路径 {}", path, e);
            return null;
        }
        if (StringUtils.isNotBlank(accountInfo)) {
            return JSON.parseObject(accountInfo, AccountInfo.class);
        } else {
            log.error("文件内容为空，文件路径 {}",path);
            return null;
        }
    }
    
}