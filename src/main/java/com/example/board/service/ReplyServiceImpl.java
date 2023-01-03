package com.example.board.service;

import com.example.board.domain.ReplyDTO;
import com.example.board.mapper.ReplyMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReplyServiceImpl implements ReplyService{
    @Autowired
    private ReplyMapper replyMapper;

    @Override
    public List<ReplyDTO> list(int bno) { return replyMapper.list(bno); }
    // 댓글 작성
    @Override
    public void create(ReplyDTO dto) { replyMapper.create(dto); }
    // 댓글 수정
    @Override
    public void update(ReplyDTO dto) {
        replyMapper.update(dto);
    }
    // 댓글 삭제
    @Override
    public void delete(int rno) {
        replyMapper.delete(rno);
    }
    //댓글 상세
    @Override
    public ReplyDTO detail(int rno) {
        return replyMapper.detail(rno);
    }
}
