package com.itwillbs.controller;

import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.itwillbs.domain.BoardVO;
import com.itwillbs.domain.Criteria;
import com.itwillbs.domain.PageVO;
import com.itwillbs.service.BoardService;

@Controller
@RequestMapping("/board/*")
public class BoardController {
		
	private static final Logger logger
		= LoggerFactory.getLogger(BoardController.class);
	
	// 서비스 주입
	@Inject
	private BoardService service;
	
	// http://localhost:8080/board/regist
	// http://localhost:8080/board/list
	

	
	// 게시판 글쓰기 GET
	@RequestMapping(value = "/regist" ,method = RequestMethod.GET)
	public void registGET() throws Exception {
		logger.debug(" /board/registGET() 호출 -> 페이지이동 ");
		
	}
	// 게시판 글쓰기 POST
	@RequestMapping(value = "/regist",method = RequestMethod.POST)
	public String registPOST(BoardVO vo,RedirectAttributes rttr) throws Exception{
		logger.debug(" /board/registPOST() 호출");
		logger.debug(" GET방식의 데이터 전달 -> DB 저장 -> 페이지 이동");
		
		// 0. 한글처리(POST)
		// 1. 전달정보 저장(title,content,writer)
		logger.debug(vo.toString());
		
		// 2. 서비스 -> DAO 접근 (mapper) 
		service.insertBoard(vo);
		
		logger.debug(" 게시판 글쓰기 완료");
		// 3. 페이지 이동(list페이지)
		
		// redirect 방식일때 쓸수있는 객체.
		// model.addAttribute("result", "createOK");
		rttr.addFlashAttribute("result", "createOK");
		// 주소줄에 안나옴 ! 
		
		return "redirect:/board/list";
	}
	
	// 게시판 목록 GET
	@RequestMapping(value = "/list",method = RequestMethod.GET)
	public void listGET(Model model,@ModelAttribute("result") String result,HttpSession session) throws Exception {
		logger.debug(" /board/getBoardListGET() 호출 -> 페이지이동");
		
		// 전달받은 정보 
		logger.debug(" result : "+result);
		
		// 세션객체 - 글 조회수 증가 체크정보
		session.setAttribute("updateCheck", true);
		
		// 서비스 -> DAO 게시판 전체리스트 가져오기
		List<BoardVO> boardList = service.getBoardListAll();
		// 연결되어있는 뷰페이지 전달(Model 객체)
		model.addAttribute("boardList",boardList);
		// 페이지이동 (/board/list.jsp)
	}
	
	
	// @ModelAttribute : Model 객체에 저장 -> Model 객체에 저장 (1:n 관계)
	//					=>>> 전달되는 데이터가 객체류 일때
	
	// @RequestParam : request.getParameter() 동작 (1:1 관계)	
	// 					=>>> 전달되는 데이터가 기본형or참조형 타입일때
	//					-> 문자열,숫자,날짜 등 데이터 형변환 가능
	
	
	// 게시판 본문보기 GET
	@RequestMapping(value = "/read",method = RequestMethod.GET)
	public void readGET(Model model,@RequestParam("bno") int bno,HttpSession session) throws Exception{
		logger.debug(" readGET() 호출 -> 페이지이동");
		
		// 전달정보 (bno)
		logger.debug(" 전달정보 bno : "+bno);
		
		// 세션객체	
		boolean isUpdateCheck = (boolean)session.getAttribute("updateCheck");
		// 조회수 1증가 (list -> read 증가, refresh는 증가X)
		if(isUpdateCheck) {
			// 세션에 updateCheck가 있을때(= true일때)
			logger.debug(" 조회수 1증가 ! ");
			// 조회수증가 -> 상태변경(true => false)
			
			session.setAttribute("updateCheck", !isUpdateCheck);
			
			// 서비스 -> DAO (특정 글번호에 해당하는 글 가져오기)
			service.updateReadcnt(bno);
		}
		
		// 서비스 -> DAO (특정 글번호에 해당하는 정보 가져오기)
		BoardVO vo = service.getBoard(bno);
		// 연결된 뷰페이지로 정보 전달(model)	
		model.addAttribute("vo", vo);
		
		//model.addAttribute("vo", service.getBoard(bno));
		//model.addAttribute(service.getBoard(bno));
		
	}
	
	// 게시글 수정 GET
	@RequestMapping(value = "/modify",method = RequestMethod.GET)
	public void modifyGET(Model model,@RequestParam("bno") int bno) throws Exception{
		logger.debug(" modifyGET() 호출");
		
		// 파라미터 저장 (bno)
		// 서비스 - DAO(글 조회)
		// model 객체 -> view 페이지로 정보전달
		model.addAttribute("vo",service.getBoard(bno));
		// board/modify.jsp 이동
	}
	
	// 게시글 수정 POST
	@RequestMapping(value = "/modify",method = RequestMethod.POST)
	public String modifyPOST(BoardVO vo,RedirectAttributes rttr)throws Exception{
		// 전달된 정보(수정할 정보) vo에 저장
		// 서비스 - DAO : 정보 수정메서드 호출
		
		Integer result = service.updateBoard(vo);
		logger.debug(" result : "+result);
		if(result>0) {
			// "수정완료" - 정보전달
			rttr.addFlashAttribute("result","modOK");
		}
		// 페이지 이동 (목록페이지)
		
		return "redirect:/board/list";
	}
	
	
	// 게시글 삭제 POST
	@RequestMapping(value = "/remove",method = RequestMethod.POST)
	public String removePOST(@RequestParam("bno") int bno,RedirectAttributes rttr)throws Exception{
		// 전달정보 저장(bno)
		// 서비스 - DAO : 게시판 글삭제 메서드
		Integer result = service.removeBoard(bno);
		// "삭제완료" 정보를 list페이지로 전달
		if(result>0) {
			rttr.addFlashAttribute("result","remOK");
		}
		// 리스트로 이동
		return "redirect:/board/list";
	}
	
	// 게시판(페이징) 목록 GET
	// http://localhost:8080/board/listPage
	// http://localhost:8080/board/listPage?page=2&perPageNum=20
		@RequestMapping(value = "/listPage",method = RequestMethod.GET)
		public String listPageGET(Criteria cri,Model model,@ModelAttribute("result") String result,HttpSession session) throws Exception {
			logger.debug(" /board/getBoardListGET() 호출 -> 페이지이동");
			
			// 전달받은 정보 
			logger.debug(" result : "+result);
			
			// 세션객체 - 글 조회수 증가 체크정보
			session.setAttribute("updateCheck", true);
			
			// 페이징 처리 객체( 강합결합 X => _)
			// Criteria cri = new Criteria();
			// cri.setPage(2);
			// cri.setPerPageNum(20);
			
			// 서비스 -> DAO 게시판 전체리스트 가져오기
			List<BoardVO> boardList = service.getListPage(cri);
			
			// 페이징처리 하단부 정보 준비 -> view페이지 전달
			PageVO pvo = new PageVO();
			pvo.setCri(cri);
//			pvo.setTotalCount(7963);
			logger.debug(" totalCnt : "+service.totalCnt());
			pvo.setTotalCount(service.totalCnt()); // 작성되어있는 글 전체 개수
			
			model.addAttribute("pvo", pvo);
			
			
			// 연결되어있는 뷰페이지 전달(Model 객체)
			model.addAttribute("boardList",boardList);
			
			return "/board/list";
		}
	
}
