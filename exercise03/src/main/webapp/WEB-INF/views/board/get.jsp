<%@ page language="java" contentType="text/html; charset=UTF-8"
     pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ include file="inc/top.jsp" %>

            <div class="row">
                <div class="col-lg-12">
                    <h1 class="page-header">board/get</h1>
                </div>
            </div>
            <div class="row">
                <div class="col-lg-12">
                    <div class="panel panel-default">
                        <div class="panel-heading">
                            read
                        </div>
                        <div class="panel-body">
                            	<div class="form-group">
                            		<label>BNO</label>
                            		<input class="form-control" name="bno" value="<c:out value='${boardVO.bno}'/>" readonly>
                            	</div>
                            	<div class="form-group">
                            		<label>TITLE</label>
                            		<input class="form-control" name="title" value="<c:out value='${boardVO.title}'/>" readonly>
                            	</div>
                            	<div class="form-group">
                            		<label>CONTENT</label>
                            		<textarea class="form-control" rows="3" name="content" readonly><c:out value='${boardVO.content}'/></textarea>
                            	</div>
                            	<div class="form-group">
                            		<label>WRITER</label>
                            		<input class="form-control" name="writer" value="<c:out value='${boardVO.writer}'/>" readonly>
                            	</div>
                            	<button data-oper="update" class="btn btn-default">Update</button>
                            	<button data-oper="list" class="btn btn-info">List</button>
                            <form id="operForm" action="/board/update" method="get">
                            	<input type="hidden" name="bno" id="bno" value="<c:out value='${boardVO.bno }'/>"> 
                            </form>
                        </div>
                    </div>
                </div>
            </div>

<script type="text/javascript">
$(document).ready(function(){
	
	var operForm = $("#operForm");
	
	$("button[data-oper='update']").on("click", function(e){
		operForm.attr("action","/board/update").submit();
	}); // $("button[data-oper='update']").on("click", function(e){
	
	$("button[data-oper='list']").on("click", function(e){
		operForm.find("#bno").remove();
		operForm.attr("action","/board/list")
		operForm.submit();
	}); // 	$("button[data-oper='list']").on("click", function(e){
	

	
}); // $(document).ready(function()



</script>

        
<%@ include file="inc/bottom.jsp" %>