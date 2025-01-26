<%@ page language="java" contentType="text/html; charset=UTF-8"
     pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ include file="inc/top.jsp" %>

            <div class="row">
                <div class="col-lg-12">
                    <h1 class="page-header">board/get</h1>
                </div>
            </div>
            <div class="row">
                <div class="col-lg-12">
                    <div class="panel panel-default">
                        <div class="panel-heading">
                            read
                        </div>
                        <div class="panel-body">
                            	<div class="form-group">
                            		<label>BNO</label>
                            		<input class="form-control" name="bno" value="<c:out value='${boardVO.bno}'/>" readonly>
                            	</div>
                            	<div class="form-group">
                            		<label>TITLE</label>
                            		<input class="form-control" name="title" value="<c:out value='${boardVO.title}'/>" readonly>
                            	</div>
                            	<div class="form-group">
                            		<label>CONTENT</label>
                            		<textarea class="form-control" rows="3" name="content" readonly><c:out value='${boardVO.content}'/></textarea>
                            	</div>
                            	<div class="form-group">
                            		<label>WRITER</label>
                            		<input class="form-control" name="writer" value="<c:out value='${boardVO.writer}'/>" readonly>
                            	</div>
                            	<button data-oper="update" class="btn btn-default">Update</button>
                            	<button data-oper="list" class="btn btn-info">List</button>
                            <form id="operForm" action="/board/update" method="get">
                            	<input type="hidden" name="bno" id="bno" value="<c:out value='${boardVO.bno }'/>"> 
                            	<input type="hidden" name="pageNum" value="<c:out value='${criteria.pageNum}'/>"> 
                            	<input type="hidden" name="amount" value="<c:out value='${criteria.amount}'/>"> 
                            	<input type="hidden" name="keyword" value="<c:out value='${criteria.keyword}'/>"> 
                            	<input type="hidden" name="type" value="<c:out value='${criteria.type}'/>"> 
                            </form>
                            <br>
                            <div class="row">
                            	<div class="col-lg-12">
                            		<div class="panel panel-default">
                            			<div class="panel panel-heading">
                            				<i class="fa fa-comments fa-fw"></i>Reply
                            				<button id="addReplyBtn" class="btn btn-primary btn-xs pull-right">New Reply</button>
                            			</div>
                            			
                            			<div class="panel-body">
                            				<ul class="chat">
                            					<li class="left clearfix" data-rno="5">
                            						<div>
                            							<div class="header">
                            								<strong class="primary-font">Replyer</strong>
                            								<small class="pull-right text-muted">2025-01-22 22:05</small>
                            							</div>
                            							<p>Hello World</p>
                            						</div>
                            					</li>
                            				</ul>
				                            <div class="panel-footer"></div>
                            			</div>
                            		</div>
                            	</div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>

<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
				<h4 class="modal-title" id="myModalLabel">REPLY MODAL</h4>
			</div>
			<div class="modal-body">
				<div class="form-grouop">
					<label>Reply</label>
					<input class="form-control" name="reply" value="reply">
				</div>
				<div class="form-group">
					<label>Replyer</label>
					<input class="form-control" name="replyer" value="replyer">
				</div>
				<div class="form-group">
					<label>Reply Date</label>
					<input class="form-control" name="replydate" value="">
				</div>
			</div>
			<div class="modal-footer">
				<button id="modalModBtn" type="button" class="btn btn-warning">Update</button>
				<button id="modalRemoveBtn" type="button" class="btn btn-danger">Delete</button>
				<button id="modalRegisterBtn" type="button" class="btn btn-primary">Insert</button>
				<button id="modalCloseBtn" type="button" class="btn btn-default">Close</button>
			</div>
		</div>
	</div>
</div>


<script type="text/javascript" src="/resources/js/reply.js"></script>


<script>
// $(function() {
// 	console.log("==============");
// 	console.log("Javascript Test");
// 	var bnoValue = "<c:out value='${boardVO.bno}'/>";
	
	// add test
	/*
	 replyService.add(
		{reply:"Javascript TEST", replyer:"Javascript tester", bno:bnoValue},
		function(result){
			alert("RESULT: " + result);
		}
	); 
	*/
	
	// getList test
	/*
	replyService.getListWithPaging({bno:bnoValue, page:1}, function(list){
		for(var i = 0, len = list.length||0; i < len; i++){
			console.log(list[i]);
		}
	}); 
	*/
	
	// delete test
	/*
	replyService.drop(70, function(count) {
		console.log(count);
		if(count === "success"){
			alert("delete");
		}
		}, 
		function(err){
			alert('error');
	});
	*/
	
	// update test
	/*
	replyService.update({
		rno : 5,
		bno : bnoValue,
		reply : "수정한 댓글......."
	}, function(result){
		alert("update success");
	});
	*/
	
	// get test
	/*
	replyService.get(5, function(data){
		console.log(data);
	});
	*/
// });

</script>


<script type="text/javascript">
$(document).ready(function(){
	
	var operForm = $("#operForm");
	
	$("button[data-oper='update']").on("click", function(e){
		operForm.attr("action","/board/update").submit();
	}); // $("button[data-oper='update']").on("click", function(e){
	
	$("button[data-oper='list']").on("click", function(e){
		operForm.find("#bno").remove();
		operForm.attr("action","/board/list")
		operForm.submit();
	}); // 	$("button[data-oper='list']").on("click", function(e){
	
	var bnoValue = "<c:out value='${boardVO.bno}' />"
	var replyUL = $(".chat");
	
	showList(1);
	
// 	function showList(page){
// 		replyService.getListWithPaging({bno:bnoValue, page:page || 1}, function(list){
// 			var str ="";
// 			if(list ==null || list.length == 0){
// 				replyUL.html("");
// 				return;
// 			}
// 			for(var i = 0, len = list.length || 0; i < len ; i++){
// 				str += "<li class='left clearfix' data-rno='"+list[i].rno+"'>";
// 				str += "	<div><div class='header'><string class='primary-font'>"+list[i].replyer+"</strong>";
// 				str += "		<small class='pull-right text-muted'>"+replyService.displayTime(list[i].replydate)+"</small></div>";
// 				str += "			<p>"+list[i].reply+"</p></div></li>";
// 			}
// 			replyUL.html(str);
// 		})
	function showList(page){
		console.log(page);
		
		replyService.getListWithPaging({bno:bnoValue, page:page < 1? 1 : page}, function(replyCnt, list){
			
			if(page == -1){
				pageNum = Math.ceil(replyCnt/10.0);
				showList(pageNum);
				return;
			}
			
			var str ="";
			if(list ==null || list.length == 0){
				replyUL.html("");
				return;
			}
			
			for(var i = 0, len = list.length || 0; i < len ; i++){
				str += "<li class='left clearfix' data-rno='"+list[i].rno+"'>";
				str += "	<div><div class='header'><string class='primary-font'>"+list[i].replyer+"</strong>";
				str += "		<small class='pull-right text-muted'>"+replyService.displayTime(list[i].replydate)+"</small></div>";
				str += "			<p>"+list[i].reply+"</p></div></li>";
			}
			
			replyUL.html(str);
            showReplyPage(replyCnt);
		})
	} // function showList(page)
	
	// 댓글 추가 시작 시 버튼 이벤트 처리	
	var modal = $(".modal");
	var modalInputReply = modal.find("input[name='reply']");
	var modalInputReplyer = modal.find("input[name='replyer']");
	var modalInputReplyDate = modal.find("input[name='replydate']");
	
	var modalModBtn = $("#modalModBtn");
	var modalRemoveBtn = $("#modalRemoveBtn");
	var modalRegisterBtn = $("#modalRegisterBtn");
	
	$("#addReplyBtn").on("click",function(e){
		modal.find("input").val("");
		modalInputReplyDate.closest("div").hide();
		modal.find("button[id!='modalCloseBtn']").hide();
		
		modalRegisterBtn.show();
		
		$(".modal").modal("show");
		
	}); // $("#addReplyBtn")
	
	// 새로운 댓글 추가 처리
	modalRegisterBtn.on("click", function(e){
		
		var reply = { reply : modalInputReply.val(),
					  replyer : modalInputReplyer.val(),
					  bno : bnoValue
					};
		replyService.add(reply, function(result){
			alert(result);
			modal.find("input").val("");
			modal.modal("hide");
// 			showList(1); // 목록 갱신
			showList(-1);
		});
	}); // modalRegisterBtn.on
	
	// 댓글 이벤트 처리
	$(".chat").on("click", "li", function(e){
		var rno = $(this).data("rno");
// 		console.log(rno);
		
		replyService.get(rno, function(reply){
			modalInputReply.val(reply.reply);
			modalInputReplyer.val(reply.replyer);
			modalInputReplyDate.val(replyService.displayTime(reply.replydate)).attr("readonly","readonly");
			modal.data("rno", reply.rno);
			
			modal.find("button[id != 'modalCloseBtn']").hide();
			modalModBtn.show();
			modalRemoveBtn.show();
			$(".modal").modal("show");
		});
	}); // $(".chat")
	
	// 댓글 수정
	modalModBtn.on("click", function(e){
		var reply = {rno:modal.data("rno"), reply:modalInputReply.val()};
		
		replyService.update(reply, function(result){
			alert(result);
			modal.modal("hide");
			showList(pageNum);
		});
	}); // modalModBtn
	
	modalRemoveBtn.on("click", function(e){
		var rno = modal.data("rno");
		
		replyService.drop(rno, function(result){
			alert(result);
			modal.modal("hide");
			showList(pageNum);
		});
	}); // modalRemoveBtn
	
	var pageNum = 1;
	var replyPageFooter = $(".panel-footer");
	
    function showReplyPage(replyCnt) {
        var endNum = Math.ceil(pageNum / 10.0) * 10;
        var startNum = endNum - 9;

        var prev = startNum != 1;
        var next = false;

        if (endNum * 10 >= replyCnt) {
            endNum = Math.ceil(replyCnt / 10.0);
        }
        if (endNum * 10 < replyCnt) {
            next = true;
        }

        var str = "<ul class='pagination pull-right'>";
        if (prev) {
            str += "<li class='page-item'><a class='page-link' href='" + (startNum - 1) + "'>Previous</a></li>";
        }
        for (var i = startNum; i <= endNum; i++) {
            var active = pageNum == i ? "active" : "";

            str += "<li class='page-item " + active + "'><a class='page-link' href='" + i + "'>" + i + "</a></li>";
        }
        if (next) {
            str += "<li class='page-item'><a class='page-link' href='" + (endNum + 1) + "'>Next</a></li>";
        }
        str += "</ul>";

        replyPageFooter.html(str);
    }; // showReplyPage(replyCnt) 
    
    replyPageFooter.on("click", "li a", function (e) {
        e.preventDefault();
        console.log("page click");

        var targetPageNum = $(this).attr("href");
        console.log("targetPageNum: " + targetPageNum);

        pageNum = targetPageNum;
        showList(pageNum);
    });	// replyPageFooter
	
}); // $(document).ready(function()
</script>

<%@ include file="inc/bottom.jsp" %>
