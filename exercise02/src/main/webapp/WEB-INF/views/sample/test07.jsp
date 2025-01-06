<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>sample/test07</title>
</head>
<body>
<h1>sample/test07.jsp</h1>
<h2>SampleDTO : ${sampleDTO}</h2>
<h2>page : ${page}</h2>
<!-- 
http://localhost:8080/sample/test07?name=aaa&age=20&page=1 호출하면 
sample/test07.jsp
SampleDTO : SampleDTO(name=aaa, age=20)
page :
화면에 SampleDTO만 전달되고, int 타입으로 선언된 page는 전달되지 않음
-->
</body>
</html>