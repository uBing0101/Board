package com.example.board.controller;

import com.example.board.domain.UserDTO;
import com.example.board.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

@Controller
public class UserController {
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);
    @Autowired
    private UserService userService;

    //로그인 화면
    @RequestMapping("/user/login")
    public String login() {
        return "/user/login";
    }

    //로그인 처리
    @RequestMapping("/user/loginCheck")
    public ModelAndView loginCheck(@ModelAttribute UserDTO dto, HttpSession session) {
        Integer result = userService.loginCheck(dto, session);
        ModelAndView mv = new ModelAndView();
        if(result > 0) {
            mv.setViewName("/user/home");
            mv.addObject("msg","success");
        }else{
            mv.setViewName("/user/login");
            mv.addObject("msg","failure");
        }
        return mv;
    }

    //로그아웃 처리
    @RequestMapping("/user/logout")
    public ModelAndView logout(HttpSession session) {
        userService.logout(session);
        ModelAndView mv = new ModelAndView();
        mv.setViewName("/user/login");
        mv.addObject("msg", "logout");

        return mv;
    }


    //회원목록
    @GetMapping("/user/list")
    public ModelAndView userList() {
        return new ModelAndView("/user/userList").addObject("user",userService.userList());
    }

    //회원등록
    @GetMapping("/user/write")
    public ModelAndView userWrite() {
        return new ModelAndView("/user/userWrite");
    }
    @PostMapping("/user/write")
    public ModelAndView userInsert(UserDTO dto) {
        userService.insertUser(dto);
        return new ModelAndView("redirect:/user/list");
    }

    //회원상세정보조회
    @GetMapping("/user/view")
    public ModelAndView userView(String userId) {
        return new ModelAndView("/user/userView").addObject("view",userService.viewUser(userId));
    }

    //회원정보수정
    @PostMapping("/user/update")
    public ModelAndView userUpdate(UserDTO dto) {
        userService.updateUser(dto);
        return new ModelAndView("redirect:/user/list");
    }

    //회원삭제
    @PostMapping("/user/delete")
    public ModelAndView userDelete(@RequestParam String userId, @RequestParam String userPw) {
        userService.deleteUset((userId));
        return new ModelAndView("redirect:/user/list");
    }

}
