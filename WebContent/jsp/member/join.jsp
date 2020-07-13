<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	
<%@ include file="/jsp/part/head.jspf"%>

<div class="join-box">
	<div class="con">
		<div class="join-title">
			<h1>회원가입</h1>
		</div>
		<div class="join-body">
			<form class="member-join" method="POST" action="doJoin" onsubmit="submitJoinForm(this); return false;">
				<div class="join-loginId"><span>ID</span> <input type="text" name="loginId" value="" maxlength="20"></div>
				<div class="join-loginPw"><span>비밀번호</span> <input type="password" name="loginPw" value="" maxlength="20"></div>
				<div class="join-loginPwConfirm"><span>비밀번호 확인</span> <input type="password" name="loginPwConfirm" value="" maxlength="20"></div>
				<div class="join-name"><span>이름</span> <input type="text" name="name" value="" maxlength="10"></div>
				<div class="join-nickname"><span>닉네임</span> <input type="text" name="nickname" value="" maxlength="16"></div>
				<div class="join-email"><span>이메일</span> <input type="text" name="email" value=""></div>
				<input class="submit" type='submit' value='회원가입'>
				<input type="hidden" name="loginPwReal">
				<input type="hidden" name="loginPwConfirmReal">
			</form>
		</div>
	</div>
</div>

<%@ include file="/jsp/part/foot.jspf"%>

<script src="https://cdnjs.cloudflare.com/ajax/libs/js-sha256/0.9.0/sha256.min.js"></script>
<script>
/* 입력관련 */
var joinFormSubmitted = false;

function submitJoinForm(form) {
	if (joinFormSubmitted) {
		alert('처리 중입니다.');
		return;
	}

	/* 로그인 아이디 입력 검사 */
	form.loginId.value = form.loginId.value.trim();
	if (form.loginId.value.length == 0) {
		alert('아이디가 입력되지 않았습니다.');
		form.loginId.focus();

		return;
	}
	if (form.loginId.value.indexOf(' ') != -1) {
		alert('아이디를 영문소문자와 숫자의 조합으로 입력해주세요.')
		form.loginId.focus();

		return;
	}

	/* 비밀번호 입력 검사 */
	form.loginPw.value = form.loginPw.value.trim();
	if (form.loginPw.value.length == 0) {
		alert('비밀번호가 입력되지 않았습니다.');
		form.loginPw.focus();

		return;
	}

	/* 비밀번호 확인 입력 검사 */
	form.loginPwConfirm.value = form.loginPwConfirm.value.trim();
	if (form.loginPwConfirm.value.length == 0) {
		alert('비밀번호 확인이 입력되지 않았습니다.');
		form.loginPwConfirm.focus();

		return;
	}

	/* 이름 입력 검사 */
	form.name.value = form.name.value.trim();
	if (form.name.value.length == 0) {
		alert('이름이 입력되지 않았습니다.');
		form.name.focus();

		return;
	}

	/* 닉네임 입력 검사 */
	form.nickname.value = form.nickname.value.trim();
	if (form.nickname.value.length == 0) {
		alert('닉네임이 입력되지 않았습니다.');
		form.nickname.focus();

		return;
	}

	/* 이메일 입력 검사 */
	form.email.value = form.email.value.trim();
	if (form.email.value.length == 0) {
		alert('이메일이 입력되지 않았습니다.');
		form.email.focus();

		return;
	}

	form.loginPwReal.value = sha256(form.loginPw.value);
	form.loginPwConfirmReal.value = sha256(form.loginPwConfirm.value);
	form.loginPw.value = '';
	form.loginPwConfirm.value = '';

	form.submit();
	joinFormSubmitted = true;
}
</script>
