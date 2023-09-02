package com.news.service.impl;

import com.news.dao.UserDao;
import com.news.pojo.User;
import com.news.service.UserService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.persistence.criteria.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

/**
 * @author 18786
 */
@Service
public class UserServiceImpl implements UserService {
    @Resource
    private UserDao userDao;

    @Override
    public User checkUser(String username, String password) {
        User user = userDao.getUserByUsernameAndPassword(username, password);
        if (user != null) {
            user.setPassword(null);
        }
        return user;
    }

    @Override
    public boolean checkUserIsExist(String username) {
        boolean userIsExist = userDao.existsUserByUsername(username);
        return userIsExist;
    }

    @Override
    public void addUser(User user) {
        userDao.save(user);
    }

    @Override
    public Page<User> getUserList(Integer pageNum, Integer pageSize, String username) {
        //查询条件存在这个对象中
        //重新Specification的toPredicate方法
        Specification<User> specification = (root, criteriaQuery, criteriaBuilder) -> {
            //我要模糊查询的字段是username
            Path path = root.get("username");
            //criteriaBuilder.like模糊查询，第一个参数是上一行的返回值，第二个参数是like('%xxx%')中，xxx的值
            Predicate predicate = criteriaBuilder.like(path, "%" + username + "%");
            return predicate;
        };
        //分页条件存在这个对象中
        PageRequest pageRequest = PageRequest.of(pageNum, pageSize, Sort.by(Sort.Direction.ASC, "id"));
        //进行查询操作，第一个参数是查询条件对象，第二个参数是分页对象
        Page<User> page = userDao.findAll(specification, pageRequest);
        //返回的数据都封装在了Page<User>对象中
        for (User user : page.getContent()) {
            user.setPassword(null);
        }
        return page;
    }

    @Override
    public void deleteUser(Integer id) {
        userDao.deleteById(id);
    }

    @Override
    public void lock(Integer id, Integer days) {
        Optional<User> optionalUser = userDao.findById(id);
        optionalUser.get().setLocked(true);
        optionalUser.get().setLockDate(LocalDateTime.now());
        optionalUser.get().setLockDays(days);
        userDao.save(optionalUser.get());
    }

    @Override
    public void checkLockDate() {
        List<User> userList = userDao.findAll();
        LocalDateTime dateTime;
        for (User user : userList) {
            if (user.isLocked()) {
                dateTime = user.getLockDate().plusDays(user.getLockDays());
                if (dateTime.isBefore(LocalDateTime.now()) || dateTime.isEqual(LocalDateTime.now())) {
                    user.setLockDate(null);
                    user.setLockDays(null);
                    user.setLocked(false);
                    userDao.save(user);
                }
            }
        }
    }
}