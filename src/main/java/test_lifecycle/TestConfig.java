package test_lifecycle;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * ProjectName: sprng_ioc
 * Package: test_lifecycle
 * Description:
 *
 * @author a0909
 * @version v1.0
 * @create 2023/4/9 - 下午 01:55
 * @since JDK 1.8
 */
@Configuration
@ComponentScan("test_lifecycle")
public class TestConfig {

    @Bean
    public String hello(){
        return "hello";
    }

}
