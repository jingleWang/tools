package jingl.wang.security.controller;

import jingl.wang.security.dao.TestDao;
import jingl.wang.security.entity.TestEntity;
import jingl.wang.security.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.PostConstruct;

/**
 * Created by Wang Jinglu on 2017/9/5.
 */
@Controller
public class t {
    @RequestMapping("/")
    @ResponseBody
    public String index() {
        testService.test();
        return "index";
    }

    @Autowired
    TestService testService;

    @PostConstruct
    public void init() {

    }

}
