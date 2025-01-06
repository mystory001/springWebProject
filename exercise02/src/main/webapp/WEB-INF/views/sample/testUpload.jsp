<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>sample/testUpload</title>
</head>
<body>
<h1>sample/testUpload</h1>
<p>여러 개의 파일을 한꺼번에 업로드</p>
<form action="/sample/testUploadPost" method="post" enctype="multipart/form-data"> <!-- form 태그의 action, method 속성 그리고 enctype 속성에 주의해서 작성해야함 -->
<div>
	<input type="file" name="files">
</div>
<div>
	<input type="file" name="files">
</div>
<div>
	<input type="file" name="files">
</div>
<div>
	<input type="file" name="files">
</div>
<div>
	<input type="file" name="files">
</div>
<div>
	<input type="submit">
</div>
</form>

</body>
</html>