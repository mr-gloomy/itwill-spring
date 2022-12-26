<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="../include/header.jsp"%>

	<h1>/board/read.jsp</h1>

	<!-- 수정, 삭제 bno정보를 전달하는 폼태그 -->	
	<form role="form" method="post">
		<input type="hidden" name="bno" value="${vo.bno }">
	</form>


<!-- 폼 -->
<div class="box box-primary">
	<div class="box-header with-border">
		<h3 class="box-title">게시글 본문내용</h3>


	</div>
		<div class="box-body">
			<div class="form-group">
				<label for="exampleInputEmail1">글 번 호</label>
				 <input type="text" class="form-control" id="exampleInputEmail1" value="${vo.bno }" 
				 name="title" readonly>
			</div>
			
			<div class="form-group">
				<label for="exampleInputEmail1">글 제 목</label>
				 <input type="text" class="form-control" id="exampleInputEmail1" value="${vo.title }"
				 name="title" readonly>
			</div>
			<div class="form-group">
				<label for="exampleInputPassword1">작 성 자</label>
				 <input	type="text" class="form-control" id="exampleInputPassword1" value="${vo.writer }"
				 name="writer" readonly>
			</div>
			
			<div class="form-group">
	                <label>내 용</label>
	                <textarea class="form-control" rows="3" 
	                name="content" readonly>${vo.content }</textarea>
	        </div>
	        
		</div>
		
		
		<div class="box-footer">
                <button type="submit" class="btn btn-danger">수정</button>
                <button type="submit" class="btn btn-warning">삭제</button>
                <button type="submit" class="btn bg-purple">목록</button>
		</div>
</div>
<!-- 폼 -->

<!-- jQuery를 이용한 페이지 이동 -->
<script>
	$(document).ready(function(){
		var formObj=$("form[role='form']");
		
		// 수정버튼 (bno 가지고 submit-/board/modify)
		$(".btn-danger").click(function(){
			formObj.attr("action","/board/modify");			
			formObj.attr("method","get");
			formObj.submit();
		});
		// 삭제버튼 (bno 가지고 submit-/board/remove)
		$(".btn-warning").click(function(){
			// 바로삭제동작 >> POST방식
			formObj.attr("action","/board/remove");			
// 			formObj.attr("method","post"); 원래 폼태그의 전달방식이 post임.
			formObj.submit();
		});
		
		// 목록버튼
		$(".bg-purple").click(function(){
			location.href="/board/listPage";
		});
	});
	
	

</script>



<%@ include file="../include/footer.jsp"%>