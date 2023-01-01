package com.example.board.mapper;

import com.example.board.domain.UserDTO;
import org.apache.ibatis.annotations.Mapper;

import javax.servlet.http.HttpSession;
import java.util.List;

@Mapper
public interface UserMapper {
    //로그인체크
    Integer loginCheck(UserDTO dto);
    //로그인정보
    UserDTO viewUsers(UserDTO dto);
    //로그아웃
    void logout(HttpSession session);

    // 회원 목록
    List<UserDTO> userList();
    // 회원 입력
    void insertUser(UserDTO dto);
    // 회원 정보 상세보기
    UserDTO viewUser(String userId);
    // 회원 삭제
    boolean deleteUser(String userId);
    // 회원 수정
    boolean updateUser(UserDTO dto);
    boolean checkPw(String userId, String userPw);

}
