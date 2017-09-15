package jingl.wang.utils;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * Created by Wang Jinglu on 2017/9/13.
 */
//@Component
public class SpringObjectUtil implements ApplicationContextAware {

    private static  ApplicationContext context;
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        context = applicationContext;
    }

    public static Object getBean(String name) {
        return context.getBean(name);
    }
}
