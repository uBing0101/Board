package com.example.board.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@Data
public class ReplyDTO {
    private Integer rno;        // 댓글 번호
    private Integer bno;        // 게시글 번호
    private String replytext;    // 댓글 내용
    private String replyer;        // 댓글 작성자
    private String username;    // 댓글 작성자의 이름(회원의 이름)
    @JsonFormat(shape= JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss", timezone="Asia/Seoul")
    private Date regdate;        // 댓글 작성일자
    @JsonFormat(shape= JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss", timezone="Asia/Seoul")
    private Date updatedate;    // 댓글 수정일자
}
