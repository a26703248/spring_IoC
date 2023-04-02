package BDRPP.processor;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.stereotype.Component;

/**
 * ProjectName: sprng_ioc
 * Package: BDRPP.processor
 * Description:
 *
 * @author a0909
 * @version v1.0
 * @create 2023/4/2 - 下午 07:37
 * @since JDK 1.8
 */
@Component
public class MyBFPP implements BeanFactoryPostProcessor {
    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        System.out.println("MyBFPP start ...");
    }
}
