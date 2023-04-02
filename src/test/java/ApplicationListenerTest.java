import BDRPP.config.BDRPPConfig;
import app_listener.config.ApplicationListenerConfig;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Collection;

/**
 * ProjectName: sprng_ioc
 * Package: PACKAGE_NAME
 * Description:
 *      ApplicationListener 為事件驅動介面物件泛型為須監聽事件
 *      ，其監聽物件必須繼承 ApplicationEvent
 *      監聽工作原理
 *
 * @author a0909
 * @version v1.0
 * @create 2023/4/2 - 下午 07:43
 * @since JDK 1.8
 */
public class ApplicationListenerTest {

    @Test
    public void test01(){
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(ApplicationListenerConfig.class);
        context.publishEvent(new ApplicationEvent(new String("my event")) {
        });

        context.close();
    }

}
