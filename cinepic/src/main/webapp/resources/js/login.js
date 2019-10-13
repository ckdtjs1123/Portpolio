	function login() {
		var id = document.getElementById("id")
		var pw = document.getElementById("pw")
		var loginForm = document.getElementById("login-form")

		if (id.value.length === 0) {
			alert("아이디를 입력하세요")
			return;
		}

		if (id.value.length > 10 || id.value.length < 4) {
			alert("아이디는 4~10자로 입력하세요")
			return;
		}

		if (pw.value.length === 0) {
			alert("비밀번호를 입력하세요")
			return;
		}

		if (pw.value.length > 20 || pw.value.length < 8) {
			alert("비밀번호는 8~20자로 입력하세요")
			return;
		}

		loginForm.submit();
	}

	function find() {
		window.location.href = "/cinepic/find"
	}