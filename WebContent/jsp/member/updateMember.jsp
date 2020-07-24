<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<%@ include file="/jsp/part/head.jspf"%>

<div class="join-box">
	<div class="con">
		<div class="join-title">
			<h1>회원 정보 수정</h1>
		</div>
		<div class="join-body">
			<form class="member-join" method="POST" action="doUpdateMember" onsubmit="submitJoinForm(this); return false;">
				<div class="join-loginId"><span>ID</span> <span>${loggedInMember.loginId }</span></div>
				<div class="join-loginPw"><span>비밀번호</span> <input type="password" name="loginPw" value="" maxlength="20"></div>
				<div class="join-loginPwConfirm"><span>비밀번호 확인</span> <input type="password" name="loginPwConfirm" value="" maxlength="20"></div>
				<div class="join-name"><span>이름</span> <span> ${loggedInMember.name }</span></div>
				<div class="join-nickname"><span>닉네임</span> <span>${loggedInMember.nickname }</span></div>
				<div class="join-email"><span>이메일</span> <span>${loggedInMember.email }</span></div>
				<input type="hidden" name="loginPwReal">
				<input type="hidden" name="loginPwConfirmReal">
				<input type="hidden" name="loginId" value="${loggedInMember.loginId }">
				<input class="submit" type='submit' value='수정완료'>
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

	form.loginPwReal.value = sha256(form.loginPw.value);
	form.loginPwConfirmReal.value = sha256(form.loginPwConfirm.value);
	form.loginPw.value = '';
	form.loginPwConfirm.value = '';

	form.submit();
	joinFormSubmitted = true;
}
</script>
