<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" >
<title>uploadAjax</title>
</head>
<body>
<h1>uploadAjax.jsp</h1>
<div class="uploadDiv">
	<input type="file" name="uploadFile" multiple>
</div>

<button id="uploadBtn">Upload</button>

<script
  src="https://code.jquery.com/jquery-3.7.1.min.js"
  integrity="sha256-/JqT3SQfawRcv/BIHPThkBvs0OEvtFFmqPF/lYI/Cxo="
  crossorigin="anonymous"></script>

<script type="text/javascript">
$(document).ready(function(){
	// 파일 확장자 및 크기 검증 함수
	var regex = new RegExp("(.*?)\.(exe|sh|zip|alz)$");
	var maxSize = 5242880; // 5MB

	function checkExtension(fileName, fileSize){
		if(fileSize >= maxSize){
			alert("파일 사이즈 초과 하였습니다.");
			return false;
		}
		
		if(regex.test(fileName)){
			alert("해당 종류의 파일은 업로드할 수 없습니다.");
			return false;
		}
		return true;
	}

	// 파일 업로드 버튼 클릭 이벤트 핸들러
	$("#uploadBtn").on("click", function(e){
		var formData = new FormData();
		var inputFile = $("input[name='uploadFile']");
		var files = inputFile[0].files;
		console.log(files);
		
		// 파일 확장자 및 크기 검증
		for(var i = 0; i < files.length; i++){
			if(!checkExtension(files[i].name, files[i].size)){
				alert(files[i].name+" 을/를 업로드 중 문제가 발생했습니다.");
				return false; // 검증 실패 시 업로드 중단
			}
			formData.append("uploadFile", files[i]);
			alert(files[i].name + " 을/를 업로드 하였습니다.");
		}
		
		// AJAX를 통한 파일 업로드
		$.ajax({
			url : "/uploadAjaxAction",
			processData : false,
			contentType : false,
			data : formData,
			type : "post",
			dataType: "json",
			success : function(result){
				console.log(result);
			}
		}); // $.ajax
	}); // $("#uploadBtn")
});
</script>
  
</body>
</html>