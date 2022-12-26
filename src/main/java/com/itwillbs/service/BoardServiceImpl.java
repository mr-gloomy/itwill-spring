package com.itwillbs.service;


import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itwillbs.domain.BoardVO;
import com.itwillbs.domain.Criteria;
import com.itwillbs.persistence.BoardDAO;

@Service
public class BoardServiceImpl implements BoardService {
	
	@Autowired
	private BoardDAO dao;
	
	private static final Logger logger 
			= LoggerFactory.getLogger(BoardServiceImpl.class);
	
	@Override
	public void insertBoard(BoardVO vo) throws Exception {
		logger.debug(" insertBoard 호출 -> DAO 동작 호출 ");
		
		dao.createBoard(vo);
	}

	@Override
	public List<BoardVO> getBoardListAll() throws Exception {
		
		logger.debug(" getBoardListAll 호출 -> DAO 동작 호출(결과리턴받기) ");
		
		return dao.getBoardListAll();
	}

	@Override
	public void updateReadcnt(Integer bno) throws Exception {
		logger.debug(" updateReadcnt(Integer bno) - DAO호출( 조회수1 증가) ");
		
		dao.updateReadcnt(bno);
		
	}

	@Override
	public BoardVO getBoard(Integer bno) throws Exception {
		logger.debug("getBoard(Integer bno) 호출");
		
		return dao.getBoard(bno);
	}

	@Override
	public Integer updateBoard(BoardVO vo) throws Exception {
		logger.debug(" updateBoard(Integer bno) 호출");
		
		
		return dao.updateBoard(vo);
	}

	@Override
	public Integer removeBoard(Integer bno) throws Exception {
		
		return dao.removeBoard(bno);
	}

	@Override
	public List<BoardVO> getListPage(Criteria cri) throws Exception {
		return dao.getListPage(cri);
	}

	@Override
	public int totalCnt() throws Exception {
		
		return dao.totalCnt();
	}
	

	
}
