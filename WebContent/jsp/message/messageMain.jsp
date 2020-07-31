<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html style="background-image: url('/blog/resource/ETC/msb_campfireimg_600x700.jpg');
	background-repeat: no-repeat;
	background-size: cover;
	background-color: black;">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">

<script type="text/javascript"
	src="http://ajax.googleapis.com/ajax/libs/jquery/1.3.0/jquery.min.js"></script>

<!-- 구글 폰트 불러오기 -->
<!-- rotobo(400/900), notosanskr(400/900) -->
<link
	href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@400;900&family=Roboto:wght@400;900&display=swap"
	rel="stylesheet">

<!-- 폰트어썸 불러오기 -->
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.13.1/css/all.min.css">

<link rel="stylesheet" type="text/css"
	href="/blog/resource/css/common.css">

<!-- 제이쿼리 불러오기 -->
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>

<script src="/blog/resource/js/common.js"></script>

<!-- 구글폰트 불러오기 -->
<link
	href="https://fonts.googleapis.com/css2?family=Black+Han+Sans&family=Do+Hyeon&family=Hi+Melody&family=Jua&family=Nanum+Gothic&family=Nanum+Pen+Script&family=Sunflower:wght@300&display=swap"
	rel="stylesheet">
	
<title>쪽지관리창</title>

</head>

<body>
	<div class="message-box">
		<div class="con">
			<div class="message-box-title">
				<span>받은 쪽지함</span>
			</div>
			<div class="message-box-body">
				<div class="message-box-body-top">
					<span>받은 메세지 : 몇개</span>
					<a href="/blog/s/home/receivcedMessage">받은 쪽지함</a>
					<a href="/blog/s/home/sendMessage">쪽지 보내기</a>
				</div>
				<div class="message-box-body-bottom">
					<div class="message-box-list">
						<!-- 포문으로 리스트 가져옴 -->
						<!-- 쪽지 항목 한줄마다 끝부분에 [답장], [삭제] 버튼있음 -->
						<c:forEach var="message" begin="0" end="${fn:length(messages)}" step="1">
							<ul>
								<li>작성자</li>
								<li>제목</li>
								<a href="/blog/s/home/replyMessage">답장</a>
								<a href="/blog/s/home/deleteMessage">삭제</a>
							</ul>
						</c:forEach>
					</div>
					<div class="message-box-page">
						
					</div>
				</div>
			</div>
		</div>
	</div>

<%@ include file="/jsp/part/foot.jspf"%>