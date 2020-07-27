function MobileSideBar__init() {
	var $btnToggleMobileSideBar = $('.btn-toggle-mobile-side-bar');
	var $btnToggleMoblieSideBarMenu = $('.mobile-side-bar > .menu-box > ul > li');

	$btnToggleMobileSideBar.click(function() {
		if ($(this).hasClass('active')) {
			$(this).removeClass('active');
			$('.mobile-side-bar').removeClass('active');
		} else {
			$(this).addClass('active');
			$('.mobile-side-bar').addClass('active');
		}
	});

	$btnToggleMoblieSideBarMenu.click(function() {
		if ($(this).hasClass('active')) {
			$(this).removeClass('active');
		} else {
			$(this).addClass('active');
		}
	});
}

$(function() {
	MobileSideBar__init();
});

function printClock() {

	var clock = document.getElementById("clock"); // 출력할 장소 선택
	var currentDate = new Date(); // 현재시간
	var calendar = currentDate.getFullYear() + "-"
			+ (currentDate.getMonth() + 1) + "-" + currentDate.getDate() // 현재
	// 날짜
	var amPm = 'AM'; // 초기값 AM
	var currentHours = addZeros(currentDate.getHours(), 2);
	var currentMinute = addZeros(currentDate.getMinutes(), 2);
	var currentSeconds = addZeros(currentDate.getSeconds(), 2);

	if (currentHours >= 12) { // 시간이 12이상일때 PM으로 세팅
		amPm = 'PM';
	}
	if (currentHours > 12) { // 시간이 13이상일때 12를 빼줌
		currentHours = addZeros(currentHours - 12, 2);
	}

	clock.innerHTML = currentHours + ":" + currentMinute + ":" + currentSeconds
			+ " <span style='font-size:1.5rem;'>" + amPm + "</span>"; // 날짜를
	// 출력해 줌

	setTimeout("printClock()", 1000); // 1초마다 printClock() 함수 호출
}

function addZeros(num, digit) { // 자릿수 맞춰주기
	var zero = '';
	num = num.toString();
	if (num.length < digit) {
		for (i = 0; i < digit - num.length; i++) {
			zero += '0';
		}
	}
	return zero + num;
}

// lib 시작
String.prototype.replaceAll = function(org, dest) {
	return this.split(org).join(dest);
}

function jq_attr($el, attrName, elseValue) {
	var value = $el.attr(attrName);

	if (value === undefined || value === "") {
		return elseValue;
	}

	return value;
}

function getUrlParams(url) {
	url = url.trim();
	url = url.replaceAll('&amp;', '&');
	if (url.indexOf('#') !== -1) {
		var pos = url.indexOf('#');
		url = url.substr(0, pos);
	}

	var params = {};

	url.replace(/[?&]+([^=&]+)=([^&]*)/gi, function(str, key, value) {
		params[key] = value;
	});
	return params;
}
// lib 끝



/* </script> 글작성시 오류 수정 */

function getBodyFromXTemplate(selector) {
	return $(selector).html().trim().replace(/<!--REPLACE:script-->/gi,
			'script');
}

