package com.Dao.practice;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.DB.practice.DBConnection;
import com.Dto.practice.Article;
import com.Dto.practice.ArticleReply;
import com.Dto.practice.CateItem;
import com.Main.practice.Factory;

// Dao
public class ArticleDao {
	DBConnection dbConnection;

	public ArticleDao() {
		dbConnection = Factory.getDBConnection();
	}

	public List<Article> getArticlesByBoardCode(String code) {
		StringBuilder sb = new StringBuilder();

		sb.append(String.format("SELECT A.* "));
		sb.append(String.format("FROM `article` AS A "));
		sb.append(String.format("INNER JOIN `board` AS B "));
		sb.append(String.format("ON A.boardId = B.id "));
		sb.append(String.format("WHERE 1 "));
		sb.append(String.format("AND B.`code` = '%s' ", code));
		sb.append(String.format("ORDER BY A.id DESC "));

		List<Article> articles = new ArrayList<>();
		List<Map<String, Object>> rows = dbConnection.selectRows(sb.toString());
		
		for ( Map<String, Object> row : rows ) {
			articles.add(new Article(row));
		}
		
		return articles;
	}

	public List<CateItem> getBoards() {
		StringBuilder sb = new StringBuilder();

		sb.append(String.format("SELECT * "));
		sb.append(String.format("FROM `board` "));
		sb.append(String.format("WHERE 1 "));
		sb.append(String.format("ORDER BY id DESC "));

		List<CateItem> boards = new ArrayList<>();
		List<Map<String, Object>> rows = dbConnection.selectRows(sb.toString());
		
		for ( Map<String, Object> row : rows ) {
			boards.add(new CateItem(row));
		}
		
		return boards;
	}

	public CateItem getBoardByCode(String code) {
		StringBuilder sb = new StringBuilder();

		sb.append(String.format("SELECT * "));
		sb.append(String.format("FROM `board` "));
		sb.append(String.format("WHERE 1 "));
		sb.append(String.format("AND `code` = '%s' ", code));

		Map<String, Object> row = dbConnection.selectRow(sb.toString());
		
		if ( row.isEmpty() ) {
			return null;
		}
		
		return new CateItem(row);
	}

	public int saveBoard(CateItem board) {
		StringBuilder sb = new StringBuilder();

		sb.append(String.format("INSERT INTO board "));
		sb.append(String.format("SET regDate = '%s' ", board.getRegDate()));
		sb.append(String.format(", `name` = '%s' ", board.getName()));

		return dbConnection.insert(sb.toString());
	}

	public int save(Article article) {
		StringBuilder sb = new StringBuilder();

		sb.append(String.format("INSERT INTO article "));
		sb.append(String.format("SET regDate = '%s' ", article.getRegDate()));
		sb.append(String.format(", title = '%s' ", article.getTitle()));
		sb.append(String.format(", `body` = '%s' ", article.getBody()));
		sb.append(String.format(", cateItemId = '%d' ", article.getCateItemId()));

		return dbConnection.insert(sb.toString());
	}

	public CateItem getBoard(int id) {
		StringBuilder sb = new StringBuilder();

		sb.append(String.format("SELECT * "));
		sb.append(String.format("FROM `board` "));
		sb.append(String.format("WHERE 1 "));
		sb.append(String.format("AND `id` = '%d' ", id));

		Map<String, Object> row = dbConnection.selectRow(sb.toString());
		
		if ( row.isEmpty() ) {
			return null;
		}
		
		return new CateItem(row);
	}

	public List<Article> getArticles() {
		StringBuilder sb = new StringBuilder();

		sb.append(String.format("SELECT * "));
		sb.append(String.format("FROM `article` "));
		sb.append(String.format("WHERE 1 "));
		sb.append(String.format("ORDER BY id DESC "));

		List<Article> articles = new ArrayList<>();
		List<Map<String, Object>> rows = dbConnection.selectRows(sb.toString());
		
		for ( Map<String, Object> row : rows ) {
			articles.add(new Article(row));
		}
		
		return articles;
	}

	public int Delete(int id) {
		String sql = "";
		sql += "DELETE ";
		sql += "FROM article ";
		sql += "WHERE id = '" + id + "';";
		return dbConnection.delete(sql);
	}

	public int modify(String title, String body, int id) {
		String sql = "";
		sql += "UPDATE article ";
		sql += "SET title = '" + title + "',";
		sql += "`body` = '" + body + "'";
		sql += "WHERE id = '" + id + "';";
		return dbConnection.update(sql);
	}

	public Article getArticle(int artNum) {
		StringBuilder sb = new StringBuilder();
		
		sb.append(String.format("SELECT * "));
		sb.append(String.format("FROM `article` "));
		sb.append(String.format("WHERE id = '%d' ", artNum));
		
		Article article = new Article();
		Map<String, Object> row = dbConnection.selectRow(sb.toString());
		
		article = new Article(row);
		
		return article;
	}

	public List<ArticleReply> getArticleReplysByArticleId(int artNum) {
		StringBuilder sb = new StringBuilder();

		sb.append(String.format("SELECT A.* "));
		sb.append(String.format("FROM `articleReply` AS A "));
		sb.append(String.format("INNER JOIN `article` AS B "));
		sb.append(String.format("ON A.articleId = B.id "));
		sb.append(String.format("WHERE 1 "));
		sb.append(String.format("AND B.`id` = '%d' ", artNum));

		List<ArticleReply> articleReplys = new ArrayList<>();
		List<Map<String, Object>> rows = dbConnection.selectRows(sb.toString());
		
		for ( Map<String, Object> row : rows ) {
			articleReplys.add(new ArticleReply(row));
		}
		
		return articleReplys;
	}

}