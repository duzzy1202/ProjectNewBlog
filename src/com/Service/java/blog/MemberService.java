package com.Service.java.blog;

import java.sql.Connection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.Dao.java.blog.MemberDao;
import com.Dto.java.blog.Member;

public class MemberService extends Service {

	private MemberDao memberDao;

	public MemberService(Connection dbConn, HttpServletRequest req, HttpServletResponse resp) {
		super(req, resp);
		memberDao = new MemberDao(dbConn, req, resp);
	}

	public void insertJoinMember(String loginId, String loginPw, String name, String nickname) {
		memberDao.insertJoinMember(loginId, loginPw, name, nickname);
	}

	public boolean checkIsExistsLoginId(String loginId) {
		return memberDao.checkIsExistsLoginId(loginId);
	}

	public boolean checkIsExistsLoginIdAndLoginPw(String loginId, String loginPw) {
		return memberDao.checkIsExistsLoginIdAndLoginPw(loginId, loginPw);
	}

	public Member getMemberByLoginId(String loginId) {
		return memberDao.getMemberByLoginId(loginId);
	}


}
