package app_listener.service;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

/**
 * ProjectName: sprng_ioc
 * Package: app_listener.service
 * Description:
 *
 * @author a0909
 * @version v1.0
 * @create 2023/4/2 - 下午 08:08
 * @since JDK 1.8
 */
@Service
public class UserService {

    @EventListener(classes = {ApplicationEvent.class})
    public void listener(ApplicationEvent event){
        System.out.println("UserService 監聽事件: " + event);
    }

}
