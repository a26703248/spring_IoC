package BFPP.processor;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * ProjectName: sprng_ioc
 * Package: BFPP.processor
 * Description:
 *
 * @author a0909
 * @version v1.0
 * @create 2023/4/2 - 下午 04:48
 * @since JDK 1.8
 */
@Component
public class MyBeanFactoryPostProcessor implements BeanFactoryPostProcessor {
    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        System.out.println("MyBeanFactoryPostProcessor start ...");
        int count = beanFactory.getBeanDefinitionCount();
        String[] beans = beanFactory.getBeanDefinitionNames();
        System.out.println("count: " + count);
        System.out.println(Arrays.asList(beans));
    }
}
