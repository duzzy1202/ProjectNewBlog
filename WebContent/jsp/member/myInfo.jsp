<%@ page import="com.java.blog.dto.Member"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<%@ include file="/jsp/part/head.jspf"%>

<div class="myInfo-box">
	<div class="con">
		<div class="myInfo-title">
			<h1>회원 정보</h1>
		</div>
		<div class="myInfo-body">
			<div class="Information">
				<div class="name">이름 : <span>${loggedInMember.name }</span></div>
				<div class="nick">닉네임 : <span>${loggedInMember.nickname }</span></div>
				<div class="email">E-Mail : <span>${loggedInMember.email }</span></div>
				<div class="email">E-Mail 인증여부 : 
				<c:choose>
					<c:when test="${loggedInMember.level >= 2 }"> <span> 인증 완료 </span> </c:when>
					<c:otherwise> <span> 미인증 </span> </c:otherwise>
				</c:choose>
				</div>
				<div class="updateDate">최후 수정일 : <span> ${loggedInMember.updateDate }</span></div>
			</div>
			<div class="btn-box">
				<a href="/blog/s/member/checkPw">정보 변경하기</a>
				<c:if test="${loggedInMember.level < 2 }">
					<a href="/blog/s/member/authMail">이메일 인증하기</a>
				</c:if>
			</div>
		</div>
	</div>
</div>

<%@ include file="/jsp/part/foot.jspf"%>

