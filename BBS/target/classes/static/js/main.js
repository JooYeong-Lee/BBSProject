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

function openPost() {
	//여기에 게시글 클릭 후 이벤트 작성
	window.location.href = '/post'; //쓰기 편하려고 일부러 넣어놓은 거!! 나중에 백 작업하면서 수정해도 ok
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