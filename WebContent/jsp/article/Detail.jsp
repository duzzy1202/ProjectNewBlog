<%@ page import="com.Dto.java.blog.Article"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	
<%@ include file="/jsp/part/head.jspf"%>

<%
	Article article = (Article) request.getAttribute("article");
%>

<div class="detail-box visible-md-up">
	<div class="con visible-md-up">
		<div class="detail-title">
			<div class="detail-title-box">
				<i class="fab fa-free-code-camp"></i><span><%=article.getTitle()%></span>
				<div class="article-regdate">
					<span><%=article.getRegDate()%></span>
				</div>
				<div class="back-list-btn">
					<a href="javascript:history.back();">목록</a>
				</div>
			</div>
		</div>

		<div class="detail-body">
			<div class="detail-body-box">
				<script type="text/x-template" id="origin1" style="display: none;"><%=article.getBody()%></script>
				<div id="viewer1"></div>
				<script>
					var editor1__initialValue = $('#origin1').html();
					var editor1 = new toastui.Editor({
						el : document.querySelector("#viewer1"),
						viewer : true,
						initialValue : editor1__initialValue,
						plugins : [ toastui.Editor.plugin.codeSyntaxHighlight,
								youtubePlugin, replPlugin, codepenPlugin ]
					});
				</script>
			</div>
		</div>
	</div>
</div>

<%@ include file="/jsp/part/foot.jspf"%>