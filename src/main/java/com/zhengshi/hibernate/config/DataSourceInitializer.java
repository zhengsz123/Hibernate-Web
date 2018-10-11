package com.zhengshi.hibernate.config;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.DependsOn;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;

import javax.sql.DataSource;
import java.util.Properties;

public class DataSourceInitializer {
    @Value("#{databaseProperties['db.username']}")
    private String userName;
    @Value("#{databaseProperties['db.password']}")
    private String passWord;
    @Value("#{databaseProperties['db.url']}")
    private String url;
    @Value("#{databaseProperties['db.driver']}")
    private String driver;
    @Value("#{databaseProperties['db.port']}")
    private String port;
    @Value("#{databaseProperties['db.dName']}")
    private String dName;
    @Value("#{databaseProperties['db.serverName']}")
    private String serverName;
    @Value("#{databaseProperties['db.jdbcPostgresql']}")
    private  String jdbc;

    @Bean
    public BasicDataSource CreateDataSource(){
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName(driver);
        dataSource.setUrl(jdbc+"://"+url+":"+port+"/"+dName);
        dataSource.setUsername(userName);
        dataSource.setPassword(passWord);
        dataSource.setTestOnBorrow(true);
        dataSource.setTestOnReturn(true);
        dataSource.setTestWhileIdle(true);
        dataSource.setTimeBetweenEvictionRunsMillis(1800000);
        dataSource.setNumTestsPerEvictionRun(3);
        dataSource.setMinEvictableIdleTimeMillis(1800000);
        return dataSource;
    }

    @Bean
    public DataSource getDataSource(){
        DataSource dataSource = CreateDataSource();
        return dataSource;
    }

    @Bean(name="hibernate4AnnotatedSessionFactory")
    @DependsOn("flyway")
    public LocalSessionFactoryBean getLocalSessionFactoryBean(@Autowired DataSource dataSource){
        LocalSessionFactoryBean sessionFactoryBean = new LocalSessionFactoryBean();
        sessionFactoryBean.setDataSource(dataSource);
        sessionFactoryBean.setPackagesToScan(new String[]{"com.zhengshi.hibernate.domain","com.zhengshi.hibernate.dao"});
        Properties props = new Properties();
        props.put("hibernate.dialect", "org.hibernate.spatial.dialect.postgis.PostgisDialect");
        props.put("hibernate.hbm2ddl.auto", "validate");
        props.put("hibernate.connection.charSet","UTF-8");
        props.put("hibernate.show_sql","true");
        sessionFactoryBean.setHibernateProperties(props);
        return sessionFactoryBean;
    }
}
