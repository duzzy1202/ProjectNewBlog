<%@ page import="com.java.blog.dto.CateItem"%>
<%@ page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="/jsp/part/head.jspf"%>


<meta name="viewport" content="width=device-width, initial-scale=1">

<!-- 하이라이트 라이브러리 추가, 토스트 UI 에디터에서 사용됨 -->
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/highlight.js/10.1.1/highlight.min.js"></script>
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/highlight.js/10.1.1/styles/default.min.css">

<!-- 하이라이트 라이브러리, 언어 -->
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/highlight.js/10.1.1/languages/css.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/highlight.js/10.1.1/languages/javascript.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/highlight.js/10.1.1/languages/xml.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/highlight.js/10.1.1/languages/php.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/highlight.js/10.1.1/languages/php-template.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/highlight.js/10.1.1/languages/sql.min.js"></script>

<!-- 코드 미러 라이브러리 추가, 토스트 UI 에디터에서 사용됨 -->
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/codemirror/5.48.4/codemirror.min.css" />

<!-- 토스트 UI 에디터, 자바스크립트 코어 -->
<script
	src="https://uicdn.toast.com/editor/latest/toastui-editor-all.min.js"></script>

<!-- 토스트 UI 에디터, 신택스 하이라이트 플러그인 추가 -->
<script
	src="https://uicdn.toast.com/editor-plugin-code-syntax-highlight/latest/toastui-editor-plugin-code-syntax-highlight-all.min.js"></script>

<!-- 토스트 UI 에디터, CSS 코어 -->
<link rel="stylesheet"
	href="https://uicdn.toast.com/editor/latest/toastui-editor.min.css" />

<%
	List<CateItem> cateItems = (List<CateItem>) request.getAttribute("cateItems");
	int articleId = (int) request.getAttribute("id");
	int articleCateItemId = (int) request.getAttribute("cateItemId");
	String articleTitle = (String) request.getAttribute("title");
	String articleBody = (String) request.getAttribute("body");
%>
<div class="doWrite-box">
	<div class="con">
		<div class="doWrite-title">
			<h1>글 수정하기</h1>
		</div>
		<div class="doWrite-body">
			<form class="input-article" method="POST" action="doUpdate"
				onsubmit="submitWriteForm(this); return false">
				<div class="select-cateItem inputs">
					<span>카테고리</span> <select name="cateItem" id="select-cate">
						<%
							for (CateItem cateItem : cateItems) {
								String cateItemName = cateItem.getName();
								int cateItemId = cateItem.getId();
								String selected = "";
								if (cateItemId == articleCateItemId) {
									selected = "selected=\"selected\"";
								}
						%>
						<option value="<%=cateItemId%>" <%= selected %>>
							<%=cateItemName%>
						</option>
						<%
							}
						%>
					</select>
				</div>
				<div class="input-title inputs">
					<span>제목</span> <input type="text" name="title" value="<%= articleTitle %>"
						maxlength="50">
				</div>
				<div class="input-body inputs">
					<span>내용</span> <input type="hidden" name="body">

					<div id="editor1" style="background-color: white;"></div>
				</div>
				<input type="hidden" name="articleId" value="<%= articleId %>">
				<input class="submit" type='submit' value='수정 완료'>
			</form>
		</div>
	</div>
</div>

<%@ include file="/jsp/part/foot.jspf"%>

<script>
	var editor1 = new toastui.Editor({
		el : document.querySelector("#editor1"),
		width : "600px",
		height : "500px",
		previewStyle : "vertical",
		initialValue : "",
		plugins : [ toastui.Editor.plugin.codeSyntaxHighlight, youtubePlugin,
				replPlugin, codepenPlugin ]
	});

	function submitWriteForm(form) {
		/* 제목 입력 검사 */
		form.title.value = form.title.value.trim();
		if (form.title.value.length == 0) {
			alert('제목이 입력되지 않았습니다.');
			form.title.focus();

			return;
		}
		/* 내용 입력 검사 */
		var source = editor1.getMarkdown().trim();
		if (source.length == 0) {
			alert('내용을 입력해주세요.');
			editor1.focus();
			return;
		}

		form.body.value = source;
		form.submit();
	}
</script>