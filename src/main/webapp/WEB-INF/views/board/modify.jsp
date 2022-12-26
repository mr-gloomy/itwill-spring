<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="../include/header.jsp"%>

	<h1>/board/modify.jsp</h1>

	<!-- 수정, 삭제 bno정보를 전달하는 폼태그 -->	
	<form role="form" method="post">


<!-- 폼 -->
<div class="box box-primary">
	<div class="box-header with-border">
		<h3 class="box-title">게시글 수정</h3>


	</div>
		<div class="box-body">
			<div class="form-group">
				<label for="exampleInputEmail1">글 번 호</label>
				 <input type="text" class="form-control" id="exampleInputEmail1" value="${vo.bno }" 
				 name="bno" readonly>
			</div>
			
			<div class="form-group">
				<label for="exampleInputEmail1">글 제 목</label>
				 <input type="text" class="form-control" id="exampleInputEmail1" value="${vo.title }"
				 name="title" >
			</div>
			<div class="form-group">
				<label for="exampleInputPassword1">작 성 자</label>
				 <input	type="text" class="form-control" id="exampleInputPassword1" value="${vo.writer }"
				 name="writer" readonly>
			</div>
			
			<div class="form-group">
	                <label>내 용</label>
	                <textarea class="form-control" rows="3" 
	                name="content" >${vo.content }</textarea>
	        </div>
	        
		</div>
		
		
		<div class="box-footer">
                <button type="submit" class="btn btn-danger">수정</button>
                <button type="reset" class="btn btn-warning">취소</button>
                

		</div>
	</div>
		</form>
<!-- 폼 -->

<!-- jQuery를 이용한 페이지 이동 -->
<script>
	$(document).ready(function(){
		var formObj=$("form[role='form']");
		
		// 취소버튼 >> 리스트로 이동
		$(".btn-warning").click(function(){
// 			location.href="/board/list";
			location.href="/board/read?bno=${vo.bno}";
		});
	});
	
	

</script>



<%@ include file="../include/footer.jsp"%>