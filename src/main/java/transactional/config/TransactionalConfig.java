package transactional.config;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.beans.PropertyVetoException;

/**
 * ProjectName: sprng_ioc
 * Package: transactional.config
 * Description:
 *      加入事務管理功能條件
 *      1. @EnableTransactionManagement 開啟事務管理
 *      2. 配置 PlatformTransactionManager 事務管理器並傳入資料來源
 *      3. @Transactional 加至需事務管理方法上面
 *
 * @author a0909
 * @version v1.0
 * @create 2023/4/2 - 下午 04:07
 * @since JDK 1.8
 */
@Configuration
@ComponentScan({"transactional"})
@EnableTransactionManagement // 開啟事務管理器功能
public class TransactionalConfig {

    @Bean
    public DataSource dataSource() throws PropertyVetoException {
        ComboPooledDataSource dataSource = new ComboPooledDataSource();
        dataSource.setJdbcUrl("jdbc:mysql://localhost:3306/testdb");
        dataSource.setUser("root");
        dataSource.setPassword("a0909007892");
        dataSource.setDriverClass("com.mysql.jdbc.Driver");
        return dataSource;
    }

    @Bean
    public JdbcTemplate jdbcTemplate() throws PropertyVetoException {
        // Spring 中針對 Configuration 設定檔中若是直接呼叫方法則會從容器中尋找
        return new JdbcTemplate(dataSource());
    }

    @Bean
    public PlatformTransactionManager tx() throws PropertyVetoException {
        return new DataSourceTransactionManager(dataSource());
    }

}
