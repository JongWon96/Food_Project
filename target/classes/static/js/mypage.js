/**
 * 
 */
/*
** 상품을 장바구니에 담기
*/
 function go_cart() {
	 if ($("#quantity").val() == "") {
		 alert("수량을 입력해 주세요.");
		 $("#quantity").focus();
		 return false;
	 } else if ($("#quantity").val() > 100) {
	 	 alert("수량이 너무 큽니다.");
	 	 $("#quantity").focus();
		 return false;
	 } else {
		 $("#theform").attr("action", "cart_insert").submit();
	 }
 }

 /*
 ** 장바구니 상품 삭제하기
 */
function go_cart_delete() {
	var count = 0;	// 체크된 항목의 수 저장
	var len = $("[name='cseq']:checked").length;  // 체크된 항목의 수 구하기
	
	count = len;
	console.log("삭제할 항목의 수", count);
	
	if (count == 0) {
		alert("삭제할 항목을 선택하십시오!");
		return false;
	} else {
		// 삭제할 항목 전송
		$("#theform").attr("action", "cart_delete").submit();
	}
}
 
/* 
** 장바구니 내역을 주문처리 요청
*/
function go_order_insert() {
	$("#theform").attr("action", "order_insert").submit();
} 
 
 
 
 
 
 
 
 
 
 
 