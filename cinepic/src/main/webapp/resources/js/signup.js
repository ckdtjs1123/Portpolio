	function signup() {
		var id = document.getElementById("id1");
		var pw = document.getElementById("pw1");
		var pwr = document.getElementById("pwr");
		var name = document.getElementById("name");
		var email = document.getElementById("email");
		var phone = document.getElementById("phone");
		var signupForm = document.getElementById("register-form");
			
		if(id.value.length===0) {
			alert("아이디를 입력하세요")
			return;
		}
		
		if(id.value.length>10||id.value.length<4) {
			alert("아이디는 4~10자로 입력하세요")
			return;
		}
		
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
		
		if(name.value.length===0) {
			alert("이름을 입력하세요")
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
		
		signupForm.submit();
	}