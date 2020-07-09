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

// 유튜브 플러그인 시작
function youtubePlugin() {
	toastui.Editor.codeBlockManager.setReplacer("youtube", function(youtubeId) {
		// Indentify multiple code blocks
		const wrapperId = "yt" + Math.random().toString(36).substr(2, 10);

		// Avoid sanitizing iframe tag
		setTimeout(renderYoutube.bind(null, wrapperId, youtubeId), 0);

		return '<div id="' + wrapperId + '"></div>';
	});
}

function renderYoutube(wrapperId, youtubeId) {
	const el = document.querySelector('#' + wrapperId);

	var urlParams = getUrlParams(youtubeId);

	var width = '100%';
	var height = '100%';

	if (urlParams.width) {
		width = urlParams.width;
	}

	if (urlParams.height) {
		height = urlParams.height;
	}

	var maxWidth = 500;

	if (urlParams['max-width']) {
		maxWidth = urlParams['max-width'];
	}

	var ratio = '16-9';

	if (urlParams['ratio']) {
		ratio = urlParams['ratio'];
	}

	var marginLeft = 'auto';

	if (urlParams['margin-left']) {
		marginLeft = urlParams['margin-left'];
	}

	var marginRight = 'auto';

	if (urlParams['margin-right']) {
		marginRight = urlParams['margin-right'];
	}

	if (youtubeId.indexOf('?') !== -1) {
		var pos = youtubeId.indexOf('?');
		youtubeId = youtubeId.substr(0, pos);
	}

	el.innerHTML = '<div style="max-width:'
			+ maxWidth
			+ 'px; margin-left:'
			+ marginLeft
			+ '; margin-right:'
			+ marginRight
			+ ';" class="ratio-'
			+ ratio
			+ ' relative"><iframe class="abs-full" width="'
			+ width
			+ '" height="'
			+ height
			+ '" src="https://www.youtube.com/embed/'
			+ youtubeId
			+ '" frameborder="0" allow="accelerometer; autoplay; encrypted-media; gyroscope; picture-in-picture" allowfullscreen></iframe></div>';
}
// 유튜브 플러그인 끝

// repl 플러그인 시작
function replPlugin() {
	toastui.Editor.codeBlockManager.setReplacer("repl", function(replUrl) {
		var postSharp = "";
		if (replUrl.indexOf('#') !== -1) {
			var pos = replUrl.indexOf('#');
			postSharp = replUrl.substr(pos);
			replUrl = replUrl.substr(0, pos);
		}

		if (replUrl.indexOf('?') === -1) {
			replUrl += "?dummy=1";
		}

		replUrl += "&lite=true";
		replUrl += postSharp;

		// Indentify multiple code blocks
		const wrapperId = `yt${Math.random().toString(36).substr(2, 10)}`;

		// Avoid sanitizing iframe tag
		setTimeout(renderRepl.bind(null, wrapperId, replUrl), 0);

		return "<div id=\"" + wrapperId + "\"></div>";
	});
}

function renderRepl(wrapperId, replUrl) {
	const el = document.querySelector(`#${wrapperId}`);

	var urlParams = getUrlParams(replUrl);

	var height = 400;

	if (urlParams.height) {
		height = urlParams.height;
	}

	el.innerHTML = '<iframe height="'
			+ height
			+ 'px" width="100%" src="'
			+ replUrl
			+ '" scrolling="no" frameborder="no" allowtransparency="true" allowfullscreen="true" sandbox="allow-forms allow-pointer-lock allow-popups allow-same-origin allow-scripts allow-modals"></iframe>';
}
// repl 플러그인 끝

// codepen 플러그인 시작
function codepenPlugin() {
	toastui.Editor.codeBlockManager
			.setReplacer(
					"codepen",
					function(codepenUrl) {
						// Indentify multiple code blocks
						const wrapperId = `yt${Math.random().toString(36).substr(2, 10)}`;

						// Avoid sanitizing iframe tag
						setTimeout(renderCodepen.bind(null, wrapperId,
								codepenUrl), 0);

						return '<div id="' + wrapperId + '"></div>';
					});
}

function renderCodepen(wrapperId, codepenUrl) {
	const el = document.querySelector(`#${wrapperId}`);

	var urlParams = getUrlParams(codepenUrl);

	var height = 400;

	if (urlParams.height) {
		height = urlParams.height;
	}

	var width = '100%';

	if (urlParams.width) {
		width = urlParams.width;
	}

	if (!isNaN(width)) {
		width += 'px';
	}

	if (codepenUrl.indexOf('#') !== -1) {
		var pos = codepenUrl.indexOf('#');
		codepenUrl = codepenUrl.substr(0, pos);
	}

	el.innerHTML = '<iframe height="'
			+ height
			+ '" style="width: '
			+ width
			+ ';" scrolling="no" title="" src="'
			+ codepenUrl
			+ '" frameborder="no" allowtransparency="true" allowfullscreen="true"></iframe>';
}
// repl 플러그인 끝

// lib 시작
String.prototype.replaceAll = function(org, dest) {
	return this.split(org).join(dest);
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
