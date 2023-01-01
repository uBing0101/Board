package com.example.board.service;

import com.example.board.domain.UserDTO;

import javax.servlet.http.HttpSession;
import java.util.List;

public interface UserService {
    //로그인 체크
    Integer loginCheck(UserDTO dto, HttpSession session);
    //로그인 정보
    UserDTO viewUsers(UserDTO dto);
    //로그아웃
    void logout(HttpSession session);
    //회원 목록
    List<UserDTO> userList();
    //회원 입력
    void insertUser(UserDTO dto);
    UserDTO viewUser(String userId);
    boolean deleteUset(String userId);
    boolean updateUser(UserDTO dto);
    boolean checkPw(String useId, String userPw);
}
