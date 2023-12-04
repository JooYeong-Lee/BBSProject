function checkDuplicate() {
    var userId = document.querySelector('.id');
    var dupl_msg = document.querySelector('.dupl_msg');
    var submit_btn = document.querySelector('.submit_btn')

    //길이 제한
	if (userId.value.length < 5 || userId.value.length > 15) {
        dupl_msg.innerText = "5~15글자 이내로 설정해주세요"
        dupl_msg.style.color = "red";
        submit_btn.disabled = true;
        return; // 길이가 범위를 벗어나면 검증 중단
    } else {
		dupl_msg.innerText = ""
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
                    submit_btn.disabled = false;
                }
            } else {
                alert("에러가 발생하였습니다.");
            }
        }
    };
    xhr.send('id=' + encodeURIComponent(userId.value));
};