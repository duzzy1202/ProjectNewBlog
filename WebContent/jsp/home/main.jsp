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
		<div class="chatting-title">
			<span> (( 유저 채팅방 ))</span>
		</div>
		<div class="chatting-box" id="chattingBox">
			<div class="chatting-insidebox" id="chattinginsidebox">
				<%
					for (Chat chat : chattings) {
				%>
				<div class="chatList" id="chatList">
					<div class="nick">
						[
						<%=chat.getExtra().get("writer")%>
						]
						<%
						if (chat.getMemberId() == loggedInMemberId) {
					%>
						( 나 )
						<%
						}
					%>
					</div>
					<div class="time">
						[<%=chat.getRegDate()%>]
					</div>
					<div class="body"><%=chat.getBody()%></div>
				</div>
				<%
					}
				%>
			</div>

			<form class="input-chat" id="inputChat" method="POST"
				action="writeChat">
				<input type="text" name="chattingBody" class="chattingBody" autocomplete="off" id="chattext" autofocus > 
					<input type="hidden" name="memberId" value="<%=loggedInMemberId%>">
				<%
					if (isloggedIn == true) {
				%>
				<input class="submit" type='submit' onclick="submit()" value='[ > ]'>
				<%
					}
				%>
			</form>
		</div>
	</div>
</div>

<%@ include file="/jsp/part/foot.jspf"%>

<script>
	/* 0.5초마다 채팅창을 로드 */
	/*
	setInterval(function() {
		$("#chattinginsidebox").load(location.href + " #chattinginsidebox");
		$('#chattingBox').scrollTop($('#chattingBox').prop('scrollHeight'));
	}, 500);
	*/
	
	/* 스크롤바가 바닥 또는 바닥에서 60픽셀 떨어진곳 안쪽에 위치하면 자동으로 채팅창 로드 밑 스크롤을 아래로 */
	var checkbottom;
	jQuery(function($) {
		$('#chattingBox').on('scroll', function() {
			var check = $(this).scrollTop() + $(this).innerHeight() >= $(this)[0].scrollHeight - 60;
			if (check) { 
				checkbottom = "bottom";
			} else {
			checkbottom = "nobottom";
			}
		})
	});
	setInterval(function() {
		if (checkbottom == "bottom") {
			$("#chattinginsidebox").load(location.href + " #chattinginsidebox");
			$('#chattingBox').scrollTop($('#chattingBox').prop('scrollHeight'));
		}
	}, 500);
	

	/* 채팅창 스크롤 위치를 맨 아래로 */
	$('#chattingBox').scrollTop($('#chattingBox').prop('scrollHeight'));

	/* 채팅 입력 후 키보드의 Enter 입력으로 서밋 */
	$("#chattingBody").keyup(function(e) {
		if (e.keyCode == 13)
			submit();
	});

	/* 커서를 채팅창 텍스트박스에 위치 */
	function inItCursor(){
		chattext.focus();
	}
	
</script>
