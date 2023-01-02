package com.example.board.controller;

import com.example.board.domain.ReplyDTO;
import com.example.board.service.ReplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/reply")
public class ReplyController {
    @Autowired
    ReplyService replyService;

    // 댓글 입력
    @PostMapping("/insert")
    public void insert(@ModelAttribute ReplyDTO dto, HttpSession session){
        String userId = (String) session.getAttribute("userId");
        String userName = (String) session.getAttribute("userName");
        String userNickName = (String) session.getAttribute("userNickName");
        dto.setReplyer(userId);
        dto.setUsername(userName);
//        dto.setUsername(userNickName);
        replyService.create(dto);
    }

    // 댓글 목록(@RestController Json방식으로 처리 : 데이터를 리턴)
    @GetMapping("/listJson")
    @ResponseBody // 리턴데이터를 json으로 변환(생략가능)
    public List<ReplyDTO> listJson(@RequestParam int bno){
        List<ReplyDTO> list = replyService.list(bno);
        return list;
    }

    // 댓글 삭제

}
