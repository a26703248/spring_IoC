package transactional.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import transactional.dao.UserDao;

/**
 * ProjectName: sprng_ioc
 * Package: transactional.service
 * Description:
 *
 * @author a0909
 * @version v1.0
 * @create 2023/4/2 - 下午 04:12
 * @since JDK 1.8
 */
@Service
public class UserService {

    @Autowired
    private UserDao userDao;

    @Transactional
    public void insertUser(){
        userDao.insert();
        System.out.println("insert OK ...");
        int i = 10/0;
    }

}
