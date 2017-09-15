package jingl.wang.controller;

import jingl.wang.service.ITestService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.jws.WebParam;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Wang Jinglu on 2017/9/6.
 */
@Controller
public class MainController {

    @Resource(name = "name")
    Model model;

    @Resource(name = "testService")
    ITestService testService;

    @RequestMapping("/main")
    @ResponseBody
    public Model index() {
        Map map = new HashMap();
        map.put("name", "wjl");
        return model;
    }

    public class Model {
        public String name;
        public String desc;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }

        @Override
        public String toString() {
            return "Model{" +
                    "name='" + name + '\'' +
                    ", desc='" + desc + '\'' +
                    '}';
        }
    }
}
