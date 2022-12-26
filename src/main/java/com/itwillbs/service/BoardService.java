package com.itwillbs.service;

import java.util.List;

import com.itwillbs.domain.BoardVO;
import com.itwillbs.domain.Criteria;

public interface BoardService {
		
	// 게시판 글쓰기
	public void insertBoard(BoardVO vo)throws Exception;
	
	// 게시판 목록
	public List<BoardVO> getBoardListAll()throws Exception;
	
	// 글 조회수 1증가
	public void updateReadcnt(Integer bno)throws Exception;

	// 글 정보 가져오기
	public BoardVO getBoard(Integer bno)throws Exception;
	
	// 글 수정하기
	public Integer updateBoard(BoardVO vo)throws Exception;
	
	// 글 삭제하기
	public Integer removeBoard(Integer bno)throws Exception;
	
	// 글정보 조회(페이징처리cri)
	public List<BoardVO> getListPage(Criteria cri)throws Exception;
	
	// 글 전체갯수 
	public int totalCnt()throws Exception;
}
