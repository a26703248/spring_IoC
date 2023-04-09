package test_lifecycle;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.stereotype.Component;

/**
 * ProjectName: sprng_ioc
 * Package: test_lifecycle
 * Description:
 *
 * @author a0909
 * @version v1.0
 * @create 2023/4/9 - 下午 02:02
 * @since JDK 1.8
 */
@Component
public class BeanFactoryPostProcessorTest implements BeanFactoryPostProcessor {
    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        System.out.println("BeanFactoryPostProcessor postProcessBeanFactory");
    }
}
