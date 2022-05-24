/**
 * @author 임태미
 * @since 2022.05.04
 */


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
				toast('warning', '중복된 아이디입니다.');
				$("#id").addClass("is-invalid");
				idck = 0;
			} else {
				toast('success', '사용 가능한 아이디 입니다.');

				$("#id").removeClass("is-invalid");
				$("#id").addClass("is-valid");
				idck = 1;
			}
		}, error: function() {
			toast('error', 'error! 다시 시도해주세요!');
		}
	});

});

// 아이디 변경시 다시 중복검사 진행
$("#id").change(function() {
	$("#id").removeClass("is-valid");
	$("#id").removeClass("is-invalid");
	idck = 0;
});


/** 유효성 검사 */
function checkAll() {

	let path = form.path.value;
	let alertName = "";

	// 원생/강사 등록 시 아이디 중복체크 필수
	if (path == '/admin/studentRegist' || path == '/admin/teacherRegist') {
		alertName = "등록";
		if (idck == 0) {
			toast('warning', '아이디 중복 검사를 진행해주세요.');
			return false;
		}
		
	// 원생 수정시 원생을 선택한 상태여야 함.
	} else if (path == '/admin/updateStudent') {
		alertName = "수정";
		if (form.id.value == '') {
			toast('warning', '원생을 선택해주세요!');
			return false;
		}
	// 강사 수정시 강사를 선택한 상태여야 함.
	} else if(path == '/admin/updateTeacher') {
		alertName = "수정";

		if (form.id.value == '') {
			toast('warning', '강사를 선택해주세요!');
			return false;
		}
	} else {
		alertName = "수정";
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
	let delDate = form.delDate.value;
	let status = form.status.value;

	//프로필사진 1개
	let file = document.querySelector('#profile');

	const member = JSON.stringify({
		id: id,
		name: name,
		password: password,
		email: email,
		phone: phone,
		gender: gender,
		birthDay: birthDay,
		enrollDate: enrollDate,
		address: address,
		delDate: delDate,
		status: status
	});


	//formData에 append
	let formData = new FormData()

	formData.append("file", file.files[0]);
	formData.append("member", member);

	if (path == '/admin/studentRegist' || path == '/admin/updateStudent') {
		//학생 추가정보 5개
		let studentType = form.studentType.value;
		let schoolName = form.schoolName.value;
		let schoolGrade = form.schoolGrade.value;
		let schoolClass = form.schoolClass.value;
		let consult = form.consult.value;

		//학부모정보 3개
		let parentsName = form.parentsName.value;
		let parentsType = form.parentsType.value;
		let parentsPhone = form.parentsPhone.value;

		const student = JSON.stringify({
			id: id,
			studentType: studentType,
			schoolName: schoolName,
			schoolGrade: schoolGrade,
			schoolClass: schoolClass,
			consult: consult
		});

		const parents = JSON.stringify({
			id: id,
			parentsName: parentsName,
			parentsType: parentsType,
			parentsPhone: parentsPhone
		});

		formData.append("student", student);
		formData.append("parents", parents);

	} else if (path == '/admin/teacherRegist' || path == '/admin/updateTeacher') {
		//강사 정보 2개
		let jobCode = form.jobCode.value;
		let resume = form.resume.value;

		const teacher = JSON.stringify({
			id: id,
			jobCode: jobCode,
			resume: resume
		})

		formData.append("teacher", teacher);

	}

	// FormData의 값 확인
	for (var pair of formData.entries()) {
		console.log(pair[0] + ', ' + pair[1]);
	}

	//유효성 검사
	if (!checkName(name)) {
		return false;
	} else if (!checkid(id)) {
		return false;
	} else if (!checkEmail(email)) {
		return false;
	} else if (!checkPhone(phone)) {
		return false;
	}

	//회원 가입일 경우에만 비밀번호 체크
	if (path == '/admin/studentRegist') {

		if (!checkPassword(id, password)) {
			return false;
		}
	}

	Swal.fire({
		title: alertName + '하시겠습니까?',
		icon: 'question',
		showCancelButton: true,
		confirmButtonColor: '#6610f2',
		cancelButtonColor: '#858796',
		confirmButtonText: alertName,
		cancelButtonText: '취소'

	}).then((result) => {
		if (result.isConfirmed) {
			$.ajax({
				type: "POST",
				url: path,
				data: formData,
				processData: false,
				contentType: false,
				enctype: 'multipart/form-data',
				success: function(data) {
					Swal.fire({
						title: alertName + '완료',
						text: data.message,
						icon: 'success'
					});

					location.href = data.location;
				},
				error: function() {
					toast('error', 'error! 다시 시도해주세요!');
				}
			}); //<-- ajax

		}
	})

}

// 공백 확인
function checkExistData(value, dataName) {
	if (value == "") {
		toast('warning', dataName + ' 입력해주세요!');

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

// 조회하기
$('input[name=selectMember]').on('click', function() {
	let id = '';

	let findMemberPath = form.findMemberPath.value;

	if ($(this).prop('checked')) {
		id = $(this).val();

		$.ajax({
			type: "POST",
			url: findMemberPath,
			data: { "id": id },
			success: function(data) {
				studentDetail(data);
			}, error: function() {
				toast('error', 'error! 다시 시도해주세요!');
			}

		});
	}
});

// 학생 정보 출력
function studentDetail(mem) {

	form.reset();

	let findMemberPath = form.findMemberPath.value;

	$("#img").removeAttr('src');
	let profile = '\\img\\profile.jpg';
	$("#img").attr('src', profile);

	form.id.value = mem.member.id;
	form.name.value = mem.member.name;
	form.password.value = mem.member.password;
	form.email.value = mem.member.email;
	form.phone.value = mem.member.phone;

	if (mem.member.gender == 'F') {
		$(":radio[name='gender'][value='M']").attr('checked', false);
		$(":radio[name='gender'][value='F']").attr('checked', true);
	} else if (mem.member.gender == 'M') {
		$(":radio[name='gender'][value='F']").attr('checked', false);
		$(":radio[name='gender'][value='M']").attr('checked', true);
	}

	form.birthDay.value = mem.member.birthDay;
	form.enrollDate.value = mem.member.enrollDate;
	
	let addr = mem.member.address.split('$');
	form.zipCode.value = addr[0];
	form.address.value = addr[1]
	form.extraAddress.value = addr[2];

	if (mem.member.profile != null) {
		$("#img").removeAttr('src');
		let src = '\\img\\profile\\' + mem.member.profile.fileSaveName;
		$("#img").attr('src', src);
	}
	
	if (findMemberPath == '/admin/findStudentById') {
		if (mem.studentType != null) {
			$("#studentType").val(mem.studentType).prop("selected", true);
		} else {
			$("#studentType option:eq(0)").prop("selected", true);
		}
		form.schoolName.value = mem.schoolName;
		form.schoolGrade.value = mem.schoolGrade;
		form.schoolClass.value = mem.schoolClass;
		form.parentsName.value = mem.parents.parentsName;
		form.parentsType.value = mem.parents.parentsType;
		form.parentsPhone.value = mem.parents.parentsPhone;
		form.consult.value = mem.consult;

	} else if (findMemberPath == '/admin/findTeacherById') {

		$("#jobCode").val(mem.jobCode).prop("selected", true);
		$("#status").val(mem.member.status).prop("selected", true);
		form.delDate.value = mem.member.delDate;
		form.resume.value = mem.resume;
	}


}

function deleteMember() {

	let path = form.path.value;
	let status = "";

	if (path == '/admin/updateStudent') {
		status = "퇴원생";

		if (form.id.value == '') {
			toast('warning', '원생을 선택해주세요!');

			return false;
		}
	} else {
		status = "퇴직";
		if (form.id.value == '') {
			toast('warning', '강사를 선택해주세요!');
			return false;
		}
	}

	Swal.fire({
		title: '삭제하시겠습니까?',
		icon: 'question',
		showCancelButton: true,
		confirmButtonColor: '#6610f2',
		cancelButtonColor: '#858796',
		confirmButtonText: '삭제',
		cancelButtonText: '취소'

	}).then((result) => {
		if (result.isConfirmed) {
			let id = form.id.value;

			$.ajax({
				type: "POST",
				url: "/member/deleteMember",
				data: {
					"id": id,
					"status": status
				},
				success: function(data) {
					Swal.fire({
						title: "처리 완료!",
						text: data.message,
						icon: 'success'
					})
				}, error: function() {
					toast('error', 'error! 다시 시도해주세요!');
				}
			}); //<-- ajax 끝!
		}
	})

}


/* switealert2 toast 알림창 */

function toast(icon, title) {
	const Toast = Swal.mixin({
		toast: true,
		position: 'top',
		showConfirmButton: false,
		timer: 2000
	})

	Toast.fire({
		icon: icon,
		title: title
	})
}


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



$('#updatePwd').click(function() {
	
	let id = document.getElementById("id").value;
	// 아이디 비워있는지 확인
	 if( id == ''){
		return false;
	 }
	Swal.fire({
		title: '비밀번호 변경',
	  	html:
		    '<input type="password" id="pwd" class="swal2-input" placeholder="현재 비밀번호">' +
		    '<input type="password" id="pwd2" class="swal2-input" placeholder="새로운 비밀번호">' +
		    '<input type="password" id="pwd3" class="swal2-input" placeholder="비밀번호 재확인">',
		showCancelButton: true,
		showCancelButton: true,
		confirmButtonColor: '#6610f2',
		cancelButtonColor: '#858796',
		confirmButtonText: '변경',
		cancelButtonText: '취소'

	}).then((result) => {
		if (result.isConfirmed) {
		     let pwd = document.getElementById('pwd').value;
		     let pwd2 = document.getElementById('pwd2').value;
		    
		    if( pwd == '' || pwd2 == ''){
				toast('warning', '비밀번호를 입력하세요!');
				 return false;
			}
		    if( pwd == pwd2 ){
				toast('warning', '새로운 비밀번호를 입력하세요!');
				 return false;
			}
			if( pwd2 == pwd3 ){
				toast('warning', '비밀번호 확인이 올바르지 않습니다.');
				 return false;
			}    
		    var passwordRegExp = /^[a-zA-z0-9]{8,20}$/; //비밀번호 유효성 검사
			if (!passwordRegExp.test(pwd2)) {
				toast('warning', '비밀번호는 영문 대소문자와 숫자 8~20자리로 입력해야합니다!');
				return false;
			}

			//유효성 검사 만족 후 비동기식 처리
			$.ajax({
				type: "POST",
				url: '/member/updatePwd',
				dataType: "json",
				data: { "id": id ,
						"pwd" : pwd,
						"pwd2" : pwd2
				},
				success: function(data) {
					if (data > 0) {
						Swal.fire({
						title: '변경 완료!',
						text: '비밀번호를 변경했습니다.',
						icon: 'success'
					})
					
					location.reload();
						
					} else {
						toast('warning', '현재 비밀번호가 일치하지 않습니다.');
					}
				}, error: function() {
					toast('error', 'error! 다시 시도해주세요!');
				}
			});

		  }
	});
});


function sendMessage(){
	
  let phoneArr = []
  let checkArr = $('input[name=selectMember]:checked');
	
	//console.log(checkArr);
	if(!checkArr[0]){
		toast('warning', '원생을 먼저 선택해주세요!');
		return false;
	}
	checkArr.each(function(i) {
				// checkbox.parent() : checkbox의 부모는 <td>이다.
				// checkbox.parent().parent() : <td>의 부모이므로 <tr>이다.
				var tr = checkArr.parent().parent().parent().eq(i);
				var td = tr.children();
				
				// td.eq(0)은 체크박스 이므로  td.eq(1)의 값부터 가져온다.
				var phone = td.eq(2).text();
				//console.log("phone : " + phone);
				
				// 가져온 값을 배열에 담는다.
				phoneArr.push(phone);
			});
				
	//console.log("phone : " + phoneArr);
	
		Swal.fire({
		title : '문자 발송',
		customClass: 'swal-wide',
	  	html:
		    '<input type="tel" id="from" class="form-control infoformbox" placeholder="발신번호">' +'<br>'+
		    '<textarea id="text" rows="3" class="form-control" style="resize=none;" maxlength="50" placeholder="문자 내용"/>',
		showCancelButton: true,
		confirmButtonColor: '#6610f2',
		cancelButtonColor: '#858796',
		confirmButtonText: '발송',
		cancelButtonText: '취소'

	}).then((result) => {
		if (result.isConfirmed) {
		     let from = document.getElementById('from').value;
		     let text = document.getElementById('text').value;
		     
		     console.log(text);
		    
		    if(from == ''){
				toast('warning', '발신번호를 입력하세요!');
				 return false;
			}
		    
		    if(content  == '' ){
				toast('warning', '문자 내용을 입력하세요!');
				 return false;
			}
			

			//유효성 검사 만족 후 비동기식 처리
			$.ajax({
				type: "POST",
				url: '/admin/sendMessage',
				data:{
					"phoneArr" : phoneArr,
					"from" : from,
					"text" : text
				},
				success: function(data) {
					if (data > 0) {
						Swal.fire({
						title: '발송 완료!',
						text: '문자를 성공적으로 발송했습니다.',
						icon: 'success'
					})
						
					} else {
						toast('warning', '문자 발송에 실패했습니다.');
					}
				}, error: function() {
					toast('error', 'error! 다시 시도해주세요!');
				}
			});

		  }
	});
	
	
	
}

