package com.pgs.hadoopExample.pig;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


public class PigApp {

    private static final Log log = LogFactory.getLog(PigApp.class);

    public static void main(String[] args) throws Exception {
        AbstractApplicationContext context = new ClassPathXmlApplicationContext(
                "/META-INF/spring/pig-context.xml", PigApp.class);
        log.info("Pig Application Running");
        context.registerShutdownHook();
    }
}
