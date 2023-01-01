package com.example.board.controller;

import com.example.board.domain.ReplyDTO;
import com.example.board.service.ReplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
public class ReplyController {
    @Autowired
    ReplyService replyService;

    // 댓글 입력
    @RequestMapping("/reply/insert")
    public void insert(@ModelAttribute ReplyDTO dto, HttpSession session){
        String userId = (String) session.getAttribute("userId");
        dto.setReplyer(userId);
        replyService.create(dto);
    }

    // 댓글 목록(@Controller방식 : veiw(화면)를 리턴)
    @RequestMapping("/reply/list")
    public ModelAndView list(@RequestParam int bno, ModelAndView mav){
        List<ReplyDTO> list = replyService.list(bno);
        // 뷰이름 지정
        mav.setViewName("board/replyList");
        // 뷰에 전달할 데이터 지정
        mav.addObject("list", list);
        // replyList.jsp로 포워딩
        return mav;
    }

    // 댓글 목록(@RestController Json방식으로 처리 : 데이터를 리턴)
    @RequestMapping("listJson")
    @ResponseBody // 리턴데이터를 json으로 변환(생략가능)
    public List<ReplyDTO> listJson(@RequestParam int bno){
        List<ReplyDTO> list = replyService.list(bno);
        return list;
    }
}
