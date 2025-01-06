<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>sample/test08</title>
</head>
<body>
<h1>sample/test08.jsp</h1>
<h2>SampleDTO : ${sampleDTO}</h2>
<h2>page : ${page}</h2>
<!-- 
http://localhost:8080/sample/test08?name=aaa&age=20&page=12 호출하면 
sample/test08.jsp
SampleDTO : SampleDTO(name=aaa, age=20)
page : 12
@ModelAttribute가 붙은 파라미터는 화면까지 전달되므로 브라우저를 통해 호출하면 ${page}가 출력
-->
</body>
</html>