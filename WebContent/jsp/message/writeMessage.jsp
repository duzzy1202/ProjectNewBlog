<%@ page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<%@ include file="/jsp/part/messageHead.jspf"%>


<meta name="viewport" content="width=device-width, initial-scale=1">

<div class="messageWrite-box">
	<div class="con">
		<div class="messageWrite-title">
			<h1>쪽지 작성하기</h1>
		</div>
		<div class="messageWrite-body">
			<form class="input-message" method="POST" action="sendMessage" onsubmit="submitWriteForm(this); return false">
				<div class="input-receiver inputs">
					<span>받는사람 닉네임</span> <input type="text" name="receiver" value="" maxlength="50">
				</div>
				<div class="input-title inputs">
					<span>제목</span> <input type="text" name="title" value="" maxlength="50">
				</div>
				<div class="input-body inputs">
					<span>내용</span> <input type="hidden" name="body">
					<script type="text/x-template"></script>
					<div class="toast-editor" style="background-color: white;"></div>
				</div>
				<input class="submit" type='submit' value='보내기'> 
				<input type="hidden" name="writerId" value="${loggedInMemberId}">
			</form>
		</div>
	</div>
</div>

<%@ include file="/jsp/part/foot.jspf"%>

<%@ include file="/jsp/part/toastUiEditor.jspf"%>

<script>
	var submitWriteFormDone = false;
	function submitWriteForm(form) {
		if (submitWriteFormDone) {
			alert('처리중입니다.');
			return;
		}

		form.receiver.value = form.receiver.value.trim();
		if (form.receiver.value.length == 0) {
			alert('받는사람의 닉네임을 입력해주세요.');
			form.receiver.focus();
			return false;
		}

		form.title.value = form.title.value.trim();
		if (form.title.value.length == 0) {
			alert('제목을 입력해주세요.');
			form.title.focus();
			return false;
		}
		
		var editor = $(form).find('.toast-editor').data('data-toast-editor');
		
		var body = editor.getMarkdown();
		body = body.trim();
		if (body.length == 0) {
			alert('내용을 입력해주세요.');
			editor.focus();
			return false;
		}
		
		form.body.value = body;
		form.submit();
		submitWriteFormDone = true;
	}
</script>