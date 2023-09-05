package com.news.pojo;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * @author 18786
 */
@Data
@Entity
@Table(name = "comment")
public class Comment {
    @Id
    @GeneratedValue
    private Integer id;
    private Integer uid;
    private Integer nid;
    private String content;
    @Column(name = "createtime")
    private LocalDateTime createTime;
    @Transient
    private News news;
    @Transient
    private User user;
}
