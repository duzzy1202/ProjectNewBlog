<%@ page import="com.Dto.java.blog.CateItem"%>
<%@ page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="/jsp/part/head.jspf"%>

<%
	List<CateItem> cateItems = (List<CateItem>) request.getAttribute("cateItems");
%>
<div class="doWrite-box">
	<div class="con">
		<div class="doWrite-title">
			<h1>글 작성하기</h1>
		</div>
		<div class="doWrite-body">
			<form class="input-article" method="POST" action="doWrite"
				onsubmit="submitWriteForm(this); return false">
				<div class="select-cateItem inputs">
					<span>카테고리</span> <select name="cateItem" id="select-cate">
						<%
							for (CateItem cateItem : cateItems) {
								String cateItemName = cateItem.getName();
								int cateItemId = cateItem.getId();
						%>
						<option value="<%=cateItemId%>">
							<%=cateItemName%>
						</option>
						<%
							}
						%>
					</select>
				</div>
				<div class="input-title inputs">
					<span>제목</span> <input type="text" name="title" value=""
						maxlength="50">
				</div>
				<div class="input-body inputs">
					<span>내용</span>
					<textarea name="body"></textarea>
				</div>
				<input class="submit" type='submit' value='작성 완료'>
			</form>
		</div>
	</div>
</div>

<%@ include file="/jsp/part/foot.jspf"%>

<script>
	function submitWriteForm(form) {
		/* 제목 입력 검사 */
		form.title.value = form.title.value.trim();
		if (form.title.value.length == 0) {
			alert('제목이 입력되지 않았습니다.');
			form.title.focus();

			return;
		}
		/* 내용 입력 검사 */
		form.body.value = form.body.value.trim();
		if (form.body.value.length == 0) {
			alert('내용이 입력되지 않았습니다.');
			form.body.focus();

			return;
		}
		
		form.submit();
	}
</script>