package com.study.dangdang.sharding.jdbc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.study.dangdang.sharding.jdbc.entity.Student;
import com.study.dangdang.sharding.jdbc.entity.User;
import com.study.dangdang.sharding.jdbc.service.StudentService;
import com.study.dangdang.sharding.jdbc.service.UserService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:config/spring/spring-database.xml",
        "classpath*:config/spring/spring-sharding.xml"})
public class ShardingJdbcMybatisTest {

    @Resource
    public UserService userService;

    @Resource
    public StudentService studentService;

    @Test
    public void testUserInsert() {
        User u;
        for (int i = 1; i < 100; i++) {
            u = new User();
            u.setUserId(i);
            u.setAge(i);
            u.setName("zhang san " + i);
            Assert.assertEquals(userService.insert(u), true);
        }
    }

    @Test
    public void testStudentInsert() {
        Student student;
        for (int i = 1; i < 100; i++) {
            student = new Student();
            student.setStudentId(i);
            student.setAge(i);
            student.setName("li si " + i);
            Assert.assertEquals(studentService.insert(student), true);
        }
    }

    @Test
    public void testFindAll() {
        List<User> users = userService.findAll();
        if (null != users && !users.isEmpty()) {
            for (User u : users) {
                System.out.println(u);
            }
        }
    }

    @Test
    public void testFindUser() {
        List<Integer> ls = new ArrayList<Integer>();
        ls.add(16);
        List<User> users = userService.findByUserIds(ls);
        if (null != users && !users.isEmpty()) {
            for (User u : users) {
                System.out.println(u);
            }
        }
    }

    @Test
    public void testFindStudent() {
        List<Integer> ls = new ArrayList<Integer>();
        ls.add(16);
        List<User> users = userService.findByUserIds(ls);
        if (null != users && !users.isEmpty()) {
            for (User u : users) {
                System.out.println(u);
            }
        }
    }

    @Test
    public void testSQLIN() {
        List<User> users = userService.findByUserIds(Arrays.asList(12, 14, 17));
        if (null != users && !users.isEmpty()) {
            for (User u : users) {
                System.out.println(u);
            }
        }
    }

    @Test
    public void testTransactionTestSucess() {
        userService.transactionTestSucess();
    }

    @Test(expected = IllegalAccessException.class)
    public void testTransactionTestFailure() throws IllegalAccessException {
        userService.transactionTestFailure();
    }


}
