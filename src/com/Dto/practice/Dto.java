package com.Dto.practice;

import com.Main.practice.Util;

public abstract class Dto {
	private int id;
	private String regDate;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getRegDate() {
		return regDate;
	}

	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}

	public Dto() {
		this(0);
	}

	public Dto(int id) {
		this(id, Util.getNowDateStr());
	}

	public Dto(int id, String regDate) {
		this.id = id;
		this.regDate = regDate;
	}
}