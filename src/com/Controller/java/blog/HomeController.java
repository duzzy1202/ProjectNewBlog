package com.Controller.java.blog;

import java.sql.Connection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.Dto.java.blog.Article;
import com.Service.java.blog.ArticleService;

public class HomeController extends Controller {
	private ArticleService articleService;

	public HomeController(Connection dbConn) {
		articleService = new ArticleService(dbConn);
	}

	@Override
	public String doAction(String actionMethodName, HttpServletRequest req, HttpServletResponse resp) {
		switch (actionMethodName) {
		case "main":
			return doActionMain(req, resp);
		case "aboutMe":
			return doActionAboutMe(req, resp);
		}

		return "";
	}

	private String doActionMain(HttpServletRequest req, HttpServletResponse resp) {
		int cateItemId = 0;

		int page = 1;

		int itemsInAPage = 12;

		req.setAttribute("page", page);

		List<Article> articles = articleService.getForPrintListArticles(page, itemsInAPage, cateItemId);
		req.setAttribute("articles", articles);
		return "home/main.jsp";
	}

	private String doActionAboutMe(HttpServletRequest req, HttpServletResponse resp) {
		return "home/aboutme.jsp";
	}

}
