package com.example.board.mapper;

import com.example.board.domain.ReplyDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ReplyMapper {
    // 댓글 목록
    List<ReplyDTO> list(Integer bno);
    // 댓글 입력
    void create(ReplyDTO dto);
    // 댓글 수정
    void update(ReplyDTO dto);
    // 댓글 삭제
    void delete(Integer rno);
}
