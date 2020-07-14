<%@ page import="java.util.List"%>
<%@ page import="com.java.blog.dto.Article"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="/jsp/part/head.jspf"%>

<%
	List<Article> articles = (List<Article>) request.getAttribute("articles");

 	int currentMemberId = -1;
 	
 	if ( session.getAttribute("loggedInMemberId") != null ) {
 		currentMemberId = (int)session.getAttribute("loggedInMemberId");
 	}
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
					case 1:
						cateName = "잡담";
						break;
					case 2:
						cateName = "IT기타";
						break;
					case 3:
						cateName = "프론트엔드";
						break;
					case 4:
						cateName = "백엔드";
						break;
					case 5:
						cateName = "알고리즘";
						break;
					case 6:
						cateName = "게임";
						break;
					case 7:
						cateName = "축구";
						break;
					case 8:
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
					<a href="/blog/s/article/detail?id=<%=article.getId()%>"><%=article.getTitle()%></a>
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
		<form>
			<button type="button" onclick="location.href='/blog/s/article/write' ">글쓰기</button>
			<% if ( currentMemberId == -1 ) {%>
			<button type="button" onclick="location.href='/blog/s/member/login' ">로그인</button>
			<button type="button" onclick="location.href='/blog/s/member/join' ">회원가입</button>
			<% } 
			else { %>
			<button type="button" onclick="location.href='/blog/s/member/logout' ">로그아웃</button>
			<% }%>
		</form>
	</div>
<%@ include file="/jsp/part/foot.jspf"%>

