package com.itwillbs.persistence;

import java.util.List;

import com.itwillbs.domain.BoardVO;
import com.itwillbs.domain.Criteria;

public interface BoardDAO {
	
	// 서버시간정보 조회
	public String getTime();
	
	// 게시판 글쓰기
	public void createBoard(BoardVO vo)throws Exception;
	
	// 게시판 전체목록 조회
	public List<BoardVO> getBoardListAll()throws Exception;
	
	// 조회수 증가
	public void updateReadcnt(Integer bno)throws Exception;
	
	// 글번호(bno)를 사용한 정보 조회
	public BoardVO getBoard(Integer bno)throws Exception;
	
	// 글 수정하기
	public Integer updateBoard(BoardVO vo)throws Exception;
	
	// 글 삭제하기
	public Integer removeBoard(Integer bno)throws Exception;
	
	
	// 페이징 처리 구현된 리스트조회
	public List<BoardVO> getListPage(Integer page)throws Exception;
	
	public List<BoardVO> getListPage(Criteria cri)throws Exception;
	
	// 글 전체개수
	public int totalCnt()throws Exception;
}
