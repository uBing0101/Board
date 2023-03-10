package com.example.board.service;

import com.example.board.domain.ReplyDTO;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public interface ReplyService {
    // 댓글 목록
    public List<ReplyDTO> list(int bno);
    // 댓글 입력
    public void create(ReplyDTO dto);
    // 댓글 수정
    public void update(ReplyDTO dto);
    // 댓글 삭제
    public void delete(int rno);
    // 댓글 상세보기
    public ReplyDTO detail(int rno);
}
