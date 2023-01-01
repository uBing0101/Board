package com.example.board.mapper;

import com.example.board.domain.BoardDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BoardMapper {
    // 게시글 작성
    void create(BoardDTO dto) throws Exception;
    // 게시글 상세보기
    BoardDTO read(int bno) throws Exception;
    // 게시글 수정
    void update(BoardDTO dto) throws Exception;
    // 게시글 삭제
    void delete(int bno) throws Exception;
    // 게시글 전체 목록
    List<BoardDTO> listAll(int start, int end, String searchOption, String keyword) throws Exception;
    // 게시글 조회 증가
    void increaseViewcnt(int bno) throws Exception;
    // 게시글 레코드 갯수 추가
    int countArticle(String searchOption, String keyword) throws Exception;
}
