package jingl.wang.service;

import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

/**
 * Created by Wang Jinglu on 2017/9/13.
 */
@Service("testService")
public class TestService implements ITestService {

    @PostConstruct
    public void init() {
        System.out.println("test Service inited");
    }
    public void test() {
        return;
    }
}
