package app_listener.listener;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * ProjectName: sprng_ioc
 * Package: app_listener.listener
 * Description:
 *
 * @author a0909
 * @version v1.0
 * @create 2023/4/2 - 下午 07:46
 * @since JDK 1.8
 */
@Component
public class MyApplicationListener implements ApplicationListener<ApplicationEvent> {
    @Override
    public void onApplicationEvent(ApplicationEvent event) {
        System.out.println("收到事件: " + event);
    }
}
