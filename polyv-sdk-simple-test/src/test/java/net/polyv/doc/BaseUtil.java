package net.polyv.doc;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.polyv.live.service.interact.impl.LiveQuestionnaireServiceImpl;

/**
 * @author: sadboy
 **/
public class BaseUtil {
    //Junit测试类的路径，用来找源码
    protected static final String JUNIT_PATH =
            "C:\\project\\IdeaProject\\polyv-java-sdk\\polyv-java-live-sdk\\src" + "\\test\\java\\";
    
    //生成文档所在文件，不存在则自动创建
    protected static String filePath = "C:\\project\\IdeaProject\\polyv-java-sdk\\docs\\live\\";
    
    protected static Map<String, List<String>> functionMap = new HashMap<>();
    
    //Junit测试类全路径
    private static String JUNIT_CLASS_NAME = "";
    
    protected static String getJunitClassName() {
        //        JUNIT_CLASS_NAME="net.polyv.live.service.chat.LiveChatRoomServiceImplTest";
        JUNIT_CLASS_NAME =
                "net.polyv.live.service.interact." + LiveQuestionnaireServiceImpl.class.getSimpleName() + "Test";
//        JUNIT_CLASS_NAME = "net.polyv.live.service.interact." + LiveCheckinServiceImpl.class.getSimpleName() + "Test";
//        JUNIT_CLASS_NAME= "net.polyv.live.service.interact."+LiveAnswerRecordServiceImpl.class.getSimpleName()+"Test";
//        JUNIT_CLASS_NAME= "net.polyv.live.service.player."+ LivePlayerServiceImpl.class.getSimpleName()+"Test";
        JUNIT_CLASS_NAME = "net.polyv.live.service.channel.LiveChannelOperateImplTest";
        JUNIT_CLASS_NAME = "net.polyv.live.service.channel.LiveChannelPlaybackImplTest";
        JUNIT_CLASS_NAME = "net.polyv.live.service.channel.LiveChannelStateImplTest";
        JUNIT_CLASS_NAME = "net.polyv.live.service.channel.LiveChannelViewdataImplTest";
        JUNIT_CLASS_NAME = "net.polyv.live.service.web.LiveWebAuthImplTest";
        JUNIT_CLASS_NAME = "net.polyv.live.service.web.LiveWebInfoImplTest";
        JUNIT_CLASS_NAME = "net.polyv.live.service.web.LiveWebInteractImplTest";
        JUNIT_CLASS_NAME = "net.polyv.live.service.web.LiveWebMenuImplTest";
        JUNIT_CLASS_NAME = "net.polyv.live.service.web.LiveWebSettingImplTest";
        return JUNIT_CLASS_NAME;
    }
    
    public static String lowerFirst(String oldStr){
        
        char[]chars = oldStr.toCharArray();
        
        chars[0] += 32;
        
        return String.valueOf(chars);
        
    }
    
    protected static String menuMdDemo = "* 产品介绍\n" + "\n" + "  * [简介](/)\n" + "  * [全局约束](/limit)\n" +
            "  * [快速接入](/quick_start)\n" + "  * [功能索引](/function_index)\n" + "* 频道管理\n" + "\n" + "  * [账户管理]()\n" +
            "  * [频道操作]()\n" + "  * [直播状态]()\n" + "  * [录制回放]()\n" + "  * [观看数据]()\n" + "  * [文档管理]()\n" + "* 观看管理\n" +
            "  \n" + "  * [快捷设置]()\n" + "  * [页面信息]()\n" + "  * [页面观看条件]()\n" + "  * [页面菜单]()\n" + "  * [页面互动]()\n" +
            "* 直播互动\n" + "    \n" + "  * [答题卡管理]()\n" + "  * [签到管理]()\n" + "  * [问卷管理]()\n" + "  * [抽奖管理]()\n" +
            "* 聊天室\n" + "\n" + "  * [聊天室]()\n" + "  * []()\n" + "  * []()\n" + "  * []()\n" + "  * []()\n" + "* 播放器\n" +
            "\n" + "  * [播放器]()\n" + "* 常见问题\n" + "\n" + "  * []()\n" + "  * []()\n" + "  * []()\n" + "  * []()\n" +
            "  * []()\n" + "  * []()";
    
    protected static String functionMdDemo = "| 功能列表    | 子功能        | API列表                                    | 备注 " +
            "|\n" +
            "| ----------- | ------------- | ------------------------------------------ | ---- |\n" +
            "| ▼账户管理    |               |                                            |      |\n" +
            "| ▼频道管理    |               |                                            |      |\n" +
            "|             | ▼频道操作      |                                            |      |\n" +
            "|             | ▼直播状态      |                                            |      |\n" +
            "|             | ▼录制回放      |                                            |      |\n" +
            "|             | ▼观看数据      |                                            |      |\n" +
            "|             | ▼文档管理      |                                            |      |\n" +
            "| ▼Web观看页  |                |                                            |      |\n" +
            "|             | ▼快捷设置      |                                            |      |\n" +
            "|             | ▼页面信息      |                                            |      |\n" +
            "|             | ▼页面观看条件   |                                            |      |\n" +
            "|             | ▼页面菜单      |                                            |      |\n" +
            "|             | ▼页面互动      |                                            |      |\n" +
            "| ▼直播互动    |               |                                            |      |\n" +
            "|             | ▼答题卡管理    |                                            |      |\n" +
            "|             | ▼签到管理      |                                            |      |\n" +
            "|             | ▼问卷管理      |                                            |      |\n" +
            "|             | ▼抽奖管理      |                                            |      |\n" +
            "| ▼聊天室      |               |                                            |      |\n" +
            "| ▼直播播放器  |               |                                            |      |\n" +
            "|             | ▼播放器        |                                            |      |";
    
}
