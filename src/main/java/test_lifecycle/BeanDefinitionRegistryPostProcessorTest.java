package test_lifecycle;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.stereotype.Component;

/**
 * ProjectName: sprng_ioc
 * Package: test_lifecycle
 * Description:
 *
 * @author a0909
 * @version v1.0
 * @create 2023/4/9 - 下午 01:59
 * @since JDK 1.8
 */
@Component
public class BeanDefinitionRegistryPostProcessorTest implements BeanDefinitionRegistryPostProcessor {
    @Override
    public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry) throws BeansException {
        System.out.println("BeanDefinitionRegistryPostProcessor");
    }

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        System.out.println("BeanDefinitionRegistryPostProcessor postProcessBeanFactory");
    }
}
