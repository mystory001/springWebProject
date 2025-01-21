console.log("Reply Module.......................");

var replyService = (function(){

	function add(reply, callback){
		console.log("reply............."); 
		
		$.ajax({
			type: "post",
			url : "/reply/new",
			data : JSON.stringify(reply),
			contentType : "application/json; charset=utf-8",
			success : function(result, status, xhr){
				if(callback){
					callback(result);
				}
			},
			error : function(xhr,status, e){
				if(error){
					error(e);
				}
			}
		})
	}
	return {add:add};
})();

