package com.java.blog.controller;

import java.sql.Connection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.java.blog.dto.Article;
import com.java.blog.dto.CateItem;
import com.java.blog.dto.Member;
import com.java.blog.service.ArticleService;
import com.java.blog.service.MailService;
import com.java.blog.service.MemberService;

public abstract class Controller {
	protected Connection dbConn;
	protected String actionMethodName;
	protected HttpServletRequest req;
	protected HttpServletResponse resp;
	protected HttpSession session;

	protected ArticleService articleService;
	protected MemberService memberService;
	protected MailService mailService;
	
	protected String gmailId;
	protected String gmailPw;

	public Controller(Connection dbConn, String actionMethodName, HttpServletRequest req, HttpServletResponse resp, String gmailId, String gmailPw, MailService mailService) {
		this.dbConn = dbConn;
		this.actionMethodName = actionMethodName;
		this.req = req;
		this.resp = resp;
		this.session = req.getSession();
		this.gmailId = gmailId;
		this.gmailPw = gmailPw;
		this.mailService = mailService;
		
		articleService = new ArticleService(dbConn);
		memberService = new MemberService(dbConn);
	}
	
	public Controller(Connection dbConn, String actionMethodName, HttpServletRequest req, HttpServletResponse resp) {
		this.dbConn = dbConn;
		this.actionMethodName = actionMethodName;
		this.req = req;
		this.resp = resp;
		this.session = req.getSession();
		
		articleService = new ArticleService(dbConn);
		memberService = new MemberService(dbConn);
	}

	public void beforeAction() {
		// 액션 전 실행
		// 이 메서드는 모든 컨트롤러의 모든 액션이 실행되기 전에 실행된다.
		List<CateItem> cateItems = articleService.getForPrintCateItems();

		req.setAttribute("cateItems", cateItems);

		// 사용자 관련 정보를 리퀘스트 객체에 정리해서 넣기
		int loggedInMemberId = -1;
		boolean isLoggedIn = false;
		Member loggedInMember = null;

		if (session.getAttribute("loggedInMemberId") != null) {
			loggedInMemberId = (int) session.getAttribute("loggedInMemberId");
			isLoggedIn = true;
			loggedInMember = memberService.getMemberById(loggedInMemberId);
		}

		req.setAttribute("loggedInMemberId", loggedInMemberId);
		req.setAttribute("loggedInMember", loggedInMember);
		req.setAttribute("isLoggedIn", isLoggedIn);
		
		Article topNoticeArticle = articleService.getLastestNoticeArticle();
		req.setAttribute("topNoticeArticle", topNoticeArticle);
	}

	public void afterAction() {
		// 액션 후 실행
	}

	public abstract String doAction();

	public String executeAction() {
		beforeAction();

		String doGuardRs = doGuard();

		if (doGuardRs != null) {
			return doGuardRs;
		}

		String rs = doAction();
		afterAction();

		return rs;
	}

	private String doGuard() {
		boolean isloggedIn = (boolean) req.getAttribute("isLoggedIn");
		
		Member loggedInMember;
		int isMailAuthed = 0;
		if (isloggedIn) {
			loggedInMember = (Member) req.getAttribute("loggedInMember");
			isMailAuthed = loggedInMember.getMailAuthStatus();
		}

		// 로그인에 관련된 가드 시작
		boolean needToLogin = false;

		String controllerName = getControllerName();

		switch (controllerName) {
		case "member":
			switch (actionMethodName) {
			case "doLogout":
				needToLogin = true;
				break;
			}
			break;
		case "article":
			switch (actionMethodName) {
			case "write":
			case "doWrite":
			case "modify":
			case "doModify":
			case "doDelete":
				needToLogin = true;
				break;
			}
			break;

		case "home":
			switch (actionMethodName) {
			case "writeChat":
				needToLogin = true;
				break;
			}
			break;
		}
		if (needToLogin && isloggedIn == false) {
			return "html:<script> alert('로그인 후 이용해주세요.'); location.href = '../member/login'; </script>";
		}
		// 로그인에 관련된 가드 끝

		// 로그아웃에 관련된 가드 시작
		boolean needToLogout = false;

		switch (controllerName) {
		case "member":
			switch (actionMethodName) {
			case "login":
			case "join":
				needToLogout = true;
				break;
			}
			break;
		}

		if (needToLogout && isloggedIn) {
			return "html:<script> alert('로그아웃 후 이용해주세요.'); history.back(); </script>";
		}
		// 로그아웃에 관련된 가드 끝
		
		// 메일인증에 관련된 가드 시작
		boolean needToMailAuth = false;

		switch (controllerName) {
		case "article":
			switch (actionMethodName) {
			case "write":
			case "doWrite":
			case "writeReply":
				needToMailAuth = true;
				break;
			}
			break;

		case "home":
			switch (actionMethodName) {
			case "writeChat":
				needToMailAuth = true;
				break;
			}
			break;
		}
		if (isloggedIn & needToMailAuth && isMailAuthed == 0) {
			return "html:<script> alert('이메일 인증 후 이용하실 수 있습니다. \\n인증은 [내 정보] 에서 가능합니다.'); location.href = '../member/myInfo'; </script>";
		}
		// 메일인증에 관련되 가드 끝

		return null;
	}

	public abstract String getControllerName();
}
