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
                        	<form>
                            	<input type="hidden" name="pageNum" value= "<c:out value='${criteria.pageNum}' />">
                            	<input type="hidden" name="amount" value= "<c:out value='${criteria.amount}' />">
                            	<input type="hidden" name="keyword" value= "<c:out value='${criteria.keyword}' />">
                            	<input type="hidden" name="type" value= "<c:out value='${criteria.type}' />">
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

<div class='bigPictureWrapper'>
  <div class='bigPicture'>
  </div>
</div>

<style>
.uploadResult {
  width:100%;
  background-color: gray;
}
.uploadResult ul{
  display:flex;
  flex-flow: row;
  justify-content: center;
  align-items: center;
}
.uploadResult ul li {
  list-style: none;
  padding: 10px;
  align-content: center;
  text-align: center;
}
.uploadResult ul li img{
  width: 100px;
}
.uploadResult ul li span {
  color:white;
}
.bigPictureWrapper {
  position: absolute;
  display: none;
  justify-content: center;
  align-items: center;
  top:0%;
  width:100%;
  height:100%;
  background-color: gray; 
  z-index: 100;
  background:rgba(255,255,255,0.5);
}
.bigPicture {
  position: relative;
  display:flex;
  justify-content: center;
  align-items: center;
}
.bigPicture img {
  width:600px;
}
</style>

        
<script>
$(document).ready(function(){
	
	var formObj = $("form");

	$('.btn').on("click", function(e){
		e.preventDefault();
		
		var operation = $(this).data("oper");
		
		console.log(operation);
		
		if(operation == 'list'){
			formObj.attr("action", "/board/list").attr("method","get");
			
			var pageNumTag = $("input[name='pageNum']").clone();
			var amountTag = $("input[name='amount']").clone();
			var typeTag = $("input[name='type']").clone();
			var keywordTag = $("input[name='keyword']").clone();
			
			form.Obj.append(pageNumTag);
			form.Obj.append(amountTag);
			form.Obj.append(typeTag);
			form.Obj.append(keywordTag);
			
			formObj.submit();	
		} else if(operation == "update"){
			formObj.attr("action", "/board/update").attr("method","post");
		} else if(operation == 'delete'){
			formObj.attr("action", "/board/delete").attr("method","post");
		}  
			formObj.submit();
	});
		
});

$(document).ready(function() {
	  (function(){
	    var bno = '<c:out value="${board.bno}"/>';
	    
	    $.getJSON("/board/getAttachList", {bno: bno}, function(arr){
	      console.log(arr);
	      var str = "";
	      $(arr).each(function(i, attach){
	          //image type
	          if(attach.fileType){
	            var fileCallPath =  encodeURIComponent( attach.uploadPath+ "/s_"+attach.uuid +"_"+attach.fileName);
	            
	            str += "<li data-path='"+attach.uploadPath+"' data-uuid='"+attach.uuid+"' "
	            str +=" data-filename='"+attach.fileName+"' data-type='"+attach.fileType+"' ><div>";
	            str += "<span> "+ attach.fileName+"</span>";
	            str += "<button type='button' data-file=\'"+fileCallPath+"\' data-type='image' "
	            str += "class='btn btn-warning btn-circle'><i class='fa fa-times'></i></button><br>";
	            str += "<img src='/display?fileName="+fileCallPath+"'>";
	            str += "</div>";
	            str +"</li>";
	          }else{
	              
	            str += "<li data-path='"+attach.uploadPath+"' data-uuid='"+attach.uuid+"' "
	            str += "data-filename='"+attach.fileName+"' data-type='"+attach.fileType+"' ><div>";
	            str += "<span> "+ attach.fileName+"</span><br/>";
	            str += "<button type='button' data-file=\'"+fileCallPath+"\' data-type='file' "
	            str += " class='btn btn-warning btn-circle'><i class='fa fa-times'></i></button><br>";
	            str += "<img src='/resources/img/attach.png'></a>";
	            str += "</div>";
	            str +"</li>";
	          }
	       });
	      $(".uploadResult ul").html(str);
	    }); // $.getJSON("/board/getAttachList", {bno: bno}, function(arr){
	  })();// (function(){
});

</script>        

<%@ include file="inc/bottom.jsp" %>