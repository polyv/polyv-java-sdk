package net.polyv.doc;

import java.util.ArrayList;
import java.util.List;

import com.sun.javadoc.ClassDoc;
import com.sun.javadoc.MethodDoc;
import com.sun.javadoc.RootDoc;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * @author: sadboy
 **/
public class JavaDocUtil {
    
    private static RootDoc root;
    
    // 一个简单Doclet,收到 RootDoc对象保存起来供后续使用
    // 参见参考资料6
    public static class Doclet {
        
        public Doclet() {
        }
        
        public static boolean start(RootDoc root) {
            JavaDocUtil.root = root;
            return true;
        }
    }
    
    // 显示DocRoot中的基本信息
    public static void show() {
        ClassDoc[] classes = root.classes();
        for (int i = 0; i < classes.length; ++i) {
            System.out.println("=====" + classes[i]);
            System.out.println(">>>>>>>>" + classes[i].commentText());
            int j = 0;
            for (MethodDoc method : classes[i].methods()) {
                System.out.printf(j++ + "\t%s\n", method.commentText());
            }
        }
    }
    
    public static RootDoc getRoot() {
        return root;
    }
    
    public JavaDocUtil() {
    
    }
    
    public static PolyvClassDoc getList() {
        ClassDoc[] classes = root.classes();
        PolyvClassDoc polyvClassDoc = new PolyvClassDoc();
        for (int i = 0; i < classes.length; ++i) {
            polyvClassDoc.setTitle(classes[i].commentText());
            System.out.println("=====" + classes[i]);
            System.out.println(">>>>>>>>" + classes[i].commentText());
            int j = 0;
            PolyvMethodDoc polyvMethodDoc;
            List<PolyvMethodDoc> methodDocs = new ArrayList<>();
            for (MethodDoc method : classes[i].methods()) {
                polyvMethodDoc = new PolyvMethodDoc();
                String commentText = method.commentText();
                String[] split = commentText.split("\n");
                System.out.printf(j++ + "\t%s\n", commentText);
                System.out.println(split.length);
                String title = split[0];
                title = title.startsWith("测试") ? title.substring(2) : title;
                polyvMethodDoc.setTitle(title);
                for (int num = 1; num < split.length; num++) {
                    String temp = split[num].trim();
                    if (temp.toLowerCase().contains("todo")) {
                        continue;
                    } else if (temp.startsWith("描述：")) {
                        polyvMethodDoc.setDescription(polyvMethodDoc.getDescription() == null ? temp.substring(3) :
                                polyvMethodDoc.getDescription() + "\n" + temp.substring(3));
                    } else if (temp.startsWith("约束：")) {
                        polyvMethodDoc.setNote(polyvMethodDoc.getNote() == null ? temp.substring(3) :
                                polyvMethodDoc.getNote() + "\n" + temp.substring(3));
                    }
                }
                if (polyvMethodDoc.getDescription() == null) {
                    polyvMethodDoc.setDescription(title);
                }
                methodDocs.add(polyvMethodDoc);
            }
            polyvClassDoc.setMethodDocs(methodDocs);
        }
        return polyvClassDoc;
    }
    
    @Data
@EqualsAndHashCode(callSuper = true)
    @Accessors(chain = true)
    @AllArgsConstructor
    @NoArgsConstructor
    public static class PolyvClassDoc {
        private String title;
        private List<PolyvMethodDoc> methodDocs;
    }
    
    @Data
@EqualsAndHashCode(callSuper = true)
    @Accessors(chain = true)
    @AllArgsConstructor
    @NoArgsConstructor
    public static class PolyvMethodDoc {
        private String title;
        private String description;
        private String note = "";
    }
    
    public static PolyvClassDoc getDoc(String fileName) {
        // 调用com.sun.tools.javadoc.Main执行javadoc,参见 参考资料3
        // javadoc的调用参数，参见 参考资料1
        com.sun.tools.javadoc.Main.execute(
                new String[]{"-doclet", Doclet.class.getName(), "-docletpath", Doclet.class.getResource("/").getPath(),
                        "-classpath", "C:/project/IdeaProject/polyv-java-sdk/polyv-java-live-sdk/target;", fileName});
        return getList();
    }
    
    public static void main(final String... args) throws Exception {
        // 调用com.sun.tools.javadoc.Main执行javadoc,参见 参考资料3
        // javadoc的调用参数，参见 参考资料1
        com.sun.tools.javadoc.Main.execute(
                new String[]{"-doclet", Doclet.class.getName(), "-docletpath", Doclet.class.getResource("/").getPath(),
                        "-classpath", "C:/project/IdeaProject/polyv-java-sdk/polyv-java-live-sdk/target;" +
                        "D:/j/facelog/facelog-main/target/classes;D:/j/facelog/db/target/classes;" +
                        "D:/j/facelog/db/sql2java/lib/swift-annotations-0.14.2.jar",
                        "C:/project/IdeaProject/polyv-java-sdk/polyv-java-live-sdk/src/test/java/net/polyv/live" +
                                "/service/channel/LiveChannelDocImplTest.java"});
        getList();
    }
    
}
