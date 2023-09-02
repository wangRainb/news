package com.news.dao;

import com.news.pojo.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 * @author 18786
 */
@Repository
public interface UserDao extends JpaRepository<User, Integer>, JpaSpecificationExecutor<User> {
    /**
     * 通过用户名和密码获取用户
     *
     * @param username
     * @param password
     * @return User
     */
    User getUserByUsernameAndPassword(String username, String password);

    /**
     * 通过用户名获取用户
     *
     * @param username
     * @return boolean
     */
    boolean existsUserByUsername(String username);
}
