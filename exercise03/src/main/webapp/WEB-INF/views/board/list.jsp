<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ include file="inc/top.jsp" %>

            <div class="row">
                <div class="col-lg-12">
                    <h1 class="page-header">board/list</h1>
                </div>
                <!-- /.col-lg-12 -->
            </div>
            <!-- /.row -->
            <div class="row">
                <div class="col-lg-12">
                    <div class="panel panel-default">
                        <div class="panel-heading">
                            list
                            <button id="regBtn" type="button" class="btn btn-xs pull-right">WRITING</button>
                        </div>
                        <!-- /.panel-heading -->
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
                                		<td><c:out value="${boardVO.bno }" /></td>  
                                		<td><a class="move" href="<c:out value='${boardVO.bno}'/>"><c:out value="${boardVO.title}"/></a></td>
                                		<td><c:out value="${boardVO.writer }" /></td>
                                		<td><fmt:formatDate value="${boardVO.regdate }" pattern="yyyy-MM-dd" /></td>
                                		<td><fmt:formatDate value="${boardVO.updatedate }" pattern="yyyy-MM-dd" /></td>
                                	</tr>
                                </c:forEach>
                            </table>
                            	${page}
                            <div class="pull-right">
                            	<ul class="pagination">
                            		<c:if test="${page.prev}"> 
								    	<li class="page-item"><a class="page-link" href="${page.startPage - 1}" tabindex="-1">Previous</a></li>
									</c:if>
									<c:forEach begin="${page.startPage}" end="${page.endPage}" var="num">
										<li class="page-item ${page.criteria.pageNum == num? 'active' :''}"><a class="page-link" href="${num}">${num}</a></li>
									</c:forEach>                
									<c:if test="${page.next}">            	
								    	<li class="page-item"><a class="page-link" href="${page.endPage + 1}">Next</a></li>
                            		</c:if>
                            	</ul>
                            </div>
                            
                            
                            <form id="actionForm" action="/board/list" method="get">
                            	<input type="hidden" name="pageNum" value= "${page.criteria.pageNum}">
                            	<input type="hidden" name="amount" value= "${page.criteria.amount}">
                            </form>
                            
                            <div class="modal fade" id="myModal" tabindex="-1" role="dialog">
							  <div class="modal-dialog" role="document">
							    <div class="modal-content">
							      <div class="modal-header">
							        <h5 class="modal-title">Result</h5>
							        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
							          <span aria-hidden="true">&times;</span>
							        </button>
							      </div>
							      <div class="modal-body">
							        <p>처리가 완료되었습니다.</p>
							      </div>
							      <div class="modal-footer">
							        <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
							      </div>
							    </div>
							  </div>
							</div>
                            
                        </div>
                        <!-- /.panel-body -->
                    </div>
                    <!-- /.panel -->
                </div>
                <!-- /.col-lg-6 -->
            </div>
            <!-- /.row -->
 
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
	 
	 $(".page-link").on("click", function(e){
		 e.preventDefault();
		 
		 actionForm.find("input[name='pageNum']").val($(this).attr("href"));
		 actionForm.submit();
	 }); // actionForm
	 
	 $(".move").on("click", function(e){
		 e.preventDefault();
		 
		 actionForm.append("<input type='hidden' name='bno' value='"+$(this).attr("href")+"'>");
		 actionForm.attr("action","/board/get");
		 actionForm.submit();
	 }); // move
	 
	 
}); //document

</script>


<%@ include file="inc/bottom.jsp" %>