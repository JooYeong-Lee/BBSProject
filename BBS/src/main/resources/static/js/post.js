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
    document.getElementById('file-input').click();
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
    var userid = button.getAttribute('data-id');
    var bbsnum = button.getAttribute('data-bbs-num');
    
    if(userid == null){
		alert("로그인하셔야 댓글 등록이 가능합니다.");
		return false;
	}
    
    if(comment.trim() === ""){
		alert("댓글 내용을 입력해주세요");
		return false;
	}
	
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
	    return response.text();
	})
	.then(data => {
	    console.log('댓글 등록 완료');
	    location.reload();
	})
	.catch(error => {
	    console.error('댓글 등록중 오류 발생');
	});
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

function idChange_submit(button){
	var afterid = document.querySelector(".id_change_box");
	var beforeid = button.getAttribute('data-id');
	
	fetch('/changeId', {
	    method: 'POST',
	    headers: {
	        'Content-Type': 'application/x-www-form-urlencoded',
	    },
	    body: 'beforeid=' + encodeURIComponent(beforeid) + '&afterid=' + encodeURIComponent(afterid.value),
	})
	.then(response => {
	    if (!response.ok) {
	        throw new Error('아이디 변경중 오류 발생');
	    }
	    return response.text();
	})
	.then(data => {
	    console.log('아이디 변경 완료');
	})
	.catch(error => {
	    console.error('아이디 변경중 오류 발생');
	});
	setTimeout(function () {
      location.reload();
    }, 500);
}

function checkDuplicate(){
	var userId = document.querySelector('.id_change_box');
    var change_txt = document.querySelector('.change_txt');
    var submit_btn = document.querySelector('.change_submit_btn');
    
    //길이 제한
	if (userId.value.length < 5 || userId.value.length > 15) {
        change_txt.innerText = "5~15글자 이내로 설정해주세요"
        change_txt.style.color = "red";
        submit_btn.disabled = true;
        return; // 길이가 범위를 벗어나면 검증 중단
    } else {
		change_txt.innerText = "";
    }
	
	var xhr = new XMLHttpRequest();
    xhr.open('POST', '/check_id', true);
    xhr.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
    xhr.onreadystatechange = function() {
        if (xhr.readyState === 4) {
            if (xhr.status === 200) {
                var ck = JSON.parse(xhr.responseText);
                if (ck == false) {
                    change_txt.innerText = "사용중인 아이디입니다.";
                    change_txt.style.color = "red";
                    submit_btn.disabled = true;
                } else {
                    change_txt.innerText = "사용가능한 아이디입니다.";
                    change_txt.style.color = "green";
                    userId.readOnly = true;
                    submit_btn.disabled = false;
                }
            } else {
                alert("에러가 발생하였습니다.");
            }
        }
    };
    xhr.send('id=' + encodeURIComponent(userId.value));
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

function handleFileSelect() {
    const fileInput = document.getElementById('file-input');
    const files = fileInput.files;

    if (files.length === 0) {
        alert('파일을 선택하세요.');
        return;
    } else {
        //console.log('파일 :', files);

        const formData = new FormData();

        for (let i = 0; i < files.length; i++) {
            const fileNameWithoutExtension = files[i].name.replace(/\.[^.]+$/, "");

            formData.append('uploadedFiles', new File([files[i]], fileNameWithoutExtension, { type: files[i].type }));
        }

        const xhr = new XMLHttpRequest();
        xhr.open('POST', '/UploadImg', true);

        xhr.onload = function () {
            if (xhr.status === 200) {
                //alert('파일 업로드가 완료되었습니다.');
            } else {
                //alert('파일 업로드 중 오류가 발생했습니다.');
            }
        };

        xhr.send(formData);
        setTimeout(function () {
            location.reload();
        }, 300);
    }
}
/*
function addImageExtension(imgElement) {
    // 이미지 경로에서 확장자 추출
    var imagePath = imgElement.src;
    var extension = imagePath.split('.').pop();
    
    // 이미지 태그에 확장자 동적으로 추가
    imgElement.src = imgElement.src + '.' + extension.toLowerCase();
}*/