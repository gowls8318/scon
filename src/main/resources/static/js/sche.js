
	document.addEventListener('DOMContentLoaded', function() {
		let calendarEl = document.getElementById('calendar');
		let calendar = new FullCalendar.Calendar(calendarEl, {
			
			defaultDate : new Date(),
			// locale: 'ko',
			initialView : 'dayGridMonth', // 홈페이지에서 다른 형태의 view를 확인할 수 있다.
			events :
			// 일정 데이터 추가 , DB의 event를 가져오려면 JSON 형식으로 변환해 events에 넣어주면된다.

			// ajax 처리로 데이터를 로딩 시킨다. 
			$.ajax({
				type : "get",
				url : "/schedule/allschedule",
				dataType : "json",
				success : function(data) {
					console.log(data);
					let result = data.schedule;
					for (i = 0; i < result.length; i++) {

						calendar.addEvent({
							id : result[i]['scheNo'],
							title : result[i]['content'],
							start : result[i]['startDay'],
							end : result[i]['endDay'],
							color : result[i]['color'],
							allDay : false
						})
					}
				}
			})

			,
			headerToolbar : {
				center : 'addEventButton' // headerToolbar에 버튼을 추가
			},
			customButtons : {
				addEventButton : { // 추가한 버튼 설정
					text : "일정 추가", // 버튼 내용
					click : function() { // 버튼 클릭 시 이벤트 추가

						// 일정 등록 모달 함수 호출
						insertModal();
					}
				}
			},
			eventClick : function(info) {

				//일정 클릭 시 일정 수정 모달 함수 호출
				updateModal(info);

			},
			editable : true, // false로 변경 시 draggable 작동 x 
			displayEventTime : false
		// 시간 표시 x
		});

		calendar.render();

	});

	function insertModal() {

		$("#calendarModal").modal("show"); // modal 나타내기
		
		$("#addCalendar").removeClass("d-none");
		$("#upadteCalendar").addClass("d-none");
		$("#deleteCalendar").addClass("d-none");

		$("#addCalendar").on("click", function() { // modal의 추가 버튼 클릭 시
			let content = $("#calendar_content").val();
			let start_date = $("#calendar_start_date").val();
			let end_date = $("#calendar_end_date").val();
			let color = $("#calendar_color").val();

			//내용 입력 여부 확인
			if (content == null || content == "") {
				toast('warning', '내용을 입력하세요!');
			} else if (start_date == "" || end_date == "") {
				toast('warning', '날짜를 입력하세요!');
			} else if (new Date(end_date) - new Date(start_date) < 0) { // date 타입으로 변경 후 확인
				toast('warning', '종료일이 시작일보다 먼저 입니다!');
			} else { // 정상적인 입력 시

				$.ajax({
					type : 'post',
					url : '/schedule/schedule',
					data : {
						"content" : content,
						"startDay" : start_date,
						"endDay" : end_date,
						"color" : color
					},
					dataType : "json",
					success : function(data) {
						let result = data;
						Swal.fire({
							text : '일정이 등록되었습니다!',
							icon : 'success'
						});
						
						location.reload();

					},
					error : function() {
						toast('error', 'error! 다시 시도해주세요!');
					}
				}); //<-- ajax

			}
		}); //모달 종료 시점
	}

	function updateModal(info) {
		$("#calendarModal").modal("show"); // modal 나타내기
		$("#addCalendar").addClass("d-none");
		$("#upadteCalendar").removeClass("d-none");
		$("#deleteCalendar").removeClass("d-none");
		// console.log(info);

		let startDate = info.event._instance.range.start;
		let endDate = info.event._instance.range.end;
		let scheNo = info.event.id;
		let start = startDate.toISOString().substring(0, 10);
		let end = endDate.toISOString().substring(0, 10);

		$("#calendar_content").val(info.event._def.title);
		$("#calendar_start_date").val(start);
		$("#calendar_color").val(info.event._def.ui.backgroundColor);

		console.log("확인1 : " + end);
		
		if (start != end) {
			let yy = endDate.getFullYear();
			let mm, dd;
			
			// 만약 일정이 6월 1일인 경우 6월 00일이 아닌 5월 31일이 되도록 하기
			if(endDate.getDate() == '01'){
				mm = endDate.getMonth();
				let month = [ 1, 3, 5, 7, 8, 10, 12];
				dd = (month.includes(mm))?  '31' : (mm == '2')? 28 : 30;
			} else{
				mm = endDate.getMonth() + 1;
				dd = endDate.getDate() - 1;
			}
			end = yy + "-" + (("00" + mm.toString()).slice(-2)) + "-"
					+ (("00" + dd.toString()).slice(-2));
			// console.log("확인2 : " + end);
		}

		$("#calendar_end_date").val(end);
		$("#upadteCalendar").on("click", function() { // modal의 수정 버튼 클릭 시

			let content = $("#calendar_content").val();
			let start_date = $("#calendar_start_date").val();
			let end_date = $("#calendar_end_date").val();
			let color = $("#calendar_color").val();

			//내용 입력 여부 확인
			if (content == null || content == "") {
				toast('warning', '내용을 입력하세요!');
			} else if (start_date == "" || end_date == "") {
				toast('warning', '날짜를 입력하세요!');
			} else if (new Date(end_date) - new Date(start_date) < 0) { // date 타입으로 변경 후 확인
				toast('warning', '종료일이 시작일보다 먼저 입니다!');
			} else { // 정상적인 입력 시

				$.ajax({
					type : 'post',
					url : '/schedule/updateSchedule',
					data : {
						"scheNo" : scheNo,
						"content" : content,
						"startDay" : start_date,
						"endDay" : end_date,
						"color" : color
					},
					dataType : "json",
					success : function(data) {
						let result = data;
						Swal.fire({
							text : '일정이 수정되었습니다!',
							icon : 'success'
						});

						location.reload();

					},
					error : function() {
						toast('error', 'error! 다시 시도해주세요!');
					}
				}); //<-- ajax
				
			}
			
		});
				// 삭제 버튼 클릭시 
				$("#deleteCalendar").on("click", function() {

					$.ajax({
						type : 'post',
						url : '/schedule/deleteSchedule',
						data : {
							"scheNo" : scheNo,
						},
						dataType : "json",
						success : function(data) {
							let result = data;
							Swal.fire({
								text : '일정이 삭제되었습니다!',
								icon : 'success'
							});

							location.reload();

						},
						error : function() {
							toast('error', 'error! 다시 시도해주세요!');
						}
					}); //<-- ajax
				});
	}

	//alert창
	function toast(icon, title) {
		const Toast = Swal.mixin({
			toast : true,
			position : 'top',
			showConfirmButton : false,
			timer : 2000
		})

		Toast.fire({
			icon : icon,
			title : title
		})
	}

