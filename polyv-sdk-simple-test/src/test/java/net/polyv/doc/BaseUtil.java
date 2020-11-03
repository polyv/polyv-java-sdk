package net.polyv.doc;

import java.io.IOException;
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
    
    public static String lowerFirst(String oldStr) {
        char[] chars = oldStr.toCharArray();
        chars[0] += 32;
        return String.valueOf(chars);
    }
    
    
    protected static String menuMdDemo = "";
    
    protected static String functionMdDemo = "";
    
    static {
        String barPath = BaseUtil.class.getResource("/doc/_slidebar.md").getPath();
        String funPath = BaseUtil.class.getResource("/doc/function_index.md").getPath();
        try {
            menuMdDemo = DocUtil.readFileString(barPath);
            functionMdDemo = DocUtil.readFileString(funPath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
