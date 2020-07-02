package com.Controller.java.blog;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public abstract class Controller {
	public abstract String doAction(String actionMethodName, HttpServletRequest req, HttpServletResponse resp);
}