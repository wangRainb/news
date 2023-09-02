package com.news.pojo;

import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author 18786
 */
@Entity
@Table(name = "user")
@Data
@DynamicUpdate
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String username;
    private String password;
    private boolean role;
    private String phone;
    private String email;
    private String avatar;
    @Column(name = "islocked")
    private boolean isLocked;
    @Column(name = "lockdate")
    private LocalDateTime lockDate;
    @Column(name = "lockdays")
    private Integer lockDays;
}
