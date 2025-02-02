<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>uploadAjax</title>
<style type="text/css">

.uploadResult{
	width : 100%;
	background-color : aqua;
}

.uploadResult ul{
	display : flex;
	flex-flow : row;
	justify-content : center;
	align-itmes : center;
}

.uploadResult ul li{
	list-style : none;
	padding : 10px;
}

.uploadResult ul li img{
	width: 150px;
	height: auto;
}
</style>
</head>
<body>
<h1>uploadAjax2.jsp</h1>

<div class="uploadDiv">
<input type="file" name="uploadFile" multiple="multiple">
</div>

<div class="uploadResult">
<ul></ul>
</div>

<button id="uploadBtn">Upload</button>

<script src="https://code.jquery.com/jquery-3.7.1.min.js" integrity="sha256-/JqT3SQfawRcv/BIHPThkBvs0OEvtFFmqPF/lYI/Cxo=" crossorigin="anonymous"></script>

<script>
$(document).ready(function(){
	
	var regex = new RegExp("(.*?)\.(exe|sh|zip|alz)$");
	var maxSize = 5242880; // 10MB(바이트 단위 1MB = 1024KB, 1KB가 1024바이트 → 1MB는 정확히 1024*1024= 1,048,576바이트 따리서 nMB = n * 1,048,576)
	
	var cloneObj = $(".uploadDiv").clone();
	
	var uploadResult = $(".uploadResult ul");
	
	function showUploadedFile(uploadResultArr){
		var str = "";
		
		$(uploadResultArr).each(function(i, obj){
			
			if(!obj.image){
				str += "<li><img src='/resources/img/attach.png'>" + obj.fileName + "</li>";
			} else {
// 				str += "<li>" + obj.fileName + "</li>";
				var fileCallPath = encodeURIComponent(obj.uploadPath+ "/s_" + obj.uuid + "_" + obj.fileName);
				str += "<li><img src='/display?fileName="+fileCallPath+"'></li>";
			}
		});
		uploadResult.append(str);
	}
	
	function checkExtension(fileName, fileSize){
		if(fileSize > maxSize){
			alert("file size exceeded");
			return false;
		}
		
		if(regex.test(fileName)){
			alert("This extension cannot be uploaded");
			return false;
		}
		
		return true;
	} // function checkExtension
	
	// jQuery를 이용한 첨부 파일 전송
	$("#uploadBtn").on("click", function(e){
		var formData = new FormData // FormData는 폼을 쉽게 보내도록 도와주는 객체, HTML 폼 데이터를 나타냄. Ajax를 이용하는 파일 업로드는 FormData를 이용해서 필요한 파라미터를 담아서 전송하는 방식
		var inputFile = $("input[name='uploadFile']");
		var files = inputFile[0].files;
		console.log(files);
		
		for(var i = 0; i < files.length; i++){
			
			if(!checkExtension(files[i].name, files[i].size)){
				return false;
			}
			
			formData.append("uploadFile", files[i]);
		}
		
		$.ajax({
			url : "uploadAjaxAction",
			processData : false,
			contentType : false,
			data : formData,
			type : "post",
			dataType : "json",
			success : function(result){
				alert("Uploaded");
				
				showUploadedFile(result);
				
				$(".uploadDiv").html(cloneObj.html());
			}
		}); // $.ajax
	}) // $("#uploadBtn")
	
}) // $(document).ready(function()
</script>

</body>
</html>