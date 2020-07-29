<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="/jsp/part/head.jspf"%>

<div class="aboutme-box visible-md-up">
	<div class="con">
		<div class="aboutme-catebox">
			<ul>
				<li><a href="#" onclick="changeAboutMeIntroduce();">소개</a></li>
				<li><a href="#" onclick="changeAboutMeHistory();">경력</a></li>
			</ul>
		</div>
		<div class="right-screen">
			<div class="aboutme-body first-screen" id="firstScreen">
				<div class="pic">
					<img src="/blog/resource/ETC/pc_aboutMe_profile_img_1280x1280.png">
				</div>
				<div class="introduce">
					<h2>소개</h2>
					<span>- 이정한 -</span> 
					<span>나이 : 1994년생</span>
					<span>직업 : 전사</span>  
					<span>E-Mail : duzzy1202@gmail.com</span>
					<span>기타 : 보스 레이드 파티 구합니다</span>
				</div>
			</div> 
			<div class="aboutme-body second-screen" id="secondScreen">
				<div class="history">
					<h2>경력</h2>
					<div class="history-body"> 
						<span> - 사랑해요 연예가중계</span> 
						<span> - 프리미어리그 32R Man of the Match</span>
						<span> - 트럼프 대통령이 선정한 올해의 인물 5위</span>
						<span> - SBS 인기가요 11주 연속 3위</span>
						<span> - 삼성전자 주최 [ 제 132회 햄버거 빨리먹기 대회 ] 준우승</span>
						<span> - (주)홍철투어 대표이사 비서 역임</span>
						<span> - 2017년 올해를 빛낸 먹방 유튜버 3위</span>
						<span> - 아이돌 그룹 팬카페 부매니저 역임</span>
						<span> - 식약처 인증 위생등급 1등급 업체</span>
						<span> - 2018년 수험생들이 뽑은 올해의 기술/가정 강사 12위</span>
						<span> - 온라인게임 보스 레이드 딜량 2위</span>
						<span> - 오박사 선정 올해의 포켓몬 3위</span>
						<span> - [ 나는 공산당이 싫어요 ] 광화문 1인 시위 4시간 </span>
						<span> - Google 본사 주최 창의력 경진대회 행사 스태프</span>
						<span> - 하나은행 주최 [ 조혜련배 태보 토너먼트 최강자전 ] 동상</span>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>

<%@ include file="/jsp/part/foot.jspf"%>

<script>
function changeAboutMeIntroduce() {
	var firstScreen = document.getElementById('firstScreen');
	var secondScreen = document.getElementById('secondScreen');
	
	firstScreen.style.display = 'flex';
	firstScreen.style.opacity = '1';
	firstScreen.style.visibility = 'visible';
	
	secondScreen.style.display = 'none';
	secondScreen.style.opacity = '0';
	secondScreen.style.visibility = 'hidden';
}

function changeAboutMeHistory() {
	var firstScreen = document.getElementById('firstScreen');
	var secondScreen = document.getElementById('secondScreen');
	
	secondScreen.style.display = 'block';
	secondScreen.style.opacity = '1';
	secondScreen.style.visibility = 'visible';
	
	firstScreen.style.display = 'none';
	firstScreen.style.opacity = '0';
	firstScreen.style.visibility = 'hidden';
}


</script>
