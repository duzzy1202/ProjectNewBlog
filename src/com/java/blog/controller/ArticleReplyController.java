package com.java.blog.controller;

import java.sql.Connection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.java.blog.dto.ArticleReply;
import com.java.blog.dto.CateItem;
import com.java.blog.util.Util;

public class ArticleReplyController extends Controller {
	public ArticleReplyController(Connection dbConn, String actionMethodName, HttpServletRequest req,
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
		case "writeReply":
			return doActionWriteReply(req, resp);
		}

		return "";
	}

	private String doActionWriteReply(HttpServletRequest req, HttpServletResponse resp) {
		
		return null;
	}

}
