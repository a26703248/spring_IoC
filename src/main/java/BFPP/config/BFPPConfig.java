package BFPP.config;

import BFPP.entity.Person;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * ProjectName: sprng_ioc
 * Package: BFPP.config
 * Description:
 *      BeanFactoryPostProcessor: 標準初始化(所有 Bean 以初始化完成並等待建立)後被呼叫
 * @author a0909
 * @version v1.0
 * @create 2023/4/2 - 下午 04:45
 * @since JDK 1.8
 */
@Configuration
@ComponentScan({"BFPP"})
public class BFPPConfig {

    @Bean
    public Person hello(){
        return new Person();
    }

}
