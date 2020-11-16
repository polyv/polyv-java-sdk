package net.polyv.vod.v1.constant;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: thomas
 **/
public class VodConstant {
    public  static  final Integer ERROR_CODE = 400;
    
    private static  final Map<String,String> ERROR_CODE_MAP = new HashMap();
    
    static{
        ERROR_CODE_MAP.put("0","无错误");
        ERROR_CODE_MAP.put("1","找不到writetoken关联的user");
        ERROR_CODE_MAP.put("2","文件为空或者writetoken为空");
        ERROR_CODE_MAP.put("3","提交的json名字JSONRPC为null");
        ERROR_CODE_MAP.put("4","提交文件格式不正确");
        ERROR_CODE_MAP.put("5","readtoken为空");
        ERROR_CODE_MAP.put("6","分页输入出错");
        ERROR_CODE_MAP.put("7","vid不能为空");
        ERROR_CODE_MAP.put("8","找不到方法名");
        ERROR_CODE_MAP.put("10","userid不能为空");
        ERROR_CODE_MAP.put("11","上传目录为空");
        ERROR_CODE_MAP.put("12","远程URL文件不能访问");
        ERROR_CODE_MAP.put("13","远程视频文件格式无法识别");
        ERROR_CODE_MAP.put("15","参数异常");
        ERROR_CODE_MAP.put("16","空间已满");
        ERROR_CODE_MAP.put("17","用户无接口权限");
        ERROR_CODE_MAP.put("18","标题重复");
        ERROR_CODE_MAP.put("19","标题为空");
        ERROR_CODE_MAP.put("20","播放列表不存在");
        ERROR_CODE_MAP.put("21","参数错误");
        ERROR_CODE_MAP.put("22","参数签名错误");
        ERROR_CODE_MAP.put("23","视频不存在");
        ERROR_CODE_MAP.put("24","时间戳已过期");
    }
    public enum ErrorCode{
 	
    }
}
