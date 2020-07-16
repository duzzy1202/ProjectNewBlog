package com.java.blog.controller;

import java.sql.Connection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.java.blog.dto.Article;
import com.java.blog.dto.Member;

public class HomeController extends Controller {
	public HomeController(Connection dbConn, String actionMethodName, HttpServletRequest req, HttpServletResponse resp) {
		super(dbConn, actionMethodName, req, resp);
	}

	@Override
	public String doAction() {
		switch (actionMethodName) {
		case "main":
			return doActionMain();
		case "aboutMe":
			return doActionAboutMe();
		}

		return "";
	}

	private String doActionMain() {
		int cateItemId = 0;
		String searchKeywordType = "";
		String searchKeyword = "";
		int page = 1;
		int itemsInAPage = 12;

		req.setAttribute("page", page);

		List<Article> articles = articleService.getForPrintListArticles(page, itemsInAPage, cateItemId, searchKeywordType, searchKeyword);
		req.setAttribute("articles", articles);
		
		return "home/main.jsp";
	}

	private String doActionAboutMe() {
		return "home/aboutMe.jsp";
	}

}
