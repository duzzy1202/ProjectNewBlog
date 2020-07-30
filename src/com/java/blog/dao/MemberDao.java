package com.java.blog.dao;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.java.blog.dto.Article;
import com.java.blog.dto.CateItem;
import com.java.blog.dto.Member;
import com.java.blog.util.DBUtil;
import com.java.blog.util.SecSql;

public class MemberDao extends Dao {
	private Connection dbConn;

	public MemberDao(Connection dbConn) {
		this.dbConn = dbConn;
	}

	public void insertJoinMember(String loginId, String loginPw, String name, String nickname, String email, String mailAuthCode) {
		SecSql secSql = new SecSql();
		
		secSql.append("INSERT INTO member ");
		secSql.append("SET regDate = NOW() ");
		secSql.append(", updateDate = NOW() ");
		secSql.append(", loginId = ? ", loginId);
		secSql.append(", loginPw = ? ", loginPw);
		secSql.append(", name = ? ", name);
		secSql.append(", nickname = ? ", nickname);
		secSql.append(", email = ? ", email);
		secSql.append(", level = 1 ");
		secSql.append(", mailAuthCode = ? ", mailAuthCode);
		secSql.append(", mailAuthStatus = 0 ");
		secSql.append(", delStatus = 0 ");
		
		int id = DBUtil.insert(dbConn, secSql);
		
	}

	public boolean checkIsExistsLoginIdAndLoginPw(String loginId, String loginPw) {
		SecSql secSql = new SecSql();
		
		secSql.append("SELECT COUNT(*) ");
		secSql.append("FROM member ");
		secSql.append("WHERE 1 ");
		secSql.append("AND loginID = ? ", loginId);
		secSql.append("AND loginPw = ? ", loginPw);
		
		int num = DBUtil.selectRowIntValue(dbConn, secSql);
		
		boolean exist = false;
		
		if (num == 1) {
			exist = true;
		}
		
		return exist;
	}

	public Member getMemberByLoginId(String loginId) {
		SecSql secSql = new SecSql();

		secSql.append("SELECT * ");
		secSql.append("FROM member ");
		secSql.append("WHERE 1 ");
		secSql.append("AND loginId = ? ", loginId);

		return new Member(DBUtil.selectRow(dbConn, secSql));
	}
	
	public boolean isJoinableLoginId(String loginId) {
		SecSql sql = SecSql.from("SELECT COUNT(*) AS cnt");
		sql.append("FROM `member`");
		sql.append("WHERE loginId = ?", loginId);

		return DBUtil.selectRowIntValue(dbConn, sql) == 0;
	}

	public boolean isJoinableNickname(String nickname) {
		SecSql sql = SecSql.from("SELECT COUNT(*) AS cnt");
		sql.append("FROM `member`");
		sql.append("WHERE nickname = ?", nickname);

		return DBUtil.selectRowIntValue(dbConn, sql) == 0;
	}

	public boolean isJoinableEmail(String email) {
		SecSql sql = SecSql.from("SELECT COUNT(*) AS cnt");
		sql.append("FROM `member`");
		sql.append("WHERE email = ?", email);

		return DBUtil.selectRowIntValue(dbConn, sql) == 0;
	}
	
	public Member getMemberById(int id) {
		SecSql sql = SecSql.from("SELECT *");
		sql.append("FROM `member`");
		sql.append("WHERE id = ?", id);

		return new Member(DBUtil.selectRow(dbConn, sql));
	}

	public boolean isExistsMember(String loginId, String name, String email) {
		SecSql sql = SecSql.from("SELECT COUNT(*) AS cnt");
		sql.append("FROM `member`");
		sql.append("WHERE loginId = ?", loginId);
		sql.append("And name = ?", name);
		sql.append("And email = ?", email);

		return DBUtil.selectRowIntValue(dbConn, sql) != 0;
	}

	public void updateTempPw(int memberId, String tempPw) {
		SecSql secSql = new SecSql();

		secSql.append("UPDATE member ");
		secSql.append("SET loginPw = ? ", tempPw);
		secSql.append("WHERE Id = ? ", memberId);

		int id = DBUtil.update(dbConn, secSql);
	}

	public boolean isExistsMemberByLoginIdAndEmail(String name, String email) {
		SecSql sql = SecSql.from("SELECT COUNT(*) AS cnt");
		sql.append("FROM `member`");
		sql.append("WHERE name = ?", name);
		sql.append("And email = ?", email);

		return DBUtil.selectRowIntValue(dbConn, sql) != 0;
	}
	
	public Member getMemberByNameAndEmail(String name, String email) {
		SecSql secSql = new SecSql();

		secSql.append("SELECT * ");
		secSql.append("FROM member ");
		secSql.append("WHERE 1 ");
		secSql.append("AND email = ? ", email);
		secSql.append("AND name = ? ", name);

		return new Member(DBUtil.selectRow(dbConn, secSql));
	}

	public void updateMember(String loginId, String loginPw, String nickname) {
		SecSql secSql = new SecSql();

		secSql.append("UPDATE member");
		secSql.append("SET updateDate = NOW()");
		secSql.append(", loginPw = ? ", loginPw);
		secSql.append(", nickname = ? ", nickname);
		secSql.append("WHERE loginId = ? ", loginId);

		int id = DBUtil.update(dbConn, secSql);
	}

	public void updateMailAuthStatus(int memberId) {
		SecSql secSql = new SecSql();

		secSql.append("UPDATE member");
		secSql.append("SET updateDate = NOW()");
		secSql.append(", mailAuthStatus = 1 ");
		secSql.append("WHERE id = ? ", memberId);

		int id = DBUtil.update(dbConn, secSql);
	}
}
