<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="/jsp/part/messageHead.jspf"%>

<body>
	<div class="message-box">
		<div class="con">
			<div class="message-box-title">
				<span>받은 쪽지함</span>
			</div>
			<div class="message-box-body">
				<div class="message-box-body-top">
					<span>받은 쪽지 : 몇개</span>
					<a href="/blog/s/message/sentMessageList">보낸 쪽지함</a>
					<a href="/blog/s/message/writeMessage">쪽지 보내기</a>
				</div>
				<div class="message-box-body-bottom">
					<div class="message-box-list">
						<c:choose>
						<c:when test="${fn:length(receivedMessages) == 0 }" >
							<div style="margin-left: 50%; transform: translatex(-50%); margin-top: 50px; color: white; text-align: center;">받은 메세지가 없습니다.</div>
						</c:when>
						<c:otherwise>
						<c:forEach var="message" items="${receivedMessages }" begin="0" end="${fn:length(receivedMessages)}" step="1">
							<ul>
								<li>${message.getExtra().get("messageWriter") }</li>
								<li><a href="./messageDetail?id=${message.id }">${message.title }</a></li>
								<a href="replyMessage?id=${message.id}">답장</a>
								<a href="deleteMessage?id=${message.id}" onclick="if ( confirm('삭제하시겠습니까?') == false ) return false;">삭제</a>
							</ul>
						</c:forEach>
						</c:otherwise>
						</c:choose>
					</div>
					<div class="message-box-page">
						
					</div>
				</div>
			</div>
		</div>
	</div>

<%@ include file="/jsp/part/foot.jspf"%>