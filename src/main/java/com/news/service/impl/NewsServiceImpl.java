package com.news.service.impl;

import cn.hutool.core.util.IdUtil;
import com.mysql.cj.util.StringUtils;
import com.news.dao.NewsDao;
import com.news.pojo.Category;
import com.news.pojo.News;
import com.news.service.NewsService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;

import javax.annotation.Resource;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * @author 18786
 */
@Service
public class NewsServiceImpl implements NewsService {
    @Resource
    private NewsDao newsDao;

    @Override
    public Page<News> getNewsList(Integer pageNum, Integer pageSize, String categoryId, String search) {
        //查询条件存在这个对象中
        //重新Specification的toPredicate方法
        Specification<News> specification = (root, criteriaQuery, criteriaBuilder) -> {
            //我要模糊查询的字段是title
            Path path = root.get("title");
            //criteriaBuilder.like模糊查询，第一个参数是上一行的返回值，第二个参数是like('%xxx%')中，xxx的值
            Predicate predicate = criteriaBuilder.like(path, "%" + search + "%");
            Join<Category, News> join = root.join("category", JoinType.INNER);
            Integer cid = null;
            if (!StringUtils.isNullOrEmpty(categoryId)) {
                cid = Integer.valueOf(categoryId);
            }
            if (cid == null) {
                return predicate;
            } else {
                Predicate predicate1 = criteriaBuilder.equal(join.get("id"), cid);
                Predicate predicate2 = criteriaBuilder.and(predicate, predicate1);
                return predicate2;
            }
        };
        //分页条件存在这个对象中
        PageRequest pageRequest = PageRequest.of(pageNum, pageSize, Sort.by(Sort.Direction.DESC, "id"));
        //进行查询操作，第一个参数是查询条件对象，第二个参数是分页对象
        Page<News> page = newsDao.findAll(specification, pageRequest);
        //返回的数据都封装在了Page<News>对象中
        return page;
    }

    @Override
    public void addNews(News news, byte[] img) throws IOException {
        if (img == null) {
            news.setImg(null);
        } else {
            news.setImg(IdUtil.randomUUID() + ".png");
            File path = new File("../news/src/main/resources/static");
            String filePath = path.getCanonicalPath() + "\\img\\" + news.getImg() + ".png";
            FileOutputStream fileOutputStream = new FileOutputStream(filePath);
            FileCopyUtils.copy(img, fileOutputStream);
        }
        newsDao.save(news);
    }

    @Override
    public void deleteNews(Integer id) {
        newsDao.deleteById(id);
    }
}
