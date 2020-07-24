<%@ page import="com.java.blog.dto.Article"%>
<%@ page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<%@ include file="/jsp/part/head.jspf"%>
<%@ include file="/jsp/part/toastUiEditor.jspf"%>


<meta name="viewport" content="width=device-width, initial-scale=1">

<div class="doWrite-box">
	<div class="con">
		<div class="doWrite-title">
			<h1>글 수정하기</h1>
		</div>
		<div class="doWrite-body">
			<form class="input-article" method="POST" action="doUpdate"
				onsubmit="submitWriteForm(this); return false">
				<input type="hidden" name="articleId" value="${article.id}">
				<div class="select-cateItem inputs">
					<span>카테고리</span> <select name="cateItem" id="select-cate">
						<c:forEach var="cateItem" items="${cateItems }" begin="0" end="${fn:length(cateItems) }" step="1" >
							<c:set var="selected" value="" />
							<c:if test="${cateItem.id == article.cateItemId }">
								<c:set var="selected" value=" selected=\"selected\" " />
							</c:if>
						<option value="${cateItem.id }" ${selected }>
							${cateItem.name }
						</option>
						</c:forEach>
					</select>
				</div>
				<div class="input-title inputs">
					<span>제목</span> <input type="text" name="title" value="${article.title}" maxlength="50">
				</div>
				<div class="input-body inputs">
					<span>내용</span> <input type="hidden" name="body">
					<script type="text/x-template">${article.getBodyForXTemplate()}</script>
					<div class="toast-editor" style="background-color: white;"></div>
				</div>
				<input class="submit" type='submit' value='수정 완료'>
			</form>
		</div>
	</div>
</div>

<%@ include file="/jsp/part/foot.jspf"%>

<script>
	var submitWriteFormDone = false;
	function submitWriteForm(form) {
		if (submitWriteFormDone) {
			alert('처리중입니다.');
			return;
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