//package jingl.wang.security.config;
//
//import com.alibaba.druid.pool.DruidDataSource;
//import org.mybatis.spring.SqlSessionFactoryBean;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
//
//import javax.annotation.Resource;
//import javax.sql.DataSource;
//import java.util.ArrayList;
//import java.util.List;
//
///**
// * Created by Ben on 17/09/2017.
// */
//@Configuration
//public class DataConfig {
//    @Bean(name="dataSource",initMethod ="init",destroyMethod = "close")
//    public DataSource dataSource(){
//        DruidDataSource druidDataSource = new DruidDataSource();
//        //基本属性
//        druidDataSource.setUrl("jdbc:mysql://jingl.wang:3306/dbtest");
//        druidDataSource.setUsername("ben");
//        druidDataSource.setPassword("311258");
//        //配置初始化大小，最小，最大
//        druidDataSource.setInitialSize(5);
//        druidDataSource.setMinIdle(5);
//        druidDataSource.setMaxActive(100);
//        druidDataSource.setValidationQuery("SELECT 'x'");
//        //配置获取连接超时
//        druidDataSource.setMaxWait(60000);
//        //间隔多久一次检测
//        druidDataSource.setTimeBetweenEvictionRunsMillis(60000);
//        //连接在连接池中的最小生存时间，单位毫秒
//        druidDataSource.setMinEvictableIdleTimeMillis(300000);
//        druidDataSource.setTestWhileIdle(true);
//        druidDataSource.setTestOnBorrow(false);
//        druidDataSource.setTestOnReturn(false);
//        druidDataSource.setRemoveAbandoned(true);
//        druidDataSource.setRemoveAbandonedTimeout(60);
//        List list = new ArrayList();
//        list.add("set names utf8mb4");
//        druidDataSource.setConnectionInitSqls(list);
//        return druidDataSource;
//    }
//
//    @Bean
//    public SqlSessionFactoryBean sqlSessionFactory(@Qualifier("dataSource") DataSource dataSource) throws Exception{
//        //配置对象
//        org.apache.ibatis.session.Configuration config = new org.apache.ibatis.session.Configuration();
//        //开启驼峰规则映射
//        config.setMapUnderscoreToCamelCase(true);
//        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
//        sqlSessionFactoryBean.setTypeAliasesPackage("jingl.wang.security.dao"); //注册后可以直接使用类名，而不用使用全限定的类名（就是不用包含包名）
//        sqlSessionFactoryBean.setConfiguration(config);
//        sqlSessionFactoryBean.setDataSource(dataSource);
//        //需要spring的Resouce对象，使用查找器通过路径获取
//        sqlSessionFactoryBean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:mapper/*.xml"));
//        return sqlSessionFactoryBean;
//    }
//}
