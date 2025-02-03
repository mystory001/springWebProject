<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>uploadAjax</title>
<style type="text/css">
.uploadResult {
	width: 100%;
	background-color: gray;
}

.uploadResult ul {
	display: flex;
	flex-flow: row;
	justify-content: center;
	align-items: center;
}

.uploadResult ul li {
	list-style: none;
	padding: 10px;
}

.uploadResult ul li img {
	width: 100px;
}
</style>

<style>
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
}

.bigPicture {
  position: relative;
  display:flex;
  justify-content: center;
  align-items: center;
}
</style>
</head>
<body>
<h1>uploadAjax.jsp</h1>

<div class='bigPictureWrapper'>
  <div class='bigPicture'>
  </div>
</div>

<div class="uploadDiv">
<input type="file" name="uploadFile" multiple="multiple">
</div>

<div class="uploadResult">
<ul></ul>
</div>

<button id="uploadBtn">Upload</button>

<script src="https://code.jquery.com/jquery-3.7.1.min.js" integrity="sha256-/JqT3SQfawRcv/BIHPThkBvs0OEvtFFmqPF/lYI/Cxo=" crossorigin="anonymous"></script>

<script>

/* <a>태그에 직접 showImage()를 호출할 수 있는 방식으로 작성하기 위해서 jQuery의 바깥쪽에 작성 */
function showImage(fileCallPath){
// 	alert(fileCallPath);
	$(".bigPictureWrapper").css("display","flex").show();
	$(".bigPicture").html("<img src='/display?fileName=" + encodeURI(fileCallPath) + "'>").animate({width: '100%', height: '100%'}, 1000);
}

$(".bigPictureWrapper").on("click", function(e){
	$(".bigPicture").animate({width: '0%', height: '0%'}, 1000);
	setTimeout(function(){
		$(".bigPictureWrapper").hide();
	}, 1000);
});

$(".uploadResult").on("click", "span", function(e){
	var targetFile = $(this).data("file");
	var type = $(this).data("type");
	
	$.ajax({
		url : "/deleteFile",
		data : {fileName : targetFile, type : type},
		dataType: "text",
		type : "post",
		success : function(result){
			alert(result)
		}
	}); // ajax
}); // $(".uploadResult") 

$(document).ready(function(){
	
	var regex = new RegExp("(.*?)\.(exe|sh|zip|alz)$");
	var maxSize = 5242880; // 10MB(바이트 단위 1MB = 1024KB, 1KB가 1024바이트 → 1MB는 정확히 1024*1024= 1,048,576바이트 따리서 nMB = n * 1,048,576)
	
	var cloneObj = $(".uploadDiv").clone();
	
	var uploadResult = $(".uploadResult ul");
	
	function showUploadedFile(uploadResultArr){
		var str = "";
		
		$(uploadResultArr).each(function(i, obj){
			if(!obj.image){
			       var fileCallPath =  encodeURIComponent( obj.uploadPath+"/"+ obj.uuid +"_"+obj.fileName);
			       var fileLink = fileCallPath.replace(new RegExp(/\\/g),"/");
			       
			       str += "<li><div><a href='/download?fileName="+fileCallPath+"'>"+
			    		  "<img src='/resources/img/attach.png'>"+obj.fileName+"</a>"+
			           	  "<span data-file=\'"+fileCallPath+"\' data-type='file'> x </span>"+
			           	  "<div></li>"
			} else {
			       var fileCallPath =  encodeURIComponent( obj.uploadPath+ "/s_"+obj.uuid +"_"+obj.fileName);
			       var originPath = obj.uploadPath+ "\\"+obj.uuid +"_"+obj.fileName;
			       originPath = originPath.replace(new RegExp(/\\/g),"/");
			       str += "<li><a href=\"javascript:showImage(\'"+originPath+"\')\">"+
			              "<img src='display?fileName="+fileCallPath+"'></a>"+
			              "<span data-file=\'"+fileCallPath+"\' data-type='image'> x </span>"+
			              "<li>";
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