package com.example.board.domain;

import lombok.Data;

import java.util.Date;

@Data
public class BoardDTO {
    private int bno;            // 게시글 번호
    private String title;       // 게시글 제목
    private String content;     // 게시글 내용
    private String writer;      // 게시글 작성자
    private Date regdate;       // 게시글 작성일자 util.Date
    private int viewcnt;        // 게시글 조회수
    private String userName;    // 게시글 회원 이름 = 게시글 작성자

    private int recnt;          // 게시글 댓글의 수 추가

}
