import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import transactional.config.TransactionalConfig;
import transactional.service.UserService;

/**
 * ProjectName: sprng_ioc
 * Package: PACKAGE_NAME
 * Description:
 *
 * @author a0909
 * @version v1.0
 * @create 2023/4/2 - 下午 04:24
 * @since JDK 1.8
 */
public class TransactionalTest {

    @Test
    public void test01(){
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(TransactionalConfig.class);
        UserService userService = context.getBean(UserService.class);
        userService.insertUser();
    }

}
