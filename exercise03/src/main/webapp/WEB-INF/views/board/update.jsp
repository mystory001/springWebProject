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
                            	<button data-oper="update" type="submit" class="btn btn-default">Update</button>
                            	<button data-oper="delete" type="submit" class="btn btn-danger">Delete</button>
                            	<button data-oper="list" type="submit" class="btn btn-info">List</button>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        
<script>
$(document).ready(function(){
	
	var formObj = $("form");

	$('button').on("click", function(e){
		e.preventDefault();
		
		var operation = $(this).data("oper");
		
		if(operation == "delete"){
			formObj.attr("action", "/board/delete");
		} else if(operation == 'list'){
			formObj.attr("action","/board/list").attr("method","get");
			formObj.empty();
		}
		formObj.submit();
	});
});
</script>        
        
<%@ include file="inc/bottom.jsp" %>