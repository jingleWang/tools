package jingl.wang.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by Ben on 20/09/2017.
 */
@Configuration
public class Data {
    @Bean(name = "test")
    public String getTest() {
        return "test";
    }
}
