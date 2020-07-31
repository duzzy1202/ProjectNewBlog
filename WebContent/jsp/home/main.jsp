<%@ page import="java.util.List"%>
<%@ page import="com.java.blog.dto.Article"%>
<%@ page import="com.java.blog.dto.ArticleReply"%>
<%@ page import="com.java.blog.dto.Chat"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<%@ include file="/jsp/part/head.jspf"%>

<div class="lastest-article-list-box visible-md-up">
	<div class="total-article-box1 visible-md-up">
		<div class="total-article-box2">
			<span> <i class="fab fa-free-code-camp"></i>최근 게시물<i class="fab fa-free-code-camp"></i>
			</span>
		</div>
	</div>


	<div class="article-list-body visible-md-up">
		<ul>
			<c:forEach var="article" items="${articles}" begin="0" end="${fn:length(articles) }" step="1" >
				<c:set var="cateItemName" />
				<c:forEach var="i" begin="1" end="${fn:length(cateItems)}" step="1">
					<c:if test="${article.cateItemId == i}">
						<c:set var="cateItemName" value="${cateItems[i-1].name}" />
					</c:if>
				</c:forEach>
				<li>
					<div><i class="fab fa-free-code-camp"></i></div>
					<div class="articleId">${article.id }</div>
					<div class="cateName">${cateItemName }</div>
					<div class="articleTitle">
						<c:set var="replyCounts" value="0" />
						<c:forEach var="reply" items="${replysCount}" begin="0" end="${fn:length(replysCount) }" step="1" >
							<c:if test="${reply.articleId == article.id }">
								<c:set var="replyCounts" value="${replyCounts + 1 }" />
							</c:if>
						</c:forEach>
						<a href="/blog/s/article/detail?id=${article.id }">${article.title } (${replyCounts})</a>
					</div>
					<div class="articleRegDate">${article.regDate }</div>
				</li>
			</c:forEach>
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
				<c:forEach var="chat" items="${chattings }" begin="0" end="${fn:length(chattings) }" step="1" >
				<div class="chatList" id="chatList"
					<c:if test="${chat.memberId == loggedInMemberId }">
					style="background: rgba(200, 230, 255, 0.5);"
					</c:if> >
					<div class="nick">
						[
						${chat.getExtra().get("writer") }
						]
						<c:if test="${chat.memberId == loggedInMemberId }">
						( 나 )
						</c:if>
					</div>
					<div class="time">
						[${chat.regDate }]
					</div>
					<div class="body">${chat.body }</div>
				</div>
				</c:forEach>
			</div>

			<form class="input-chat" id="inputChat" method="POST"
				action="writeChat" onsubmit="submitChatForm(this); return false;">
				<input type="text" name="chattingBody" class="chattingBody"
					autocomplete="off" id="chattext" autofocus> <input
					type="hidden" name="memberId" value="${loggedInMemberId}">
					
				<c:if test="${isLoggedIn && loggedInMember.level >= 2 }" >
				<button class="submit" type='button'
					onclick="submitChatForm(inputChat); return false;">[ > ]</button>
				</c:if>
				
			</form>
		</div>
	</div>
</div>

<c:if test="${isLoggedIn }">
	<c:if test="${loggedInMember.level == 10 }">
<div class="admin"
	style="display: block; text-align: center; position: absolute; top: 82%; left: 40%;">
	<form class="admin-btn" action="deleteAllChats">
		<input type='submit' value="[관리자] 채팅 내역 삭제하기"
			onclick="if ( confirm('삭제하시겠습니까?') == false ) return false;"
			style="width: 300px; height: 150px;">
	</form>
</div>
</c:if> </c:if>

<%@ include file="/jsp/part/foot.jspf"%>

<script>

	/* 스크롤바가 바닥 또는 바닥에서 60픽셀 떨어진곳 안쪽에 위치하면 자동으로 채팅창 로드 밑 스크롤을 아래로 */
	var checkbottom;
	jQuery(function($) {
		$('#chattingBox').on('scroll',function() {
			var check = $(this).scrollTop() + $(this).innerHeight() >= $(this)[0].scrollHeight - 60;
			if (check) {
				checkbottom = "bottom";
			} else {
				checkbottom = "nobottom";
			}
		})
	});

	setInterval(function() { 
		if (checkbottom == "bottom") { $("#chattinginsidebox").load( location.href + " #chattinginsidebox");
			$('#chattingBox').scrollTop($('#chattingBox').prop('scrollHeight'));
			}}, 500);

	/* 채팅창 스크롤 위치를 맨 아래로 */
	$('#chattingBox').scrollTop($('#chattingBox').prop('scrollHeight'));

	/* 채팅 입력 후 키보드의 Enter 입력으로 서밋 */
	$("#chattingBody").keyup(function(e) {
		if (e.keyCode == 13)
			submit();
	});

	/* 커서를 채팅창 텍스트박스에 위치 */
	function inItCursor() {
		chattext.focus();
	}

	/* 채팅내용 입력 검사 */
	function submitChatForm(form) {
		form.chattingBody.value = form.chattingBody.value.trim();
		if (form.chattingBody.value.length == 0) {
			alert('채팅 내용을 입력해주세요.');
			form.chattingBody.focus();

			return;
		} else {
			form.submit();
		}
	}
</script>
