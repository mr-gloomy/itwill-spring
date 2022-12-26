<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="../include/header.jsp"%>

<h1>/board/regist.jsp</h1>


<!-- 폼 -->
<div class="box box-primary">
	<div class="box-header with-border">
		<h3 class="box-title">게시판 글쓰기</h3>


	</div>
	<form role="form" action="" method="post">
		<div class="box-body">
			<div class="form-group">
				<label for="exampleInputEmail1">글 제 목</label>
				 <input type="text" class="form-control" id="exampleInputEmail1" placeholder="제목을 입력하세요"
				 name="title">
			</div>
			<div class="form-group">
				<label for="exampleInputPassword1">작 성 자</label>
				 <input	type="text" class="form-control" id="exampleInputPassword1" placeholder="작성자명"
				 name="writer">
			</div>
			
			<div class="form-group">
	                <label>내 용</label>
	                <textarea class="form-control" rows="3" placeholder="Enter ..."
	                name="content" ></textarea>
	        </div>
	        
		</div>
		
		
		<div class="box-footer">
			<button type="submit" class="btn btn-primary">글쓰기</button>
		</div>
	</form>
</div>
<!-- 폼 -->

<%@ include file="../include/footer.jsp"%>