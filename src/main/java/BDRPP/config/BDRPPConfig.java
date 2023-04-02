package BDRPP.config;

import BDRPP.entity.Car;
import org.springframework.cache.Cache;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * ProjectName: sprng_ioc
 * Package: BDRPP.config
 * Description:
 *      BeanDefinitionRegistryPostProcessors: 在 Bean 定義值和建立物件之間執行，
 *              執行時機會在 BeanFactoryPostProcessor 之前
 * @author a0909
 * @version v1.0
 * @create 2023/4/2 - 下午 04:59
 * @since JDK 1.8
 */
@Configuration
@ComponentScan({"BDRPP"})
public class BDRPPConfig {

    @Bean
    public Car car(){
        return new Car();
    }

}
