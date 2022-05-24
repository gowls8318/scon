
// 아이디 찾기 js
function findId() {

	let name = document.getElementById('name').value;
	let email = document.getElementById('email').value;

	if (name == '' || email == '') {
		toast('warning', '가입된 정보를 입력하세요!');
		return false;
	}


	$.ajax({
		type: "POST",
		url: "/member/forgotId",
		data: {
			"name": name,
			"email": email
		},
		success: function(data) {
			if (data != "") {
				Swal.fire({
					title: "아이디 찾기 완료!",
					text: "가입된 아이디는 " + data + " 입니다.",
					icon: 'success'
				}).then(() => {
					location.href = '/member/login';
				})

			} else {
				toast('warning', '입력하신 정보로 해당하는 아이디를 찾을 수 없습니다!');
			}

		}, error: function() {
			toast('error', 'error! 다시 시도해주세요!');
		}

	}); //<-- ajax 끝!
}


let auth_check = 0;
let num = 0;

function send_injeung_num() {
	
	let frm = document.forgotPwd;
	
	let name = frm.name.value;
	let id = frm.id.value;
	let email = frm.email.value;

	if (name == '' || email == '' || id == '') {
		toast('warning', '가입된 정보를 입력하세요!');
		return false;
	}
	
	// 인증번호 발송을 진행하지 않은 경우
	if (auth_check > 0) {
		toast('warning', '이미 인증 번호를 발송했습니다. 메일함을 확인해주세요.');
		return false;
	}
	
	$.ajax({
		type: "POST",
		url: "/member/authPwd",
		data: {
			"name": name,
			"id" : id,
			"email": email
		},
		success: function(data) {
			if (data > 0) {
				num = data;
				frm.injeung_num.focus();
				
				auth_check = 1;
				
				Swal.fire({
					title: "인증번호 발송 완료",
					text: "이메일로 발송된 인증번호를 입력해주세요!",
					icon: 'success'
				})
			} else if(data == 0){
				toast('warning', '입력하신 정보로 회원정보를 찾을 수 없습니다!');
			} else {
				toast('error', '인증번호 발송 실패! 다시 시도해주세요!');
			}

		}, error: function() {
			toast('error', 'error! 다시 시도해주세요!');
		}
	}); //<-- ajax 끝!
	
}


// 비밀번호 찾기 js
function check_injeung_num() {

	let frm = document.forgotPwd;
	
	//인증번호 발송을 진행했는지 여부
	if (auth_check == 0) {
		toast('warning', '인증번호 발송을 먼저 진행해주세요!')
		return false;
	}

	// 입력받은 인증 번호
	inputNum = frm.injeung_num.value; 
	
	if (inputNum == '') {
		toast('warning', '인증번호를 입력해주세요!')
		return false;
	}
	
	let id = frm.id.value;
	
	// 인증번호 확인 후 성공시 새로운 비밀번호 페이지로 이동
	if (num == inputNum){

		Swal.fire({
			title: "인증번호 확인 완료",
			text: "새로운 비밀번호 변경 페이지로 이동합니다!",
			icon: 'success'
		}).then(() => {
			auth_check = 1;
			localStorage.setItem("auth_check", auth_check);
			localStorage.setItem("id_check", id);
			location.href="/member/newPwd";		
		})
		
	} else {
		toast('warning', '인증번호가 불일치합니다!');
	}
}
	
// 비밀번호 경고창
var msg1 = document.getElementById('pwd');
var result1 = document.getElementById('passwordHelp');

msg1.onblur = function() {
	result1.innerHTML = "";
};


// 비밀번호 변경 js

function newPassword(){
	let frm = document.newPwd;
	// 비밀번호 입력
	let pwd = frm.pwd.value;
	let pwd2 = frm.pwd2.value;
	
	if(!localStorage.getItem("auth_check")){
		toast('warning', '비밀번호 찾기를 처음부터 진행해주세요!');
		return false;
	}
	
	// 앞에서 이메일 인증한 id 저장
	const id = localStorage.getItem("id_check");
	
	//비밀번호가 입력되었는지 확인하기
	if (pwd == '' || pwd2 == ''){
		toast('warning', '비밀번호를 입력해주세요!');
		return false;
	}
	
	//비밀번호 유효성 검사
	var passwordRegExp = /^[a-zA-z0-9]{8,20}$/; //비밀번호 유효성 검사
	if (!passwordRegExp.test(pwd)) {
		
		document.getElementById('passwordHelp').innerHTML =
			"비밀번호는 영문 대소문자와 숫자 8~20자리로 입력해야합니다!";
		frm.pwd.value = "";
		frm.pwd.focus();
		return false;
	}
	
	// 변경할 비밀번호 재확인 검사
	if(pwd != pwd2){
		toast('warning', '비밀번호가 서로 다릅니다.');
		frm.pwd2.value = "";
		frm.pwd2.focus();
	}
	
	localStorage.clear();		// 로컬 스토리지 클리어
	
	Swal.fire({
      title: '비밀번호를 변경하시겠습니까?',
      icon: 'question',
      showCancelButton: true,
      confirmButtonColor: '#6610f2',
      cancelButtonColor: '#858796',
      confirmButtonText: '확인',
      cancelButtonText: '취소'
      
    }).then((result) => {
      if (result.isConfirmed) {
		$.ajax({
		type: "POST",
		url: '/member/newPwd',
		data: {
			id : id,
			password: pwd
			},
		dataType: 'json',	
		success: function(data) {
			if(data > 0){
				Swal.fire({
					title : '비밀번호 변경완료!',
					text: '로그인 해주세요!',
					icon: 'success'
				}).then(() => {
					location.href="/member/login";		
				});
			}
		},
		error: function() {
			toast('error', 'error! 다시 시도해주세요!');
		}
	}); //<-- ajax
	
      }
   })
}

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

