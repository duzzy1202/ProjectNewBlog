package com.java.blog.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.java.blog.app.App;
import com.java.blog.service.MailService;

public class DispatcherServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html; charset=UTF-8");
		req.setCharacterEncoding("UTF-8");
		
		String gmailId = getServletConfig().getInitParameter("gmailId");
		String gmailPw = getServletConfig().getInitParameter("gmailPw");
		MailService mailService = new MailService(gmailId, gmailPw, gmailId, "LCF");
		
		new App(req, resp, gmailId, gmailPw, mailService).start();
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}

}
