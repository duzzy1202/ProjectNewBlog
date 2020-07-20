<%@ page import="java.util.List"%>
<%@ page import="com.java.blog.dto.Article"%>
<%@ page import="com.java.blog.dto.ArticleReply"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	
<%@ include file="/jsp/part/head.jspf"%>

<%
	List<Article> articles = (List<Article>) request.getAttribute("articles");
	List<ArticleReply> replysCount = (List<ArticleReply>) request.getAttribute("replysCount");
	int totalPage = (int) request.getAttribute("totalPage");
	int paramPage = (int) request.getAttribute("page");
	String cateItemName = (String) request.getAttribute("cateItemName");
%>

<div class="article-list-box  visible-md-up">
	<div class="total-article-box1 visible-md-up">
		<div class="total-article-box2">
			<span> <i class="fab fa-free-code-camp"></i> <%=cateItemName %> 게시판 : ${totalCount} 개 <i class="fab fa-free-code-camp"></i>
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
				<div><i class="fab fa-free-code-camp"></i></div>
				<div class="articleId"><%=article.getId()%></div> 
				<% if (cateItemName.equals("전체")) { %>
				<div class="cateName"><%=cateName%></div>
				<% } else { %>
				<div class="cateName"><%=article.getExtra().get("writer")%></div>
				<% }
					int replyCount = 0;
					for (ArticleReply reply : replysCount) {
						if (reply.getArticleId() == article.getId()) {
							replyCount += 1;
						}
					}
				%>
				<div class="articleTitle"><a href="./detail?id=<%=article.getId()%>&cateItemId=${param.cateItemId}"><%=article.getTitle()%> (<%=replyCount%>)</a></div>
				<div class="articleRegDate"><%=article.getRegDate()%></div>
			</li>
			
			<%
				}
			%>
		</ul>
	</div>

	<div class="con page-box visible-md-up">
		<ul class="flex flex-jc-c">
			<%
				for (int i = 1; i <= totalPage; i++) {
			%>
			<li class="<%=i == paramPage ? "current" : ""%>"><a href="?cateItemId=${param.cateItemId}&page=<%=i%>" class="block"><%=i%></a></li>
			<%
				}
			%>
		</ul>
	</div>
</div>



<%@ include file="/jsp/part/foot.jspf"%>