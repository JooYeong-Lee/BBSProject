function backToMain() {
	window.location.href = '/main';
}

var idcheck = false;
var pwdcheck = false;
var pwd = document.querySelector('.user-pw');
var repwd = document.querySelector('.check-pw');
var submit_btn = document.querySelector('.join-btn');

function enableSubmitButton() {
    if (idcheck && pwdcheck) {
        submit_btn.disabled = false; // 아이디 중복 확인이 완료되고, 비밀번호도 일치할 때 버튼 활성화
    } else {
        submit_btn.disabled = true;
    }
}

function checkPassword() {
	var pwd_msg = document.querySelector('.pwd_msg');
	
    if (pwd.value.trim() !== "" && pwd.value.trim() === repwd.value.trim()) {
        pwdcheck = true;
        pwd_msg.innerText = "비밀번호가 일치합니다."
        pwd_msg.style.color = "green";
    } else {
        submit_btn.disabled = true;
        pwd_msg.innerText = "비밀번호가 일치하지않습니다."
        pwd_msg.style.color = "red";
    }
    enableSubmitButton();
}

pwd.addEventListener('input', checkPassword);
repwd.addEventListener('input', checkPassword);

function checkDuplicate() {
    var userId = document.querySelector('.user-id');
    var dupl_msg = document.querySelector('.dupl_msg');

    //길이 제한
	if (userId.value.length < 5 || userId.value.length > 15) {
        dupl_msg.innerText = "5~15글자 이내로 설정해주세요"
        dupl_msg.style.color = "red";
        submit_btn.disabled = true;
        return; // 길이가 범위를 벗어나면 검증 중단
    } else {
		dupl_msg.innerText = "";
    }
	
	// 디비에 있는지 확인(중복 확인)
    var xhr = new XMLHttpRequest();
    xhr.open('POST', '/check_id', true);
    xhr.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
    xhr.onreadystatechange = function() {
        if (xhr.readyState === 4) {
            if (xhr.status === 200) {
                var ck = JSON.parse(xhr.responseText);
                if (ck == false) {
                    dupl_msg.innerText = "사용중인 아이디입니다.";
                    dupl_msg.style.color = "red";
                    submit_btn.disabled = true;
                } else {
                    dupl_msg.innerText = "사용가능한 아이디입니다.";
                    dupl_msg.style.color = "green";
                    userId.readOnly = true;
                    idcheck = true;
                }
            } else {
                alert("에러가 발생하였습니다.");
            }
            enableSubmitButton();
        }
    };
    xhr.send('id=' + encodeURIComponent(userId.value));
};