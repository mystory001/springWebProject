<%@ page language="java" contentType="text/html; charset=UTF-8"
     pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ include file="inc/top.jsp" %>

            <div class="row">
                <div class="col-lg-12">
                    <h1 class="page-header">board/update</h1>
                </div>
            </div>
            <div class="row">
                <div class="col-lg-12">
                    <div class="panel panel-default">
                        <div class="panel-heading">
                            update
                        </div>
                        <div class="panel-body">
                        	<form action="/board/update" method="post" role="form" >
                            	<input type="hidden" name="pageNum" value= "<c:out value='${criteria.pageNum}' />">
                            	<input type="hidden" name="amount" value= "<c:out value='${criteria.amount}' />">
                            	<div class="form-group">
                            		<label>BNO</label>
                            		<input class="form-control" name="bno" value="<c:out value='${boardVO.bno}'/>" readonly>
                            	</div>
                            	<div class="form-group">
                            		<label>TITLE</label>
                            		<input class="form-control" name="title" value="<c:out value='${boardVO.title}'/>">
                            	</div>
                            	<div class="form-group">
                            		<label>CONTENT</label>
                            		<textarea class="form-control" rows="3" name="content"><c:out value='${boardVO.content}'/></textarea>
                            	</div>
                            	<div class="form-group">
                            		<label>WRITER</label>
                            		<input class="form-control" name="writer" value="<c:out value='${boardVO.writer}'/>">
                            	</div>
                            	<button data-oper="update" class="btn btn-default">Update</button>
                            	<button data-oper="delete" class="btn btn-danger">Delete</button>
                            	<button data-oper="list" class="btn btn-info">List</button>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        
<script>
$(document).ready(function(){
	
	var formObj = $("form");

	$('.button').on("click", function(e){
		e.preventDefault();
		
		var operation = $(this).data("oper");
		
		console.log(operation);

		if(operation == "list"){
			formObj.attr("action", "/board/list").attr("method","get");
			var pageNumTag = $("input[name='pageNum']").clone();
			var amountTag = $("input[name='amount']").clone();
			formObj.empty();
			formObj.append(pageNumTag);
			formObj.append(amountTag);
		} else if(operation == 'delete'){
			formObj.attr("action", "/board/delete").attr("method","post");
		}
		formObj.submit();
	});
		
});
</script>        
        
<%@ include file="inc/bottom.jsp" %>