package com.java.blog.controller;

import java.sql.Connection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.java.blog.dto.Member;

public class MemberController extends Controller {

	public MemberController(Connection dbConn, String actionMethodName, HttpServletRequest req,
			HttpServletResponse resp) {
		super(dbConn, actionMethodName, req, resp);
	}

	public void beforeAction() {
		super.beforeAction();
		// 이 메서드는 게시물 컨트롤러의 모든 액션이 실행되기 전에 실행된다.
		// 필요없다면 지워도 된다.
	}

	public String doAction() {
		switch (actionMethodName) {
		case "join":
			return doActionJoin(req, resp);
		case "doJoin":
			return doActionDoJoin(req, resp);
		case "login":
			return doActionLogin(req, resp);
		case "doLogin":
			return doActionDoLogin(req, resp);
		}

		return "";
	}

	private String doActionDoLogin(HttpServletRequest req, HttpServletResponse resp) {
		String loginId = req.getParameter("loginId");
		String loginPw = req.getParameter("loginPwReal");
		
		boolean exist = memberService.checkIsExistsLoginIdAndLoginPw(loginId, loginPw);
		
		if (exist == false) {
			return "html:<script> alert('아이디 또는 비밀번호가 일치하지 않습니다.'); history.back(); </script>";
		}
		
		Member member = memberService.getMemberByLoginId(loginId);
		
		String nickname = member.getNickname();
		
		return "html:<script> alert('[" + nickname + "]님 환영합니다.'); location.replace('/blog/s/home/main'); </script>";
	}

	private String doActionLogin(HttpServletRequest req, HttpServletResponse resp) {
		
		return "member/login.jsp";
	}

	private String doActionDoJoin(HttpServletRequest req, HttpServletResponse resp) {
		String loginId = req.getParameter("loginId");
		String loginPw = req.getParameter("loginPwReal");
		String loginPwConfirm = req.getParameter("loginPwConfirmReal");
		String name = req.getParameter("name");
		String nickname = req.getParameter("nickname");
		
		if (!loginPw.equals(loginPwConfirm)) {
			return "html:<script> alert('비밀번호와 비밀번호 확인이 일치하지 않습니다.'); history.back(); </script>";
		}
		
		boolean checkId = memberService.checkIsExistsLoginId(loginId);
		
		if (checkId == true) {
			return "html:<script> alert('이미 사용중인 아이디입니다.'); history.back(); </script>";
		}
		
		memberService.insertJoinMember(loginId, loginPw, name, nickname);
		
		return "html:<script> alert('[" + nickname + "]님 가입을 환영합니다.'); location.replace('/blog/s/home/main'); </script>";
	}

	private String doActionJoin(HttpServletRequest req, HttpServletResponse resp) {

		return "member/join.jsp";
	}

}
