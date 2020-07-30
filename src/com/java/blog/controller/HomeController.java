package com.java.blog.controller;

import java.sql.Connection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.java.blog.dto.Article;
import com.java.blog.dto.ArticleReply;
import com.java.blog.dto.Chat;
import com.java.blog.dto.Message;
import com.java.blog.util.Util;

public class HomeController extends Controller {
	public HomeController(Connection dbConn, String actionMethodName, HttpServletRequest req, HttpServletResponse resp) {
		super(dbConn, actionMethodName, req, resp);
	}

	@Override
	public String doAction() {
		switch (actionMethodName) {
		case "main":
			return doActionMain();
		case "aboutMe":
			return doActionAboutMe();
		case "writeChat":
			return doActionWriteChat();
		case "deleteAllChats":
			return doActionDeleteAllChats();
		case "message":
			return doActionPopupMessage();
		}

		return "";
	}
	
	private String doActionPopupMessage() {
		
		
		int id = (int) session.getAttribute("loggedInMemberId");
		
		/* 쪽지 페이징 */
		int page = 1; 
		if (!Util.empty(req, "page") && Util.isNum(req, "page")) { 
			page = Util.getInt(req, "page"); 
		}
		 
		int itemsInAPage = 10; 
		int totalCount = articleService.getMessagesCount(id);
		int totalPage = (int) Math.ceil(totalCount / (double) itemsInAPage);
		 
		req.setAttribute("totalCount", totalCount); 
		req.setAttribute("totalPage", totalPage); 
		req.setAttribute("page", page);
		
		/* 쪽지 리스트 */
		List<Message> messages = articleService.getMessageList(page, itemsInAPage);
		req.setAttribute("messages", messages);
		
		return "home/message.jsp";
	}

	private String doActionDeleteAllChats() {
		articleService.deleteAllChats();
		return "html:<script> location.replace('/blog/s/home/main'); </script>";
	}

	private String doActionWriteChat() {
		int memberId = Util.getInt(req, "memberId");
		String body = req.getParameter("chattingBody");
		
		articleService.writeChat(memberId, body);
		
		return "html:<script> location.replace('/blog/s/home/main'); </script>";
	}

	@Override
	public String getControllerName() {
		return "home";
	}

	private String doActionMain() {
		int cateItemId = 0;
		String searchKeywordType = "";
		String searchKeyword = "";
		int page = 1;
		int itemsInAPage = 12;

		req.setAttribute("page", page);

		List<Article> articles = articleService.getForPrintListArticles(page, itemsInAPage, cateItemId, searchKeywordType, searchKeyword);
		req.setAttribute("articles", articles);
		
		List<ArticleReply> replys = articleService.getAllReplysCount();
		req.setAttribute("replysCount", replys);
		
		int printChattingStart = articleService.getChattingCount() - 100;
		
		List<Chat> chattings = articleService.getChatting(printChattingStart);
		req.setAttribute("chattings", chattings);
		
		return "home/main.jsp";
	}

	private String doActionAboutMe() {
		return "home/aboutMe.jsp";
	}

}
