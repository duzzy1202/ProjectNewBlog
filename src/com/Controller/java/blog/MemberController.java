package com.Controller.java.blog;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.Main.java.blog.Request;

public class MemberController extends Controller {

	@Override
	public String doAction(String actionMethodName, HttpServletRequest req, HttpServletResponse resp) {
		System.out.printf("member 컨트롤러인 나는 %s 요청을 받았다.\n", actionMethodName);
		return "";
	}

}