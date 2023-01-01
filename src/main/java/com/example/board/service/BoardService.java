package com.example.board.service;

import com.example.board.domain.BoardDTO;

import javax.servlet.http.HttpSession;
import java.util.List;

public interface BoardService {
    //게시글 작성
    void create(BoardDTO dto) throws Exception;
    //게시글 상세
    BoardDTO read(int bno) throws Exception;
    //게시글 수정
    void update(BoardDTO dto) throws Exception;
    //게시글 삭제
    void delete(int bno) throws Exception;
    //게시글 전체 목록
    List<BoardDTO> listAll(int start, int end, String searchOption, String keyword) throws Exception;
    //게시글 조회
    void increaseViewcnt(int bno, HttpSession session) throws Exception;
    // 게시글 레코드 갯수 메서드 추가
    int countArticle(String searchOption, String keyword) throws Exception;

}
