<%@ page import="java.util.List"%>
<%@ page import="com.java.blog.dto.Article"%>
<%@ page import="com.java.blog.dto.ArticleReply"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<%@ include file="/jsp/part/head.jspf"%>

<div class="article-list-box  visible-md-up">
	<div class="total-article-box1 visible-md-up">
		<div class="total-article-box2">
			<span> <i class="fab fa-free-code-camp"></i> ${cateItemName } 게시판 : ${totalCount} 개 <i class="fab fa-free-code-camp"></i>
			</span>
		</div>
	</div>


	<div class="article-list-body visible-md-up">
		<ul>
			<c:forEach var="article" items="${articles}" begin="0" end="${fn:length(articles)}" step="1" >
				<c:set var="cateItemN" value="전체" />
				<c:forEach var="i" begin="1" end="${fn:length(cateItems)}" step="1">
					<c:if test="${article.cateItemId == i}">
						<c:set var="cateItemN" value="${cateItems[i-1].name}" />
					</c:if>
				</c:forEach>
			<li>
				<div><i class="fab fa-free-code-camp"></i></div>
				<div class="articleId">${article.id }</div> 
				<c:choose>
					<c:when test="${cateItemName eq '전체' }">
						<div class="cateName">${cateItemN }</div>
					</c:when>
					<c:otherwise>
						<div class="cateName">${article.getExtra().get("writer")}</div>
					</c:otherwise>
				</c:choose>
				<c:set var="replyCounts" value="0" />
				<c:forEach var="reply" items="${replysCount}" begin="0" end="${fn:length(replysCount) }" step="1" >
					<c:if test="${reply.articleId == article.id }">
						<c:set var="replyCounts" value="${replyCounts + 1 }" />
					</c:if>
				</c:forEach>
				<div class="articleTitle"><a href="./detail?id=${article.id }&cateItemId=${param.cateItemId}">${article.title } (${replyCounts})</a></div>
				<div class="articleRegDate">${article.regDate }</div>
			</li>
			</c:forEach>

		</ul>
	</div>

	<div class="con page-box visible-md-up">
		<ul class="flex flex-jc-c">
			<c:forEach var="i" begin="1" end="${totalPage }" step="1" >
			<li class="${i eq page ? 'current' : ''}"><a href="?cateItemId=${param.cateItemId}&page=${i }" class="block">${i }</a></li>
			</c:forEach>
		</ul>
	</div>
</div>



<%@ include file="/jsp/part/foot.jspf"%>
