<%@ page import="com.java.blog.dto.Article"%>
<%@ page import="com.java.blog.dto.ArticleReply"%>
<%@ page import="com.java.blog.dto.Member"%>
<%@ page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<%@ include file="/jsp/part/head.jspf"%>
<%@ include file="/jsp/part/toastUiEditor.jspf"%>

<div class="detail-box visible-md-up">
	<div class="con visible-md-up">
		<div class="detail-title">
			<div class="detail-title-box">
				<i class="fab fa-free-code-camp"></i><span>${article.title }</span>
				<div class="back-list-btn">
					<button type="button" onclick="javascript:location.replace('list?cateItemId=${article.cateItemId }');">목록</button>
				</div>
			</div>
		</div>
		<div class="detail-body">
			<div class="bottom-box">
				<span>작성자 : ${article.getExtra().get("writer") }</span> <span>게시물 작성일 : ${article.regDate }</span> 
				 <span>조회수 : ${article.hits }</span> <span>게시물 수정일 : ${article.updateDate }</span>
			</div>
			<div class="detail-body-box">
				<script type="text/x-template">${article.getBodyForXTemplate() }</script>
				<div class="toast-editor toast-editor-viewer"></div>
			</div>
			<c:if test="${loggedInMemberId == article.memberId }">
			<div class="buttons">
				<div class="update-btn">
					<a href="update?id=${param.id}">수정하기</a>
				</div>
				<div class="delete-btn">
					<a href="delete?id=${param.id}" onclick="if ( confirm('삭제하시겠습니까?') == false ) return false;">삭제하기</a>
				</div>
			</div>
			</c:if>
		</div>
	</div>
</div>
<div class="reply-box visible-md-up">
	<div class="reply-list-box">
		<div class="reply-list visible-md-up">
			<ul style="overflow: auto; height: 600px;">
				<c:forEach var="reply" items="${replys }" begin="0" end="${fn:length(replys) }" step="1">

				<li>
					<div class="writer">${reply.getExtra().get("replyWriter")}</div>
					<div class="replyRegdate">${reply.regDate }</div>
					<div class="replyBody" id="replyBody(${reply.id })">${reply.body }</div>
					<form class="update-reply" method="POST" action="updateReply" id="updateReply(${reply.id })" onsubmit="submitReplyForm(this); return false;">
						<textarea name="replyBody">${reply.body }</textarea>
						<input type="hidden" name="replyId" value="${reply.id }">
						<input type="hidden" name="articleId" value="${article.id }">  
						<input class="submit" type='submit' value='수정완료' onclick="javascript:changeDisplayNone(${reply.id })">
					</form>
				</li>
				<c:choose>
					<c:when test="${loggedInMember == null }"></c:when>
					<c:when test="${reply.memberId == loggedInMember.id }">
						<div class="reply-buttons" style="display: flex; justify-content: flex-end;">
							<input type="button" name="updateReply" value="수정" style="margin: 5px;" onclick="javascript:changeDisplayBlock(${reply.id })">
							<form class="deleteReply" method="POST" action="deleteReply" style="margin: 5px; padding: 0;"> 
								<input type="hidden" name="replyId" value="${reply.id }">
								<input type="hidden" name="articleId" value="${article.id }">  
								<input type="submit" name="deleteReply" onclick="if ( confirm('삭제하시겠습니까?') == false ) return false;" value="삭제">
							</form>
						</div>
					</c:when>
				</c:choose>
				</c:forEach>
			</ul>
		</div>
		<div class="con page-box visible-md-up">
			<ul class="flex flex-jc-c" style="color: white; margin: 5px;">
				<c:forEach var="i" begin="1" end="${totalPage }" step="1">
					<li class="${i == paramPage ? 'current' : ''}" style="padding: 10px;"><a href="?id=${param.id}&page=${i }" class="block">${i }</a></li>
				</c:forEach>
			</ul>
		</div>
		<div class="write-reply">
			<form class="input-reply" method="POST" action="writeReply" onsubmit="submitReplyForm(this); return false;">
				<c:choose>
					<c:when test="${loggedInMember == null }">
						<textarea name="replyBody">로그인 후에 댓글 작성 가능합니다.</textarea>
					</c:when>
					<c:when test="${loggedInMember.level < 2}">
						<textarea name="replyBody">이메일 인증 후에 작성 가능합니다.</textarea>
					</c:when>
					<c:otherwise>
						<textarea name="replyBody"></textarea>
						<input class="submit" type='submit' value='등록하기'> 
						<input type="hidden" name="replyMemberId" value="${loggedInMember.id }"> 
						<input type="hidden" name="articleId" value="${article.id }">
					</c:otherwise>
				</c:choose>
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

	function submitReplyForm(form) {

		/* 댓글 입력 검사 */
		form.replyBody.value = form.replyBody.value.trim();
		if (form.replyBody.value.length == 0) {
			alert('댓글 내용이 입력되지 않았습니다.');
			form.replyBody.focus();

			return;
		}
		form.submit();
	}


</script>

