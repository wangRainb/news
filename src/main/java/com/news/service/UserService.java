package com.news.service;

import com.news.pojo.User;
import org.springframework.data.domain.Page;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * @author 18786
 */
public interface UserService {
    /**
     * 登录校验
     *
     * @param username
     * @param password
     * @return User
     */
    User checkUser(String username, String password);

    /**
     * 检查用户是否存在
     *
     * @param username
     * @return boolean
     */
    boolean checkUserIsExist(String username);

    /**
     * 添加用户
     *
     * @param user
     */
    void addUser(User user);

    /**
     * 获取用户列表
     *
     * @param pageSize
     * @param pageNum
     * @param username
     * @return Page<User>
     */
    Page<User> getUserList(Integer pageNum, Integer pageSize, String username);

    /**
     * 删除用户
     *
     * @param id
     */
    void deleteUser(Integer id);

    /**
     * 封号处理
     *
     * @param id
     * @param days
     */
    void lock(Integer id, Integer days);

    /**
     * 检查封号用户是否到解封时间
     */
    void checkLockDate();

    /**
     * 获取用户
     *
     * @param id
     * @return User
     */
    User getUser(Integer id);

    /**
     * 获取用户总数
     *
     * @return
     */
    long getUserCount();

    /**
     * 修改用户
     *
     * @param user
     * @param file
     * @return User
     * @throws IOException
     */
    User updateUser(User user, MultipartFile file) throws IOException;
}
