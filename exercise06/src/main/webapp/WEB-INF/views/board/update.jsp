<%@ page language="java" contentType="text/html; charset=UTF-8"
     pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ include file="inc/top.jsp" %>

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
                        	<form  role="form" action="/board/update" method="post">
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
                            	<div class="form-group">
									<label>RegDate</label> 
									<input class="form-control" name='regDate' value='<fmt:formatDate pattern = "yyyy/MM/dd" value = "${board.regdate}" />'  readonly="readonly">            
								</div>
								<div class="form-group">
									<label>Update Date</label> 
									<input class="form-control" name='updateDate' value='<fmt:formatDate pattern = "yyyy/MM/dd" value = "${board.updateDate}" />'  readonly="readonly">            
								</div>
                            	<button type="submit" data-oper="update" class="btn btn-default">Update</button>
                            	<button type="submit" data-oper="delete" class="btn btn-danger">Delete</button>
                            	<button type="submit" data-oper="list" class="btn btn-info">List</button>
                            </form>
                        </div>
                    </div>
                </div>
            </div>

<div class='bigPictureWrapper'>
	<div class='bigPicture'></div>
</div>

<div class="row">
	<div class="col-lg-12">
		<div class="panel panel-default">

			<div class="panel-heading">Files</div>
			<!-- /.panel-heading -->
			<div class="panel-body">
				<div class="form-group uploadDiv">
					<input type="file" name='uploadFile' multiple="multiple">
				</div>
				<div class='uploadResult'>
					<ul>
					</ul>
				</div>
			</div>
		</div>
	</div>
</div>

<script>
$(document).ready(function() {
	  var formObj = $("form");
	  $('button').on("click", function(e){
	    e.preventDefault(); 
	    var operation = $(this).data("oper");
	    console.log(operation);
	    
	    if(operation === 'delete'){
	      formObj.attr("action", "/board/delete");
	    }else if(operation === 'list'){
	      formObj.attr("action", "/board/list").attr("method","get");
	      
	      var pageNumTag = $("input[name='pageNum']").clone();
	      var amountTag = $("input[name='amount']").clone();
	      var keywordTag = $("input[name='keyword']").clone();
	      var typeTag = $("input[name='type']").clone();      
	      
	      formObj.empty();
	      
	      formObj.append(pageNumTag);
	      formObj.append(amountTag);
	      formObj.append(keywordTag);
	      formObj.append(typeTag);	  
	    }else if(operation === "update"){
	        console.log("submit clicked");
	        var str = "";
	        $(".uploadResult ul li").each(function(i, obj){
	          var jobj = $(obj);
	          console.dir(jobj);
	          str += "<input type='hidden' name='attachList["+i+"].fileName' value='"+jobj.data("filename")+"'>";
	          str += "<input type='hidden' name='attachList["+i+"].uuid' value='"+jobj.data("uuid")+"'>";
	          str += "<input type='hidden' name='attachList["+i+"].uploadPath' value='"+jobj.data("path")+"'>";
	          str += "<input type='hidden' name='attachList["+i+"].fileType' value='"+ jobj.data("type")+"'>";
	          
	        });
	        formObj.append(str).submit();
        }
	    formObj.submit();
	  });
});
</script>

<script>
$(document).ready(function() {(function() {
	var bno = '<c:out value="${board.bno}"/>';
	$.getJSON("/board/getAttachList", {bno : bno}, function(arr) {
		console.log(arr);
		var str = "";
		$(arr).each(function(i,attach) {
			//image type
			if (attach.fileType) {
				var fileCallPath = encodeURIComponent(attach.uploadPath + "/s_" + attach.uuid + "_" + attach.fileName);
	
	            str += "<li data-path='"+attach.uploadPath+"' data-uuid='"+attach.uuid+"' "
	            str +=" data-filename='"+attach.fileName+"' data-type='"+attach.fileType+"' ><div>";
	            str += "<span> "+ attach.fileName+"</span>";
	            str += "<button type='button' data-file=\'"+fileCallPath+"\' data-type='image' "
	            str += "class='btn btn-warning btn-circle'><i class='fa fa-times'></i></button><br>";
	            str += "<img src='/display?fileName="+fileCallPath+"'>";
	            str += "</div>";
	            str + "</li>";
			} else {
	            str += "<li data-path='"+attach.uploadPath+"' data-uuid='"+attach.uuid+"' "
	            str += "data-filename='"+attach.fileName+"' data-type='"+attach.fileType+"' ><div>";
	            str += "<span> "+ attach.fileName+"</span><br/>";
	            str += "<button type='button' data-file=\'"+fileCallPath+"\' data-type='file' "
	            str += " class='btn btn-warning btn-circle'><i class='fa fa-times'></i></button><br>";
	            str += "<img src='/resources/img/attach.png'></a>";
	            str += "</div>";
	            str + "</li>";
			}
		}); // $(arr).each(function(i,attach)
		$(".uploadResult ul").html(str);
	}); // $.getJSON("/board/getAttachList", {bno: bno}, function(arr){
})();// (function(){

	$(".uploadResult").on("click", "button", function(e) {
		console.log("delete file");
		if (confirm("Delete this file?")) {
			var targetLi = $(this).closest("li");
			targetLi.remove();
		}
	}); // $(".uploadResult")
	
	var regex = new RegExp("(.*?)\.(exe|sh|zip|alz)$");
	var maxSize = 5242880; //5MB
	
	function checkExtension(fileName, fileSize) {
		if (fileSize > maxSize) {
			alert("file size exceeded");
			return false;
		}
	
		if (regex.test(fileName)) {
			alert("This extension cannot be uploaded");
			return false;
		}
		return true;
	} // checkExtension
	
	$("input[type='file']").change(function(e) {
		var formData = new FormData();
		var inputFile = $("input[name='uploadFile']");
		var files = inputFile[0].files;
	
		for (var i = 0; i < files.length; i++) {
			if (!checkExtension(files[i].name, files[i].size)) {
				return false;
			}
			formData.append("uploadFile",files[i]);
		}
	
		$.ajax({
			url : "/uploadAjaxAction",
			processData : false,
			contentType : false,
			data : formData,
			type : "post",
			dataType : "json",
			success : function(result) {
				console.log(result);
				showUploadResult(result); //업로드 결과 처리 함수 
			}
		}); //$.ajax
	}); // $("input[type='file']")  

	function showUploadResult(uploadResultArr) {
		if (!uploadResultArr || uploadResultArr.length == 0) {
			return;
		}
		
		var uploadUL = $(".uploadResult ul");
		var str = "";
	    $(uploadResultArr).each(function(i, obj){
			
			if(obj.image){
				var fileCallPath = encodeURIComponent(obj.uploadPath + "/s_" + obj.uuid + "_" + obj.fileName);
				str += "<li data-path='"+obj.uploadPath+"'";
				str +=" data-uuid='" + obj.uuid + "' data-filename='" + obj.fileName + "' data-type='" + obj.image + "'"
				str +" ><div>";
				str += "<span> " + obj.fileName + "</span>";
				str += "<button type='button' data-file=\'" + fileCallPath + "\' "
				str += "data-type='image' class='btn btn-warning btn-circle'><i class='fa fa-times'></i></button><br>";
				str += "<img src='/display?fileName=" + fileCallPath + "'>";
				str += "</div>";
				str +"</li>";
			}else{
				var fileCallPath = encodeURIComponent(obj.uploadPath + "/" + obj.uuid + "_" + obj.fileName);			      
			    var fileLink = fileCallPath.replace(new RegExp(/\\/g),"/");
			      
				str += "<li "
				str += "data-path='" + obj.uploadPath + "' data-uuid='" + obj.uuid + "' data-filename='" + obj.fileName + "' data-type='" + obj.image + "' ><div>";
				str += "<span> " + obj.fileName + "</span>";
				str += "<button type='button' data-file=\'" + fileCallPath + "\' data-type='file' " 
				str += "class='btn btn-warning btn-circle'><i class='fa fa-times'></i></button><br>";
				str += "<img src='/resources/img/attach.png'></a>";
				str += "</div>";
				str +"</li>";
			}

	    });
	    uploadUL.append(str);
	}
	  
});
</script>        

<%@ include file="inc/bottom.jsp" %>