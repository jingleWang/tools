package jingl.wang.listener;

import jingl.wang.controller.MainController;
import jingl.wang.service.ITestService;
import jingl.wang.utils.SpringObjectUtil;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 * Created by Wang Jinglu on 2017/9/13.
 */
//@WebListener
//@Component
public class TestListener implements ApplicationContextAware,ServletContextListener {
    private static ApplicationContext context;

    public void contextInitialized(ServletContextEvent servletContextEvent) {
        context = WebApplicationContextUtils.getWebApplicationContext(servletContextEvent.getServletContext());
//        MainController.Model model = (MainController.Model) context.getBean("name");
//        ITestService testService = (ITestService) context.getBean("testService");
//        testService.test();
        System.out.println("load context");
    }

    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }

    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        context = applicationContext;
    }
}
