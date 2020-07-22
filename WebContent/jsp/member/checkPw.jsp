<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="/jsp/part/head.jspf"%>

<div class="login-box">
	<div class="con">
		<div class="login-title">
			<h1>비밀번호 확인</h1>
		</div>
		<div class="login-body">
			<form class="member-login" method="POST" action="updateMember" onsubmit="submitLoginForm(this); return false;">
				<div class="login-loginId">
					<span>ID : <%=loggedInMember.getLoginId() %></span>
				</div>
				<div class="login-loginPw">
					<span>비밀번호</span> <input type="password" name="loginPw" value="" maxlength="20">
				</div>
				<input type="hidden" name="loginId" value="<%=loggedInMember.getLoginId() %>">
				<input type="hidden" name="loginPwReal">
				<input class="submit" type='submit' value='확인'> 
			</form>
		</div>
	</div>
</div>

<%@ include file="/jsp/part/foot.jspf"%>

<script
	src="https://cdnjs.cloudflare.com/ajax/libs/js-sha256/0.9.0/sha256.min.js"></script>
<script>
	function submitLoginForm(form) {
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