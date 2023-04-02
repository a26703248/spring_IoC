import BDRPP.config.BDRPPConfig;
import BDRPP.entity.Car;
import BFPP.config.BFPPConfig;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * ProjectName: sprng_ioc
 * Package: PACKAGE_NAME
 * Description:
 *
 * @author a0909
 * @version v1.0
 * @create 2023/4/2 - 下午 05:10
 * @since JDK 1.8
 */
public class BDRPPTest {

    @Test
    public void test01(){
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(BDRPPConfig.class);
        Car bean = context.getBean(Car.class);
    }

}
