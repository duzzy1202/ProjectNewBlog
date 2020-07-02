package com.Dto.practice;

import java.util.Map;

public class CateItem extends Dto {
	private String name;

	public CateItem() {
	}

	public CateItem(String name) {
		this.name = name;
	}

	public CateItem(Map<String, Object> row) {
		super((int) row.get("id"), (String) row.get("regDate"));
		this.name = (String) row.get("name");
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}