package com.news;

import com.news.dao.UserDao;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
class NewsApplicationTests {
    @Resource
    UserDao userDao;

    @Test
    void contextLoads() throws Exception {
        System.out.println(userDao.existsUserByUsername("admin1"));
    }

}
