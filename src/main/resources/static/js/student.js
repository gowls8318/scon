/**
 * @author 임태미
 * @version 04.30.1
 */
 
 /* 서브 메뉴 이동 함수 */
 	const btn = document.querySelectorAll('#menu-btns .btn');

 	btn.forEach((el, index) => {
 	  el.onclick = () => {

 		let subMenuList = document.querySelectorAll(".menu");
 		  
 		subMenuList.forEach((el, index) => {
 			subMenuList[index].classList.add('d-none');
 		});
 		
 		subMenuList[index].classList.remove('d-none');

 	  }
});
		//아이디
 		var msg1 = document.getElementById('userId');
		var result1 = document.getElementById('idHelp');
		//비밀번호
		var msg2 = document.getElementById('password');
		var result2 = document.getElementById('passwordHelp');
		//이메일
		var msg3 = document.getElementById('email');
		var result3 = document.getElementById('emailHelp');
		//이름
		var msg4 = document.getElementById('name');
		var result4 = document.getElementById('nameHelp');
		//전화번호
		var msg5 = document.getElementById('phone');
		var result5 = document.getElementById('phoneHelp');
		
		
		msg1.onblur = function(){
			result1.innerHTML = "";
		};
		msg2.onblur = function(){
			result2.innerHTML = "";
		};
		msg3.onblur = function(){
			result3.innerHTML = "";
		};
		msg4.onblur = function(){
			result4.innerHTML = "";
		};
		msg5.onblur = function(){
			result5.innerHTML = "";
		};
		
	  
	  
/** 유효성 검사 */
function checkAll() {
        if (!checkName(form.name.value)) {
            return false;
        } else if (!checkUserId(form.userId.value)) {
            return false;
        } else if (!checkPassword(form.userId.value,form.password.value)) {
            return false;
        } else if (!checkEmail(form.email.value)) {
            return false;
        } else if (!checkPhone(form.phone.value)) {
            return false;
        } 
        return true;
    }

// 공백 확인
function checkExistData(value, dataName) {
        if (value == "") {
            alert(dataName + " 입력해주세요!");
            return false;
        }
        return true;
    }
    
 function checkUserId(id) {
        //Id가 입력되었는지 확인하기
        if (!checkExistData(id, "아이디를"))
            return false;
 
        var idRegExp = /^[a-zA-z0-9]{4,12}$/; //아이디 유효성 검사
        if (!idRegExp.test(id)) {
			document.getElementById('idHelp').innerHTML =
			"아이디는 영문 대소문자와 숫자 4~12자리로 입력해야합니다!";
            form.userId.value = "";
            form.userId.focus();
            return false;
        }
        return true; //확인이 완료되었을 때
    }

 function checkPassword(id, password) {
        //비밀번호가 입력되었는지 확인하기
        if (!checkExistData(password, "비밀번호를"))
            return false;

        var passwordRegExp = /^[a-zA-z0-9]{8,20}$/; //비밀번호 유효성 검사
        if (!passwordRegExp.test(password)) {
	
		document.getElementById('passwordHelp').innerHTML =
			"비밀번호는 영문 대소문자와 숫자 8~20자리로 입력해야합니다!";
            form.password.value = "";
            form.password.focus();
            return false;
        }
      
      	 //아이디와 비밀번호가 같을 때
        if (id == password) {
            document.getElementById('passwordHelp').innerHTML =
			"아이디와 비밀번호는 같을 수 없습니다.";
            form.password.value = "";
            form.password.focus();
            return false;
        }

        return true; //확인이 완료되었을 때
    }    

function checkEmail(email) {
        //mail이 입력되었는지 확인하기
        if (!checkExistData(email, "이메일을"))
            return false;
 
        var emailRegExp = /^[A-Za-z0-9_]+[A-Za-z0-9]*[@]{1}[A-Za-z0-9]+[A-Za-z0-9]*[.]{1}[A-Za-z]{1,3}$/;
        if (!emailRegExp.test(email)) {
			document.getElementById('emailHelp').innerHTML =
			"이메일 형식이 올바르지 않습니다!";

            form.email.value = "";
            form.email.focus();
            return false;
        }
        return true; //확인이 완료되었을 때
    }

 function checkName(name) {
        //이름이 입력되었는지 확인하기
        if (!checkExistData(name, "이름을"))
            return false;
 
        if (name.length < 2) {
			document.getElementById('nameHelp').innerHTML =
			"이름은 두 글자 이상 입력해야합니다!";
            form.name.value = "";
            form.name.focus();
            return false;
        }
        return true; //확인이 완료되었을 때
    }


function checkPhone(phone) {
    if (!checkExistData(phone, "연락처를"))
            return false;
 	
 	var phoneRegExp = /^\d{2,3}-\d{3,4}-\d{4}$/;
    if (!phoneRegExp.test(phone)) {
        document.getElementById('phoneHelp').innerHTML =
			"전화번호 형식이 올바르지 않습니다!";

            form.phone.value = "";
            form.phone.focus();
            return false;

    }

    return true; //확인이 완료되었을 때
}


