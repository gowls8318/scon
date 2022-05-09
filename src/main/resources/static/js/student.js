/**
 * @author 임태미
 * @since 2022.05.04
 */

/* 다음 도로명 주소 Api */
function execDaumPostcode() {
	new daum.Postcode(
		{
			oncomplete: function(data) {
				// 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.
				// 각 주소의 노출 규칙에 따라 주소를 조합한다.
				// 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
				var addr = ''; // 주소 변수
				var extraAddr = ''; // 상세주소 변수
				//사용자가 선택한 주소 타입에 따라 해당 주소 값을 가져온다.
				if (data.userSelectedType === 'R') { // 사용자가 도로명 주소를 선택했을 경우
					addr = data.roadAddress;
				} else { // 사용자가 지번 주소를 선택했을 경우(J)
					addr = data.jibunAddress;
				}
				// 우편번호와 주소 정보를 해당 필드에 넣는다.
				document.getElementById('zipCode').value = data.zonecode;
				document.getElementById('address').value = addr;
				// 커서를 상세주소 필드로 이동한다.
				document.getElementById('extraAddress').focus();
				document.getElementById('zipCode').readOnly = true;
				document.getElementById('address').readOnly = true;
			}
		}).open();
}


/* 프로필 사진 미리보기 */
function readFile() {
	let file = event.target.files[0];
	let href = window.URL.createObjectURL(file);
	$("#img").attr('src', href)

	//5분뒤에 메모리 해제 
	setTimeout(function() { window.URL.revokeObjectURL(href) }, 1000 * 60 * 5)
}

/* 유효성 검사 후 Help메세지 출력 */
	//아이디
	var msg1 = document.getElementById('id');
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
	
	
	msg1.onblur = function() {
		result1.innerHTML = "";
	};
	msg2.onblur = function() {
		result2.innerHTML = "";
	};
	msg3.onblur = function() {
		result3.innerHTML = "";
	};
	msg4.onblur = function() {
		result4.innerHTML = "";
	};
	msg5.onblur = function() {
		result5.innerHTML = "";
	};


//아이디 중복검사
let idck = 0;

$('#checkId').click(function() {

	let id = form.id.value;

	if (!checkExistData(id, "아이디를"))
		return false;

	$.ajax({
		type: "POST",
		url: '/member/checkId',
		dataType: "json",
		data: { "id": id },
		success: function(data) {
			if (data > 0) {
				alert("중복된 아이디 입니다.")
				$("#id").addClass("is-invalid");
				idck = 0;
			} else {
				alert("사용 가능한 아이디 입니다.")
				$("#id").removeClass("is-invalid");
				$("#id").addClass("is-valid");
				idck = 1;
			}
		}, error: function() {
			alert("error");
		}
	});

});

// 아이디 변경시 다시 중복검사 진행
$("#id").change(function(){
	$("#id").removeClass("is-valid");
	$("#id").removeClass("is-invalid");
	idck = 0;
});


/** 유효성 검사 */
function checkAll() {
	
	if(idck == 0){
		alert("아이디 중복 확인을 진행해주세요.")
		return false;
	}
	
	//필수정보 9개
	let id = form.id.value;
	let name = form.name.value;
	let password = form.password.value;
	let email = form.email.value;
	let phone = form.phone.value;
	let gender = form.gender.value;
	let birthDay = form.birthDay.value;
	let enrollDate = form.enrollDate.value;
	let address = form.zipCode.value + '$' + form.address.value + '$' + form.extraAddress.value;
	
	//학생 추가정보 5개
	let studentType = form.studentType.value;
	let schoolName = form.schoolName.value;
	let schoolGrade = form.schoolGrade.value;
	let schoolClass = form.schoolClass.value;
	let consult = form.consult.value;
	
	//학부모정보 3개
	let parentsName =  form.parentsName.value;
	let parentsType = form.parentsType.value;
	let parentsPhone = form.parentsPhone.value;
	
	//프로필사진 1개
	let file = document.querySelector('#profile');
	
	const member = JSON.stringify({
      id : id,
      name: name,
      password : password,
      email : email,
      phone : phone,
      gender: gender,
      birthDay : birthDay,
      enrollDate : enrollDate,
      address : address,
       });
       
     const student = JSON.stringify({ 
	  id : id,
      studentType :studentType,
      schoolName : schoolName,
      schoolGrade : schoolGrade,
      schoolClass : schoolClass,
      consult : consult,
      });
      
     const parents = JSON.stringify({
	  id : id,
      parentsName : parentsName,
      parentsType : parentsType,
      parentsPhone : parentsPhone
      });


	//formData에 append
	let formData = new FormData()
	
	formData.append("file", file.files[0]);
	formData.append("member", member);
	formData.append("student", student);
	formData.append("parents", parents);

	
	// FormData의 값 확인
	for (var pair of formData.entries()) {
		 console.log(pair[0]+ ', ' + pair[1]);
	}
	

	//유효성 검사
	if (!checkName(name)) {
		return false;
	} else if (!checkid(id)) {
		return false;
	} else if (!checkPassword(id, password)) {
		return false;
	} else if (!checkEmail(email)) {
		return false;
	} else if (!checkPhone(phone)) {
		return false;
	}
	
	//회원가입
			$.ajax({
			  type: "POST",
		      url: '/admin/studentRegist',
		      data: formData,           
		      processData: false,   
		      contentType: false,    
		      enctype : 'multipart/form-data',  
		      success: function(data) {
		      
		     	 alert("원생 등록에 성공하였습니다.");
				 location.href="/admin/studentList";
		      },
		      error: function() {
				 alert("원생 등록에 실패하였습니다.")
		 }
	});
}

// 공백 확인
function checkExistData(value, dataName) {
	if (value == "") {
		alert(dataName + " 입력해주세요!");
		return false;
	}
	return true;
}

function checkid(id) {
	//Id가 입력되었는지 확인하기
	if (!checkExistData(id, "아이디를"))
		return false;

	var idRegExp = /^[a-zA-z0-9]{4,12}$/; //아이디 유효성 검사
	if (!idRegExp.test(id)) {
		document.getElementById('idHelp').innerHTML =
			"아이디는 영문 대소문자와 숫자 4~12자리로 입력해야합니다!";
		form.id.value = "";
		form.id.focus();
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

$('input[name=selectStd]').on('click',function() {
	let id = '';
	
	if($(this).prop('checked')){
		id = $(this).val();

		$.ajax({
			type: "POST",
			url: '/admin/findStudentById',
			data: { "id" : id },
			success: function(data) {
				var student = data;
			}, error: function() {
				alert("실패");
			}
		
	});
  }
});



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


/* 등록하기 메뉴 이동 함수 */
const lecbtn = document.querySelectorAll('.btn-lec');

lecbtn.forEach((el, index) => {
	el.onclick = () => {

		let subMenuList = document.querySelectorAll(".menu");

		subMenuList.forEach((el, index) => {
			subMenuList[index].classList.add('d-none');
		});

		subMenuList[2].classList.remove('d-none');

	}
});

/* 수정하기 메뉴 이동 함수 */
const upbtn = document.querySelectorAll('.btn-acc1');

upbtn.forEach((el, index) => {
	el.onclick = () => {

		let subMenuList = document.querySelectorAll(".menu");

		subMenuList.forEach((el, index) => {
			subMenuList[index].classList.add('d-none');
		});

		subMenuList[3].classList.remove('d-none');

	}
});
