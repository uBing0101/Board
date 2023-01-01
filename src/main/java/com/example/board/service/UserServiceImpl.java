package com.example.board.service;

import com.example.board.domain.UserDTO;
import com.example.board.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public Integer loginCheck(UserDTO dto, HttpSession session) {
        Integer result = userMapper.loginCheck(dto);
        if(result > 0) {
            UserDTO dto2 = viewUsers(dto);
            session.setAttribute("userId",dto2.getUserId());
            session.setAttribute("userName",dto2.getUserName());
        }
        return result;
    }

    @Override
    public UserDTO viewUsers(UserDTO dto) {
        return userMapper.viewUsers(dto);
    }

    @Override
    public void logout(HttpSession session) {
        session.invalidate();
    }

    //회원목록
    @Override
    public List<UserDTO> userList() {
        return userMapper.userList();
    }
    //회원등록
    @Override
    public void insertUser(UserDTO dto) {
        userMapper.insertUser(dto);
    }
    //회원정보 상세조회
    @Override
    public UserDTO viewUser(String userId) {
        return userMapper.viewUser(userId);
    }
    //회원삭제
    @Override
    public boolean deleteUset(String userId) {
        return userMapper.deleteUser(userId);
    }
    //회원수정
    @Override
    public boolean updateUser(UserDTO dto) {
        return userMapper.updateUser(dto);
    }

    @Override
    public boolean checkPw(String userId, String userPw) {
        return userMapper.checkPw(userId, userPw);
    }
}
