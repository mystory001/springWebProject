<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>uploadForm</title>
</head>
<body>
<h1>uploadForm.jsp</h1>
<form action="uploadFormAction" method="post" enctype="multipart/form-data">
<input type="file" name="uploadFile" multiple> <!-- multiple 속성은 한 번에 여러 개의 파일을 업로드 할 수 있음, 단 브라우저 버전에 따라 지원 여부가 달라짐 -->
<!-- <input type="file" name="uploadFile"> : multiple 속서잉 붙지 않으면 한 번에 하나의 파일만 업로드 할 수 있음 --> 
<button>Submit</button>
</form>
</body>
</html>