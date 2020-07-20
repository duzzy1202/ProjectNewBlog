package com.java.blog.dto;

import java.util.Map;

public class Chat extends Dto{
	String body;
	int memberId;
	
	public Chat(Map<String, Object> row) {
		super(row);

		this.body = (String) row.get("body");
		this.memberId = (int) row.get("memberId");
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public int getMemberId() {
		return memberId;
	}

	public void setMemberId(int memberId) {
		this.memberId = memberId;
	}
	
}
