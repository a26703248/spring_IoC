package BDRPP.processor;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.stereotype.Component;

/**
 * ProjectName: sprng_ioc
 * Package: BDRPP.processor
 * Description:
 *      BeanDefinitionRegistryPostProcessors
 * @author a0909
 * @version v1.0
 * @create 2023/4/2 - 下午 05:01
 * @since JDK 1.8
 */
@Component
public class MyBeanDefinitionRegistryPostProcessors implements BeanDefinitionRegistryPostProcessor {

    @Override
    public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry) throws BeansException {
        System.out.println("registry: " + registry.getBeanDefinitionCount());
    }

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        System.out.println("BeanFactory: " + beanFactory.getBeanDefinitionCount());
    }
}
