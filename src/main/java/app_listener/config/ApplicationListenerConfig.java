package app_listener.config;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.Aware;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * ProjectName: sprng_ioc
 * Package: app_listener.config
 * Description:
 *
 * @author a0909
 * @version v1.0
 * @create 2023/4/2 - 下午 07:43
 * @since JDK 1.8
 */
@Configuration
@ComponentScan({"app_listener"})
public class ApplicationListenerConfig {
}
