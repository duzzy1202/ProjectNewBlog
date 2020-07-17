package com.java.blog.dao;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.java.blog.dto.Article;
import com.java.blog.dto.ArticleReply;
import com.java.blog.dto.CateItem;
import com.java.blog.dto.Member;
import com.java.blog.util.DBUtil;
import com.java.blog.util.SecSql;

public class ArticleDao extends Dao {
	private Connection dbConn;

	public ArticleDao(Connection dbConn) {
		this.dbConn = dbConn;
	}

	public List<Article> getForPrintListArticles(int page, int itemsInAPage, int cateItemId, String searchKeywordType,
			String searchKeyword) {
		SecSql secSql = new SecSql();

		int limitFrom = (page - 1) * itemsInAPage;
	
		secSql.append("SELECT A.* ");
		secSql.append(", M.nickname AS extra__writer ");
		secSql.append("FROM article AS A");
		secSql.append("INNER JOIN member AS M");
		secSql.append("ON A.memberId = M.id");
		secSql.append("WHERE A.displayStatus = 1");
		
		if (cateItemId != 0) {
			secSql.append("AND cateItemId = ? ", cateItemId);
		}
		if (searchKeywordType.equals("title") && searchKeyword.length() > 0) {
			secSql.append("AND title LIKE CONCAT('%%', ?, '%%')", searchKeyword);
		}
		
		secSql.append("ORDER BY id DESC ");
		secSql.append("LIMIT ?, ? ", limitFrom, itemsInAPage);

		List<Map<String, Object>> rows = DBUtil.selectRows(dbConn, secSql);
		List<Article> articles = new ArrayList<>();

		for (Map<String, Object> row : rows) {
			articles.add(new Article(row));
		}

		return articles;
	}

	public int getForPrintListArticlesCount(int cateItemId, String searchKeywordType, String searchKeyword) {
		SecSql secSql = new SecSql();
		
		secSql.append("SELECT COUNT(*) AS cnt ");
		secSql.append("FROM article ");
		secSql.append("WHERE displayStatus = 1 ");

		if (cateItemId != 0) {
			secSql.append("AND cateItemId = ? ", cateItemId);
		}

		if (searchKeywordType.equals("title") && searchKeyword.length() > 0) {
			secSql.append("AND title LIKE CONCAT('%%', ?, '%%')", searchKeyword);
		}

		int count = DBUtil.selectRowIntValue(dbConn, secSql);
		return count;
	}
	/* 기존에 사용하던 게시글 불러오기 
	public Article getForPrintArticle(int id) {
		SecSql secSql = new SecSql();
		
		secSql.append("SELECT * ");
		secSql.append("FROM article ");
		secSql.append("WHERE 1 ");
		secSql.append("AND id = ? ", id);
		secSql.append("AND displayStatus = 1 ");

		return new Article(DBUtil.selectRow(dbConn, secSql));
	}
	*/

	public List<CateItem> getForPrintCateItems() {
		SecSql secSql = new SecSql();

		secSql.append("SELECT * ");
		secSql.append("FROM cateItem ");
		secSql.append("WHERE 1 ");
		secSql.append("ORDER BY id ASC ");

		List<Map<String, Object>> rows = DBUtil.selectRows(dbConn, secSql);
		List<CateItem> cateItems = new ArrayList<>();

		for (Map<String, Object> row : rows) {
			cateItems.add(new CateItem(row));
		}

		return cateItems;
	}

	public CateItem getCateItem(int cateItemId) {
		SecSql secSql = new SecSql();
		
		secSql.append("SELECT * ");
		secSql.append("FROM cateItem ");
		secSql.append("WHERE 1 ");
		secSql.append("AND id = ? ", cateItemId);

		return new CateItem(DBUtil.selectRow(dbConn, secSql));
	}

	public int insertWrittenArticle(int cateItemId, String title, String body, int writerId) {
		SecSql secSql = new SecSql();

		secSql.append("INSERT INTO article");
		secSql.append("SET regDate = NOW()");
		secSql.append(", updateDate = NOW()");
		secSql.append(", title = ? ", title);
		secSql.append(", body = ? ", body);
		secSql.append(", displayStatus = '1'");
		secSql.append(", cateItemId = ?", cateItemId);
		secSql.append(", hits = '0'");
		secSql.append(", memberId = ? ", writerId);

		int id = DBUtil.insert(dbConn, secSql);

		return id;
	}
	
	public int updateArticle(int cateItemId, String title, String body, int articleId) {
		SecSql secSql = new SecSql();

		secSql.append("UPDATE article");
		secSql.append("SET updateDate = NOW()");
		secSql.append(", title = ? ", title);
		secSql.append(", body = ? ", body);
		secSql.append(", cateItemId = ? ", cateItemId);
		secSql.append("WHERE Id = ? ", articleId);

		int id = DBUtil.update(dbConn, secSql);
		
		return id;
	}

	public void deleteArticle(int articleId) {
		SecSql secSql = new SecSql();
		
		secSql.append("DELETE FROM article");
		secSql.append("WHERE id = ? ", articleId);
		
		int id = DBUtil.update(dbConn, secSql);
	}

	public int increaseHit(int id) {
		SecSql sql = SecSql.from("UPDATE article");
		sql.append("SET hits = hits + 1");
		sql.append("WHERE id = ?", id);

		return DBUtil.update(dbConn, sql);
	}

	public void writeReply(int articleId, String replyBody, int replyMemberId) {
		SecSql secSql = new SecSql();
		
		secSql.append("INSERT INTO reply");
		secSql.append("SET regDate = NOW()");
		secSql.append(", updateDate = NOW()");
		secSql.append(", body = ? ", replyBody);
		secSql.append(", articleId = ?", articleId);
		secSql.append(", memberId = ? ", replyMemberId);

		int id = DBUtil.insert(dbConn, secSql);
		
	}
	/*
	public List<ArticleReply> getForPrintListReplys(int id, int itemsInAPage, int page) {
		SecSql secSql = new SecSql();
		
		int limitFrom = (page - 1) * itemsInAPage;

		secSql.append("SELECT * ");
		secSql.append("FROM reply ");
		secSql.append("WHERE 1 ");
		secSql.append("AND articleId = ? ", id);
		secSql.append("ORDER BY id ASC ");
		secSql.append("LIMIT ?, ? ", limitFrom, itemsInAPage);
		
		
		List<Map<String, Object>> rows = DBUtil.selectRows(dbConn, secSql);
		List<ArticleReply> replys = new ArrayList<>();

		for (Map<String, Object> row : rows) {
			replys.add(new ArticleReply(row));
		}
		
		return replys;
	}
	*/
	
	public int getForPrintListReplysCount(int articleId) {
		SecSql secSql = new SecSql();
		
		secSql.append("SELECT COUNT(*) AS cnt ");
		secSql.append("FROM reply ");
		secSql.append("WHERE 1 ");
		secSql.append("AND articleId = ? ", articleId);
		
		int count = DBUtil.selectRowIntValue(dbConn, secSql);
		
		return count;
	}

	public Member getMemberById(int memberId) {
		SecSql secSql = new SecSql();

		secSql.append("SELECT * ");
		secSql.append("FROM member ");
		secSql.append("WHERE 1 ");
		secSql.append("AND id = ? ", memberId);
		
		Member member = new Member(DBUtil.selectRow(dbConn, secSql));

		return member;
	}
	
	/*
	public List<Member> getReplyMembersByReplysList(List<ArticleReply> replys) {
		List<Member> members = new ArrayList<>();
		for ( ArticleReply reply : replys) {
			
			SecSql secSql = new SecSql();
			
			secSql.append("SELECT * ");
			secSql.append("FROM member ");
			secSql.append("WHERE 1 ");
			secSql.append("AND id = ? ", reply.getMemberId());
			
			Member member = new Member(DBUtil.selectRow(dbConn, secSql));
			
			members.add(member);
		}
		
		return members;
	}
	*/

	public void UpdateReply(int replyId, String replyBody) {
		SecSql secSql = new SecSql();

		secSql.append("UPDATE reply");
		secSql.append("SET updateDate = NOW()");
		secSql.append(", body = ? ", replyBody);
		secSql.append("WHERE Id = ? ", replyId);

		int id = DBUtil.update(dbConn, secSql);
	}

	public void deleteReply(int replyId) {
		SecSql secSql = new SecSql();
		
		secSql.append("DELETE FROM reply");
		secSql.append("WHERE id = ? ", replyId);
		
		int id = DBUtil.update(dbConn, secSql);
	}
	
	
	public Article getForPrintArticle(int id) {
		SecSql secSql = new SecSql();
		
		secSql.append("SELECT A.* ");
		secSql.append(", M.nickname AS extra__writer ");
		secSql.append("FROM article AS A");
		secSql.append("INNER JOIN member AS M");
		secSql.append("ON A.memberId = M.id");
		secSql.append("WHERE A.displayStatus = 1");
		secSql.append("AND A.id = ?", id);
		
		return new Article(DBUtil.selectRow(dbConn, secSql));
	}
	
	public List<ArticleReply> getForPrintListReplys(int id, int itemsInAPage, int page) {
		SecSql secSql = new SecSql();
		
		int limitFrom = (page - 1) * itemsInAPage;

		secSql.append("SELECT R.* ");
		secSql.append(", M.nickname AS extra__replyWriter ");
		secSql.append("FROM reply AS R");
		secSql.append("INNER JOIN member AS M");
		secSql.append("ON R.memberId = M.id");
		secSql.append("WHERE 1");
		secSql.append("AND R.articleId = ?", id);
		secSql.append("ORDER BY id ASC ");
		secSql.append("LIMIT ?, ? ", limitFrom, itemsInAPage);
		
		List<Map<String, Object>> rows = DBUtil.selectRows(dbConn, secSql);
		List<ArticleReply> replys = new ArrayList<>();

		for (Map<String, Object> row : rows) {
			replys.add(new ArticleReply(row));
		}
		
		return replys;
	}
}
