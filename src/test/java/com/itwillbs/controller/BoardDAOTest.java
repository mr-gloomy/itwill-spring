package com.itwillbs.controller;

import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.itwillbs.domain.BoardVO;
import com.itwillbs.domain.Criteria;
import com.itwillbs.domain.PageVO;
import com.itwillbs.persistence.BoardDAO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(
		locations = { "file:src/main/webapp/WEB-INF/spring/root-context.xml" }
		)
public class BoardDAOTest {
	
	// logger
	private static final Logger logger = LoggerFactory.getLogger(BoardDAOTest.class);
		
	// DAO 객체에 있는 DB호출 메서드 실행
	
	// BoardDAO 객체
	@Inject
	private BoardDAO dao;
	
//	@Test
	public void 디비연결시간정보() {
		
		logger.debug(dao.getTime());
	}
	
//	@Test
	public void 페이징처리_게시판리스트() {
		try {
			for(BoardVO vo : dao.getListPage(1)) {
				logger.debug(vo.getBno()+":"+vo.getTitle());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
//	@Test
	public void cri테스트() {
		// 임시 페이지 처리객체
		Criteria cri = new Criteria(); // 1,10
		cri.setPage(2);
		cri.setPerPageNum(50);
		
		try {
			for(BoardVO vo : dao.getListPage(cri)) {
				logger.debug(vo.getBno()+":"+vo.getTitle());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}
	
	@Test
	public void pageVO_테스트() {
		
		Criteria cri = new Criteria(); // 1페이지, 10개씩
		
		PageVO vo = new PageVO();
		vo.setCri(cri);
		vo.setTotalCount(100);
		
		try {
			dao.getListPage(cri);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	
	
}
