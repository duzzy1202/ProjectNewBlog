<%@ page import="com.java.blog.dto.Member"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="/jsp/part/head.jspf"%>

<div class="myInfo-box">
	<div class="con">
		<div class="myInfo-title">
			<h1>회원 정보</h1>
		</div>
		<div class="myInfo-body">
			<div class="Information">
				<div class="name">이름 : <span><%=loggedInMember.getName() %></span></div>
				<div class="nick">닉네임 : <span><%=loggedInMember.getNickname() %></span></div>
				<div class="email">E-Mail : <span><%=loggedInMember.getEmail() %></span></div>
				<div class="updateDate">최후 수정일 : <span><%=loggedInMember.getUpdateDate() %></span></div>
			</div>
			<div class="btn-box">
				<input type="button" value="정보 변경하기" onClick="location.href='/blog/s/member/checkPw'">
			</div>
		</div>
	</div>
</div>

<%@ include file="/jsp/part/foot.jspf"%>

