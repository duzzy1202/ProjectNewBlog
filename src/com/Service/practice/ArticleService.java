package com.Service.practice;
import java.util.List;

import com.Dao.practice.ArticleDao;
import com.Dto.practice.Article;
import com.Dto.practice.ArticleReply;
import com.Dto.practice.Board;
import com.Main.practice.Factory;

public class CateItem {
	private ArticleDao articleDao;

	public ArticleService() {
		articleDao = Factory.getArticleDao();
	}

	public List<Article> getArticlesByBoardCode(String code) {
		return articleDao.getArticlesByBoardCode(code);
	}

	public List<CateItem> getCateItems() {
		return articleDao.getBoards();
	}

	public int makeCateItem(String name) {
		CateItem oldBoard = articleDao.getBoardByCode(code);

		if (oldBoard != null) {
			return -1;
		}

		Board board = new Board(name, code);
		return articleDao.saveBoard(board);
	}

	public Board getBoard(int id) {
		return articleDao.getBoard(id);
	}

	public int write(int boardId, String title, String body) {
		Article article = new Article(boardId, title, body);
		return articleDao.save(article);
	}

	public List<Article> getArticles() {
		return articleDao.getArticles();
	}

	public void makeBoardIfNotExists(String name, String code) {
		Board board = articleDao.getBoardByCode(code);
		
		if ( board == null ) {
			makeBoard(name, code);
		}
	}

	public Board getBoardByCode(String boardCode) {
		return articleDao.getBoardByCode(boardCode);
	}

	public int Delete(int id) {
		return articleDao.Delete(id);
	}

	public int modify(String title, String body, int id) {
		return articleDao.modify(title, body, id);
	}

	public Article Detail(int artNum) {
		return articleDao.getArticle(artNum);
	}

	public List<ArticleReply> getArticleReplysByArticleId(int artNum) {
		return articleDao.getArticleReplysByArticleId(artNum);
	}

}