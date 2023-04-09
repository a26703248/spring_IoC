package test_lifecycle;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

/**
 * ProjectName: sprng_ioc
 * Package: test_lifecycle
 * Description:
 *
 * @author a0909
 * @version v1.0
 * @create 2023/4/9 - 下午 01:56
 * @since JDK 1.8
 */
@Component
public class BeanPostProcessorTest implements BeanPostProcessor {

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("BeanPostProcessor before");
        return BeanPostProcessor.super.postProcessBeforeInitialization(bean, beanName);
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("BeanPostProcessor after");
        return BeanPostProcessor.super.postProcessAfterInitialization(bean, beanName);
    }
}
