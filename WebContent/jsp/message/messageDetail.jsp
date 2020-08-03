<%@ page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<%@ include file="/jsp/part/messageHead.jspf"%>

<div class="messageDetail-box">
	<div class="con">
		<div class="messageDetail-title">
			<div class="messageDetail-title-box">
				<span>${message.title }</span>
				<div class="back-list-btn">
					<button type="button" onclick="javascript: history.back();">뒤로</button>
				</div>
			</div>
		</div>
		<div class="messageDetail-body">
			<div class="messageDetail-top-box">
				<span>보낸이 : ${fromMember.nickname }</span> <span> [ ${message.regDate } ]</span> 
			</div>
			<div class="messageDetail-body-box">
				<script type="text/x-template">${message.body }</script>
				<div class="toast-editor toast-editor-viewer"></div>
			</div>
			<c:if test="${loggedInMemberId == message.toMemberId }">
			<div class="messageDetail-buttons">
				<div class="delete-btn btn">
					<a href="deleteMessage?id=${param.id}" onclick="if ( confirm('삭제하시겠습니까?') == false ) return false;">삭제하기</a>
				</div>
				<div class="reply-btn btn">
					<a href="replyMessage?id=${param.id}">답장하기</a>
				</div>
			</div>
			</c:if>
		</div>
	</div>
</div>

<%@ include file="/jsp/part/foot.jspf"%>

<%@ include file="/jsp/part/toastUiEditor.jspf"%>
