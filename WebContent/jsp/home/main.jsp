<%@ page import="java.util.List"%>
<%@ page import="com.java.blog.dto.Article"%>
<%@ page import="com.java.blog.dto.ArticleReply"%>
<%@ page import="com.java.blog.dto.Chat"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="/jsp/part/head.jspf"%>

<%
	List<Article> articles = (List<Article>) request.getAttribute("articles");
	List<ArticleReply> replysCount = (List<ArticleReply>) request.getAttribute("replysCount");
	List<Chat> chattings = (List<Chat>) request.getAttribute("chattings");
%>

<div class="lastest-article-list-box visible-md-up">
	<div class="total-article-box1 visible-md-up">
		<div class="total-article-box2">
			<span> <i class="fab fa-free-code-camp"></i>최근 게시물<i
				class="fab fa-free-code-camp"></i>
			</span>
		</div>
	</div>


	<div class="article-list-body visible-md-up">
		<ul>
			<%
				for (Article article : articles) {
					String cateName = "";
					switch (article.getCateItemId()) {
						case 1 :
							cateName = "잡담";
							break;
						case 2 :
							cateName = "IT기타";
							break;
						case 3 :
							cateName = "프론트엔드";
							break;
						case 4 :
							cateName = "백엔드";
							break;
						case 5 :
							cateName = "알고리즘";
							break;
						case 6 :
							cateName = "게임";
							break;
						case 7 :
							cateName = "축구";
							break;
						case 8 :
							cateName = "유튜브";
							break;
					}
			%>

			<li>
				<div>
					<i class="fab fa-free-code-camp"></i>
				</div>
				<div class="articleId"><%=article.getId()%></div>
				<div class="cateName"><%=cateName%></div>
				<div class="articleTitle">
					<%
						int replyCount = 0;
							for (ArticleReply reply : replysCount) {
								if (reply.getArticleId() == article.getId()) {
									replyCount += 1;
								}
							}
					%>
					<a href="/blog/s/article/detail?id=<%=article.getId()%>"><%=article.getTitle()%>
						(<%=replyCount%>)</a>
				</div>
				<div class="articleRegDate"><%=article.getRegDate()%></div>
			</li>

			<%
				}
			%>
		</ul>
	</div>
</div>
<div class="main-right visible-md-up">
	<div class="chatting visible-md-up" id="chatting">
		<div class="chatting-box" onload="javascript: chattingScroll();"
			id="chattingBox">
			<div class="chatting-insidebox" id="chattinginsidebox">
				<%
					for (Chat chat : chattings) {
				%>
				<div class="chatList" id="chatList">
					<div class="nick"><%=chat.getExtra().get("writer")%></div>
					<div class="time"><%=chat.getRegDate()%></div>
					<div class="body"><%=chat.getBody()%></div>
				</div>
				<%
					}
				%>
			</div>

			<form class="input-chat" method="POST" action="writeChat">
				<textarea name="chattingBody">채팅 입력</textarea>
				<input type="hidden" name="memberId" value="<%=loggedInMemberId%>">
				<%
					if (isloggedIn == true) {
				%>
				<input class="submit" type='submit' value='[ > ]'>
				<%
					}
				%>
			</form>
		</div>
	</div>
</div>

<%@ include file="/jsp/part/foot.jspf"%>

<script>
	/*
	 window.setInterval("refreshDiv()", 1000);
	 function refreshDiv() {
		 $("#chattingBox").load(document.reload("#chatList"));
		 }
	 */
	
	setInterval(function() {
	$('#chattinginsidebox').reload(location.URL + '#chatList');
	}, 1000);
	

	/*
	 function asasas(){  
	 setTimeout('location.reload()',1000); 
	 }*/
</script>
