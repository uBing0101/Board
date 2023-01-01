package com.example.board.service;

import com.example.board.domain.BoardDTO;
import com.example.board.mapper.BoardMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.List;


@Service
public class BoardServiceImpl implements BoardService{
    @Autowired
    private BoardMapper boardMapper;

    //게시글 쓰기
    @Override
    public void create(BoardDTO dto) throws Exception {
        String title = dto.getTitle();
        String content = dto.getContent();
        String writer = dto.getWriter();

        dto.setTitle(title);
        dto.setContent(content);
        dto.setWriter(writer);
        boardMapper.create(dto);
    }
    //게시글 상세보기
    @Override
    public BoardDTO read(int bno) throws Exception {
        return boardMapper.read(bno);
    }
    //게시글 수정
    @Override
    public void update(BoardDTO dto) throws Exception {
        boardMapper.update(dto);
    }
    //게시글 삭제
    @Override
    public void delete(int bno) throws Exception {
        boardMapper.delete(bno);
    }
    //게시글 전체 목록
    @Override
    public List<BoardDTO> listAll(int start, int end, String searchOption, String keyword) throws Exception {
        return boardMapper.listAll(start, end, searchOption, keyword);
    }
    //게시글 조회수 증가
    @Override
    public void increaseViewcnt(int bno, HttpSession session) throws Exception {
        long update_time = 0;
        // 세션에 저장된 조회시간 검색
        // 최초로 조회할 경우 세션에 저장된 값이 없기 때문에 if문은 실행X
        if(session.getAttribute("update_time_"+bno) != null){
            // 세션에서 읽어오기
            update_time = (long)session.getAttribute("update_time_"+bno);
        }
        // 시스템의 현재시간을 current_time에 저장
        long current_time = System.currentTimeMillis();
        // 일정시간이 경과 후 조회수 증가 처리 24*60*60*1000(24시간)
        // 시스템현재시간 - 열람시간 > 일정시간(조회수 증가가 가능하도록 지정한 시간)
        if(current_time - update_time > 5*1000){
            boardMapper.increaseViewcnt(bno);
            // 세션에 시간을 저장 : "update_time_"+bno는 다른변수와 중복되지 않게 명명한 것
            session.setAttribute("update_time_"+bno, current_time);
        }
    }
    //게시글 레코드 갯수
    @Override
    public int countArticle(String searchOption, String keyword) throws Exception {
        return boardMapper.countArticle(searchOption, keyword);
    }

}
