package com.Dto.practice;

import java.util.Map;

public class Article extends Dto {
	private int displayStatus;
	private int cateItemId;
	private String updateDate;
	private String title;
	private String body;

	public Article() {

	}
	
	public Article(int boardId, String title, String body) {
		this.cateItemId = boardId;
		this.title = title;
		this.body = body;
	}

	public Article(Map<String, Object> row) {
		this.updateDate = (String) row.get("updateDate");
		this.title = (String) row.get("title");
		this.body = (String) row.get("body");
	}

	@Override
	public String toString() {
		return "Article [id=" + getId() + ", regDate=" + getRegDate() + ", updateDate=" + updateDate + ", title="
				+ title + ", body=" + body + "]";
	}

	public String getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(String updateDate) {
		this.updateDate = updateDate;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public int getDisplayStatus() {
		return displayStatus;
	}

	public void setDisplayStatus(int displayStatus) {
		this.displayStatus = displayStatus;
	}

	public int getCateItemId() {
		return cateItemId;
	}

	public void setCateItemId(int cateItemId) {
		this.cateItemId = cateItemId;
	}
	
}