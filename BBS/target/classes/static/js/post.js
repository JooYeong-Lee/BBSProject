function toggleCategoryPopup() {
    var categoryPopup = document.getElementById("categoryPopup");
    categoryPopup.style.display = (categoryPopup.style.display === "block") ? "none" : "block";
    categoryPopup.style.animation = "fadeInOut 0.5s ease"; 
}

function closeCategoryPopup() {
    var categoryPopup = document.getElementById("categoryPopup");
    categoryPopup.style.animation = "fadeInOut 0.5s ease"; 
    setTimeout(function() {
        categoryPopup.style.display = "none";
    }, 100); 
}
function searchTitle() {
	//여기에 제목 검색 후 이벤트 작성
}

function editCamera() {
	//여기에 배경화면 수정 이벤트 작성
}
function backToMain() {
	//여기에 메인 페이지로 돌아가는 이벤트 작성
	window.location.href = '/main'; //쓰기 편하려고 일부러 넣어놓은 거!! 나중에 백 작업하면서 수정해도 ok
}
function writePost() {
	//여기에 게시글 작성 이벤트 작성
	window.location.href = '/write'; //쓰기 편하려고 일부러 넣어놓은 거!! 나중에 백 작업하면서 수정해도 ok
}
function logOut() {
	//여기에 로그아웃 이벤트 작성
	window.location.href = '/logout';
}
function click_comment_btn(button) {
    var comment = document.querySelector('.comment').value;
    if(comment.trim() === ""){
		alert("댓글 내용을 입력해주세요");
		return false;
	}
    
    var userid = button.getAttribute('data-id');
    var bbsnum = button.getAttribute('data-bbs-num');
	console.log(userid, bbsnum, comment);
	
	fetch('/comment_clear', {
	    method: 'POST',
	    headers: {
	        'Content-Type': 'application/x-www-form-urlencoded',
	    },
	    body: 'bbsnum=' + encodeURIComponent(bbsnum) + '&comment=' + encodeURIComponent(comment) + '&id=' + encodeURIComponent(userid),
	})
	.then(response => {
	    if (!response.ok) {
	        throw new Error('댓글 등록중 오류 발생');
	    }
	    return response.text(); // 또는 response.json() 등, 필요에 따라 응답을 파싱
	})
	.then(data => {
	    console.log('댓글 등록 완료'); // 서버로부터의 응답을 처리하는 코드
	    location.reload();
	})
	.catch(error => {
	    console.error('댓글 등록중 오류 발생');
	});
}