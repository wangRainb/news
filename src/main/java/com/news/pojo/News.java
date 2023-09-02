package com.news.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
    @JsonIgnore
    private String content;
    private String img;
    @Column(name = "createtime")
    private LocalDateTime createTime;
    private String author;
    private int views;
    @ManyToOne(targetEntity = Category.class, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "cid", referencedColumnName = "id")
    private Category category;
}
