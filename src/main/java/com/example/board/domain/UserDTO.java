package com.example.board.domain;

import lombok.Data;

import java.sql.Date;

@Data
public class UserDTO {
    private String userId;
    private String userPw;
    private String userName;
    private String userEmail;
    private String userNickName;
    private Date userRegDate;
    private Date userUpdateDate;
}
