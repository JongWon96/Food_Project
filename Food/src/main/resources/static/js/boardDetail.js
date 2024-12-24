// 댓글 작성 버튼 클릭 시 textarea 토글
function toggleCommentForm(event) {
    var commentForm = document.getElementById("commentForm");
    var submitButton = document.getElementById("submitButton");
    var commentText = document.getElementsByName("content")[0];

    // 댓글 내용이 없으면 댓글 작성 취소
    if (commentForm.style.display === "none") {
        commentForm.style.display = "block";
        submitButton.innerText = "댓글 작성 완료"; // 버튼 텍스트 변경
    } else {
        // 댓글이 비어있지 않으면 폼을 제출
        if (commentText.value.trim() !== "") {
            event.preventDefault(); // 기본 폼 제출을 막음
            commentForm.submit();  // 폼 제출
        } else {
            // 댓글 내용이 없으면 텍스트박스 숨기기
            commentForm.style.display = "none";
            submitButton.innerText = "댓글 작성"; // 버튼 텍스트 초기화
        }
    }
}
