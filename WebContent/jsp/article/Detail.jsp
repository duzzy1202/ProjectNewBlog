<%@ page import="com.java.blog.dto.Article"%>
<%@ page import="com.java.blog.dto.ArticleReply"%>
<%@ page import="com.java.blog.dto.Member"%>
<%@ page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="/jsp/part/head.jspf"%>

<%
	Article article = (Article) request.getAttribute("article");
	Member writer = (Member) request.getAttribute("writer");
	Member currentMember = (Member) request.getAttribute("currentMember");
	List<Member> replyMembers = (List<Member>) request.getAttribute("replyMembers");
	List<ArticleReply> replys = (List<ArticleReply>) request.getAttribute("replys");
	int totalPage = (int) request.getAttribute("totalPage");
	int paramPage = (int) request.getAttribute("page");
	int replyUpdateMode = (int) request.getAttribute("replyUpdateMode");
%>

<div class="detail-box visible-md-up">
	<div class="con visible-md-up">
		<div class="detail-title">
			<div class="detail-title-box">
				<i class="fab fa-free-code-camp"></i><span><%=article.getTitle()%></span>
				<div class="back-list-btn">
					<button type="button" onclick="javascript:location.replace('detail?id="<%=article.getCateItemId()%>"');">목록</button>
				</div>
			</div>
		</div>
		<div class="detail-body">
			<div class="bottom-box">
				<span>작성자 : <%=writer.getNickname()%></span> <span>게시물 작성일 : <%=article.getRegDate()%></span> 
				 <span>조회수 : <%=article.getHits()%></span> <span>게시물 수정일 : <%=article.getUpdateDate()%></span>
			</div>
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
			<div class="buttons">
				<div class="update-btn">
					<form class="input-article" method="POST" action="update"
						onsubmit="submitUpdateForm(this); return false">
						<input type="hidden" name="articleId" value="<%=article.getId()%>">
						<input type="hidden" name="articleTitle"
							value="<%=article.getTitle()%>"> <input type="hidden"
							name="articleBody" value="<%=article.getBody()%>"> <input
							type="hidden" name="cateItemId"
							value="<%=article.getCateItemId()%>"> <input
							class="submit" type='submit' value='수정하기'>
					</form>
				</div>
				<div class="delete-btn">
					<form class="input-article" method="POST" action="delete"
						onsubmit="submitUpdateForm(this); return false">
						<input type="hidden" name="cateItemId"
							value="<%=article.getCateItemId()%>"> <input
							type="hidden" name="articleId" value="<%=article.getId()%>">
						<input class="submit" type='submit' value='삭제하기'>
					</form>
				</div>
			</div>
		</div>
	</div>
</div>
<div class="reply-box visible-md-up">
	<div class="reply-list-box">
		<div class="reply-list visible-md-up">
			<ul>
				<%
					for (int i = 0; i < replys.size(); i++) {
						String replyWriter = replyMembers.get(i).getNickname();
				%>

				<li>
					<div class="writer"><%=replyWriter%></div>
					<div class="replyRegdate"><%=replys.get(i).getRegDate()%></div>
					<div class="replyBody" id="replyBody(<%=replys.get(i).getId()%>)"><%=replys.get(i).getBody()%></div>
					<form class="update-reply" method="POST" action="updateReply" id="updateReply(<%=replys.get(i).getId()%>)" >
						<textarea name="replyBody"><%=replys.get(i).getBody()%></textarea>
						<input type="hidden" name="replyId" value="<%=replys.get(i).getId()%>">
						<input type="hidden" name="articleId" value="<%=article.getId()%>">  
						<input class="submit" type='submit' value='수정완료' onclick="javascript:changeDisplayNone(<%=replys.get(i).getId()%>)">
					</form>
				</li>
				<% if (currentMember == null) {
						}

						else if (replys.get(i).getMemberId() == currentMember.getId()) { %>
				<div class="reply-buttons" style="display: flex; justify-content: flex-end;">
					<input type="button" name="updateReply" value="수정" style="margin: 5px; onclick="javascript:changeDisplayBlock(<%=replys.get(i).getId()%>);">
					<form class="deleteReply" method="POST" action="deleteReply" style="margin: 5px; padding: 0;"> 
						<input type="hidden" name="replyId" value="<%=replys.get(i).getId() %>">
						<input type="hidden" name="articleId" value="<%=article.getId()%>">  
						<input type="submit" name="deleteReply" value="삭제">
					</form>
				</div>
				<% } %>
				<% } %>
			</ul>
		</div>
		<div class="con page-box visible-md-up">
			<ul class="flex flex-jc-c" style="color: white; margin: 5px;">
				<%
					for (int i = 1; i <= totalPage; i++) {
				%>
				<li class="<%=i == paramPage ? "current" : ""%>"
					style="padding: 10px;"><a href="?id=${param.id}&page=<%=i%>"
					class="block"><%=i%></a></li>
				<%
					}
				%>
			</ul>
		</div>
		<div class="write-reply">
			<form class="input-reply" method="POST" action="writeReply">
				<%
					if (currentMember == null) {
				%>
				<textarea name="replyBody">로그인 후에 댓글 작성 가능합니다.</textarea>
				<%
					} else {
				%>
				<textarea name="replyBody">댓글 내용을 입력해주세요</textarea>
				<input class="submit" type='submit' value='등록하기'> 
				<input type="hidden" name="replyMemberId" value="<%=currentMember.getId()%>"> 
				<input type="hidden" name="articleId" value="<%=article.getId()%>">
				<%
					}
				%>
			</form>
		</div>
	</div>
</div>
<%@ include file="/jsp/part/foot.jspf"%>

<script>
	function changeDisplayBlock(id) {
		var updateReply = document.getElementById('updateReply('+id+')');
		var replyBody = document.getElementById('replyBody('+id+')');
		
		updateReply.style.display = 'block';
		replyBody.style.display = 'none';
	}
	
	function changeDisplayNone(id) {
		var updateReply = document.getElementById('updateReply('+id+')');
		var replyBody = document.getElementById('replyBody('+id+')');
		
		updateReply.style.display = 'none';
		replyBody.style.display = 'block';
	}

</script>
