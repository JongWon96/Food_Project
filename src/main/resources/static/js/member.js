/**
 *  회원처리 관련 자바스크립트 함수
 */
function go_next() {
	if($(".agree")[0].checked == true) {
		$("#join").attr("action", "join_form").submit();  // 회원가입 화면을 표시요청하도록 서버에 전송
	} else if($(".agree")[1].checked == true) {
		alert("약관에 동의하셔야 가입할 수 있습니다.");
	}
}

/*
**  id중복 확인 화면 출력 요청
*/
function idcheck() {
	// id입력값 입력 확인
	if($("#uid").val() == "") {
		alert("아이디를 입력해 주세요!");
		$("#uid").focus();
		return false;
	}
	
	// id중복확인 창 오픈
	var url = "id_check_form?uid=" + $("#uid").val();
	window.open(url, "_blank_", "toolbar=no, menubar=no, scrollbars=no, " +
			"resizable=yes, width=350, height=200");
}

/*
**  전화번호 입력시 autoHyphen
*/
function autoHyphen(input) {
    // 숫자만 남기고 모든 문자 제거
    var uphone = input.value.replace(/[^\d]/g, "");

    // 전화번호 형식에 맞게 하이푼 추가
    if (uphone.length >= 4 && uphone.length <= 7) {
        input.value = uphone.slice(0, 3) + "-" + uphone.slice(3);
    } else if (uphone.length >= 8) {
        input.value = uphone.slice(0, 3) + "-" + uphone.slice(3, 7) + "-" + uphone.slice(7);
    }
}

/*
 * 회원 가입시, 필수 입력 항목 확인
 */
function go_save() {
	if ($("#uid").val() == "") {
		alert("아이디를 입력해 주세요!");
		$("#uid").focus();
		return false;
	} else if($("#uid").val() != $("#reuid").val()) {
		alert("아이디 중복 체크를 해주세요!");
		$("#uid").focus();
		return false;
	} else if ($("#upw").val() == "") {
		alert("비밀번호를 입력해 주세요!");
		$("#upw").focus();
		return false;
	} else if($("#upw").val() != $("#pwdCheck").val()) {
		alert("비밀번호가 일치하지 않습니다!");
		$("#upw").focus();
		return false;
	} else if ($("#uname").val() == "") {
		alert("이름을 입력해 주세요!");
		return false;
	} else if ($("#uphone").val() == "") {
		alert("전화번호를 입력해 주세요!");
		return false;
	} else if ($("#ugender").val() == "") {
	alert("성별을 입력해 주세요!");
		return false;	
	} else if ($("#uage").val() == "") {
	alert("나이를 입력해 주세요!");
		return false;
	} else if ($("#uweight").val() == "") {
	alert("몸무게를 입력해 주세요!");
		return false;
	} else if ($("#uheight").val() == "") {
	alert("키를 입력해 주세요!");
		return false;
	} else if ($("#ugoal").val() == "") {
	alert("목표체중을 입력해 주세요!");
		return false;
	} else if ($("#uallergy").val() == "") {
	alert("알레르기를 입력해 주세요!");
		return false;									
	} else {
		// 회원 가입 요청
		$("#join").attr("action", "join").submit();
	}
}

/*
**  아이디 찾기 화면 요청
*/
function find_id_form() {
	var url = "find_id_form";
	
	window.open(url, "_blank_", "toolbar=no, menubar=no, scrollbars=no, " +
			"resizable=yes, width=550, height=450");
}

/*
**  비밀번호 찾기 화면 요청
*/
function find_pwd_form() {
	var url = "find_pwd_form";
	
	window.open(url, "_blank_", "toolbar=no, menubar=no, scrollbars=no, " +
			"resizable=yes, width=550, height=450");
}

/*
**  아이디 찾기 요청
*/
function findMemberId() {
	if ($("#uname").val() == "") {
		alert("이름을 입력해 주세요.");
		$("#uname").focus();
		return false;
	} else if ($("#uphone").val() == "") {
		alert("전화번호를 입력해 주세요.");
		$("#uphone").focus();
		return false;
	} else {
		var form = $("#findId");
		form.action = "find_id";  // 컨트롤러 요청 URL
		form.submit();  // 컨트롤러로 전송
	}
}

/*
**  비밀번호 찾기 요청
*/
function findPassword() {
	if ($("#uid").val() == "") {
		alert("아이디를 입력해 주세요.");
		$("#uid").focus();
		return false;
	} else if ($("#uname").val() == "") {
		alert("이름을 입력해 주세요.");
		$("#uname").focus();
		return false;
	} else if ($("#uphone").val() == "") {
		alert("전화번호를 입력해 주세요.");
		$("#uphone").focus();
		return false;
	} else {
		var form = $("#findPW");
		form.action = "find_pwd";  // 컨트롤러 요청 URL
		form.submit();  // 컨트롤러로 전송
	}
}

/*
**  비밀번호 변경
*/
function changePassword() {
	console.log("changePassword()...")
	if($("#upw").val() == "") {
		alert("비밀번호를 입력해 주세요.");
		$("#upw").focus();
		return false;
	} else if($("#upw").val() != $("#pwdcheck").val()) {
		alert("비밀번호가 맞지 않습니다. 다시 입력해 주세요");
		$("#pwdcheck").focus();
		return false;		
	} else {
		$("#pwd_form").action = "change_pwd";
		$("#pwd_form").submit();
	}
}