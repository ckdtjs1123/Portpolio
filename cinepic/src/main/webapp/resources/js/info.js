	window.onload = function() {
		var mno = document.getElementById("mno")
		
		$.ajax({
			url : "/cinepic/review",
			type : "post",
			data : {
				"mno" : mno.value
			},
			success : function(result) {
				$(result).each( function(index,item) {
					reviewTable.innerHTML
						+=	"<tr>"
						+		"<td>" + item.id + "</td>"
						+		"<td> ★" + item.score + "</td>"
						+		"<td> ▶" + item.review + "</td>"
						+	"</tr>"
				})
			},
			error : function() {}
		})
		
		 $.ajax({
	         url : "/cinepic/score",
	         type : "post",
	         data : {
	            "mno" : mno.value
	         },
	         success : function(result) {
	        
	            $(result).each( function(index,item) {
	               scoreTable.innerHTML
	                  +=   "<tr>"
	                  +      "<td> ★  " + item + "</td>"
	                  +   "</tr>"
	            })
	         },
	         error : function() {}
	      })

	}
	
	function upload() {
		var mno = document.getElementById("mno")
		var id = document.getElementById("id")
		var score = document.getElementById("score")
		var review = document.getElementById("review")
	
		$.ajax({
			url : "/cinepic/upload",
			type : "post",
			data : {
				"mno" : mno.value,
				"id" : id.value,
				"score" : score.value,
				"review" : review.value
			},
			success : function(result) {
				reviewTable.innerHTML=""
				$(result).each( function(index,item) {
					reviewTable.innerHTML
						+=	"<tr>"
						+		"<td>" + item.id + "</td>"
						+		"<td> ★" + item.score + "</td>"
						+		"<td> ▶" + item.review + "</td>"
						+	"</tr>"
				})
			},
			error : function() {}
		})
		window.location.href=window.location.href;
		
		
	}