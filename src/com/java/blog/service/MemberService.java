package com.java.blog.service;

import java.sql.Connection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.java.blog.dao.MemberDao;
import com.java.blog.dto.Member;

public class MemberService extends Service {

	private MemberDao memberDao;

	public MemberService(Connection dbConn) {
		memberDao = new MemberDao(dbConn);
	}

	public void insertJoinMember(String loginId, String loginPw, String name, String nickname, String email) {
		memberDao.insertJoinMember(loginId, loginPw, name, nickname, email);
	}

	public boolean checkIsExistsLoginIdAndLoginPw(String loginId, String loginPw) {
		return memberDao.checkIsExistsLoginIdAndLoginPw(loginId, loginPw);
	}

	public Member getMemberByLoginId(String loginId) {
		return memberDao.getMemberByLoginId(loginId);
	}

	public boolean isJoinableLoginId(String loginId) {
		return memberDao.isJoinableLoginId(loginId);
	}
	
	public boolean isJoinableNickname(String nickname) {
		return memberDao.isJoinableNickname(nickname);
	}

	public boolean isJoinableEmail(String email) {
		return memberDao.isJoinableEmail(email);
	}
	
	public Member getMemberById(int id) {
		return memberDao.getMemberById(id);
	}

	public boolean isExistsMember(String loginId, String name, String email) {
		return memberDao.isExistsMember(loginId, name, email);
	}

	public void updateTempPw(int memberId, String tempPw) {
		memberDao.updateTempPw(memberId, tempPw);
	}

	public boolean isExistsMemberByLoginIdAndEmail(String name, String email) {
		return memberDao.isExistsMemberByLoginIdAndEmail(name, email);
	}

	public Member getMemberByNameAndEmail(String name, String email) {
		return memberDao.getMemberByNameAndEmail(name, email);
	}

	public void updateMember(String loginId, String loginPw) {
		memberDao.updateMember(loginId, loginPw);
	}
}
