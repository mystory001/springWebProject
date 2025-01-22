console.log("reply.js");

var replyService = (function() {

	// 댓글 추가
	function add(reply, callback, error){
		$.ajax({
			type : 'post',
			url : '/reply/new',
			data : JSON.stringify(reply),
			contentType : "application/json; charset=utf-8",
			success : function(result, status, xhr){
				if(callback)
					callback(result);
			},
			error : function(xhr, status, er){
				if(error){
					error(er);
				}
			}
		})
	}
	
	// 댓글 조회
	function getListWithPaging(param, callback, error){
		var bno = param.bno;
		var page = param.page || 1;
		
		$.getJSON("/reply/pages/" + bno + "/" + page + ".json",
				  function(data){
				  	if(callback)
				  		callback(data); //댓글 목록만 가져오는 경우
				  }).fail(function(xhr, status, err){
				  		if(error)
				  			error();
				  	});
	}
	
	// 댓글 삭제 → delete는 예약어이기 때문에 함수로 만들 수 없음
	function drop(rno, callback, error){
		$.ajax({
			type : 'delete',
			url : '/reply/' + rno,
			success : function(deleteResult, status, xhr) {
				if(callback)
					callback(deleteResult);
			},
			error : function(xhr, status, er) {
				if(error){
					error();
				}
			}
		});
	}
	
	// 댓글 수정
	function update(replyVO, callback, error){
		console.log("rno : " + replyVO.rno);
		$.ajax({
			type : "put",
			url : "/reply/" + replyVO.rno,
			data : JSON.stringify(replyVO),
			contentType : "application/json; charset=utf-8",
			success : function(result, status, xhr){
				if(callback){
					callback(result);
				}
			},
			error : function(xhr, status, er){
				if(error){
					error(er);
				}
			}
		}); // ajax
	} // 닫는 괄호
	

	return {add:add, 
			getListWithPaging : getListWithPaging, 
			drop : drop,
			update : update
			};
})();