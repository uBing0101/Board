package com.example.board.controller;

import com.example.board.domain.ReplyDTO;
import com.example.board.service.ReplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.List;

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
    @PostMapping("/insertRest")
    public ResponseEntity<String> insertRest(@RequestBody ReplyDTO dto, HttpSession session){
        ResponseEntity<String> entity = null;
        try{
            String userId = (String) session.getAttribute("userId");
            String userName = (String) session.getAttribute("userName");
            String userNickName = (String) session.getAttribute("userNickName");
            dto.setReplyer(userId);
            dto.setUsername(userName);
            replyService.create(dto);
            entity = new ResponseEntity<String>("success", HttpStatus.OK);
        } catch (Exception e){
            e.printStackTrace();
            entity = new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return entity;
    }

    // 댓글 목록(@RestController Json방식으로 처리 : 데이터를 리턴)
    @GetMapping("/listJson")
    @ResponseBody // 리턴데이터를 json으로 변환(생략가능)
    public List<ReplyDTO> listJson(@RequestParam int bno){
        List<ReplyDTO> list = replyService.list(bno);
        return list;
    }

//    @RequestMapping(value="/list/{bno}", method=RequestMethod.GET)
//    public ModelAndView replyList(@PathVariable("bno") int bno, ModelAndView mav){
//        List<ReplyDTO> list = replyService.list(bno);
//        mav.setViewName("/board/replyList");
//        mav.addObject("list", list);
//        return mav;
//    }

    @RequestMapping(value="/list/{bno}", method=RequestMethod.GET)
    public ModelAndView replyList(@PathVariable("bno") int bno, ModelAndView mv){
        List<ReplyDTO> list = replyService.list(bno);
        // 뷰이름 지정
        mv.setViewName("/board/replyList");
        // 뷰에 전달할 데이터 지정
        mv.addObject("list", list);
        // replyList.jsp로 포워딩
        return mv;
    }

    // 댓글 삭제
    //ResponseEntity 란 mav 와 달리 뷰 처리를 하지 않지만 데이터를 주고받을시 http header,body 에 메시와 , 상태코드를 리턴해줌
    @RequestMapping(value="/delete/{rno}")
    public ResponseEntity<String> Delete(@PathVariable("rno") int rno) {
        ResponseEntity<String> entity = null;
        try{
            replyService.delete(rno);
            // 댓글 삭제처리 시 성공 메세지 저장
            entity = new ResponseEntity<String>("success", HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
            // 댓글 삭제 실패시 메세지 저장
            entity = new ResponseEntity<String>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
        //삭제 처리 HTTP 상태 메새지를 리턴
        return entity;
    }

    //댓글 수정
    @RequestMapping(value="/update/{rno}", method={RequestMethod.PUT, RequestMethod.PATCH})
    public ResponseEntity<String> Update(@PathVariable("rno") int rno, @RequestBody ReplyDTO dto) {
        ResponseEntity<String> entity = null;
        try{
            dto.setRno(rno);
            replyService.update(dto);
            entity = new ResponseEntity<String>("success", HttpStatus.OK);
        }catch (Exception e) {
            e.printStackTrace();
            entity = new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return entity;
    }

    @GetMapping(value="/detail/{rno}")
    public ModelAndView Detail(@PathVariable("rno") int rno, ModelAndView mv) {
        ReplyDTO dto = replyService.detail(rno);
        mv.setViewName("/board/replyDetail");
        mv.addObject("dto", dto);
        return mv;
    }

}
