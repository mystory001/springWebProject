<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ include file="inc/top.jsp" %>

<div class="row">
	<div class="col-lg-12">
		<h1 class="page-header">board/list</h1>
	</div>
</div>


<div class="row">
	<div class="col-lg-12">
		<div class="panel panel-default">
			<div class="panel-heading">
				list
				<button id='regBtn' type="button" class="btn btn-xs pull-right">Write New Board</button>
			</div>

<div class="panel-body">
	<table class="table table-striped table-bordered table-hover">
		<thead>
			<tr>
				<th>#BNO</th>
				<th>TITLE</th>
				<th>WRITER</th>
				<th>REGDATE</th>
				<th>UPDATEDATE</th>
			</tr>
		</thead>

	     <c:forEach items="${list}" var="boardVO">
	        <tr>
	          <td><c:out value="${boardVO.bno}" /></td>
	           <td>
	              <a class='move' href='<c:out value="${boardVO.bno}"/>'><c:out value="${boardVO.title}" /><b>[<c:out value="${boardVO.replyCnt}" />]</b></a>
	          <td><c:out value="${boardVO.writer}" /></td>
              <td><fmt:formatDate value="${boardVO.regdate }" pattern="yyyy-MM-dd" /></td>
              <td><fmt:formatDate value="${boardVO.updatedate }" pattern="yyyy-MM-dd" /></td>
	        </tr>
	      </c:forEach>
	</table>

<div class='row'>
	<div class="col-lg-12">
		<form id="searchForm" action="/board/list" method="get">
        	<select name="type">
				<option value="" <c:out value="${pageMaker.cri.type == null?'selected':''}"/>>-----</option>
				<option value="T" <c:out value="${page.criteria.type eq 'T'?'selected':''}"/>>TITLE</option>
				<option value="C" <c:out value="${page.criteria.type eq 'C'?'selected':''}"/>>CONTENT</option>
				<option value="W" <c:out value="${page.criteria.type eq 'W'?'selected':''}"/>>WRITER</option>
            </select>
            	<input type="text" name="keyword" value="${page.criteria.keyword}">
            	<input type="hidden" name="pageNum" value="${page.criteria.pageNum}">
            	<input type="hidden" name="amount" value="${page.criteria.amount}">
            	<button class="btn btn-default">SEARCH</button>
		</form>
	</div>
</div>

<div class='pull-right'>
	<ul class="pagination">
		<c:if test="${page.prev}">
			<li class="paginate_button previous"><a href="${page.startPage -1}">Previous</a></li>
		</c:if>
		
		<c:forEach var="num" begin="${page.startPage}" end="${page.endPage}">
			<li class="paginate_button ${page.criteria.pageNum == num? 'active' :''}"><a href="${num}">${num}</a></li>
		</c:forEach>
		
		<c:if test="${page.next}">
			<li class="paginate_button next"><a href="${page.endPage + 1}">Next</a></li>
		</c:if>
	</ul>
</div>

</div>

<form id='actionForm' action="/board/list" method='get'>
	<input type='hidden' name='pageNum' value='${page.criteria.pageNum}'>
	<input type='hidden' name='amount' value='${page.criteria.amount}'>
	<input type='hidden' name='type' value='<c:out value="${page.criteria.type}"/>'>
	<input type='hidden' name='keyword' value='<c:out value="${page.criteria.keyword}"/>'>
</form>

                            
<!-- Modal -->
<div class="modal fade" id="myModal" tabindex="-1" role="dialog"
	aria-labelledby="myModalLabel" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
					aria-hidden="true">&times;</button>
				<h4 class="modal-title" id="myModalLabel">Modal title</h4>
			</div>
			<div class="modal-body">처리가 완료되었습니다.</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-default"
					data-dismiss="modal">Close</button>
				<button type="button" class="btn btn-primary">Save
					changes</button>
			</div>
		</div>
	</div>
</div>


		</div>
	</div>
</div>
 
<!--  등록, 수정, 삭제 작업은 처리가 완료된 후 다시 동일한 내용을 전송할 수 없도록 아예 브라우저의 URL을 이동하는 방식을 이용 → 사용자에게 작업이 완료됨을 알려줘야함 → alert()나 모달 이용 -->
<!-- redirect 처리할 때 RedirectAttributes라는 특별한 타입의 객체를 이용, 일회성으로만 데이터를 전달 -->
<script type="text/javascript">
$(document).ready(function(){

	 var result = "<c:out value='${result}' />";
	
	 checkModal(result);
	 
	 history.replaceState({},null,null);
	 
	 function checkModal(result){
		 if(result == '' || history.state){
			 return;
		 }
		 if(parseInt(result)>0){
			 $(".modal-body").html("게시글 " + parseInt(result) + "번이 등록되었습니다.");
		 }
		 $("#myModal").modal("show");
	 } // Modal
	
	 $("#regBtn").on("click", function(){
		self.location = "/board/insert"; 
	 }); // regBtn
	 
	 var actionForm = $("#actionForm");

	 $(".paginate_button a").on("click", function(e) {
		 e.preventDefault();
		 console.log('click');
		 
		 actionForm.find("input[name='pageNum']").val($(this).attr("href"));
		 actionForm.submit();
	 }); // $(".paginate_button a")

	 $(".move").on("click", function(e) {
		 e.preventDefault();
		actionForm.append("<input type='hidden' name='bno' value='"+ $(this).attr("href")+ "'>");
		actionForm.attr("action","/board/get");
		actionForm.submit();
	}); // $(".move")
	
	var searchForm = $("#searchForm");
	$("#searchForm button").on("click",function(e) {
		if (!searchForm.find("option:selected").val()) {
			alert("검색종류를 선택하세요");
			return false;
		}

		if (!searchForm.find("input[name='keyword']").val()) {
			alert("키워드를 입력하세요");
			return false;
		}

		searchForm.find("input[name='pageNum']").val("1");
			e.preventDefault();
			searchForm.submit();
	}); // $("#searchForm button")

}); //document
</script>

<%@ include file="inc/bottom.jsp" %>