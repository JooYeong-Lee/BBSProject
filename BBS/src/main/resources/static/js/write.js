function backToMain() {
	//여기에 메인 페이지로 돌아가는 이벤트 작성
	window.location.href = '/main'; //쓰기 편하려고 일부러 넣어놓은 거!! 나중에 백 작업하면서 수정해도 ok
}

//카테고리가 선택 안돼있으면 페이지 안넘기는 함수
function validateForm() {
    var categoryDropdown = document.getElementById("category-dropdown");
	
    if (categoryDropdown.value === "") {
        alert("카테고리를 선택하세요.");
        categoryDropdown.focus();
        return false;
    }
    return true;
}