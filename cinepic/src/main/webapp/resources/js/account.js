	function update() {
		var id = document.getElementById("id");
		var pw = document.getElementById("pw");
		var pwr = document.getElementById("pwr");
		var name = document.getElementById("name");
		var email = document.getElementById("email");
		var phone = document.getElementById("phone");
			
		if(pw.value.length===0) {
			alert("비밀번호를 입력하세요")
			return;
		}
		
		if(pw.value.length>20||pw.value.length<8) {
			alert("비밀번호는 8~20자로 입력하세요")
			return;
		}
		
		if(pw.value!==pwr.value) {
			alert("비밀번호가 다릅니다")
			return;
		}
		
		if(email.value.length===0) {
			alert("이메일을 입력하세요")
			return;
		}
		
		if(phone.value.length===0) {
			alert("전화번호를 입력하세요")
			return;
		}
		
		if(isNaN(phone.value)===true) {
			alert("숫자만 입력하세요")
			return;
		}
		
		$.ajax({
			url : "/cinepic/updateUser",
			type : "post",
			data : {
				"id" : id.value,
				"pw" : pw.value,
				"name" : name.value,
				"email" : email.value,
				"phone" : phone.value
			},
			success : function(result) {
				alert(result);
				window.location.href="/cinepic/";
			},
			error : function() {}
		})
	}
	
	function withdraw() {
		
		var id = document.getElementById("id");
		var pw = document.getElementById("pw");
		var pwr = document.getElementById("pwr");
		
		if(pw.value.length===0) {
			alert("비밀번호를 입력하세요")
			return;
		}
		
		if(pw.value.length>20||pw.value.length<8) {
			alert("비밀번호는 8~20자로 입력하세요")
			return;
		}
		
		if(pw.value!==pwr.value) {
			alert("비밀번호가 다릅니다")
			return;
		}
		
		$.ajax({
			url : "/cinepic/deleteUser",
			type : "post",
			data : {
				"id" : id.value,
				"pw" : pw.value
			},
			success : function(result) {
					alert(result);
					window.location.href="/cinepic/";
			},
			error : function() {}
		})
	}