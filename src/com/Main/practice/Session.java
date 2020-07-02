package com.Main.practice;

import com.Dto.practice.CateItem;

// Session
// 현재 사용자가 이용중인 정보
// 이 안의 정보는 사용자가 프로그램을 사용할 때 동안은 계속 유지된다.
public class Session {
	private CateItem currentCateItem;

	public CateItem getCurrentBoard() {
		return currentCateItem;
	}

	public void setCurrentBoard(CateItem currentCateItem) {
		this.currentCateItem = currentCateItem;
	}
}