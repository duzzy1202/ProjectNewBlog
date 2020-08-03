package com.java.blog.service;

import java.sql.Connection;
import java.util.List;

import com.java.blog.dao.MessageDao;
import com.java.blog.dto.Article;
import com.java.blog.dto.ArticleReply;
import com.java.blog.dto.CateItem;
import com.java.blog.dto.Chat;
import com.java.blog.dto.Member;
import com.java.blog.dto.Message;

public class MessageService extends Service {

	private MessageDao messageDao;

	public MessageService(Connection dbConn) {
		messageDao = new MessageDao(dbConn);
	}

	public List<Message> getReceivedMessageList(int page, int itemsInAPage, int id) {
		return messageDao.getReceivedMessageList(page, itemsInAPage, id);
	}

	public int getMessagesCount(int id) {
		return messageDao.getMessagesCount(id);
	}

	public List<Message> getSentMessageList(int page, int itemsInAPage) {
		return messageDao.getSentMessageList(page, itemsInAPage);
	}

	public void sendMessage(String title, String body, int writerId, int receiverId) {
		messageDao.sendMessage(title, body, writerId, receiverId);
	}

	public Message getMessageById(int id) {
		return messageDao.getMessageById(id);
	}

}
