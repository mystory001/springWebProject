<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="inc/top.jsp" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
            <div class="row">
                <div class="col-lg-12">
                    <h1 class="page-header">Tables</h1>
                </div>
                <!-- /.col-lg-12 -->
            </div>
            <!-- /.row -->
            <div class="row">
                <div class="col-lg-12">
                    <div class="panel panel-default">
                        <div class="panel-heading">
                            DataTables Advanced Tables
                            <button id="regBtn" type="button" class="btn btn-xs pull-right">INSERT</button>
                        </div>
                        <!-- /.panel-heading -->
                        <div class="panel-body">
                            <table width="100%" class="table table-striped table-bordered table-hover">
                                <thead>
                                    <tr>
                                        <th>#BNO</th>
                                        <th>TITLE</th>
                                        <th>WRITER</th>
                                        <th>REGDATE</th>
                                        <th>UPDATEDATE</th>
                                    </tr>
                                </thead>
                                <tbody>
                                <c:forEach items="${list}" var="boardVO">
                                    <tr class="odd gradeX">
                                        <td><c:out value="${boardVO.bno}" /></td>
                                        <td><c:out value="${boardVO.title}" /></td>
                                        <td><c:out value="${boardVO.writer}" /></td>
                                        <td><fmt:formatDate value="${boardVO.regdate }" pattern="yyyy-MM-dd"/> </td>
                                        <td><fmt:formatDate value="${boardVO.updatedate }" pattern="yyyy-MM-dd" /></td>
                                    </tr>
                                </c:forEach>
                                </tbody>
                            </table>
                            <!-- /.table-responsive -->
                        </div>
                        <!-- /.panel-body -->
                    </div>
                    <!-- /.panel -->
                </div>
                <!-- /.col-lg-12 -->
            </div>

<div id="myModal" class="modal" tabindex="-1" role="dialog">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title">Modal title</h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body">
        <p></p>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-primary" data-dismiss="modal">Save changes</button>
        <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
      </div>
    </div>
  </div>
</div>

<script>
$(document).ready(function(){
	var result = '<c:out value="${result}"/>';
	
	console.log(result);
	
	checkModal(result);
	
	history.replaceState({},null,null); // 히스토리 클리어
	
	function checkModal(result){
		
		if(result ==='' || history.state){
			return;
		}
		
		if(result === 'success'){
			$('.modal-body').html("정상적으로 처리됨.");
		} else if(parseInt(result)>0){
			$('.modal-body').html("게시글 " + parseInt(result) + " 번이 등록됨.");
		}
		
		$("#myModal").modal("show");
	}

	$("#regBtn").click(function(){
		self.location = "/board/insert";
	})

	
});
</script>

<%@include file="inc/bottom.jsp" %>            