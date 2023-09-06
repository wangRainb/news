package com.news.service.impl;

import com.news.dao.CommentDao;
import com.news.pojo.Comment;
import com.news.service.CommentService;
import com.news.service.UserService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author 18786
 */
@Service
public class CommentServiceImpl implements CommentService {
    @Resource
    private CommentDao commentDao;
    @Resource
    private UserService userService;

    @Override
    public Page<Comment> getCommentList(Integer pageNum, Integer pageSize, Integer nid) {
        //查询条件存在这个对象中
        //重新Specification的toPredicate方法
        Specification<Comment> specification = (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.equal(root.get("nid"), nid);
        //分页条件存在这个对象中
        PageRequest pageRequest = PageRequest.of(pageNum, pageSize, Sort.by(Sort.Direction.DESC, "createTime"));
        //进行查询操作，第一个参数是查询条件对象，第二个参数是分页对象
        Page<Comment> page = commentDao.findAll(specification, pageRequest);
        List<Comment> commentList = page.getContent();
        for (Comment comment : commentList) {
            comment.setUser(userService.getUser(comment.getUid()));
            comment.getUser().setPassword(null);
        }
        return page;
    }

    @Override
    public int getCommentCount(Integer nid) {
        return commentDao.countByNid(nid);
    }

    @Override
    public void addComment(Comment comment) {
        commentDao.save(comment);
    }

    @Override
    public boolean isUserMakeComment(Integer nid, Integer uid) {
        return commentDao.existsCommentByUidAndNid(uid, nid);
    }

    @Override
    public long getCommentCount() {
        return commentDao.count();
    }

    @Override
    public void deleteComment(Integer id) {
        commentDao.deleteById(id);
    }
}
