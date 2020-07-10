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
	private DBUtil dbUtil;

	public MemberDao(Connection dbConn, HttpServletRequest req, HttpServletResponse resp) {
		super(req, resp);
		this.dbConn = dbConn;
		dbUtil = new DBUtil(req, resp);
	}

	public void insertJoinMember(String loginId, String loginPw, String name, String nickname) {
		SecSql secSql = new SecSql();
		
		secSql.append("INSERT INTO member ");
		secSql.append("SET regDate = NOW() ");
		secSql.append(", loginId = ? ", loginId);
		secSql.append(", loginPw = ? ", loginPw);
		secSql.append(", name = ? ", name);
		secSql.append(", nickname = ? ", nickname);
		
		int id = dbUtil.insert(dbConn, secSql);
		
	}

	public boolean checkIsExistsLoginId(String loginId) {
		SecSql secSql = new SecSql();
		
		secSql.append("SELECT COUNT(*) ");
		secSql.append("FROM member ");
		secSql.append("WHERE 1 ");
		secSql.append("AND loginID = ? ", loginId);
		
		int num = dbUtil.selectRowIntValue(dbConn, secSql);
		
		boolean exist = false;
		
		if (num == 1) {
			exist = true;
		}
		
		return exist;
	}

	public boolean checkIsExistsLoginIdAndLoginPw(String loginId, String loginPw) {
		SecSql secSql = new SecSql();
		
		secSql.append("SELECT COUNT(*) ");
		secSql.append("FROM member ");
		secSql.append("WHERE 1 ");
		secSql.append("AND loginID = ? ", loginId);
		secSql.append("AND loginPw = ? ", loginPw);
		
		int num = dbUtil.selectRowIntValue(dbConn, secSql);
		
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

		return new Member(dbUtil.selectRow(dbConn, secSql));
	}
}
