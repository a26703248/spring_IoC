package transactional.dao;

import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.UUID;

/**
 * ProjectName: sprng_ioc
 * Package: transactional.dao
 * Description:
 *
 * @author a0909
 * @version v1.0
 * @create 2023/4/2 - 下午 04:12
 * @since JDK 1.8
 */
@Repository
public class UserDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void insert(){
        String sql = "insert into `tbl_user`(name, age) values(?, ?);";
        String name = UUID.randomUUID().toString().substring(0, 5);
        jdbcTemplate.update(sql, name, 19);
    }

}
