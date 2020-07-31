package com.java.blog.controller;

import java.sql.Connection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.java.blog.dto.Message;
import com.java.blog.util.Util;

public class MessageController extends Controller {
	public MessageController(Connection dbConn, String actionMethodName, HttpServletRequest req, HttpServletResponse resp) {
		super(dbConn, actionMethodName, req, resp);
	}

	@Override
	public String doAction() {
		switch (actionMethodName) {
		case "messageMain":
			return doActionPopupMessage();
//		case "replyMessage":
//			return doActionPopupMessage();
//		case "deleteMessage":
//			return doActionPopupMessage();
//		case "messageBox":
//			return doActionPopupMessage();
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
		int totalCount = messageService.getMessagesCount(id);
		int totalPage = (int) Math.ceil(totalCount / (double) itemsInAPage);
		 
		req.setAttribute("totalCount", totalCount); 
		req.setAttribute("totalPage", totalPage); 
		req.setAttribute("page", page);
		
		/* 쪽지 리스트 */
		List<Message> messages = messageService.getMessageList(page, itemsInAPage);
		req.setAttribute("messages", messages);
		
		return "message/messageMain.jsp";
	}

	@Override
	public String getControllerName() {
		// TODO Auto-generated method stub
		return "message";
	}

}
