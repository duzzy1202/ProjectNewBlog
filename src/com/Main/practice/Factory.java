package com.Main.practice;
import java.util.Scanner;

import com.DB.practice.DBConnection;
import com.Dao.practice.ArticleDao;
import com.Dao.practice.ArticleReplyDao;
import com.Service.practice.ArticleReplyService;
import com.Service.practice.ArticleService;
import com.Service.practice.BuildService;

public class Factory {
	private static Session session;
	private static DBConnection dbConnection;
	private static BuildService buildService;
	private static ArticleService articleService;
	private static ArticleDao articleDao;
	private static ArticleReplyService articleReplyService;
	private static ArticleReplyDao articleReplyDao;
	private static Scanner scanner;

	public static ArticleReplyDao getArticleReplyDao() {
		if (articleReplyDao == null) {
			articleReplyDao = new ArticleReplyDao();
		}
		return articleReplyDao;
	}

	public static ArticleReplyService getArticleRelpyService() {
		if (articleReplyService == null) {
			articleReplyService = new ArticleReplyService();
		}
		
		return articleReplyService;
	}

	public static DBConnection getDBConnection() {
		if (dbConnection == null) {
			dbConnection = new DBConnection();
		}

		return dbConnection;
	}

	public static Session getSession() {
		if (session == null) {
			session = new Session();
		}

		return session;
	}

	public static Scanner getScanner() {
		if (scanner == null) {
			scanner = new Scanner(System.in);
		}

		return scanner;
	}

	public static ArticleService getArticleService() {
		if (articleService == null) {
			articleService = new ArticleService();
		}

		return articleService;
	}

	public static ArticleDao getArticleDao() {
		if (articleDao == null) {
			articleDao = new ArticleDao();
		}

		return articleDao;
	}

	public static BuildService getBuildService() {
		if (buildService == null) {
			buildService = new BuildService();
		}

		return buildService;
	}
}