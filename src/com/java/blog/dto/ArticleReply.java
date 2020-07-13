package com.java.blog.dto;

import java.util.Map;

public class ArticleReply extends Dto{
	String updateDate;
	int articleId;
	String body;
	String writer;
	
	public ArticleReply(Map<String, Object> row) {
		super(row);

		this.updateDate = (String) row.get("updateDate");
		this.articleId = (int) row.get("articleId");
		this.body = (String) row.get("body");
	}

	public String getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(String updateDate) {
		this.updateDate = updateDate;
	}

	public int getArticleId() {
		return articleId;
	}

	public void setArticleId(int articleId) {
		this.articleId = articleId;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public String getWriter() {
		return writer;
	}

	public void setWriter(String writer) {
		this.writer = writer;
	}
	
	
}
