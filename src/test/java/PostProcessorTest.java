import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import test_lifecycle.TestConfig;

/**
 * ProjectName: sprng_ioc
 * Package: PACKAGE_NAME
 * Description:
 *
 * @author a0909
 * @version v1.0
 * @create 2023/4/9 - 下午 02:03
 * @since JDK 1.8
 */
public class PostProcessorTest {

    @Test
    public void test01(){
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(TestConfig.class);
        context.getBean("hello");
    }

}
