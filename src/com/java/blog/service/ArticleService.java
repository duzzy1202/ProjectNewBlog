package com.java.blog.service;

import java.sql.Connection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.java.blog.dao.ArticleDao;
import com.java.blog.dto.Article;
import com.java.blog.dto.ArticleReply;
import com.java.blog.dto.CateItem;

public class ArticleService extends Service {

	private ArticleDao articleDao;

	public ArticleService(Connection dbConn, HttpServletRequest req, HttpServletResponse resp) {
		super(req, resp);
		articleDao = new ArticleDao(dbConn, req, resp);
	}

	public List<Article> getForPrintListArticles(int page, int itemsInAPage, int cateItemId, String searchKeywordType, String searchKeyword) {
		return articleDao.getForPrintListArticles(page, itemsInAPage, cateItemId, searchKeywordType, searchKeyword);
	}

	public int getForPrintListArticlesCount(int cateItemId, String searchKeywordType, String searchKeyword) {
		return articleDao.getForPrintListArticlesCount(cateItemId, searchKeywordType, searchKeyword);
	}

	public Article getForPrintArticle(int id) {
		return articleDao.getForPrintArticle(id);
	}

	public List<CateItem> getForPrintCateItems() {
		return articleDao.getForPrintCateItems();
	}

	public CateItem getCateItem(int cateItemId) {
		return articleDao.getCateItem(cateItemId);
	}

	public int insertWrittenArticle(int cateItemId, String title, String body) {
		return articleDao.insertWrittenArticle(cateItemId, title, body);
	}

	public int updateArticle(int cateItemId, String title, String body, int articleId) {
		return articleDao.updateArticle(cateItemId, title, body, articleId);
	}

	public void deleteArticle(int articleId) {
		articleDao.deleteArticle(articleId);
	}

	public void increaseHit(int id) {
		articleDao.increaseHit(id);
	}

	public void writeReply(int articleId, String replyBody) {
		articleDao.writeReply(articleId, replyBody);
	}

	public List<ArticleReply> getForPrintListReplys(int id, int itemsInAPage, int page) {
		return articleDao.getForPrintListReplys(id, itemsInAPage, page);
	}

	public int getForPrintListReplysCount(int id) {
		return articleDao.getForPrintListReplysCount(id);
	}

}
