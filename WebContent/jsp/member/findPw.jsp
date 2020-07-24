<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
	
<%@ include file="/jsp/part/head.jspf"%>

<div class="findPw-box">
	<div class="con">
		<div class="findPw-title">
			<h1>비밀번호 찾기</h1>
		</div>
		<div class="findPw-body">
			<form class="findPw" method="POST" action="doFindPw" onsubmit="submitFindForm(this); return false;">
				<div class="find-loginId"><span>ID</span> <input type="text" name="loginId" value="" maxlength="20"></div>
				<div class="find-name"><span>이름</span> <input type="text" name="name" value="" maxlength="10"></div>
				<div class="find-email"><span>이메일</span> <input type="email" name="email" value=""></div>
				<input type="hidden" name="loginPw" value="${tempPw }">
				<input type="hidden" name="loginPwReal">
				<input class="submit" type='submit' value='찾기'>
			</form>
		</div>
	</div>
</div>

<%@ include file="/jsp/part/foot.jspf"%>

<script src="https://cdnjs.cloudflare.com/ajax/libs/js-sha256/0.9.0/sha256.min.js"></script>
<script>
/* 입력관련 */
var joinFormSubmitted = false;

function submitFindForm(form) {
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

	/* 이름 입력 검사 */
	form.name.value = form.name.value.trim();
	if (form.name.value.length == 0) {
		alert('이름이 입력되지 않았습니다.');
		form.name.focus();

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

	form.submit();
	joinFormSubmitted = true;
}
</script>
