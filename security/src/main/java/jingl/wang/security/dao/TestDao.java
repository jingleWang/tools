package jingl.wang.security.dao;


import jingl.wang.security.entity.TestEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * Created by Ben on 17/09/2017.
 */
public interface TestDao extends JpaRepository<TestEntity, Integer> {
    @Query("from test where id = :id")
    TestEntity findForUpdate(@Param("id") int id);
}
