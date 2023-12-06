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

function openPost(bbsNum) {
	//여기에 게시글 클릭 후 이벤트 작성
    console.log(' bbsNum : ' + bbsNum);
    window.location.href = '/post?bbs_num=' + bbsNum;
}

function editCamera() {
	//여기에 배경화면 수정 이벤트 작성
}

function logOut() {
	//여기에 로그아웃 이벤트 작성
	window.location.href = '/logout';
}

function backToMain() {
	//여기에 메인 페이지로 돌아가는 이벤트 작성
	window.location.href = '/main'; //쓰기 편하려고 일부러 넣어놓은 거!! 나중에 백 작업하면서 수정해도 ok
}

function writePost() {
	//여기에 게시글 작성 이벤트 작성
	window.location.href = '/write'; //쓰기 편하려고 일부러 넣어놓은 거!! 나중에 백 작업하면서 수정해도 ok
}

function idChange() {
	var div_ID = document.querySelector(".line1");
	var div_Intro = document.querySelector(".line2");
	var div_Change = document.querySelector(".id_change");
	var submit_btn = document.querySelector(".change_submit_btn")
	
	div_ID.style.display="none";
	div_Intro.style.display="none";
	div_Change.style.display="block";
	submit_btn.disabled=true;
}

function introUpdate(){
	var div_ID = document.querySelector(".line1");
	var div_Intro = document.querySelector(".line2");
	var div_Change = document.querySelector(".intro_change");
	
	div_ID.style.display="none";
	div_Intro.style.display="none";
	div_Change.style.display="block";
}

function idChange_submit(){
	
}

function introUpdate_submit(button){
	var intro = document.querySelector(".intro_change_box");
	var userid = button.getAttribute('data-id');
	
    if(intro.value.trim() === ""){
		alert("내용을 입력해주세요");
		return false;
	}
	
	fetch('/introInsert', {
	    method: 'POST',
	    headers: {
	        'Content-Type': 'application/x-www-form-urlencoded',
	    },
	    body: 'userid=' + encodeURIComponent(userid) + '&intro=' + encodeURIComponent(intro.value),
	})
	.then(response => {
	    if (!response.ok) {
	        throw new Error('한줄 소개 등록중 오류 발생');
	    }
	    return response.text();
	})
	.then(data => {
	    console.log('한줄 소개 등록 완료');
	})
	.catch(error => {
	    console.error('한줄 소개 등록중 오류 발생');
	});
	setTimeout(function () {
      location.reload();
    }, 100);
}