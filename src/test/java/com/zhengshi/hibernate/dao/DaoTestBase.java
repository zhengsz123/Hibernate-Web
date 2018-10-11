package com.zhengshi.hibernate.dao;

import com.zhengshi.hibernate.config.AppConfig;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@ContextConfiguration(classes = {AppConfig.class)
@RunWith(SpringJUnit4ClassRunner.class)
@ActiveProfiles("unit")
public class DaoTestBase {
    @Autowired
    protected UserDao userDao;
}

