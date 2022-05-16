

const today =  document.querySelector('.date');	
const time =  document.querySelector('.time');	
const day =  document.querySelector('.day');	

var date = new Date();
var days = ["일", "월", "화", "수", "목", "금", "토"];

today.innerHTML = date.getFullYear() + "년 " + ( date.getMonth()+1 )+ "월 " + date.getDate() + "일";
time.innerHTML = date.getHours() + "시 " + date.getMinutes() + "분";
day.innerHTML =  days[date.getDay()] + "요일";

// openweather API_KEY
const API_KEY = "0ac0d62f4949817f68ac6787453b2e6d";

// 값을 받을 DOM 객체
const weatherInfo = document.querySelector('.weatherInfo');		//온도
const weatherInfo2 = document.querySelector('.weatherInfo2');	//습도
const weatherInfo3 = document.querySelector('.weatherInfo3');	//풍속
const weatherIconImg = document.querySelector('.weatherIcon');	//날씨 아이콘
const weatherPlace = document.querySelector('.weatherPlace');	//현재 위치
const weatherDesc = document.querySelector('.weatherDesc');		//날씨 설명

function init() {
    askForCoords();
}

// 사용자의 위치를 얻는 geolocation API
function askForCoords() {
    navigator.geolocation.getCurrentPosition(handleSuccess, handleError);
}

// 좌표를 얻는데 성공했을 때 쓰이는 함수 
function handleSuccess(position) {
    const latitude = position.coords.latitude;		//소수로 표현된 위도 값
    const longitude = position.coords.longitude;	//소수로 표현된 경도 값

    getWeather(latitude, longitude); //얻은 좌표값을 바탕으로 날씨정보를 불러온다.
}
// 좌표를 얻는데 실패했을 때 쓰이는 함수 
function handleError() {
    console.log("can't not access to location");
}

// 날씨 api 호출
function getWeather(lat, lon) {

var apiURI = `https://api.openweathermap.org/data/2.5/weather?lat=${lat}&lon=${lon}&appid=${API_KEY}&units=metric&lang=kr`;
// units=metric을 써주는 이유는 현재기온을 섭씨온도로 출력하기 위함. 
      
        $.ajax({
            url: apiURI,
            dataType: "json",
            type: "GET",
            async: "false",		// ajax 동기식
            success: function(json) {        
				console.log(json)
		        const wind = json.wind.speed;
		        const humidity = json.main.humidity;
		        const temperature = json.main.temp;
		        const place = json.name;
		        const weatherDescription = json.weather[0].description;
		        const weatherIcon = json.weather[0].icon;
		        const weatherIconAdrs = `http://openweathermap.org/img/wn/${weatherIcon}@2x.png`;
		    
		        //받아온 정보들을 표현한다. 
		        weatherInfo.innerText =  `${temperature} °C`;
		        weatherInfo2.innerText =  `${humidity} %`;
		        weatherInfo3.innerText =  `${wind} m/s`;
		        weatherPlace.innerText = `${place}`;
		        weatherDesc.innerText = `${weatherDescription}`;
		        weatherIconImg.setAttribute('src', weatherIconAdrs); //날씨 이미지 src 추가
		
            }
       }); 
 }       
 
init(); // 초기화 호출


