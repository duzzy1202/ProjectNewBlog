<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<%@ include file="/jsp/part/head.jspf"%>

<div class="login-box">
	<div class="con">
		<div class="login-title">
			<h1>로그인</h1>
		</div>
		<div class="login-body">
			<form class="member-login" method="POST" action="doLogin"
				onsubmit="submitLoginForm(this); return false;">
				<div class="login-loginId">
					<span>ID</span> <input type="text" name="loginId" value=""
						maxlength="20">
				</div>
				<div class="login-loginPw">
					<span>비밀번호</span> <input type="password" name="loginPw" value=""
						maxlength="20">
				</div>
				<input class="submit" type='submit' value='로그인'> <input
					type="hidden" name="loginPwReal">
			</form>
		</div>
		<div class="findbox">
			<a href="/blog/s/member/findPw">비밀번호를 잊으셨습니까?</a>
			<a href="/blog/s/member/findId">ID를 잊으셨습니까?</a>
		</div>
	</div>
</div>

<div style="display: block; text-align: center;">
	<a href="/blog/s/member/findPw">비밀번호 찾기</a>
</div>

<%@ include file="/jsp/part/foot.jspf"%>

<script
	src="https://cdnjs.cloudflare.com/ajax/libs/js-sha256/0.9.0/sha256.min.js"></script>
<script>
	function submitLoginForm(form) {
		/* 로그인 아이디 입력 검사 */
		form.loginId.value = form.loginId.value.trim();
		if (form.loginId.value.length == 0) {
			alert('아이디가 입력되지 않았습니다.');
			form.loginId.focus();

			return;
		}

		form.loginPw.value = form.loginPw.value.trim();
		if (form.loginPw.value.length == 0) {
			alert('비밀번호가 입력되지 않았습니다.');
			form.loginPw.focus();

			return;
		}

		form.loginPwReal.value = sha256(form.loginPw.value);
		form.loginPw.value = '';

		form.submit();
	}
</script>