package jingl.wang.security.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by Wang Jinglu on 2017/9/5.
 */
@Controller
public class t {
    @RequestMapping("/")
    @ResponseBody
    public String index() {
        return "index";
    }
}
