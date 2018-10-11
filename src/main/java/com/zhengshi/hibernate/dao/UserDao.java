package com.zhengshi.hibernate.dao;

import com.zhengshi.hibernate.domain.User;

import java.util.List;

public interface UserDao {
    User save(User user);
    List<User> findAll();
}
