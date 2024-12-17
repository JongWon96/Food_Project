/**
 *  회원처리 관련 자바스크립트 함수
 */
function go_next() {
	if ($(".agree")[0].checked ==  true) {
		$("#join").attr("action", "join_form").submit();   // 회원가입 화면을 표시요청하도록 서버에 전송
	} else if($(".agree")[1].checked == true) {
		alert("약관에 동의하셔야 가입할 수 있습니다."); 
	}
}

/*
**  id중복 확인 화면 출력 요청
*/
function idcheck() {
	// id입력값 입력 확인
	if ($("#id").val() == "") {
		alert("아이디를 입력해 주세요!");
		$("#id").focus();
		return false;
	}
	
	// id중복확인 창 오픈
	var url = "id_check_form?id=" + $("#id").val();
	window.open(url, "_blank_", "toolbar=no, menubar=no, scrollbars=no, " +
			"resizable=yes, width=350, height=200");
}

/*
 * 회원 가입시, 필수 입력 항목 확인
 */
function go_save() {
	if ($("#id").val() == "") {
		alert("아이디를 입력해 주세요!");
		$("#id").focus();
		return false;
	} else if($("#id").val() != $("#reid").val()) {
		alert("아이디 중복 체크를 해주세요!");
		$("#id").focus();
		return false;
	} else if ($("#pwd").val() == "") {
		alert("비밀번호를 입력해 주세요!");
		$("#pwd").focus();
		return false;
	} else if($("#pwd").val() != $("#pwdCheck").val()) {
		alert("비밀번호가 일치하지 않습니다!");
		$("#pwd").focus();
		return false;
	} else if ($("#name").val() == "") {
		alert("이름을 입력해 주세요!");
		return false;
	} else if ($("#email").val() == "") {
		alert("이메일을 입력해 주세요!");
		return false;
	} else {
		// 회원 가입 요청
		$("#join").attr("action", "join").submit();
	}
}

/*
** 우편번호 찾기 창 열기
*/
function post_zip() {
	var url = "find_zip_num";
	
	window.open(url, "_blank_", "toolbar=no, menubar=no, scrollbars=no, " +
		"resizable=yes, width=500, height=350");
}

/*
** 아이디, 비밀번호 찾기 화면 요청
*/
function find_id_form() {
	var url = "find_id_form";
	
	window.open(url, "_blank_", "toolbar=no, menubar=no, scrollbars=no, " +
		"resizable=yes, width=550, height=450");
}

/*
** 아이디 찾기 요청
*/
function findMemberId() {
	if ($("#name").val() == "") {
		alert("이름을 입력해 주세요.");
		$("#name").focus();
		return false;
	} else if ($("#email").val() == "") {
		alert("이메일을 입력해 주세요.");
		$("#email").focus();
		return false;
	} else {
		var form = $("#findId");
		form.action = "find_id";  // 컨트롤러 요청 URL
		form.submit();  // 컨트롤러로 전송
	}
}

/*
** 비밀번호 찾기 요청
*/
function findPassword() {
	if ($("#id2").val() == "") {
		alert("아이디를 입력해 주세요.");
		$("#id2").focus();
		return false;
	} else if ($("#name2").val() == "") {
		alert("이름을 입력해 주세요.");
		$("#name2").focus();
		return false;
	} else if ($("#email2").val() == "") {
		alert("이메일을 입력해 주세요.");
		$("#email2").focus();
		return false;
	} else {
		var form = $("#findPW");
		form.action = "find_pwd";  // 컨트롤러 요청 URL
		form.submit();  // 컨트롤러로 전송
	}
}

/*
** 비밀번호 변경
*/
function changePassword() {
	if ($("#pwd").val() == "") {
		alert("비밀번호를 입력해 주세요.");
		$("#pwd").focus();
		return false;
	} else if ($("#pwd").val() != $("#pwdcheck").val()) {
		alert("비밀번호가 맞지 않습니다. 다시 입력해 주세요");
		$("#pwdcheck").focus();
		return false;
	} else {
		$("#pwd_form").action = "change_pwd";
		$("#pwd_form").submit();
	}
}
















