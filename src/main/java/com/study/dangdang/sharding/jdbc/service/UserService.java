package com.study.dangdang.sharding.jdbc.service;

import java.util.List;

import com.study.dangdang.sharding.jdbc.entity.User;

public interface UserService {

    boolean insert(User u);

    List<User> findAll();

    List<User> findByUserIds(List<Integer> ids);

    void transactionTestSucess();

    void transactionTestFailure() throws IllegalAccessException;

}
