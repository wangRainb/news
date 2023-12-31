package com.news.service.impl;

import cn.hutool.core.util.IdUtil;
import com.mysql.cj.util.StringUtils;
import com.news.dao.CategoryDao;
import com.news.dao.CommentDao;
import com.news.dao.NewsDao;
import com.news.pojo.News;
import com.news.service.NewsService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.persistence.criteria.Predicate;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

/**
 * @author 18786
 */
@Service
public class NewsServiceImpl implements NewsService {
    @Resource
    private NewsDao newsDao;
    @Resource
    private CategoryDao categoryDao;
    @Resource
    private CommentDao commentDao;

    @Override
    public Page<News> getNewsList(Integer pageNum, Integer pageSize, String categoryId, String search) {
        //查询条件存在这个对象中
        //重新Specification的toPredicate方法
        Specification<News> specification = (root, criteriaQuery, criteriaBuilder) -> {
            //criteriaBuilder.like模糊查询，第一个参数是上一行的返回值，第二个参数是like('%xxx%')中，xxx的值
            Predicate predicate = criteriaBuilder.like(root.get("title"), "%" + search + "%");
            Integer cid = null;
            if (!StringUtils.isNullOrEmpty(categoryId)) {
                cid = Integer.valueOf(categoryId);
            }
            if (cid == null) {
                return predicate;
            } else {
                Predicate predicate1 = criteriaBuilder.equal(root.get("cid"), cid);
                return criteriaBuilder.and(predicate, predicate1);
            }
        };
        //分页条件存在这个对象中
        PageRequest pageRequest = PageRequest.of(pageNum, pageSize, Sort.by(Sort.Direction.DESC, "id"));
        //进行查询操作，第一个参数是查询条件对象，第二个参数是分页对象
        Page<News> page = newsDao.findAll(specification, pageRequest);
        //返回的数据都封装在了Page<News>对象中
        page.getContent().forEach(news -> news.setCategory(categoryDao.findById(news.getCid()).get()));
        return page;
    }

    @Override
    public void addNews(News news, byte[] img) throws IOException {
        if (img == null) {
            news.setImg(null);
        } else {
            news.setImg(IdUtil.randomUUID() + ".png");
            File path = new File("../news/src/main/resources/static");
            String filePath = path.getCanonicalPath() + "\\img\\" + news.getImg();
            FileOutputStream fileOutputStream = new FileOutputStream(filePath);
            FileCopyUtils.copy(img, fileOutputStream);
        }
        newsDao.save(news);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteNews(Integer id) {
        newsDao.deleteById(id);
        commentDao.deleteAllByNid(id);
    }

    @Override
    public News getNews(Integer id) {
        return newsDao.findById(id).get();
    }

    @Override
    public void updateNews(News news, MultipartFile file) throws IOException {
        Optional<News> optionalNews = newsDao.findById(news.getId());
        if (!StringUtils.isNullOrEmpty(file.getOriginalFilename())) {
            if (!file.getOriginalFilename().equals(optionalNews.get().getImg())) {
                news.setImg(IdUtil.randomUUID() + ".png");
                File path = new File("../news/src/main/resources/static");
                String filePath = path.getCanonicalPath() + "\\img\\" + news.getImg();
                FileOutputStream fileOutputStream = new FileOutputStream(filePath);
                FileCopyUtils.copy(file.getBytes(), fileOutputStream);
            } else {
                news.setImg(optionalNews.get().getImg());
            }
        } else {
            news.setImg(optionalNews.get().getImg());
        }
        news.setCreateTime(optionalNews.get().getCreateTime());
        newsDao.save(news);
    }

    @Override
    public Page<News> getNewsList(Integer pageNum, Integer pageSize, String categoryId) {
        //查询条件存在这个对象中
        //重新Specification的toPredicate方法
        Specification<News> specification = (root, criteriaQuery, criteriaBuilder) -> {
            Integer cid = null;
            if (!StringUtils.isNullOrEmpty(categoryId)) {
                cid = Integer.valueOf(categoryId);
            }
            if (cid == null) {
                throw new RuntimeException();
            } else {
                return criteriaBuilder.equal(root.get("cid"), cid);
            }
        };
        //分页条件存在这个对象中
        PageRequest pageRequest = PageRequest.of(pageNum, pageSize, Sort.by(Sort.Direction.DESC, "createTime"));
        //进行查询操作，第一个参数是查询条件对象，第二个参数是分页对象
        return newsDao.findAll(specification, pageRequest);
    }

    @Override
    public List<News> getRelatedNews(Integer id, Integer cid) {
        //查询条件存在这个对象中
        //重新Specification的toPredicate方法
        Specification<News> specification = (root, criteriaQuery, criteriaBuilder) -> {
            Predicate predicate = criteriaBuilder.equal(root.get("cid"), cid);
            Predicate predicate1 = criteriaBuilder.notEqual(root.get("id"), id);
            return criteriaBuilder.and(predicate, predicate1);
        };
        //分页条件存在这个对象中
        PageRequest pageRequest = PageRequest.of(0, 5, Sort.by(Sort.Direction.DESC, "createTime"));
        //进行查询操作，第一个参数是查询条件对象，第二个参数是分页对象
        Page<News> page = newsDao.findAll(specification, pageRequest);
        return page.getContent();
    }

    @Override
    public void updateNewsView(Integer id) {
        News news = newsDao.findById(id).get();
        news.setViews(news.getViews() + 1);
        newsDao.save(news);
    }

    @Override
    public long getNewsCount() {
        return newsDao.count();
    }

    @Override
    public long getViewCount() {
        List<News> newsList = newsDao.findAll();
        long sum = 0L;
        for (News news : newsList) {
            sum += news.getViews();
        }
        return sum;
    }

    @Override
    public Page<News> getNewsLikeTitle(Integer pageNum, Integer pageSize, String search) {
        //查询条件存在这个对象中
        //重新Specification的toPredicate方法
        Specification<News> specification = (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.like(root.get("title"), "%" + search + "%");
        //分页条件存在这个对象中
        PageRequest pageRequest = PageRequest.of(pageNum, pageSize, Sort.by(Sort.Direction.DESC, "createTime"));
        //进行查询操作，第一个参数是查询条件对象，第二个参数是分页对象
        Page<News> page = newsDao.findAll(specification, pageRequest);
        for (News news : page.getContent()) {
            news.setCategory(categoryDao.findById(news.getCid()).get());
        }
        return page;
    }
}
