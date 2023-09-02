package com.news.pojo;

import lombok.Data;

/**
 * @author 18786
 */
@Data
public class LoginModel {
    private String username;
    private String password;
    private boolean role;
}
