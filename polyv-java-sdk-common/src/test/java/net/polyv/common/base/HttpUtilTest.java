package net.polyv.common.base;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;

import com.alibaba.fastjson.JSON;

/**
 * @author: thomas
 **/

public class HttpUtilTest {

//    public HttpUtilTest() {
//        new Timer("timer - check ").schedule(new TimerTask() {
//
//            @Override
//            public void run() {
//                System.out.println("-------线程池运行情况监控--------");
//                HttpClientUtil.getHttpClient();
//                PoolStats poolStats = HttpClientUtil.getManager().getTotalStats();
//                int availNum = poolStats.getAvailable();
//                int leasedNum = poolStats.getLeased();
//                int maxNum = poolStats.getMax();
//                int pendingNum = poolStats.getPending();
//
//                System.out.println("availNum =  " + availNum);
//                System.out.println("leasedNum =  " + leasedNum);
//                System.out.println("maxNum =  " + maxNum);
//                System.out.println("pendingNum =  " + pendingNum);
//                System.out.println("-------------------");
//            }
//        }, 0, 1000);
//    }
    
    /**
     * 全局初始化工作
     */
    public HttpUtilTest() {
        HttpClientUtil.setTimeOut(10000);
        HttpClientUtil.init();
        System.out.println("--初始化完成--");
    }
    
//    @Test
//    public void testHttpPool() throws Exception {
//        for (int i = 0; i < 100; i++) {
//            new Thread() {
//                @Override
//                public void run() {
//                    String url = "https://www.w3school.com.cn/index.html";
//                    url = "http://47.115.173.234:8001/get";
//                    String encoding = "utf-8";
//                    String data = null;
//                    try {
//                        data = HttpUtil.sendGetData(url, encoding);
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    }
//                    Assert.assertNotNull(data);
//                    System.out.println(data);
//                }
//            }.start();
//
//        }
//        Thread.sleep(40000);
//
//    }
    
    /**
     * 测试 http 个体请求
     * @throws Exception
     */
   
    public void testHttpGet() throws Exception {
        String url = "https://www.w3school.com.cn/index.html";
        url = "http://47.115.173.234:8001/get";
        String encoding = "utf-8";
        String data = HttpUtil.sendGetData(url, encoding);
        Assert.assertNotNull(data);
        System.out.println(data);
    }
    
    
    /**
     * 测试post 表单提交
     */
     
    public void testPostByMap() throws IOException {
        String url = "http://47.115.173.234:8001/post";
        Map<String, String> param = new HashMap<String, String>();
        param.put("name", "thomas");
        param.put("age", "23");
        String data = HttpUtil.sendPostDataByMap(url, param, "utf-8");
        System.out.println(data);
        
    }
    
    /**
     * 测试 json post 请求
     */
   
    public void testPostByJson() throws IOException {
        String url = "http://47.115.173.234:8001/post2";
        List<String> list = new ArrayList<>();
        list.add("hello");
        list.add("thomas");
        list.add("thomas is good man");
        String data = HttpUtil.sendPostDataByJson(url, JSON.toJSONString(list), "utf-8");
        System.out.println(data);
    }

 
    
}
