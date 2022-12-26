package com.itwillbs.persistence;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.itwillbs.domain.BoardVO;
import com.itwillbs.domain.Criteria;
import com.itwillbs.service.BoardServiceImpl;

@Repository
public class BoardDAOImpl implements BoardDAO {
	
	private static final Logger logger 
	= LoggerFactory.getLogger(BoardServiceImpl.class);
	
	// 디비연결 -> beans 주입(root-context.xml)
	@Inject
	private SqlSession sqlSession;
	
	// mapper NAMESPACE 정보
	private static final String NAMESPACE
			= "com.itwillbs.mapper.BoardMapper";
	
	@Override
	public String getTime() {
		
		return sqlSession.selectOne(NAMESPACE+".getTime");
	}

	@Override
	public void createBoard(BoardVO vo) throws Exception {
		logger.debug(" createBoard(BoardVO vo) -> mapper 동작 호출 ");
		
		sqlSession.insert(NAMESPACE+".create", vo);
		
		logger.debug(" 게시판 작성 완료");
	}

	@Override
	public List<BoardVO> getBoardListAll() throws Exception {
		logger.debug(" getBoardListAll() -> sqlSession-mapper 호출 ");
		List<BoardVO> boardList = sqlSession.selectList(NAMESPACE+".listAll");
//		logger.debug(" boardList : "+boardList);
		logger.debug(" 게시판 글 개수 : "+boardList.size());
		
		return boardList;
	}

	@Override
	public void updateReadcnt(Integer bno) throws Exception {
		logger.debug("updateReadcnt(Integer bno) -> sqlSession객체");
		
		sqlSession.update(NAMESPACE+".updateReadcnt",bno);
		
	}

	@Override
	public BoardVO getBoard(Integer bno) throws Exception {
		
		logger.debug("getBoard(Integer bno) 호출");
		
		BoardVO vo = sqlSession.selectOne(NAMESPACE + ".getBoard",bno);
		
		return vo;
	}

	@Override
	public Integer updateBoard(BoardVO vo) throws Exception {
		logger.debug(" updateBoard(Integer bno) 호출");
		
		return sqlSession.update(NAMESPACE+".updateBoard",vo);
	}

	@Override
	public Integer removeBoard(Integer bno) throws Exception {
		logger.debug(" removeBoard(Integer bno) 호출");
		
		
		return sqlSession.delete(NAMESPACE+".deleteBoard",bno);
	}

	@Override
	public List<BoardVO> getListPage(Integer page) throws Exception {
		
		// 페이지정보 계산
		if(page < 0) {
			page = 1;
			
		}
		
		// 디비에서 10개씩 조회함
		// 1 - 0 / 2 - 10 / 3 - 20 / 4 - 30 / 5 - 40 /
		page = (page-1)*10;
		
		return sqlSession.selectList(NAMESPACE+".listPage", page);
	}

	@Override
	public List<BoardVO> getListPage(Criteria cri) throws Exception {
		logger.debug(" getListPage(Criteria cri) 페이징 처리");
		
		return sqlSession.selectList(NAMESPACE+".listPage2", cri);
	}

	@Override
	public int totalCnt() throws Exception {
		
		return sqlSession.selectOne(NAMESPACE+".countBoard");
	}
	
	
	
	
}
