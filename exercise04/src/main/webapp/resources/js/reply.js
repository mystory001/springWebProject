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
				  		//callback(data); //댓글 목록만 가져오는 경우
				  		callback(data.replyCnt, data.list); // 댓글 숫자와 목록을 가져오는 경우
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
	
	// 댓글 조회 처리
	function get(rno, callback, error){
		$.get("/reply/" + rno + ".json", function(result){
			if(callback){
				callback(result);
			}
		}).fail(function(xhr, status, err){
			if(error){
				error();
			}
		});
	}
	
	// 시간에 대한 처리
	function displayTime(timeValue){
		console.log("replySerivce.display()..............");
		var today = new Date();
		var gap = today.getTime() - timeValue;
		var dateObj = new Date(timeValue);
		var str = "";
		
		if(gap < (1000 * 60 * 60 * 24) ){
	        var hh = dateObj.getHours();
	        var mi = dateObj.getMinutes();
	        var ss = dateObj.getSeconds();
			
			return [(hh > 9 ? "" : "0") + hh, ":", (mi > 9 ? "" : "0") + mi, ":", (ss > 9 ? "" : "0") + ss].join("");
		} else {
	        var yy = dateObj.getFullYear();
	        var mm = dateObj.getMonth() + 1;
	        var dd = dateObj.getDate();
			
			return [yy, "/", (mm > 9 ? "" : "0") + mm, "/", (dd > 9 ? "" : "0") + dd].join("");
		}
	}; //

	return {add:add, 
			getListWithPaging : getListWithPaging, 
			drop : drop,
			update : update,
			get : get,
			displayTime : displayTime
			};

})();