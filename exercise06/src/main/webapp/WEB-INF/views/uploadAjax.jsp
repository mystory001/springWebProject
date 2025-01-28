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
	$("#uploadBtn").on("click", function(e){
		var formData = new FormData();
		var inputFile = $("input[name='uploadFile']");
		var files = inputFile[0].files;
		
// 		console.log(files);
	
		for(var i = 0; i < files.length; i++){
			formData.append("uploadFile", files[i]);
		}
		
		$.ajax({
			url : "/uploadAjaxAction",
			processData : false,
			contentType : false,
			data : formData,
			type : "post", /* 일반적으로 HTTP 메서드는 대소문자를 구분하지 않지만, 코드에서 일관성을 유지하기 위해 일반적으로 "POST"와 같은 대문자를 사용하는 것이 관례 */
			success : function(result){
				alert("Uploaded");
			}
		})

	})
	
})



</script>
  
</body>
</html>