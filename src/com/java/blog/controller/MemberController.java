package com.java.blog.controller;

import java.sql.Connection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.java.blog.dto.Member;
import com.java.blog.service.MailService;

public class MemberController extends Controller {
	
	public MemberController(Connection dbConn, String actionMethodName, HttpServletRequest req,
			HttpServletResponse resp, String gmailId, String gmailPw, MailService mailService) {
		super(dbConn, actionMethodName, req, resp, gmailId, gmailPw, mailService);
	}

	public void beforeAction() {
		super.beforeAction();
		
	}

	public String doAction() {
		switch (actionMethodName) {
		case "join":
			return doActionJoin();
		case "doJoin":
			return doActionDoJoin();
		case "login":
			return doActionLogin();
		case "doLogin":
			return doActionDoLogin();
		case "logout":
			return doActionLogout();
		case "findPw":
			return doActionFindPw();
		case "doFindPw":
			return doActionDoFindPw();
		case "findId":
			return doActionFindId();
		case "doFindId":
			return doActionDoFindId();
		case "myInfo" :
			return doActionMyInfo();
		case "checkPw" :
			return doActionCheckPw();
		case "updateMember" :
			return doActionUpdateMember();
		case "doUpdateMember" :
			return doActionDoUpdateMember();
		}

		return "";
	}
	
	private String doActionDoUpdateMember() {
		String loginId = req.getParameter("loginId");
		String loginPw = req.getParameter("loginPwReal");
		String loginPwConfirm = req.getParameter("loginPwConfirmReal");
		
		if (!loginPw.equals(loginPwConfirm)) {
			return "html:<script> alert('비밀번호와 비밀번호 확인이 일치하지 않습니다.'); history.back(); </script>";
		}

		memberService.updateMember(loginId, loginPw);
		
		return "html:<script> alert('회원정보가 수정되었습니다.'); location.replace('/blog/s/member/myInfo'); </script>";
	}

	private String doActionUpdateMember() {
		String loginPw = req.getParameter("loginPwReal");
		String loginId = req.getParameter("loginId");
		
		boolean isExists = memberService.checkIsExistsLoginIdAndLoginPw(loginId, loginPw);
		
		if (isExists == false) {
			return "html:<script> alert('비밀번호가 틀렸습니다.'); history.back(); </script>"; 
		}
		
		return "member/updateMember.jsp";
	}

	private String doActionCheckPw() {
		return "member/checkPw.jsp";
	}

	private String doActionMyInfo() {	
		return "member/myInfo.jsp";
	}

	private String doActionDoFindId() {
		String name = req.getParameter("name");
		String email = req.getParameter("email");
		
		boolean isExistsMember = memberService.isExistsMemberByLoginIdAndEmail(name, email);
		if (isExistsMember == false) {
			return "html:<script> alert('입력한 정보의 사용자는 존재하지 않습니다.'); history.back(); </script>";
		}
		
		Member member = memberService.getMemberByNameAndEmail(name, email);
		
		mailService.writeFindIdMail(email, member.getLoginId());

		return "html:<script> alert('회원님의 이메일(" + email + ")로 회원님의 ID를 발송하였습니다.'); location.replace('/blog/s/member/login'); </script>";
	}

	private String doActionFindId() {

		return "member/findId.jsp";
	}

	private String doActionDoFindPw() {
		String loginId = req.getParameter("loginId");
		String name = req.getParameter("name");
		String email = req.getParameter("email");
		String tempPw = req.getParameter("loginPwReal");
		
		boolean isExistsMember = memberService.isExistsMember(loginId, name, email);
		if (isExistsMember == false) {
			return "html:<script> alert('입력한 정보의 사용자는 존재하지 않습니다.'); history.back(); </script>";
		}
		
		Member member = memberService.getMemberByLoginId(loginId);
		
		mailService.writeFindPwMail(email);
		
		int memberId = member.getId();
		memberService.updateTempPw(memberId, tempPw);
		
		return "html:<script> alert('회원님의 이메일(" + email + ")로 임시 비밀번호를 발송하였습니다.'); location.replace('/blog/s/member/login'); </script>";
	}

	private String doActionFindPw() {
		
		return "member/findPw.jsp";
	}

	@Override
	public String getControllerName() {
		return "member";
	}

	private String doActionLogout() {
		
		HttpSession session = req.getSession();
		session.removeAttribute("loggedInMemberId");
		
		return "html:<script> alert(' 로그아웃 되었습니다. '); location.replace('/blog/s/home/main'); </script>";
	}

	private String doActionDoLogin() {
		String loginId = req.getParameter("loginId");
		String loginPw = req.getParameter("loginPwReal");
		
		boolean exist = memberService.checkIsExistsLoginIdAndLoginPw(loginId, loginPw);
		
		if (exist == false) {
			return "html:<script> alert('아이디 또는 비밀번호가 일치하지 않습니다.'); history.back(); </script>";
		}
		
		Member member = memberService.getMemberByLoginId(loginId);
		
		String nickname = member.getNickname();
		
		int currentMemberId = member.getId();
		
		HttpSession session = req.getSession();
		
		session.setAttribute("loggedInMemberId", currentMemberId);
		
		return "html:<script> alert('[" + nickname + "]님 환영합니다.'); location.replace('/blog/s/home/main'); </script>";
	}

	private String doActionLogin() {
		
		return "member/login.jsp";
	}

	private String doActionDoJoin() {
		String loginId = req.getParameter("loginId");
		String loginPw = req.getParameter("loginPwReal");
		String loginPwConfirm = req.getParameter("loginPwConfirmReal");
		String name = req.getParameter("name");
		String nickname = req.getParameter("nickname");
		String email = req.getParameter("email");
		
		if (!loginPw.equals(loginPwConfirm)) {
			return "html:<script> alert('비밀번호와 비밀번호 확인이 일치하지 않습니다.'); history.back(); </script>";
		}
		
		boolean isJoinableLoginId = memberService.isJoinableLoginId(loginId);
		
		if ( isJoinableLoginId == false ) {
			return String.format("html:<script> alert('%s(은)는 이미 사용중인 아이디 입니다.'); history.back(); </script>", loginId);
		}

		boolean isJoinableNickname = memberService.isJoinableNickname(nickname);

		if ( isJoinableNickname == false ) {
			return String.format("html:<script> alert('%s(은)는 이미 사용중인 닉네임 입니다.'); history.back(); </script>", nickname);
		}

		boolean isJoinableEmail = memberService.isJoinableEmail(email);

		if ( isJoinableEmail == false ) {
			return String.format("html:<script> alert('%s(은)는 이미 사용중인 이메일 입니다.'); history.back(); </script>", email);
		}
		
		
		memberService.insertJoinMember(loginId, loginPw, name, nickname, email);
		
		mailService.writeWelcomeMail(email, name, nickname);
		
		return "html:<script> alert('[" + nickname + "]님 가입을 환영합니다.'); location.replace('/blog/s/home/main'); </script>";
	}

	private String doActionJoin() {

		return "member/join.jsp";
	}

}
