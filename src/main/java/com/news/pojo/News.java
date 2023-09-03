package com.news.pojo;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author 18786
 */
@Data
@Entity
@Table(name = "news")
public class News implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String title;
    private String content;
    private int cid;
    private String img;
    @Column(name = "createtime")
    private LocalDateTime createTime;
    private String author;
    private int views;
    @Transient
    private Category category;
}
