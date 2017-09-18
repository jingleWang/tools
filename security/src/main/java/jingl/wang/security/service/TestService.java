package jingl.wang.security.service;

import jingl.wang.security.dao.TestDao;
import jingl.wang.security.entity.TestEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Ben on 17/09/2017.
 */
@Service
public class TestService {

    @Autowired
    private TestDao testDao;

    @Transactional(isolation = Isolation.REPEATABLE_READ)
    public void test() {
        TestEntity entity = testDao.findOne(1);

        entity.setV(entity.getV() + 100);
        testDao.save(entity);
//        int i = 1/0;
        return;
    }
}
