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
		<h1 class="page-header">board/get</h1>
	</div>
</div>

<div class="row">
	<div class="col-lg-12">
		<div class="panel panel-default">
			<div class="panel-heading">Read</div>
			<div class="panel-body">
				<div class="form-group">
					<label>BNO</label> <input class="form-control" name='bno'
						value='<c:out value="${boardVO.bno }"/>' readonly="readonly">
				</div>
				<div class="form-group">
					<label>TITLE</label> <input class="form-control" name='title'
						value='<c:out value="${boardVO.title }"/>' readonly="readonly">
				</div>
				<div class="form-group">
					<label>CONTENT</label>
					<textarea class="form-control" rows="3" name='content'
						readonly="readonly"><c:out value="${boardVO.content}" /></textarea>
				</div>

				<div class="form-group">
					<label>WRITER</label> <input class="form-control" name='writer'
						value='<c:out value="${boardVO.writer }"/>' readonly="readonly">
				</div>
				<button data-oper='update' class="btn btn-default">Update</button>
				<button data-oper='list' class="btn btn-info">List</button>
				<form id='operForm' action="/board/update" method="get">
					<input type='hidden' id='bno' name='bno'
						value='<c:out value="${boardVO.bno}"/>'> <input
						type='hidden' name='pageNum'
						value='<c:out value="${criteria.pageNum}"/>'> <input
						type='hidden' name='amount'
						value='<c:out value="${criteria.amount}"/>'> <input
						type='hidden' name='keyword'
						value='<c:out value="${criteria.keyword}"/>'> <input
						type='hidden' name='type'
						value='<c:out value="${criteria.type}"/>'>
				</form>
			</div>
		</div>
	</div>
</div>

<div class='bigPictureWrapper'>
  <div class='bigPicture'>
  </div>
</div>

<div class="row">
	<div class="col-lg-12">
		<div class="panel panel-default">
			<div class="panel-heading">Files</div>
			<div class="panel-body">
				<div class='uploadResult'>
					<ul></ul>
				</div>
			</div>
		</div>
	</div>
</div>

<div class='row'>
	<div class="col-lg-12">
		<div class="panel panel-default">
     	 <div class="panel-heading">
	        <i class="fa fa-comments fa-fw"></i> Reply
	        <button id='addReplyBtn' class='btn btn-primary btn-xs pull-right'>New Reply</button>
      	</div>      
		<div class="panel-body">        
		        <ul class="chat"></ul>
	    </div>
		<div class="panel-footer"></div>
		</div>
	</div>
</div>

<div class="modal fade" id="myModal" tabindex="-1" role="dialog"
	aria-labelledby="myModalLabel" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
				<h4 class="modal-title" id="myModalLabel">REPLY MODAL</h4>
			</div>
			<div class="modal-body">
				<div class="form-group">
					<label>Reply</label> <input class="form-control" name='reply' value='New Reply'>
				</div>
				<div class="form-group">
					<label>Replyer</label> <input class="form-control" name='replyer' value='replyer'>
				</div>
				<div class="form-group">
					<label>Reply Date</label> <input class="form-control" name='replydate' value='2018-01-01 13:13'>
				</div>
			</div>
			<div class="modal-footer">
				<button id='modalModBtn' type="button" class="btn btn-warning">Update</button>
				<button id='modalRemoveBtn' type="button" class="btn btn-danger">Delete</button>
				<button id='modalRegisterBtn' type="button" class="btn btn-primary">Insert</button>
				<button id='modalCloseBtn' type="button" class="btn btn-default">Close</button>
			</div>
		</div>
	</div>
</div>

<script type="text/javascript" src="/resources/js/reply.js"></script>

<script>

$(document).ready(function () {
  
  var bnoValue = '<c:out value="${boardVO.bno}"/>';
  var replyUL = $(".chat");
  
    showList(1);
    
    function showList(page){
    	
    	console.log("show list " + page);
        
        replyService.getList({bno:bnoValue,page: page|| 1 }, function(replyCnt, list) {
          
        console.log("replyCnt: "+ replyCnt );
        console.log("list: " + list);
        console.log(list);
        
        if(page == -1){
          pageNum = Math.ceil(replyCnt/10.0);
          showList(pageNum);
          return;
        }
          
         var str="";
         
         if(list == null || list.length == 0){
           return;
         }
         
         for (var i = 0, len = list.length || 0; i < len; i++) {
           str +="<li class='left clearfix' data-rno='"+list[i].rno+"'>";
           str +="  <div><div class='header'><strong class='primary-font'>["+list[i].rno+"] "+list[i].replyer+"</strong>"; 
           str +="    <small class='pull-right text-muted'>"+replyService.displayTime(list[i].replydate)+"</small></div>";
           str +="    <p>"+list[i].reply+"</p></div></li>";
         }
         
         replyUL.html(str);
         
         showReplyPage(replyCnt);
     
       });//end function
         
     }//end showList
    
    var pageNum = 1;
    var replyPageFooter = $(".panel-footer");
    
    function showReplyPage(replyCnt){
      
      var endNum = Math.ceil(pageNum / 10.0) * 10;  
      var startNum = endNum - 9; 
      
      var prev = startNum != 1;
      var next = false;
      
      if(endNum * 10 >= replyCnt){
        endNum = Math.ceil(replyCnt/10.0);
      }
      
      if(endNum * 10 < replyCnt){
        next = true;
      }
      
      var str = "<ul class='pagination pull-right'>";
      
      if(prev){
        str+= "<li class='page-item'><a class='page-link' href='"+(startNum -1)+"'>Previous</a></li>";
      }
      
      for(var i = startNum ; i <= endNum; i++){
        var active = pageNum == i? "active":"";
        str+= "<li class='page-item "+active+" '><a class='page-link' href='"+i+"'>"+i+"</a></li>";
      }
      
      if(next){
        str+= "<li class='page-item'><a class='page-link' href='"+(endNum + 1)+"'>Next</a></li>";
      }
      str += "</ul></div>";
      console.log(str);
      replyPageFooter.html(str);
    }
     
    replyPageFooter.on("click","li a", function(e){
        e.preventDefault();
        console.log("page click");
        var targetPageNum = $(this).attr("href");
        console.log("targetPageNum: " + targetPageNum);
        pageNum = targetPageNum;
        showList(pageNum);
      });     

   
    var modal = $(".modal");
    var modalInputReply = modal.find("input[name='reply']");
    var modalInputReplyer = modal.find("input[name='replyer']");
    var modalInputReplyDate = modal.find("input[name='replydate']");
    
    var modalModBtn = $("#modalModBtn");
    var modalRemoveBtn = $("#modalRemoveBtn");
    var modalRegisterBtn = $("#modalRegisterBtn");
    
    $("#modalCloseBtn").on("click", function(e){
    	modal.modal('hide');
    });
    
    $("#addReplyBtn").on("click", function(e){
      modal.find("input").val("");
      modalInputReplyDate.closest("div").hide();
      modal.find("button[id !='modalCloseBtn']").hide();
      modalRegisterBtn.show();
      $(".modal").modal("show");
    });

    modalRegisterBtn.on("click",function(e){
      var reply = {
            reply: modalInputReply.val(),
            replyer:modalInputReplyer.val(),
            bno:bnoValue
          };
      replyService.add(reply, function(result){
        alert(result);
        modal.find("input").val("");
        modal.modal("hide");
        //showList(1);
        showList(-1);
      });
    });


  //댓글 조회 클릭 이벤트 처리 
    $(".chat").on("click", "li", function(e){
      var rno = $(this).data("rno");
      replyService.get(rno, function(reply){
        modalInputReply.val(reply.reply);
        modalInputReplyer.val(reply.replyer);
        modalInputReplyDate.val(replyService.displayTime( reply.replydate))
        .attr("readonly","readonly");
        modal.data("rno", reply.rno);
        modal.find("button[id !='modalCloseBtn']").hide();
        modalModBtn.show();
        modalRemoveBtn.show();
        $(".modal").modal("show");
            
      });
    });
  
    
/*     
	modalModBtn.on("click", function(e){
      var reply = {rno:modal.data("rno"), reply: modalInputReply.val()};
      replyService.update(reply, function(result){
        alert(result);
        modal.modal("hide");
        showList(1);
        
      });
    });

    modalRemoveBtn.on("click", function (e){
  	  var rno = modal.data("rno");
  	  replyService.remove(rno, function(result){
  	      alert(result);
  	      modal.modal("hide");
  	      showList(1);
  	  });
  	}); 
*/

    modalModBtn.on("click", function(e){
   	  var reply = {rno:modal.data("rno"), reply: modalInputReply.val()};
   	  replyService.update(reply, function(result){
   	    alert(result);
   	    modal.modal("hide");
   	    showList(pageNum);
   	  });
   	});

   	modalRemoveBtn.on("click", function (e){
   	  var rno = modal.data("rno");
   	  replyService.remove(rno, function(result){
   	      alert(result);
   	      modal.modal("hide");
   	      showList(pageNum);
   	  });
   	});
 
});
</script>

<!-- 테스트 -->
<script>
/* 
console.log("===============");
console.log("JS TEST");
var bnoValue = '<c:out value="${board.bno}"/>'; 
*/

/* 
// 댓글 추가
replyService.add(
    
    {reply:"JS Test", replyer:"tester", bno:bnoValue}
    ,
    function(result){ 
      alert("RESULT: " + result);
    }
);
*/

/* 
// 댓글 리스트
replyService.getList({bno:bnoValue, page:1}, function(list){
	  for(var i = 0,  len = list.length||0; i < len; i++ ){
	    console.log(list[i]);
	  }
});
*/
 
/*  
// 댓글 삭제 
 replyService.remove(17, function(count) {
   console.log(count);
   if (count === "success") {
     alert("REMOVED");
   }
 }, function(err) {
   alert('ERROR...');
 });
*/
 
/* 
// 댓글 수정 
replyService.update({
  rno : 12,
  bno : bnoValue,
  reply : "Modified Reply...."
}, function(result) {

  alert("수정 완료...");

});  
 */
</script>  


<script type="text/javascript">
$(document).ready(function() {
  var operForm = $("#operForm"); 
  $("button[data-oper='modify']").on("click", function(e){
    operForm.attr("action","/board/modify").submit();
  });
  
    
  $("button[data-oper='list']").on("click", function(e){
    operForm.find("#bno").remove();
    operForm.attr("action","/board/list")
    operForm.submit();
    
  });  
});
</script>


<script>
$(document).ready(function(){
  (function(){
    var bno = '<c:out value="${board.bno}"/>';
    $.getJSON("/board/getAttachList", {bno: bno}, function(arr){
       console.log(arr);
       var str = "";
       $(arr).each(function(i, attach){
         //image type
         if(attach.fileType){
           var fileCallPath =  encodeURIComponent( attach.uploadPath+ "/s_"+attach.uuid +"_"+attach.fileName);
           
           str += "<li data-path='"+attach.uploadPath+"' data-uuid='"+attach.uuid+"' data-filename='"+attach.fileName+"' data-type='"+attach.fileType+"' ><div>";
           str += "<img src='/display?fileName="+fileCallPath+"'>";
           str += "</div>";
           str +"</li>";
         }else{
           str += "<li data-path='"+attach.uploadPath+"' data-uuid='"+attach.uuid+"' data-filename='"+attach.fileName+"' data-type='"+attach.fileType+"' ><div>";
           str += "<span> "+ attach.fileName+"</span><br/>";
           str += "<img src='/resources/img/attach.png'></a>";
           str += "</div>";
           str +"</li>";
         }
       });
       
       $(".uploadResult ul").html(str);
       
     });//end getjson
    
  })();//end function
  
  $(".uploadResult").on("click","li", function(e){
    console.log("view image");
    var liObj = $(this);
    var path = encodeURIComponent(liObj.data("path")+"/" + liObj.data("uuid")+"_" + liObj.data("filename"));
    if(liObj.data("type")){
      showImage(path.replace(new RegExp(/\\/g),"/"));
    }else {
      //download 
      self.location ="/download?fileName="+path
    }
  });
  
  function showImage(fileCallPath){
    alert(fileCallPath);
    $(".bigPictureWrapper").css("display","flex").show();
    $(".bigPicture")
    .html("<img src='/display?fileName="+fileCallPath+"' >")
    .animate({width:'100%', height: '100%'}, 1000);
  }

  $(".bigPictureWrapper").on("click", function(e){
    $(".bigPicture").animate({width:'0%', height: '0%'}, 1000);
    setTimeout(function(){
      $('.bigPictureWrapper').hide();
    }, 1000);
  });
  
});
</script>

<%@ include file="inc/bottom.jsp" %>
