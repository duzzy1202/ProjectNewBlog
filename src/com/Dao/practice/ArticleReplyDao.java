package com.Dao.practice;

import com.DB.practice.DBConnection;
import com.Dto.practice.ArticleReply;
import com.Main.practice.Factory;

public class ArticleReplyDao {
	DBConnection dbConnection;

	public ArticleReplyDao() {
		dbConnection = Factory.getDBConnection();
	}

	public void writeReply(ArticleReply articleReply) {
		StringBuilder sb = new StringBuilder();

		sb.append(String.format("INSERT INTO `articleReply` "));
		sb.append(String.format("SET regDate = '%s' ", articleReply.getRegDate()));
		sb.append(String.format(", `body` = '%s' ", articleReply.getBody()));
		sb.append(String.format(", `articleId` = '%d' ", articleReply.getArticleId()));

		dbConnection.insert(sb.toString());
	}

	public int Delete(int replyNum) {
		StringBuilder sb = new StringBuilder();

		sb.append(String.format("DELETE "));
		sb.append(String.format("FROM `articleReply` "));
		sb.append(String.format("WHERE id = '%d' ", replyNum));

		return dbConnection.delete(sb.toString());
	}

	public int modifyReply(int replyNum, String body) {
		StringBuilder sb = new StringBuilder();

		sb.append(String.format("UPDATE articleReply "));
		sb.append(String.format("SET `body` = '%s' ", body));
		sb.append(String.format("WHERE id = '%d' ", replyNum));

		return dbConnection.update(sb.toString());
	}

}
