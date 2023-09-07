package com.news.service.impl;

import cn.hutool.core.util.IdUtil;
import com.mysql.cj.util.StringUtils;
import com.news.dao.CommentDao;
import com.news.dao.UserDao;
import com.news.pojo.User;
import com.news.service.UserService;
import com.news.utils.DigestUtil;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.persistence.criteria.*;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
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
    @Resource
    private CommentDao commentDao;

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
        return userDao.existsUserByUsername(username);
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
            //criteriaBuilder.like模糊查询，第一个参数是上一行的返回值，第二个参数是like('%xxx%')中，xxx的值
            Predicate predicate = criteriaBuilder.like(root.get("username"), "%" + username + "%");
            Predicate predicate1 = criteriaBuilder.equal(root.get("role"), false);
            return criteriaBuilder.and(predicate, predicate1);
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
    @Transactional(rollbackFor = Exception.class)
    public void deleteUser(Integer id) {
        userDao.deleteById(id);
        commentDao.deleteAllByUid(id);
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

    @Override
    public User getUser(Integer id) {
        User user = userDao.findById(id).get();
        user.setPassword(null);
        return user;
    }

    @Override
    public long getUserCount() {
        return userDao.count();
    }

    @Override
    public User updateUser(User user, MultipartFile file) throws IOException {
        Optional<User> optionalUser = userDao.findById(user.getId());
        user.setRole(optionalUser.get().isRole());
        user.setLocked(optionalUser.get().isLocked());
        user.setPassword(optionalUser.get().getPassword());
        if (!StringUtils.isNullOrEmpty(file.getOriginalFilename())) {
            if (!file.getOriginalFilename().equals(optionalUser.get().getAvatar())) {
                user.setAvatar(IdUtil.randomUUID() + ".png");
                File path = new File("../news/src/main/resources/static");
                String filePath = path.getCanonicalPath() + "\\img\\" + user.getAvatar();
                FileOutputStream fileOutputStream = new FileOutputStream(filePath);
                FileCopyUtils.copy(file.getBytes(), fileOutputStream);
            } else {
                user.setAvatar(optionalUser.get().getAvatar());
            }
        } else {
            user.setAvatar(optionalUser.get().getAvatar());
        }
        userDao.save(user);
        user.setPassword(null);
        return user;
    }

    @Override
    public void updatePasswordById(Integer id, String password) {
        User user = userDao.findById(id).get();
        user.setPassword(DigestUtil.md5Hex(password));
        userDao.save(user);
    }
}