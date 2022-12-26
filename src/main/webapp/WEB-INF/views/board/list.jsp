<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

	
	<h1>/board/list.jsp</h1>
	<%-- ${result }	<br>
	request : ${requestScope.result }	<br>
	session : ${sessionScope.result }	<br>
	parameter : ${param.result }	<br> --%>
	
<%-- 	${boardList } --%>
<%-- 	${pvo } --%>
	
	<%@ include file="../include/header.jsp"%>
	
	<div class="box">
            <div class="box-header with-border">
              <h3 class="box-title">게시판 목록</h3>
            </div>
            <!-- /.box-header -->
            <div class="box-body">
              <table class="table table-bordered" >
                <tbody><tr>
                  <th style="width: 80px">글 번호</th>
                  <th>제목</th>
                  <th>작성자</th>
                  <th>작성일</th>
                  <th style="width: 80px">조회수</th>
                </tr>
                <c:forEach var="vo" items="${boardList }">
                <tr>
                  <td>${vo.bno }</td>
                  <td><a href="/board/read?bno=${vo.bno }">${vo.title }</a></td>
                  <td>${vo.writer }</td>
                  <td><fmt:formatDate value="${vo.regdate }" type="both" /></td>
                  <td><span class="badge bg-red">${vo.viewcnt }</span></td>
                </tr>
                </c:forEach>
              </tbody></table>
            </div>
            <!-- /.box-body -->
            <div class="box-footer clearfix">
              <ul class="pagination pagination-sm no-margin pull-right">
              	<!-- 이전 버튼 -->
              	<c:if test="${pvo.prev }">
             	   <li><a href="/board/listPage?page=${pvo.startPage-1 }">«</a></li>
                </c:if>
                
                
                <c:forEach var="i" begin="${pvo.startPage }" end="${pvo.endPage }" step="1">
                
                <li
                <c:out value="${i==pvo.cri.page? 'class=active':''  }" /> 
                ><a href="/board/listPage?page=${i }">${i }</a></li>
                
                </c:forEach>
                
                
               	<!-- 다음 버튼 -->
               	<c:if test="${pvo.next }">
                <li><a href="/board/listPage?page=${pvo.endPage+1 }">»</a></li>
                </c:if>
              </ul>
            </div>
          </div>
          
     <script type="text/javascript" >
     	// alert('${result}'); => EL표현식 -> JS사용 값전달 가능 (DB데이터 사용가능) 
     	// ajax호출시 (DB데이터 전달 가능)
	var result = '${result}';
	
	if(result=='createOK'){
		alert(" 글쓰기 완료!");
	}
	
	else if(result=='modOK'){
		alert(" 글 수정 완료! ");
	}
	else if(result=='remOK'){
		alert(" 글 삭제 완료! ");
	}
     </script> 
          
      <%@ include file="../include/footer.jsp"%>