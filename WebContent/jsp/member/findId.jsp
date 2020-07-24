<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<%@ include file="/jsp/part/head.jspf"%>

<div class="findId-box">
	<div class="con">
		<div class="findId-title">
			<h1>아이디 찾기</h1>
		</div>
		<div class="findId-body">
			<form class="findId" method="POST" action="doFindId" onsubmit="submitFindForm(this); return false;">
				<div class="find-name"><span>이름</span> <input type="text" name="name" value="" maxlength="10"></div>
				<div class="find-email"><span>이메일</span> <input type="email" name="email" value=""></div>
				<input type="hidden" name="loginPw" value="1234">
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
	form.loginPw.value = '';

	form.submit();
	joinFormSubmitted = true;
}
</script>
