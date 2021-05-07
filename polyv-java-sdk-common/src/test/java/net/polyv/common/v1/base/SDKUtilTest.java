package net.polyv.common.v1.base;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author: sadboy
 **/
public class SDKUtilTest {
    
    @Test
    public void testVersionCompare(){
        Assert.assertTrue(SDKUtil.compareVersion(null, null) == -1);
        Assert.assertTrue(SDKUtil.compareVersion("", "") == -1);
        Assert.assertTrue(SDKUtil.compareVersion("", "1") == -1);
        Assert.assertTrue(SDKUtil.compareVersion("1.0.0", "1.0.1") == -1);
        Assert.assertTrue(SDKUtil.compareVersion("1.0.0", "1.0.0.1") == -1);
        Assert.assertTrue(SDKUtil.compareVersion("1.0.0", "1.0.0.0") == -1);
        Assert.assertTrue(SDKUtil.compareVersion("1.0.0", "1.1.1") == -1);
        Assert.assertTrue(SDKUtil.compareVersion("1.10.1", "2.0.0") == -1);
        Assert.assertTrue(SDKUtil.compareVersion("1.0.0", "2.1.1") == -1);
        Assert.assertTrue(SDKUtil.compareVersion("1.35.26", "2.1.1") == -1);
    
        Assert.assertTrue(SDKUtil.compareVersion("1", null) == 1);
        Assert.assertTrue(SDKUtil.compareVersion("2", "") == 1);
        Assert.assertTrue(SDKUtil.compareVersion("2", "1") == 1);
        Assert.assertTrue(SDKUtil.compareVersion("1.0.1", "1.0.0") == 1);
        Assert.assertTrue(SDKUtil.compareVersion("1.0.0.1", "1.0.0") == 1);
        Assert.assertTrue(SDKUtil.compareVersion("1.0.0.0", "1.0.0") == 1);
        Assert.assertTrue(SDKUtil.compareVersion("1.1.1", "1.0.0") == 1);
        Assert.assertTrue(SDKUtil.compareVersion("2.0.0", "1.10.1") == 1);
        Assert.assertTrue(SDKUtil.compareVersion("2.1.1", "1.0.0") == 1);
        Assert.assertTrue(SDKUtil.compareVersion("2.1.1", "1.35.26") == 1);
        
        Assert.assertTrue( SDKUtil.compareVersion("2.1.0", "2.1.0")== 0);
        Assert.assertTrue( SDKUtil.compareVersion("2.1", "2.1")== 0);
        Assert.assertTrue( SDKUtil.compareVersion("2", "2")== 0);
        Assert.assertTrue( SDKUtil.compareVersion("0", "0")== 0);
    }
    
}
