package com.java.blog.dto;

import java.util.Map;

public class Message extends Dto{
	private String title;
	private String body;
	private int fromMemberId;
	private int toMemberId;
	private int readStatus;
	private int delStatus;
	
	public Message(Map<String, Object> row) {
		super(row);

		this.title = (String) row.get("title");
		this.body = (String) row.get("body");
		this.fromMemberId = (int) row.get("fromMemberId");
		this.toMemberId = (int) row.get("toMemberId");
		this.readStatus = (int) row.get("readStatus");
		this.delStatus = (int) row.get("delStatus");
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

	public int getFromMemberId() {
		return fromMemberId;
	}

	public void setFromMemberId(int fromMemberId) {
		this.fromMemberId = fromMemberId;
	}

	public int getToMemberId() {
		return toMemberId;
	}

	public void setToMemberId(int toMemberId) {
		this.toMemberId = toMemberId;
	}

	public int getReadStatus() {
		return readStatus;
	}

	public void setReadStatus(int readStatus) {
		this.readStatus = readStatus;
	}

	public int getDelStatus() {
		return delStatus;
	}

	public void setDelStatus(int delStatus) {
		this.delStatus = delStatus;
	}
	
}
