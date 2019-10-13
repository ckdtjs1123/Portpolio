	window.onload = function() {
		var id = document.getElementById("id")
		
		$.ajax({
			url : "/cinepic/myreview",
			type : "post",
			data : {
				"id" : id.value
			},
			success : function(result) {
				$(result).each( function(index,item) {
					reviewTable.innerHTML
						+=	"<tr>"
						+		"<td><a href='/cinepic/info?mno=" + item.mno + "'>" + item.mno + "</a></td>	"
						+		"<td> " + item.id + "</td>"
						+		"<td> ★" + item.score + "</td>"
						+		"<td> ▶" + item.review + "</td>"
						+		"<td> <input type='radio' name='radio' class='radio' value=" + item.mno + "> </td>"
						+	"</tr>"
				})
			},
			error : function() {}
		})
	}
	
	function deleteReview() {
		var id = document.getElementById("id")
		var radio = document.getElementsByClassName("radio")
		var check = null;
		
		$(radio).each(function(index, item) {
			if(item.checked===true){
				check=item;
				return;
			}
		})
		
		if(check===null){
			alert("Please check memo before delete");
			return;
		}
		
		$.ajax({
			"url" : "/cinepic/deleteReview",
			"type" : "post",
			"data" : {
				"mno" : check.value,
				"id" : id.value
			},
			"success" : function(result) {
				check.parentNode.parentNode.remove();
			},
			"error" : function() {}
		})
		
	}