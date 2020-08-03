package com.java.blog.controller;

import java.sql.Connection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.java.blog.dto.Article;
import com.java.blog.dto.ArticleReply;
import com.java.blog.dto.Member;
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
			return doActionPopupMessageMain();
		case "sentMessageList":
			return doActionSentMessageList();
		case "messageDetail":
			return doActionMessageDetail();
		case "writeMessage":
			return doActionWriteMessage();
		case "sendMessage":
			return doActionDoWriteMessage();
		case "replyMessage":
			return doActionReplyMessage();
//		case "deleteMessage":
//			return doActionPopupMessage();
		}

		return "";
	}

	private String doActionReplyMessage() {
		int id = Util.getInt(req, "id");
		
		Message message = messageService.getMessageById(id);
		req.setAttribute("message", message);
		
		Member toMember = memberService.getMemberById(message.getFromMemberId());
		req.setAttribute("toMember", toMember);
		
		return "message/replyMessage.jsp";
	}

	private String doActionMessageDetail() {
		int id = Util.getInt(req, "id");

		Message message = messageService.getMessageById(id);
		req.setAttribute("message", message);
		
		int fromMemberId = message.getFromMemberId();
		Member fromMember = memberService.getMemberById(fromMemberId);
		req.setAttribute("fromMember", fromMember);

		return "message/messageDetail.jsp";
	}

	private String doActionDoWriteMessage() {
		String title = req.getParameter("title");
		String body = req.getParameter("body");
		String receiverNickname = req.getParameter("receiver");
		int writerId = Util.getInt(req, "writerId");
		
		HttpSession session = req.getSession();
		int currentMemberId = (int)session.getAttribute("loggedInMemberId");
		Member currentMember = memberService.getMemberById(currentMemberId);
		
		Member receiver = memberService.getMemberByNickname(receiverNickname);
		int receiverId = receiver.getId();

		messageService.sendMessage(title, body, writerId, receiverId);

		return "html:<script> alert('" + receiver + "님에게 쪽지를 보냈습니다.'); location.replace('/blog/s/message/messageMain'); </script>";
	}

	private String doActionWriteMessage() {
		return "message/writeMessage.jsp";
	}

	private String doActionSentMessageList() {
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
		List<Message> sentMessages = messageService.getSentMessageList(page, itemsInAPage);
		req.setAttribute("sentMessages", sentMessages);
		
		return "message/sentMessageList.jsp";
	}

	private String doActionPopupMessageMain() {
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
		List<Message> receivedMessages = messageService.getReceivedMessageList(page, itemsInAPage, id);
		req.setAttribute("receivedMessages", receivedMessages);
		
		return "message/messageMain.jsp";
	}

	@Override
	public String getControllerName() {
		// TODO Auto-generated method stub
		return "message";
	}

}
