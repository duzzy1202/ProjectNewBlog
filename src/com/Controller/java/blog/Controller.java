package com.Controller.java.blog;

import java.sql.Connection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.Dto.java.blog.CateItem;
import com.Service.java.blog.ArticleService;
import com.Service.java.blog.MemberService;

public abstract class Controller {
	protected Connection dbConn;
	protected String actionMethodName;
	protected HttpServletRequest req;
	protected HttpServletResponse resp;

	protected ArticleService articleService;
	protected MemberService memberService;

	public Controller(Connection dbConn, String actionMethodName, HttpServletRequest req, HttpServletResponse resp) {
		this.dbConn = dbConn;
		this.actionMethodName = actionMethodName;
		this.req = req;
		this.resp = resp;
		articleService = new ArticleService(dbConn, req, resp);
		memberService = new MemberService(dbConn, req, resp);
	}

	public void beforeAction() {
		// 액션 전 실행
		// 이 메서드는 모든 컨트롤러의 모든 액션이 실행되기 전에 실행된다.
		List<CateItem> cateItems = articleService.getForPrintCateItems();
		
		req.setAttribute("cateItems", cateItems);
	}

	public void afterAction() {
		// 액션 후 실행
	}

	public abstract String doAction();

	public String executeAction() {
		beforeAction();
		String rs = doAction();
		afterAction();

		return rs;
	}
}
