package com.example.board.controller;

import com.example.board.domain.BoardDTO;
import com.example.board.service.BoardPager;
import com.example.board.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class BoardController {
    @Autowired
    private BoardService boardService;

    @RequestMapping({"/","/board/list"})
    public ModelAndView list(
            @RequestParam(defaultValue="title") String searchOption,
            @RequestParam(defaultValue="") String keyword,
            @RequestParam(defaultValue="1") int curPage) throws Exception {

        //레코드 개수
        int count = boardService.countArticle(searchOption, keyword);

        //페이지 나누기 처리
        BoardPager boardPager = new BoardPager(count, curPage);
        int start = boardPager.getPageBegin();
        int end = boardPager.getPageEnd();

        List<BoardDTO> list = boardService.listAll(start, end, searchOption, keyword);

        ModelAndView mv = new ModelAndView();
        mv.addObject("list", list);
        mv.addObject("count", count);
        mv.addObject("searchOption", searchOption);
        mv.addObject("keyword", keyword);
        mv.addObject("boardPager", boardPager);
        mv.setViewName("/board/boardList");

        return mv;
    }
    //게시글 작성화면
    @RequestMapping(value="/board/write", method= RequestMethod.GET)
    public ModelAndView write(){
        return new ModelAndView("/board/boardWrite");
    }
    //게시글 작성
    @RequestMapping(value="/board/insert", method=RequestMethod.POST)
    public ModelAndView insert(BoardDTO dto, HttpSession session) throws Exception {
        String writer = (String) session.getAttribute("userId");
        dto.setWriter(writer);
        boardService.create(dto);
        return new ModelAndView("redirect:/board/list");
    }
    //게시글 상세조회
    @RequestMapping(value="/board/view", method=RequestMethod.GET)
    public ModelAndView view(@RequestParam int bno, HttpSession session) throws Exception {
        //조회수 증가
        boardService.increaseViewcnt(bno,session);
        return new ModelAndView("/board/boardView").addObject("dto",boardService.read(bno));
    }
    //게시글 수정화면
    @RequestMapping(value="/board/update", method=RequestMethod.GET)
    public ModelAndView update(@RequestParam("bno") int bno) throws Exception {
        BoardDTO board = boardService.read(bno);
        return new ModelAndView("/board/boardUpdate").addObject("update",bno).addObject("data",board);
    }
    //게시글 수정
    @RequestMapping(value="/board/update", method=RequestMethod.POST)
    public ModelAndView update(BoardDTO dto) throws Exception {
        boardService.update(dto);
        return new ModelAndView("redirect:/board/view?bno="+dto.getBno());
    }
    //게시글 삭제
    @RequestMapping("/board/delete")
    public ModelAndView delete(@RequestParam int bno) throws Exception {
        boardService.delete(bno);
        return new ModelAndView("redirect:/board/list");
    }



}
