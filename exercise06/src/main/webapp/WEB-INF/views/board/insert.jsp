<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ include file="inc/top.jsp" %>

<style>
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

   <div class="row">
       <div class="col-lg-12">
           <h1 class="page-header">board/update</h1>
       </div>
       <!-- /.col-lg-12 -->
   </div>
   <!-- /.row -->
   <div class="row">
       <div class="col-lg-12">
           <div class="panel panel-default">
               <div class="panel-heading">
                   update
               </div>
               <!-- /.panel-heading -->
               <div class="panel-body">
                   <form role="form" action="/board/insert" method="post">
                   	<div class="form-group">
                   		<label>TITLE</label>
                   		<input class="form-control" name="title">
                   	</div>
                   	<div class="form-group">
                   		<label>CONTENT</label>
                   		<textarea class="form-control" rows="3" name="content"></textarea>
                   	</div>
                   	<div class="form-group">
                   		<label>WRITER</label>
                   		<input class="form-control" name="writer">
                   	</div>
                   	<button type="submit" class="btn btn-default">Submit Button</button>
                   	<button type="reset" class="btn btn-default">Reset Button</button>
                   </form>
                   
               </div>
               <!-- /.panel-body -->
           </div>
           <!-- /.panel -->
       </div>
       <!-- /.col-lg-6 -->
   </div>
   <!-- /.row -->

<div class="row">
  <div class="col-lg-12">
    <div class="panel panel-default">

      <div class="panel-heading">File Attach</div>
      <!-- /.panel-heading -->
<div class="panel-body">
  <div class="form-group uploadDiv">
      <input type="file" name='uploadFile' multiple>
  </div>
  
  <div class='uploadResult'> 
    <ul>
    
    </ul>
  </div>
  
  
</div>
<!--  end panel-body -->

</div>
<!--  end panel-body -->
</div>
<!-- end panel -->
</div>
<!-- /.row -->

<script>
$(document).ready(function(e){

  var formObj = $("form[role='form']");
  
  $("button[type='submit']").on("click", function(e){
    e.preventDefault();
    console.log("submit clicked");
    
    var str = "";
    
    $(".uploadResult ul li").each(function(i, obj){
        
        var jobj = $(obj);
        
        console.dir(jobj);
        
        console.log("-------------------------");
        console.log(jobj.data("filename"));
        
        str += "<input type='hidden' name='attachList["+i+"].fileName' value='"+jobj.data("filename")+"'>";
        str += "<input type='hidden' name='attachList["+i+"].uuid' value='"+jobj.data("uuid")+"'>";
        str += "<input type='hidden' name='attachList["+i+"].uploadPath' value='"+jobj.data("path")+"'>";
        str += "<input type='hidden' name='attachList["+i+"].fileType' value='"+ jobj.data("type")+"'>";
        
      });
      console.log(str);
      formObj.append(str).submit();
    });// $("button[type='submit']")
    
 }); // $(document)
  
  var regex = new RegExp("(.*?)\.(exe|sh|zip|alz)$");
  var maxSize = 5242880; //5MB
  
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
  } // checkExtension
  
  $("input[type='file']").change(function(e){
    var formData = new FormData();
    var inputFile = $("input[name='uploadFile']");
    var files = inputFile[0].files;
    
    for(var i = 0; i < files.length; i++){
      if(!checkExtension(files[i].name, files[i].size) ){
        return false;
      }
      formData.append("uploadFile", files[i]);
    }
    
    $.ajax({
      url: '/uploadAjaxAction',
      processData: false, 
      contentType: false,
      data: formData,
      type: "post",
      dataType: "json",
      success: function(result){
          console.log(result); 
		  showUploadResult(result); //업로드 결과 처리 함수 
      }
    }); //$.ajax
  }); // $("input[type='file']")  
  
  function showUploadResult(uploadResultArr){
    if(!uploadResultArr || uploadResultArr.length == 0){ 
    	return; 
    }
    var uploadUL = $(".uploadResult ul");
    var str ="";
    $(uploadResultArr).each(function(i, obj){
        //image type
//         if(obj.image){
//           var fileCallPath = encodeURIComponent(obj.uploadPath + "/s_" + obj.uuid + "_" + obj.fileName);

//           str += "<li><div>";
//           str += "<span>"+obj.fileName+"</span>";
//           str += "<button type='button' data-file=\'"+fileCallPath+"\' data-type='image' class='btn btn-warning btn-circle'><i class='fa fa-times'></i></button><br>";
//           str += "<img src='/display?fileName="+fileCallPath+"'>";
//           str += "</div>";
//           str +"</li>";
//         }else{
//           var fileCallPath =  encodeURIComponent( obj.uploadPath+"/"+ obj.uuid +"_"+obj.fileName);            
//           var fileLink = fileCallPath.replace(new RegExp(/\\/g),"/");

//           str += "<li><div>";
//           str += "<span> "+ obj.fileName+"</span>";
//           str += "<button type='button' data-file=\'"+fileCallPath+"\' data-type='file' class='btn btn-warning btn-circle'><i class='fa fa-times'></i></button><br>";
//           str += "<img src='/resources/img/attach.png'></a>";
//           str += "</div>";
//           str +"</li>";
//         }
		
		if(obj.image){
			var fileCallPath = encodeURIComponent(obj.uploadPath + "/s_" + obj.uuid + "_" + obj.fileName);

			str += "<li data-path='"+obj.uploadPath+"'";
			str +=" data-uuid='"+obj.uuid+"'data-filename='"+obj.fileName+"'data-type='"+obj.image+"'"
			str +" ><div>";
			str += "<span> "+ obj.fileName+"</span>";
			str += "<button type='button' data-file=\'"+fileCallPath+"\'"
			str += "data-type='image' class='btn btn-warning btn-circle'><i class='fa fa-times'></i></button><br>";
			str += "<img src='/display?fileName="+fileCallPath+"'>";
			str += "</div>";
			str + "</li>";
		}else{
			var fileCallPath =  encodeURIComponent(obj.uploadPath + "/" + obj.uuid + "_" + obj.fileName);			      
		    var fileLink = fileCallPath.replace(new RegExp(/\\/g),"/");
		      
			str += "<li "
			str += "data-path='"+obj.uploadPath+"'data-uuid='"+obj.uuid+"'data-filename='"+obj.fileName+"'data-type='"+obj.image+"'><div>";
			str += "<span>"+obj.fileName+"</span>";
			str += "<button type='button'data-file=\'"+fileCallPath+"\'data-type='file' " 
			str += "class='btn btn-warning btn-circle'><i class='fa fa-times'></i></button><br>";
			str += "<img src='/resources/img/attach.png'></a>";
			str += "</div>";
			str + "</li>";
		}
    }); //  $(uploadResultArr).each(function(i, obj)
    uploadUL.append(str);
  } // showUploadResult

  $(".uploadResult").on("click", "button", function(e){
	  console.log("delete file");
	  
	  var targetFile = $(this).data("file");
	  var type = $(this).data("type");
	  
	  var targetLi = $(this).closest("li");
	  
	  $.ajax({
		  url : "/deleteFile",
		  data : {fileName : targetFile, type : type},
		  dataType : "text",
		  type : "post",
		  success : function(result){
			  alert(result);
			  targetLi.remove();
		  }
	  }); // $.ajax
	  
  }) //  $(".uploadResult")
</script>
        
<%@ include file="inc/bottom.jsp" %>