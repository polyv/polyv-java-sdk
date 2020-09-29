package net.polyv.live;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * App入口程序
 * @author: thomas
 * @date: 2020/9/23
 **/
public class App {

    private static final Logger log = LoggerFactory.getLogger(App.class);

    public static void main(String[] args) {
        log.info("Polyv Live Sdk Version --info 1.4.0");
        log.debug("Polyv Live Sdk Version --debug 1.4.0");
    }

}
