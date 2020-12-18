package net.polyv.common.v1.base;

import net.polyv.common.v1.util.StringUtils;

/**
 * SDK处理工具类
 * @author: sadboy
 **/
public class SDKUtil {
    
    /**
     * 判断version与SDK版本的大小，version较小返回-1，相等返回0，version较大返回1
     * @param version
     * @return
     */
    public static int compareVersion(String version,String currVersion ) {
        if (StringUtils.isNotBlank(version)) {
            if (StringUtils.isBlank(currVersion)) {
                return 1;
            }
            String[] currVersions = currVersion.split("\\.");
            String[] versions = version.split("\\.");
            int minLength = Math.min(currVersions.length, versions.length);
            for (int i = 0; i < minLength; i++) {
                if (Integer.parseInt(versions[i]) > Integer.parseInt(currVersions[i])) {
                    return 1;
                }
                if (Integer.parseInt(versions[i]) < Integer.parseInt(currVersions[i])) {
                    return -1;
                }
            }
            if (currVersions.length > versions.length) {
                return -1;
            }
            if (currVersions.length < versions.length) {
                return 1;
            }
            return 0;
        }
        return -1;
    }
    
    
}
