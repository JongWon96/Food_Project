/**
 * 상품 등록 페이지 표시
 */
function go_wrt() {
	$("#prod_form").attr("action", "admin_product_write_form")
				   .submit();
}

/*
** price3(순익) 항목 계산
*/
function go_ab() {
	var price1 = $("#price1").val().replace(/,/g, '');  // 원가
	var price2 = $("#price2").val().replace(/,/g, '');  // 판매가
	var result = price2 - price1;     // 순익 계산
	
	$("#price3").val(result);
}

/*
** 숫자 3자리 마다 콤마 찍기
*/
function NumFormat(t) {
	num = t.value;
    // 숫자 이외의 문자 제거
    num = num.replace(/\D/g, '');
    // 숫자 3자리 마다 콤마 추가
    len = num.length - 3;
    while (len > 0) {
        num = num.substr(0, len) + "," + num.substr(len);
        len -= 3;
    }
   
    t.value = num;
   
    return t;
}

/*
**  숫자 데이터에서 콤마 제거
*/
function remove_comma(data) {
	
	return data.val().replace(/,/g, '');
} 

/*
** 상품 등록 요청
*/
function go_save() {
	
	if ($("#kind").val() == "") {
		alert("상품 종류를 입력하세요.");
		$("#kind").focus();
		return false;
	} else if ($("#name").val() == "") {
		alert("상품명을 입력하세요.");
		$("#name").focus();
		return false;
	} else if ($("#price1").val() == "") {
		alert("상품원가를 입력하세요.");
		$("#price1").focus();
		return false;
	} else if ($("#price2").val() == "") {
		alert("판매가를 입력하세요.");
		$("#price2").focus();
		return false;
	} else if ($("#price3").val() == "") {
		alert("상품순을를 입력하세요.");
		$("#price3").focus();
		return false;
	} else if ($("#content").val() == "") {
		alert("상품설명을 입력하세요.");
		$("#content").focus();
		return false;
	} else {
		var price1 = $("#price1");
		var price2 = $("#price2");
		var price3 = $("#price3");
		
		price1.val(remove_comma(price1));
		price2.val(remove_comma(price2));
		price3.val(remove_comma(price3));
		
		var theform = $("#write_form");
		theform.attr("enctype", "multipart/form-data");
		theform.attr("action", "admin_product_write");
		theform.submit();
	}
}

/*
** 상품이름을 조건으로 검색 요청
*/
function go_search() {
	$("#prod_form").attr("action", "admin_product_list")
			   .submit();
}

function go_list() {
	$("#detail_form").attr("action", "admin_product_list")
	.attr("method", "get").submit();
}

function go_mod(pseq) {
	$("#detail_form").attr("action", "admin_product_update_form?pseq="+pseq)
		.submit();
}

/*
** 상품정보 수정 요청
*/
function go_mod_save() {
	if ($("#kind").val() == "") {
		alert("상품 종류를 입력하세요.");
		$("#kind").focus();
		return false;
	} else if ($("#name").val() == "") {
		alert("상품명을 입력하세요.");
		$("#name").focus();
		return false;
	} else if ($("#price1").val() == "") {
		alert("상품원가를 입력하세요.");
		$("#price1").focus();
		return false;
	} else if ($("#price2").val() == "") {
		alert("판매가를 입력하세요.");
		$("#price2").focus();
		return false;
	} else if ($("#price3").val() == "") {
		alert("상품순을를 입력하세요.");
		$("#price3").focus();
		return false;
	} else if ($("#content").val() == "") {
		alert("상품설명을 입력하세요.");
		$("#content").focus();
		return false;
	} else {
		var price1 = $("#price1");
		var price2 = $("#price2");
		var price3 = $("#price3");
		
		price1.val(remove_comma(price1));
		price2.val(remove_comma(price2));
		price3.val(remove_comma(price3));
		
		var theform = $("#update_form");
		theform.attr("enctype", "multipart/form-data");
		theform.attr("action", "admin_product_update");
		theform.submit();
	}
}









