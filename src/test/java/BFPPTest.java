import BFPP.config.BFPPConfig;
import BFPP.entity.Person;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import transactional.config.TransactionalConfig;

/**
 * ProjectName: sprng_ioc
 * Package: PACKAGE_NAME
 * Description:
 *
 * @author a0909
 * @version v1.0
 * @create 2023/4/2 - 下午 04:51
 * @since JDK 1.8
 */
public class BFPPTest {

    @Test
    public void test01(){
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(BFPPConfig.class);

        Person bean = context.getBean(Person.class);

    }

}
